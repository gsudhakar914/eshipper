import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICommissionReportStatus, CommissionReportStatus } from 'app/shared/model/commission-report-status.model';
import { CommissionReportStatusService } from './commission-report-status.service';
import { CommissionReportStatusComponent } from './commission-report-status.component';
import { CommissionReportStatusDetailComponent } from './commission-report-status-detail.component';
import { CommissionReportStatusUpdateComponent } from './commission-report-status-update.component';

@Injectable({ providedIn: 'root' })
export class CommissionReportStatusResolve implements Resolve<ICommissionReportStatus> {
  constructor(private service: CommissionReportStatusService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICommissionReportStatus> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((commissionReportStatus: HttpResponse<CommissionReportStatus>) => {
          if (commissionReportStatus.body) {
            return of(commissionReportStatus.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new CommissionReportStatus());
  }
}

export const commissionReportStatusRoute: Routes = [
  {
    path: '',
    component: CommissionReportStatusComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'eshipperApp.commissionReportStatus.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CommissionReportStatusDetailComponent,
    resolve: {
      commissionReportStatus: CommissionReportStatusResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.commissionReportStatus.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CommissionReportStatusUpdateComponent,
    resolve: {
      commissionReportStatus: CommissionReportStatusResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.commissionReportStatus.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CommissionReportStatusUpdateComponent,
    resolve: {
      commissionReportStatus: CommissionReportStatusResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.commissionReportStatus.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
