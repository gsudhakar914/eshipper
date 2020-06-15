package com.eshipper.service;

import com.eshipper.service.dto.EdiShippingOrderDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.EdiShippingOrder}.
 */
public interface EdiShippingOrderService {

    /**
     * Save a ediShippingOrder.
     *
     * @param ediShippingOrderDTO the entity to save.
     * @return the persisted entity.
     */
    EdiShippingOrderDTO save(EdiShippingOrderDTO ediShippingOrderDTO);

    /**
     * Get all the ediShippingOrders.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EdiShippingOrderDTO> findAll(Pageable pageable);


    /**
     * Get the "id" ediShippingOrder.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EdiShippingOrderDTO> findOne(Long id);

    /**
     * Delete the "id" ediShippingOrder.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
