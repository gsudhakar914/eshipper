import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICommissionReport } from 'app/shared/model/commission-report.model';

@Component({
  selector: 'jhi-commission-report-detail',
  templateUrl: './commission-report-detail.component.html',
})
export class CommissionReportDetailComponent implements OnInit {
  commissionReport: ICommissionReport | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ commissionReport }) => (this.commissionReport = commissionReport));
  }

  previousState(): void {
    window.history.back();
  }
}
