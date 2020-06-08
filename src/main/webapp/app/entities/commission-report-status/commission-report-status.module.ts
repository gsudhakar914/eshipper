import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { CommissionReportStatusComponent } from './commission-report-status.component';
import { CommissionReportStatusDetailComponent } from './commission-report-status-detail.component';
import { CommissionReportStatusUpdateComponent } from './commission-report-status-update.component';
import { CommissionReportStatusDeleteDialogComponent } from './commission-report-status-delete-dialog.component';
import { commissionReportStatusRoute } from './commission-report-status.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(commissionReportStatusRoute)],
  declarations: [
    CommissionReportStatusComponent,
    CommissionReportStatusDetailComponent,
    CommissionReportStatusUpdateComponent,
    CommissionReportStatusDeleteDialogComponent,
  ],
  entryComponents: [CommissionReportStatusDeleteDialogComponent],
})
export class EshipperCommissionReportStatusModule {}
