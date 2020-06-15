import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICommissionStatus } from 'app/shared/model/commission-status.model';

@Component({
  selector: 'jhi-commission-status-detail',
  templateUrl: './commission-status-detail.component.html',
})
export class CommissionStatusDetailComponent implements OnInit {
  commissionStatus: ICommissionStatus | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ commissionStatus }) => (this.commissionStatus = commissionStatus));
  }

  previousState(): void {
    window.history.back();
  }
}
