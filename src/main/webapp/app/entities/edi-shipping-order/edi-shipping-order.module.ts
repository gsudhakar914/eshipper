import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { EdiShippingOrderComponent } from './edi-shipping-order.component';
import { EdiShippingOrderDetailComponent } from './edi-shipping-order-detail.component';
import { EdiShippingOrderUpdateComponent } from './edi-shipping-order-update.component';
import { EdiShippingOrderDeleteDialogComponent } from './edi-shipping-order-delete-dialog.component';
import { ediShippingOrderRoute } from './edi-shipping-order.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(ediShippingOrderRoute)],
  declarations: [
    EdiShippingOrderComponent,
    EdiShippingOrderDetailComponent,
    EdiShippingOrderUpdateComponent,
    EdiShippingOrderDeleteDialogComponent,
  ],
  entryComponents: [EdiShippingOrderDeleteDialogComponent],
})
export class EshipperEdiShippingOrderModule {}
