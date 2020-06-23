package com.eshipper.service;

import com.eshipper.service.dto.ElasticSearchCommReportDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.ElasticSearchCommReport}.
 */
public interface ElasticSearchCommReportService {

    /**
     * Save a elasticSearchCommReport.
     *
     * @param elasticSearchCommReportDTO the entity to save.
     * @return the persisted entity.
     */
    ElasticSearchCommReportDTO save(ElasticSearchCommReportDTO elasticSearchCommReportDTO);

    /**
     * Get all the elasticSearchCommReports.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ElasticSearchCommReportDTO> findAll(Pageable pageable);
    /**
     * Get all the ElasticSearchCommReportDTO where CommissionReport is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<ElasticSearchCommReportDTO> findAllWhereCommissionReportIsNull();


    /**
     * Get the "id" elasticSearchCommReport.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ElasticSearchCommReportDTO> findOne(Long id);

    /**
     * Delete the "id" elasticSearchCommReport.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
