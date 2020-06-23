import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { ElasticSearchCommReportComponent } from './elastic-search-comm-report.component';
import { ElasticSearchCommReportDetailComponent } from './elastic-search-comm-report-detail.component';
import { ElasticSearchCommReportUpdateComponent } from './elastic-search-comm-report-update.component';
import { ElasticSearchCommReportDeleteDialogComponent } from './elastic-search-comm-report-delete-dialog.component';
import { elasticSearchCommReportRoute } from './elastic-search-comm-report.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(elasticSearchCommReportRoute)],
  declarations: [
    ElasticSearchCommReportComponent,
    ElasticSearchCommReportDetailComponent,
    ElasticSearchCommReportUpdateComponent,
    ElasticSearchCommReportDeleteDialogComponent,
  ],
  entryComponents: [ElasticSearchCommReportDeleteDialogComponent],
})
export class EshipperElasticSearchCommReportModule {}
