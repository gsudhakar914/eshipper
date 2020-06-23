package com.eshipper.repository;

import com.eshipper.domain.CommissionStatus;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CommissionStatus entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CommissionStatusRepository extends JpaRepository<CommissionStatus, Long> {
}
