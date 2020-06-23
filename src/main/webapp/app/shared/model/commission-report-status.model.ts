export interface ICommissionReportStatus {
  id?: number;
  name?: string;
}

export class CommissionReportStatus implements ICommissionReportStatus {
  constructor(public id?: number, public name?: string) {}
}
