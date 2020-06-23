import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICommissionStatus, CommissionStatus } from 'app/shared/model/commission-status.model';
import { CommissionStatusService } from './commission-status.service';

@Component({
  selector: 'jhi-commission-status-update',
  templateUrl: './commission-status-update.component.html',
})
export class CommissionStatusUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: [],
  });

  constructor(
    protected commissionStatusService: CommissionStatusService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ commissionStatus }) => {
      this.updateForm(commissionStatus);
    });
  }

  updateForm(commissionStatus: ICommissionStatus): void {
    this.editForm.patchValue({
      id: commissionStatus.id,
      name: commissionStatus.name,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const commissionStatus = this.createFromForm();
    if (commissionStatus.id !== undefined) {
      this.subscribeToSaveResponse(this.commissionStatusService.update(commissionStatus));
    } else {
      this.subscribeToSaveResponse(this.commissionStatusService.create(commissionStatus));
    }
  }

  private createFromForm(): ICommissionStatus {
    return {
      ...new CommissionStatus(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICommissionStatus>>): void {
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
}
