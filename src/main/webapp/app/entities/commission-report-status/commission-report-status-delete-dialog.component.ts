import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICommissionReportStatus } from 'app/shared/model/commission-report-status.model';
import { CommissionReportStatusService } from './commission-report-status.service';

@Component({
  templateUrl: './commission-report-status-delete-dialog.component.html',
})
export class CommissionReportStatusDeleteDialogComponent {
  commissionReportStatus?: ICommissionReportStatus;

  constructor(
    protected commissionReportStatusService: CommissionReportStatusService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.commissionReportStatusService.delete(id).subscribe(() => {
      this.eventManager.broadcast('commissionReportStatusListModification');
      this.activeModal.close();
    });
  }
}
