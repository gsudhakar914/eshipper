import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICommissionStatus } from 'app/shared/model/commission-status.model';

type EntityResponseType = HttpResponse<ICommissionStatus>;
type EntityArrayResponseType = HttpResponse<ICommissionStatus[]>;

@Injectable({ providedIn: 'root' })
export class CommissionStatusService {
  public resourceUrl = SERVER_API_URL + 'api/commission-statuses';

  constructor(protected http: HttpClient) {}

  create(commissionStatus: ICommissionStatus): Observable<EntityResponseType> {
    return this.http.post<ICommissionStatus>(this.resourceUrl, commissionStatus, { observe: 'response' });
  }

  update(commissionStatus: ICommissionStatus): Observable<EntityResponseType> {
    return this.http.put<ICommissionStatus>(this.resourceUrl, commissionStatus, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICommissionStatus>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICommissionStatus[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
