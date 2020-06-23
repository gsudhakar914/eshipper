import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { CommissionReportStatusDetailComponent } from 'app/entities/commission-report-status/commission-report-status-detail.component';
import { CommissionReportStatus } from 'app/shared/model/commission-report-status.model';

describe('Component Tests', () => {
  describe('CommissionReportStatus Management Detail Component', () => {
    let comp: CommissionReportStatusDetailComponent;
    let fixture: ComponentFixture<CommissionReportStatusDetailComponent>;
    const route = ({ data: of({ commissionReportStatus: new CommissionReportStatus(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [CommissionReportStatusDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(CommissionReportStatusDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CommissionReportStatusDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load commissionReportStatus on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.commissionReportStatus).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
