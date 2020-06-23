package com.eshipper.service;

import com.eshipper.service.dto.CommissionReportDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.CommissionReport}.
 */
public interface CommissionReportService {

    /**
     * Save a commissionReport.
     *
     * @param commissionReportDTO the entity to save.
     * @return the persisted entity.
     */
    CommissionReportDTO save(CommissionReportDTO commissionReportDTO);

    /**
     * Get all the commissionReports.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CommissionReportDTO> findAll(Pageable pageable);


    /**
     * Get the "id" commissionReport.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CommissionReportDTO> findOne(Long id);

    /**
     * Delete the "id" commissionReport.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
