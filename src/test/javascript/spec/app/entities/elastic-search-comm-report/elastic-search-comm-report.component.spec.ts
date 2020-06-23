import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, convertToParamMap } from '@angular/router';

import { EshipperTestModule } from '../../../test.module';
import { ElasticSearchCommReportComponent } from 'app/entities/elastic-search-comm-report/elastic-search-comm-report.component';
import { ElasticSearchCommReportService } from 'app/entities/elastic-search-comm-report/elastic-search-comm-report.service';
import { ElasticSearchCommReport } from 'app/shared/model/elastic-search-comm-report.model';

describe('Component Tests', () => {
  describe('ElasticSearchCommReport Management Component', () => {
    let comp: ElasticSearchCommReportComponent;
    let fixture: ComponentFixture<ElasticSearchCommReportComponent>;
    let service: ElasticSearchCommReportService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [ElasticSearchCommReportComponent],
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
        .overrideTemplate(ElasticSearchCommReportComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ElasticSearchCommReportComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ElasticSearchCommReportService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ElasticSearchCommReport(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.elasticSearchCommReports && comp.elasticSearchCommReports[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should load a page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ElasticSearchCommReport(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.elasticSearchCommReports && comp.elasticSearchCommReports[0]).toEqual(jasmine.objectContaining({ id: 123 }));
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
