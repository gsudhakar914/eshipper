package com.eshipper.repository;

import com.eshipper.domain.EdiShippingOrder;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the EdiShippingOrder entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EdiShippingOrderRepository extends JpaRepository<EdiShippingOrder, Long> {
}
