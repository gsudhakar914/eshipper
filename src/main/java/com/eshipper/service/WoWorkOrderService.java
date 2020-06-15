package com.eshipper.service;

import com.eshipper.service.dto.WoWorkOrderDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.WoWorkOrder}.
 */
public interface WoWorkOrderService {

    /**
     * Save a woWorkOrder.
     *
     * @param woWorkOrderDTO the entity to save.
     * @return the persisted entity.
     */
    WoWorkOrderDTO save(WoWorkOrderDTO woWorkOrderDTO);

    /**
     * Get all the woWorkOrders.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<WoWorkOrderDTO> findAll(Pageable pageable);


    /**
     * Get the "id" woWorkOrder.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<WoWorkOrderDTO> findOne(Long id);

    /**
     * Delete the "id" woWorkOrder.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
