import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, convertToParamMap } from '@angular/router';

import { EshipperTestModule } from '../../../test.module';
import { EdiShippingOrderComponent } from 'app/entities/edi-shipping-order/edi-shipping-order.component';
import { EdiShippingOrderService } from 'app/entities/edi-shipping-order/edi-shipping-order.service';
import { EdiShippingOrder } from 'app/shared/model/edi-shipping-order.model';

describe('Component Tests', () => {
  describe('EdiShippingOrder Management Component', () => {
    let comp: EdiShippingOrderComponent;
    let fixture: ComponentFixture<EdiShippingOrderComponent>;
    let service: EdiShippingOrderService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EdiShippingOrderComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: {
              data: of({
                defaultSort: 'id,asc',
              }),
              queryParamMap: of(
                convertToParamMap({
                  page: '1',
                  size: '1',
                  sort: 'id,desc',
                })
              ),
            },
          },
        ],
      })
        .overrideTemplate(EdiShippingOrderComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EdiShippingOrderComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EdiShippingOrderService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new EdiShippingOrder(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.ediShippingOrders && comp.ediShippingOrders[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should load a page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new EdiShippingOrder(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.ediShippingOrders && comp.ediShippingOrders[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should calculate the sort attribute for an id', () => {
      // WHEN
      comp.ngOnInit();
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['id,desc']);
    });

    it('should calculate the sort attribute for a non-id attribute', () => {
      // INIT
      comp.ngOnInit();

      // GIVEN
      comp.predicate = 'name';

      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['name,desc', 'id']);
    });
  });
});