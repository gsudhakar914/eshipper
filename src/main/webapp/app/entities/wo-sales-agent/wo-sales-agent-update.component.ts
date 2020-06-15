import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IWoSalesAgent, WoSalesAgent } from 'app/shared/model/wo-sales-agent.model';
import { WoSalesAgentService } from './wo-sales-agent.service';

@Component({
  selector: 'jhi-wo-sales-agent-update',
  templateUrl: './wo-sales-agent-update.component.html',
})
export class WoSalesAgentUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
  });

  constructor(protected woSalesAgentService: WoSalesAgentService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ woSalesAgent }) => {
      this.updateForm(woSalesAgent);
    });
  }

  updateForm(woSalesAgent: IWoSalesAgent): void {
    this.editForm.patchValue({
      id: woSalesAgent.id,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const woSalesAgent = this.createFromForm();
    if (woSalesAgent.id !== undefined) {
      this.subscribeToSaveResponse(this.woSalesAgentService.update(woSalesAgent));
    } else {
      this.subscribeToSaveResponse(this.woSalesAgentService.create(woSalesAgent));
    }
  }

  private createFromForm(): IWoSalesAgent {
    return {
      ...new WoSalesAgent(),
      id: this.editForm.get(['id'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IWoSalesAgent>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
