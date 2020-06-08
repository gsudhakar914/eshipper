import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { CommissionReportDetailComponent } from 'app/entities/commission-report/commission-report-detail.component';
import { CommissionReport } from 'app/shared/model/commission-report.model';

describe('Component Tests', () => {
  describe('CommissionReport Management Detail Component', () => {
    let comp: CommissionReportDetailComponent;
    let fixture: ComponentFixture<CommissionReportDetailComponent>;
    const route = ({ data: of({ commissionReport: new CommissionReport(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [CommissionReportDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(CommissionReportDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CommissionReportDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load commissionReport on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.commissionReport).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
