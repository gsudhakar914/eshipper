package com.eshipper.service.impl;

import com.eshipper.service.ElasticSearchCommReportService;
import com.eshipper.domain.ElasticSearchCommReport;
import com.eshipper.repository.ElasticSearchCommReportRepository;
import com.eshipper.service.dto.ElasticSearchCommReportDTO;
import com.eshipper.service.mapper.ElasticSearchCommReportMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing {@link ElasticSearchCommReport}.
 */
@Service
@Transactional
public class ElasticSearchCommReportServiceImpl implements ElasticSearchCommReportService {

    private final Logger log = LoggerFactory.getLogger(ElasticSearchCommReportServiceImpl.class);

    private final ElasticSearchCommReportRepository elasticSearchCommReportRepository;

    private final ElasticSearchCommReportMapper elasticSearchCommReportMapper;

    public ElasticSearchCommReportServiceImpl(ElasticSearchCommReportRepository elasticSearchCommReportRepository, ElasticSearchCommReportMapper elasticSearchCommReportMapper) {
        this.elasticSearchCommReportRepository = elasticSearchCommReportRepository;
        this.elasticSearchCommReportMapper = elasticSearchCommReportMapper;
    }

    /**
     * Save a elasticSearchCommReport.
     *
     * @param elasticSearchCommReportDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ElasticSearchCommReportDTO save(ElasticSearchCommReportDTO elasticSearchCommReportDTO) {
        log.debug("Request to save ElasticSearchCommReport : {}", elasticSearchCommReportDTO);
        ElasticSearchCommReport elasticSearchCommReport = elasticSearchCommReportMapper.toEntity(elasticSearchCommReportDTO);
        elasticSearchCommReport = elasticSearchCommReportRepository.save(elasticSearchCommReport);
        return elasticSearchCommReportMapper.toDto(elasticSearchCommReport);
    }

    /**
     * Get all the elasticSearchCommReports.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ElasticSearchCommReportDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ElasticSearchCommReports");
        return elasticSearchCommReportRepository.findAll(pageable)
            .map(elasticSearchCommReportMapper::toDto);
    }



    /**
     *  Get all the elasticSearchCommReports where CommissionReport is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<ElasticSearchCommReportDTO> findAllWhereCommissionReportIsNull() {
        log.debug("Request to get all elasticSearchCommReports where CommissionReport is null");
        return StreamSupport
            .stream(elasticSearchCommReportRepository.findAll().spliterator(), false)
            .filter(elasticSearchCommReport -> elasticSearchCommReport.getCommissionReport() == null)
            .map(elasticSearchCommReportMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one elasticSearchCommReport by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ElasticSearchCommReportDTO> findOne(Long id) {
        log.debug("Request to get ElasticSearchCommReport : {}", id);
        return elasticSearchCommReportRepository.findById(id)
            .map(elasticSearchCommReportMapper::toDto);
    }

    /**
     * Delete the elasticSearchCommReport by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ElasticSearchCommReport : {}", id);
        elasticSearchCommReportRepository.deleteById(id);
    }
}
