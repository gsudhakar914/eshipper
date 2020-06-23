import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { ElasticSearchCommReportUpdateComponent } from 'app/entities/elastic-search-comm-report/elastic-search-comm-report-update.component';
import { ElasticSearchCommReportService } from 'app/entities/elastic-search-comm-report/elastic-search-comm-report.service';
import { ElasticSearchCommReport } from 'app/shared/model/elastic-search-comm-report.model';

describe('Component Tests', () => {
  describe('ElasticSearchCommReport Management Update Component', () => {
    let comp: ElasticSearchCommReportUpdateComponent;
    let fixture: ComponentFixture<ElasticSearchCommReportUpdateComponent>;
    let service: ElasticSearchCommReportService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [ElasticSearchCommReportUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ElasticSearchCommReportUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ElasticSearchCommReportUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ElasticSearchCommReportService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ElasticSearchCommReport(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new ElasticSearchCommReport();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
