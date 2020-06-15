import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICommissionReport } from 'app/shared/model/commission-report.model';
import { CommissionReportService } from './commission-report.service';

@Component({
  templateUrl: './commission-report-delete-dialog.component.html',
})
export class CommissionReportDeleteDialogComponent {
  commissionReport?: ICommissionReport;

  constructor(
    protected commissionReportService: CommissionReportService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.commissionReportService.delete(id).subscribe(() => {
      this.eventManager.broadcast('commissionReportListModification');
      this.activeModal.close();
    });
  }
}
