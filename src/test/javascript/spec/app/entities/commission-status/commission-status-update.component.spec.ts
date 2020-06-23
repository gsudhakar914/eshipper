import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { CommissionStatusUpdateComponent } from 'app/entities/commission-status/commission-status-update.component';
import { CommissionStatusService } from 'app/entities/commission-status/commission-status.service';
import { CommissionStatus } from 'app/shared/model/commission-status.model';

describe('Component Tests', () => {
  describe('CommissionStatus Management Update Component', () => {
    let comp: CommissionStatusUpdateComponent;
    let fixture: ComponentFixture<CommissionStatusUpdateComponent>;
    let service: CommissionStatusService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [CommissionStatusUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(CommissionStatusUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CommissionStatusUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CommissionStatusService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CommissionStatus(123);
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
        const entity = new CommissionStatus();
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
