package com.eshipper.repository;

import com.eshipper.domain.ElasticSearchCommReport;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ElasticSearchCommReport entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ElasticSearchCommReportRepository extends JpaRepository<ElasticSearchCommReport, Long> {
}
