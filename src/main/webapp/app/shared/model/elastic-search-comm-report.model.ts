export interface IElasticSearchCommReport {
  id?: number;
  statusId?: number;
  commissionReportId?: number;
}

export class ElasticSearchCommReport implements IElasticSearchCommReport {
  constructor(public id?: number, public statusId?: number, public commissionReportId?: number) {}
}
