export interface IEdiShippingOrder {
  id?: number;
  commissionStatusId?: number;
}

export class EdiShippingOrder implements IEdiShippingOrder {
  constructor(public id?: number, public commissionStatusId?: number) {}
}
