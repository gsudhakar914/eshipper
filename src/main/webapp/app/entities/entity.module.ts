import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'wo-work-order',
        loadChildren: () => import('./wo-work-order/wo-work-order.module').then(m => m.EshipperWoWorkOrderModule),
      },
      {
        path: 'edi-shipping-order',
        loadChildren: () => import('./edi-shipping-order/edi-shipping-order.module').then(m => m.EshipperEdiShippingOrderModule),
      },
      {
        path: 'commission-status',
        loadChildren: () => import('./commission-status/commission-status.module').then(m => m.EshipperCommissionStatusModule),
      },
      {
        path: 'commission-report-status',
        loadChildren: () =>
          import('./commission-report-status/commission-report-status.module').then(m => m.EshipperCommissionReportStatusModule),
      },
      {
        path: 'elastic-search-comm-report',
        loadChildren: () =>
          import('./elastic-search-comm-report/elastic-search-comm-report.module').then(m => m.EshipperElasticSearchCommReportModule),
      },
      {
        path: 'elastic-status',
        loadChildren: () => import('./elastic-status/elastic-status.module').then(m => m.EshipperElasticStatusModule),
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
