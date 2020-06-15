export interface ICommissionStatus {
  id?: number;
  name?: string;
}

export class CommissionStatus implements ICommissionStatus {
  constructor(public id?: number, public name?: string) {}
}
