export interface IWoWorkOrder {
  id?: number;
  commissionStatusId?: number;
}

export class WoWorkOrder implements IWoWorkOrder {
  constructor(public id?: number, public commissionStatusId?: number) {}
}
