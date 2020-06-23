import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { CommissionStatusDetailComponent } from 'app/entities/commission-status/commission-status-detail.component';
import { CommissionStatus } from 'app/shared/model/commission-status.model';

describe('Component Tests', () => {
  describe('CommissionStatus Management Detail Component', () => {
    let comp: CommissionStatusDetailComponent;
    let fixture: ComponentFixture<CommissionStatusDetailComponent>;
    const route = ({ data: of({ commissionStatus: new CommissionStatus(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [CommissionStatusDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(CommissionStatusDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CommissionStatusDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load commissionStatus on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.commissionStatus).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
