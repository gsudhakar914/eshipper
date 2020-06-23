import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICommissionStatus, CommissionStatus } from 'app/shared/model/commission-status.model';
import { CommissionStatusService } from './commission-status.service';
import { CommissionStatusComponent } from './commission-status.component';
import { CommissionStatusDetailComponent } from './commission-status-detail.component';
import { CommissionStatusUpdateComponent } from './commission-status-update.component';

@Injectable({ providedIn: 'root' })
export class CommissionStatusResolve implements Resolve<ICommissionStatus> {
  constructor(private service: CommissionStatusService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICommissionStatus> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((commissionStatus: HttpResponse<CommissionStatus>) => {
          if (commissionStatus.body) {
            return of(commissionStatus.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new CommissionStatus());
  }
}

export const commissionStatusRoute: Routes = [
  {
    path: '',
    component: CommissionStatusComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'eshipperApp.commissionStatus.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CommissionStatusDetailComponent,
    resolve: {
      commissionStatus: CommissionStatusResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.commissionStatus.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CommissionStatusUpdateComponent,
    resolve: {
      commissionStatus: CommissionStatusResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.commissionStatus.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CommissionStatusUpdateComponent,
    resolve: {
      commissionStatus: CommissionStatusResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.commissionStatus.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
