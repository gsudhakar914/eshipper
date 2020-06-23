import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IWoWorkOrder } from 'app/shared/model/wo-work-order.model';

type EntityResponseType = HttpResponse<IWoWorkOrder>;
type EntityArrayResponseType = HttpResponse<IWoWorkOrder[]>;

@Injectable({ providedIn: 'root' })
export class WoWorkOrderService {
  public resourceUrl = SERVER_API_URL + 'api/wo-work-orders';

  constructor(protected http: HttpClient) {}

  create(woWorkOrder: IWoWorkOrder): Observable<EntityResponseType> {
    return this.http.post<IWoWorkOrder>(this.resourceUrl, woWorkOrder, { observe: 'response' });
  }

  update(woWorkOrder: IWoWorkOrder): Observable<EntityResponseType> {
    return this.http.put<IWoWorkOrder>(this.resourceUrl, woWorkOrder, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IWoWorkOrder>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IWoWorkOrder[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
