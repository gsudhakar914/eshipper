import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICommissionReportStatus, CommissionReportStatus } from 'app/shared/model/commission-report-status.model';
import { CommissionReportStatusService } from './commission-report-status.service';

@Component({
  selector: 'jhi-commission-report-status-update',
  templateUrl: './commission-report-status-update.component.html',
})
export class CommissionReportStatusUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: [],
  });

  constructor(
    protected commissionReportStatusService: CommissionReportStatusService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ commissionReportStatus }) => {
      this.updateForm(commissionReportStatus);
    });
  }

  updateForm(commissionReportStatus: ICommissionReportStatus): void {
    this.editForm.patchValue({
      id: commissionReportStatus.id,
      name: commissionReportStatus.name,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const commissionReportStatus = this.createFromForm();
    if (commissionReportStatus.id !== undefined) {
      this.subscribeToSaveResponse(this.commissionReportStatusService.update(commissionReportStatus));
    } else {
      this.subscribeToSaveResponse(this.commissionReportStatusService.create(commissionReportStatus));
    }
  }

  private createFromForm(): ICommissionReportStatus {
    return {
      ...new CommissionReportStatus(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICommissionReportStatus>>): void {
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
