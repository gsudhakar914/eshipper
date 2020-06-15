import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IWoWorkOrder, WoWorkOrder } from 'app/shared/model/wo-work-order.model';
import { WoWorkOrderService } from './wo-work-order.service';
import { WoWorkOrderComponent } from './wo-work-order.component';
import { WoWorkOrderDetailComponent } from './wo-work-order-detail.component';
import { WoWorkOrderUpdateComponent } from './wo-work-order-update.component';

@Injectable({ providedIn: 'root' })
export class WoWorkOrderResolve implements Resolve<IWoWorkOrder> {
  constructor(private service: WoWorkOrderService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IWoWorkOrder> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((woWorkOrder: HttpResponse<WoWorkOrder>) => {
          if (woWorkOrder.body) {
            return of(woWorkOrder.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new WoWorkOrder());
  }
}

export const woWorkOrderRoute: Routes = [
  {
    path: '',
    component: WoWorkOrderComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'eshipperApp.woWorkOrder.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: WoWorkOrderDetailComponent,
    resolve: {
      woWorkOrder: WoWorkOrderResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.woWorkOrder.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: WoWorkOrderUpdateComponent,
    resolve: {
      woWorkOrder: WoWorkOrderResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.woWorkOrder.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: WoWorkOrderUpdateComponent,
    resolve: {
      woWorkOrder: WoWorkOrderResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.woWorkOrder.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
