import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IElasticSearchCommReport } from 'app/shared/model/elastic-search-comm-report.model';

@Component({
  selector: 'jhi-elastic-search-comm-report-detail',
  templateUrl: './elastic-search-comm-report-detail.component.html',
})
export class ElasticSearchCommReportDetailComponent implements OnInit {
  elasticSearchCommReport: IElasticSearchCommReport | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ elasticSearchCommReport }) => (this.elasticSearchCommReport = elasticSearchCommReport));
  }

  previousState(): void {
    window.history.back();
  }
}
