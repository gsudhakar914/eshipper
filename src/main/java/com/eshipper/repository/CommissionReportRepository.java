package com.eshipper.repository;

import com.eshipper.domain.CommissionReport;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CommissionReport entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CommissionReportRepository extends JpaRepository<CommissionReport, Long> {
}
