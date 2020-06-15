import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { WoWorkOrderComponent } from './wo-work-order.component';
import { WoWorkOrderDetailComponent } from './wo-work-order-detail.component';
import { WoWorkOrderUpdateComponent } from './wo-work-order-update.component';
import { WoWorkOrderDeleteDialogComponent } from './wo-work-order-delete-dialog.component';
import { woWorkOrderRoute } from './wo-work-order.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(woWorkOrderRoute)],
  declarations: [WoWorkOrderComponent, WoWorkOrderDetailComponent, WoWorkOrderUpdateComponent, WoWorkOrderDeleteDialogComponent],
  entryComponents: [WoWorkOrderDeleteDialogComponent],
})
export class EshipperWoWorkOrderModule {}
