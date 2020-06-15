import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICommissionReportStatus } from 'app/shared/model/commission-report-status.model';

@Component({
  selector: 'jhi-commission-report-status-detail',
  templateUrl: './commission-report-status-detail.component.html',
})
export class CommissionReportStatusDetailComponent implements OnInit {
  commissionReportStatus: ICommissionReportStatus | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ commissionReportStatus }) => (this.commissionReportStatus = commissionReportStatus));
  }

  previousState(): void {
    window.history.back();
  }
}
