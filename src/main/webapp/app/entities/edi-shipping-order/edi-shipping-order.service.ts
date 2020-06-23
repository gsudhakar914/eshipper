import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEdiShippingOrder } from 'app/shared/model/edi-shipping-order.model';

type EntityResponseType = HttpResponse<IEdiShippingOrder>;
type EntityArrayResponseType = HttpResponse<IEdiShippingOrder[]>;

@Injectable({ providedIn: 'root' })
export class EdiShippingOrderService {
  public resourceUrl = SERVER_API_URL + 'api/edi-shipping-orders';

  constructor(protected http: HttpClient) {}

  create(ediShippingOrder: IEdiShippingOrder): Observable<EntityResponseType> {
    return this.http.post<IEdiShippingOrder>(this.resourceUrl, ediShippingOrder, { observe: 'response' });
  }

  update(ediShippingOrder: IEdiShippingOrder): Observable<EntityResponseType> {
    return this.http.put<IEdiShippingOrder>(this.resourceUrl, ediShippingOrder, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEdiShippingOrder>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEdiShippingOrder[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
