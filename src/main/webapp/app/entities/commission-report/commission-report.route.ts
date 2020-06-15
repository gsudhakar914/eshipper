import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICommissionReport, CommissionReport } from 'app/shared/model/commission-report.model';
import { CommissionReportService } from './commission-report.service';
import { CommissionReportComponent } from './commission-report.component';
import { CommissionReportDetailComponent } from './commission-report-detail.component';
import { CommissionReportUpdateComponent } from './commission-report-update.component';

@Injectable({ providedIn: 'root' })
export class CommissionReportResolve implements Resolve<ICommissionReport> {
  constructor(private service: CommissionReportService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICommissionReport> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((commissionReport: HttpResponse<CommissionReport>) => {
          if (commissionReport.body) {
            return of(commissionReport.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new CommissionReport());
  }
}

export const commissionReportRoute: Routes = [
  {
    path: '',
    component: CommissionReportComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'eshipperApp.commissionReport.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CommissionReportDetailComponent,
    resolve: {
      commissionReport: CommissionReportResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.commissionReport.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CommissionReportUpdateComponent,
    resolve: {
      commissionReport: CommissionReportResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.commissionReport.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CommissionReportUpdateComponent,
    resolve: {
      commissionReport: CommissionReportResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.commissionReport.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
