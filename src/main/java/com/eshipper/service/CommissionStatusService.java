package com.eshipper.service;

import com.eshipper.service.dto.CommissionStatusDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.CommissionStatus}.
 */
public interface CommissionStatusService {

    /**
     * Save a commissionStatus.
     *
     * @param commissionStatusDTO the entity to save.
     * @return the persisted entity.
     */
    CommissionStatusDTO save(CommissionStatusDTO commissionStatusDTO);

    /**
     * Get all the commissionStatuses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CommissionStatusDTO> findAll(Pageable pageable);


    /**
     * Get the "id" commissionStatus.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CommissionStatusDTO> findOne(Long id);

    /**
     * Delete the "id" commissionStatus.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
