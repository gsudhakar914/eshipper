import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IElasticSearchCommReport } from 'app/shared/model/elastic-search-comm-report.model';
import { ElasticSearchCommReportService } from './elastic-search-comm-report.service';

@Component({
  templateUrl: './elastic-search-comm-report-delete-dialog.component.html',
})
export class ElasticSearchCommReportDeleteDialogComponent {
  elasticSearchCommReport?: IElasticSearchCommReport;

  constructor(
    protected elasticSearchCommReportService: ElasticSearchCommReportService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.elasticSearchCommReportService.delete(id).subscribe(() => {
      this.eventManager.broadcast('elasticSearchCommReportListModification');
      this.activeModal.close();
    });
  }
}
