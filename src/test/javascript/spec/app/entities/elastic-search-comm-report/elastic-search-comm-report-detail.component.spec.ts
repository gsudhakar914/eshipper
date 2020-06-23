import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { ElasticSearchCommReportDetailComponent } from 'app/entities/elastic-search-comm-report/elastic-search-comm-report-detail.component';
import { ElasticSearchCommReport } from 'app/shared/model/elastic-search-comm-report.model';

describe('Component Tests', () => {
  describe('ElasticSearchCommReport Management Detail Component', () => {
    let comp: ElasticSearchCommReportDetailComponent;
    let fixture: ComponentFixture<ElasticSearchCommReportDetailComponent>;
    const route = ({ data: of({ elasticSearchCommReport: new ElasticSearchCommReport(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [ElasticSearchCommReportDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ElasticSearchCommReportDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ElasticSearchCommReportDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load elasticSearchCommReport on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.elasticSearchCommReport).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
