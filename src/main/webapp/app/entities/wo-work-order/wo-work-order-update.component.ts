import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IWoWorkOrder, WoWorkOrder } from 'app/shared/model/wo-work-order.model';
import { WoWorkOrderService } from './wo-work-order.service';
import { ICommissionStatus } from 'app/shared/model/commission-status.model';
import { CommissionStatusService } from 'app/entities/commission-status/commission-status.service';

@Component({
  selector: 'jhi-wo-work-order-update',
  templateUrl: './wo-work-order-update.component.html',
})
export class WoWorkOrderUpdateComponent implements OnInit {
  isSaving = false;
  commissionstatuses: ICommissionStatus[] = [];

  editForm = this.fb.group({
    id: [],
    commissionStatusId: [],
  });

  constructor(
    protected woWorkOrderService: WoWorkOrderService,
    protected commissionStatusService: CommissionStatusService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ woWorkOrder }) => {
      this.updateForm(woWorkOrder);

      this.commissionStatusService
        .query()
        .subscribe((res: HttpResponse<ICommissionStatus[]>) => (this.commissionstatuses = res.body || []));
    });
  }

  updateForm(woWorkOrder: IWoWorkOrder): void {
    this.editForm.patchValue({
      id: woWorkOrder.id,
      commissionStatusId: woWorkOrder.commissionStatusId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const woWorkOrder = this.createFromForm();
    if (woWorkOrder.id !== undefined) {
      this.subscribeToSaveResponse(this.woWorkOrderService.update(woWorkOrder));
    } else {
      this.subscribeToSaveResponse(this.woWorkOrderService.create(woWorkOrder));
    }
  }

  private createFromForm(): IWoWorkOrder {
    return {
      ...new WoWorkOrder(),
      id: this.editForm.get(['id'])!.value,
      commissionStatusId: this.editForm.get(['commissionStatusId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IWoWorkOrder>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: ICommissionStatus): any {
    return item.id;
  }
}
