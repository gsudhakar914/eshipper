import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IEdiShippingOrder, EdiShippingOrder } from 'app/shared/model/edi-shipping-order.model';
import { EdiShippingOrderService } from './edi-shipping-order.service';
import { ICommissionStatus } from 'app/shared/model/commission-status.model';
import { CommissionStatusService } from 'app/entities/commission-status/commission-status.service';

@Component({
  selector: 'jhi-edi-shipping-order-update',
  templateUrl: './edi-shipping-order-update.component.html',
})
export class EdiShippingOrderUpdateComponent implements OnInit {
  isSaving = false;
  commissionstatuses: ICommissionStatus[] = [];

  editForm = this.fb.group({
    id: [],
    commissionStatusId: [],
  });

  constructor(
    protected ediShippingOrderService: EdiShippingOrderService,
    protected commissionStatusService: CommissionStatusService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ediShippingOrder }) => {
      this.updateForm(ediShippingOrder);

      this.commissionStatusService
        .query()
        .subscribe((res: HttpResponse<ICommissionStatus[]>) => (this.commissionstatuses = res.body || []));
    });
  }

  updateForm(ediShippingOrder: IEdiShippingOrder): void {
    this.editForm.patchValue({
      id: ediShippingOrder.id,
      commissionStatusId: ediShippingOrder.commissionStatusId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const ediShippingOrder = this.createFromForm();
    if (ediShippingOrder.id !== undefined) {
      this.subscribeToSaveResponse(this.ediShippingOrderService.update(ediShippingOrder));
    } else {
      this.subscribeToSaveResponse(this.ediShippingOrderService.create(ediShippingOrder));
    }
  }

  private createFromForm(): IEdiShippingOrder {
    return {
      ...new EdiShippingOrder(),
      id: this.editForm.get(['id'])!.value,
      commissionStatusId: this.editForm.get(['commissionStatusId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEdiShippingOrder>>): void {
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
