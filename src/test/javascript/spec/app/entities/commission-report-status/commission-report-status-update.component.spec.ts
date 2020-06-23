import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { CommissionReportStatusUpdateComponent } from 'app/entities/commission-report-status/commission-report-status-update.component';
import { CommissionReportStatusService } from 'app/entities/commission-report-status/commission-report-status.service';
import { CommissionReportStatus } from 'app/shared/model/commission-report-status.model';

describe('Component Tests', () => {
  describe('CommissionReportStatus Management Update Component', () => {
    let comp: CommissionReportStatusUpdateComponent;
    let fixture: ComponentFixture<CommissionReportStatusUpdateComponent>;
    let service: CommissionReportStatusService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [CommissionReportStatusUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(CommissionReportStatusUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CommissionReportStatusUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CommissionReportStatusService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CommissionReportStatus(123);
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
        const entity = new CommissionReportStatus();
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
