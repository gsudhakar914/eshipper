import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEdiShippingOrder } from 'app/shared/model/edi-shipping-order.model';

@Component({
  selector: 'jhi-edi-shipping-order-detail',
  templateUrl: './edi-shipping-order-detail.component.html',
})
export class EdiShippingOrderDetailComponent implements OnInit {
  ediShippingOrder: IEdiShippingOrder | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ediShippingOrder }) => (this.ediShippingOrder = ediShippingOrder));
  }

  previousState(): void {
    window.history.back();
  }
}
