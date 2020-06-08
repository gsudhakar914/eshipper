import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { CommissionReportUpdateComponent } from 'app/entities/commission-report/commission-report-update.component';
import { CommissionReportService } from 'app/entities/commission-report/commission-report.service';
import { CommissionReport } from 'app/shared/model/commission-report.model';

describe('Component Tests', () => {
  describe('CommissionReport Management Update Component', () => {
    let comp: CommissionReportUpdateComponent;
    let fixture: ComponentFixture<CommissionReportUpdateComponent>;
    let service: CommissionReportService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [CommissionReportUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(CommissionReportUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CommissionReportUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CommissionReportService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CommissionReport(123);
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
        const entity = new CommissionReport();
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
