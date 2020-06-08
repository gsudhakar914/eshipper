import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'commission-report-status',
        loadChildren: () =>
          import('./commission-report-status/commission-report-status.module').then(m => m.EshipperCommissionReportStatusModule),
      },
      {
        path: 'commission-report',
        loadChildren: () => import('./commission-report/commission-report.module').then(m => m.EshipperCommissionReportModule),
      },
      {
        path: 'wo-sales-agent',
        loadChildren: () => import('./wo-sales-agent/wo-sales-agent.module').then(m => m.EshipperWoSalesAgentModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EshipperEntityModule {}
