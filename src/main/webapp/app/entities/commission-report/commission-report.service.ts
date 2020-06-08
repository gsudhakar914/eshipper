import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICommissionReport } from 'app/shared/model/commission-report.model';

type EntityResponseType = HttpResponse<ICommissionReport>;
type EntityArrayResponseType = HttpResponse<ICommissionReport[]>;

@Injectable({ providedIn: 'root' })
export class CommissionReportService {
  public resourceUrl = SERVER_API_URL + 'api/commission-reports';

  constructor(protected http: HttpClient) {}

  create(commissionReport: ICommissionReport): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(commissionReport);
    return this.http
      .post<ICommissionReport>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(commissionReport: ICommissionReport): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(commissionReport);
    return this.http
      .put<ICommissionReport>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ICommissionReport>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ICommissionReport[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(commissionReport: ICommissionReport): ICommissionReport {
    const copy: ICommissionReport = Object.assign({}, commissionReport, {
      cutOffDate:
        commissionReport.cutOffDate && commissionReport.cutOffDate.isValid() ? commissionReport.cutOffDate.format(DATE_FORMAT) : undefined,
      created: commissionReport.created && commissionReport.created.isValid() ? commissionReport.created.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.cutOffDate = res.body.cutOffDate ? moment(res.body.cutOffDate) : undefined;
      res.body.created = res.body.created ? moment(res.body.created) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((commissionReport: ICommissionReport) => {
        commissionReport.cutOffDate = commissionReport.cutOffDate ? moment(commissionReport.cutOffDate) : undefined;
        commissionReport.created = commissionReport.created ? moment(commissionReport.created) : undefined;
      });
    }
    return res;
  }
}
