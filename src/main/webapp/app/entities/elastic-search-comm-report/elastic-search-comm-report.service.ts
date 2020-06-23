import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IElasticSearchCommReport } from 'app/shared/model/elastic-search-comm-report.model';

type EntityResponseType = HttpResponse<IElasticSearchCommReport>;
type EntityArrayResponseType = HttpResponse<IElasticSearchCommReport[]>;

@Injectable({ providedIn: 'root' })
export class ElasticSearchCommReportService {
  public resourceUrl = SERVER_API_URL + 'api/elastic-search-comm-reports';

  constructor(protected http: HttpClient) {}

  create(elasticSearchCommReport: IElasticSearchCommReport): Observable<EntityResponseType> {
    return this.http.post<IElasticSearchCommReport>(this.resourceUrl, elasticSearchCommReport, { observe: 'response' });
  }

  update(elasticSearchCommReport: IElasticSearchCommReport): Observable<EntityResponseType> {
    return this.http.put<IElasticSearchCommReport>(this.resourceUrl, elasticSearchCommReport, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IElasticSearchCommReport>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IElasticSearchCommReport[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
