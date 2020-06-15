import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { CommissionReportComponent } from './commission-report.component';
import { CommissionReportDetailComponent } from './commission-report-detail.component';
import { CommissionReportUpdateComponent } from './commission-report-update.component';
import { CommissionReportDeleteDialogComponent } from './commission-report-delete-dialog.component';
import { commissionReportRoute } from './commission-report.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(commissionReportRoute)],
  declarations: [
    CommissionReportComponent,
    CommissionReportDetailComponent,
    CommissionReportUpdateComponent,
    CommissionReportDeleteDialogComponent,
  ],
  entryComponents: [CommissionReportDeleteDialogComponent],
})
export class EshipperCommissionReportModule {}
