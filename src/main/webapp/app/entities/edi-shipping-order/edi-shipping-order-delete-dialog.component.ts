import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEdiShippingOrder } from 'app/shared/model/edi-shipping-order.model';
import { EdiShippingOrderService } from './edi-shipping-order.service';

@Component({
  templateUrl: './edi-shipping-order-delete-dialog.component.html',
})
export class EdiShippingOrderDeleteDialogComponent {
  ediShippingOrder?: IEdiShippingOrder;

  constructor(
    protected ediShippingOrderService: EdiShippingOrderService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.ediShippingOrderService.delete(id).subscribe(() => {
      this.eventManager.broadcast('ediShippingOrderListModification');
      this.activeModal.close();
    });
  }
}
