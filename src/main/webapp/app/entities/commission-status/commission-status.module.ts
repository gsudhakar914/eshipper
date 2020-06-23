import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { CommissionStatusComponent } from './commission-status.component';
import { CommissionStatusDetailComponent } from './commission-status-detail.component';
import { CommissionStatusUpdateComponent } from './commission-status-update.component';
import { CommissionStatusDeleteDialogComponent } from './commission-status-delete-dialog.component';
import { commissionStatusRoute } from './commission-status.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(commissionStatusRoute)],
  declarations: [
    CommissionStatusComponent,
    CommissionStatusDetailComponent,
    CommissionStatusUpdateComponent,
    CommissionStatusDeleteDialogComponent,
  ],
  entryComponents: [CommissionStatusDeleteDialogComponent],
})
export class EshipperCommissionStatusModule {}
