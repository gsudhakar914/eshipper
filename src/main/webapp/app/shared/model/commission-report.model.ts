import { Moment } from 'moment';

export interface ICommissionReport {
  id?: number;
  cutOffDate?: Moment;
  additionalComment?: string;
  created?: Moment;
  workOrders?: number;
  totalAmount?: number;
  elasticSearchCommReportId?: number;
  woSalesAgentId?: number;
  commissionReportStatusId?: number;
}

export class CommissionReport implements ICommissionReport {
  constructor(
    public id?: number,
    public cutOffDate?: Moment,
    public additionalComment?: string,
    public created?: Moment,
    public workOrders?: number,
    public totalAmount?: number,
    public elasticSearchCommReportId?: number,
    public woSalesAgentId?: number,
    public commissionReportStatusId?: number
  ) {}
}
