export interface IWoSalesAgent {
  id?: number;
}

export class WoSalesAgent implements IWoSalesAgent {
  constructor(public id?: number) {}
}
