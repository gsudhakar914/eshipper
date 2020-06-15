import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICommissionReport, CommissionReport } from 'app/shared/model/commission-report.model';
import { CommissionReportService } from './commission-report.service';
import { IWoSalesAgent } from 'app/shared/model/wo-sales-agent.model';
import { WoSalesAgentService } from 'app/entities/wo-sales-agent/wo-sales-agent.service';
import { ICommissionReportStatus } from 'app/shared/model/commission-report-status.model';
import { CommissionReportStatusService } from 'app/entities/commission-report-status/commission-report-status.service';

type SelectableEntity = IWoSalesAgent | ICommissionReportStatus;

@Component({
  selector: 'jhi-commission-report-update',
  templateUrl: './commission-report-update.component.html',
})
export class CommissionReportUpdateComponent implements OnInit {
  isSaving = false;
  wosalesagents: IWoSalesAgent[] = [];
  commissionreportstatuses: ICommissionReportStatus[] = [];
  cutOffDateDp: any;
  createdDp: any;

  editForm = this.fb.group({
    id: [],
    cutOffDate: [],
    additionalComment: [],
    created: [],
    workOrders: [],
    totalAmount: [],
    woSalesAgentId: [],
    commissionReportStatusId: [],
  });

  constructor(
    protected commissionReportService: CommissionReportService,
    protected woSalesAgentService: WoSalesAgentService,
    protected commissionReportStatusService: CommissionReportStatusService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ commissionReport }) => {
      this.updateForm(commissionReport);

      this.woSalesAgentService.query().subscribe((res: HttpResponse<IWoSalesAgent[]>) => (this.wosalesagents = res.body || []));

      this.commissionReportStatusService
        .query()
        .subscribe((res: HttpResponse<ICommissionReportStatus[]>) => (this.commissionreportstatuses = res.body || []));
    });
  }

  updateForm(commissionReport: ICommissionReport): void {
    this.editForm.patchValue({
      id: commissionReport.id,
      cutOffDate: commissionReport.cutOffDate,
      additionalComment: commissionReport.additionalComment,
      created: commissionReport.created,
      workOrders: commissionReport.workOrders,
      totalAmount: commissionReport.totalAmount,
      woSalesAgentId: commissionReport.woSalesAgentId,
      commissionReportStatusId: commissionReport.commissionReportStatusId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const commissionReport = this.createFromForm();
    if (commissionReport.id !== undefined) {
      this.subscribeToSaveResponse(this.commissionReportService.update(commissionReport));
    } else {
      this.subscribeToSaveResponse(this.commissionReportService.create(commissionReport));
    }
  }

  private createFromForm(): ICommissionReport {
    return {
      ...new CommissionReport(),
      id: this.editForm.get(['id'])!.value,
      cutOffDate: this.editForm.get(['cutOffDate'])!.value,
      additionalComment: this.editForm.get(['additionalComment'])!.value,
      created: this.editForm.get(['created'])!.value,
      workOrders: this.editForm.get(['workOrders'])!.value,
      totalAmount: this.editForm.get(['totalAmount'])!.value,
      woSalesAgentId: this.editForm.get(['woSalesAgentId'])!.value,
      commissionReportStatusId: this.editForm.get(['commissionReportStatusId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICommissionReport>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}