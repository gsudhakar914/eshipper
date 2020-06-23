import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IWoWorkOrder } from 'app/shared/model/wo-work-order.model';
import { WoWorkOrderService } from './wo-work-order.service';

@Component({
  templateUrl: './wo-work-order-delete-dialog.component.html',
})
export class WoWorkOrderDeleteDialogComponent {
  woWorkOrder?: IWoWorkOrder;

  constructor(
    protected woWorkOrderService: WoWorkOrderService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.woWorkOrderService.delete(id).subscribe(() => {
      this.eventManager.broadcast('woWorkOrderListModification');
      this.activeModal.close();
    });
  }
}
