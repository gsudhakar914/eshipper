import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICommissionStatus } from 'app/shared/model/commission-status.model';
import { CommissionStatusService } from './commission-status.service';

@Component({
  templateUrl: './commission-status-delete-dialog.component.html',
})
export class CommissionStatusDeleteDialogComponent {
  commissionStatus?: ICommissionStatus;

  constructor(
    protected commissionStatusService: CommissionStatusService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.commissionStatusService.delete(id).subscribe(() => {
      this.eventManager.broadcast('commissionStatusListModification');
      this.activeModal.close();
    });
  }
}
