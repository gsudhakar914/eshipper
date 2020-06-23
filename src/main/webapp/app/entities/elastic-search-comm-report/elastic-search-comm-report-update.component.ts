import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IElasticSearchCommReport, ElasticSearchCommReport } from 'app/shared/model/elastic-search-comm-report.model';
import { ElasticSearchCommReportService } from './elastic-search-comm-report.service';
import { IElasticStatus } from 'app/shared/model/elastic-status.model';
import { ElasticStatusService } from 'app/entities/elastic-status/elastic-status.service';

@Component({
  selector: 'jhi-elastic-search-comm-report-update',
  templateUrl: './elastic-search-comm-report-update.component.html',
})
export class ElasticSearchCommReportUpdateComponent implements OnInit {
  isSaving = false;
  elasticstatuses: IElasticStatus[] = [];

  editForm = this.fb.group({
    id: [],
    statusId: [],
  });

  constructor(
    protected elasticSearchCommReportService: ElasticSearchCommReportService,
    protected elasticStatusService: ElasticStatusService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ elasticSearchCommReport }) => {
      this.updateForm(elasticSearchCommReport);

      this.elasticStatusService.query().subscribe((res: HttpResponse<IElasticStatus[]>) => (this.elasticstatuses = res.body || []));
    });
  }

  updateForm(elasticSearchCommReport: IElasticSearchCommReport): void {
    this.editForm.patchValue({
      id: elasticSearchCommReport.id,
      statusId: elasticSearchCommReport.statusId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const elasticSearchCommReport = this.createFromForm();
    if (elasticSearchCommReport.id !== undefined) {
      this.subscribeToSaveResponse(this.elasticSearchCommReportService.update(elasticSearchCommReport));
    } else {
      this.subscribeToSaveResponse(this.elasticSearchCommReportService.create(elasticSearchCommReport));
    }
  }

  private createFromForm(): IElasticSearchCommReport {
    return {
      ...new ElasticSearchCommReport(),
      id: this.editForm.get(['id'])!.value,
      statusId: this.editForm.get(['statusId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IElasticSearchCommReport>>): void {
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

  trackById(index: number, item: IElasticStatus): any {
    return item.id;
  }
}
