import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { EdiShippingOrderDetailComponent } from 'app/entities/edi-shipping-order/edi-shipping-order-detail.component';
import { EdiShippingOrder } from 'app/shared/model/edi-shipping-order.model';

describe('Component Tests', () => {
  describe('EdiShippingOrder Management Detail Component', () => {
    let comp: EdiShippingOrderDetailComponent;
    let fixture: ComponentFixture<EdiShippingOrderDetailComponent>;
    const route = ({ data: of({ ediShippingOrder: new EdiShippingOrder(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EdiShippingOrderDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(EdiShippingOrderDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EdiShippingOrderDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load ediShippingOrder on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.ediShippingOrder).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
