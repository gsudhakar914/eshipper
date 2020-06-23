package com.eshipper.repository;

import com.eshipper.domain.WoWorkOrder;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the WoWorkOrder entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WoWorkOrderRepository extends JpaRepository<WoWorkOrder, Long> {
}
