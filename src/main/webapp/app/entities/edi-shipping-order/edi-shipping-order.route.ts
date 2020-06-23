import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IEdiShippingOrder, EdiShippingOrder } from 'app/shared/model/edi-shipping-order.model';
import { EdiShippingOrderService } from './edi-shipping-order.service';
import { EdiShippingOrderComponent } from './edi-shipping-order.component';
import { EdiShippingOrderDetailComponent } from './edi-shipping-order-detail.component';
import { EdiShippingOrderUpdateComponent } from './edi-shipping-order-update.component';

@Injectable({ providedIn: 'root' })
export class EdiShippingOrderResolve implements Resolve<IEdiShippingOrder> {
  constructor(private service: EdiShippingOrderService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEdiShippingOrder> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((ediShippingOrder: HttpResponse<EdiShippingOrder>) => {
          if (ediShippingOrder.body) {
            return of(ediShippingOrder.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new EdiShippingOrder());
  }
}

export const ediShippingOrderRoute: Routes = [
  {
    path: '',
    component: EdiShippingOrderComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'eshipperApp.ediShippingOrder.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: EdiShippingOrderDetailComponent,
    resolve: {
      ediShippingOrder: EdiShippingOrderResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.ediShippingOrder.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: EdiShippingOrderUpdateComponent,
    resolve: {
      ediShippingOrder: EdiShippingOrderResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.ediShippingOrder.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: EdiShippingOrderUpdateComponent,
    resolve: {
      ediShippingOrder: EdiShippingOrderResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.ediShippingOrder.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
