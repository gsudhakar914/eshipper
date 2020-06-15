package com.eshipper.service.impl;

import com.eshipper.service.CommissionReportService;
import com.eshipper.domain.CommissionReport;
import com.eshipper.repository.CommissionReportRepository;
import com.eshipper.service.dto.CommissionReportDTO;
import com.eshipper.service.mapper.CommissionReportMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CommissionReport}.
 */
@Service
@Transactional
public class CommissionReportServiceImpl implements CommissionReportService {

    private final Logger log = LoggerFactory.getLogger(CommissionReportServiceImpl.class);

    private final CommissionReportRepository commissionReportRepository;

    private final CommissionReportMapper commissionReportMapper;

    public CommissionReportServiceImpl(CommissionReportRepository commissionReportRepository, CommissionReportMapper commissionReportMapper) {
        this.commissionReportRepository = commissionReportRepository;
        this.commissionReportMapper = commissionReportMapper;
    }

    /**
     * Save a commissionReport.
     *
     * @param commissionReportDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CommissionReportDTO save(CommissionReportDTO commissionReportDTO) {
        log.debug("Request to save CommissionReport : {}", commissionReportDTO);
        CommissionReport commissionReport = commissionReportMapper.toEntity(commissionReportDTO);
        commissionReport = commissionReportRepository.save(commissionReport);
        return commissionReportMapper.toDto(commissionReport);
    }

    /**
     * Get all the commissionReports.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CommissionReportDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CommissionReports");
        return commissionReportRepository.findAll(pageable)
            .map(commissionReportMapper::toDto);
    }


    /**
     * Get one commissionReport by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CommissionReportDTO> findOne(Long id) {
        log.debug("Request to get CommissionReport : {}", id);
        return commissionReportRepository.findById(id)
            .map(commissionReportMapper::toDto);
    }

    /**
     * Delete the commissionReport by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CommissionReport : {}", id);
        commissionReportRepository.deleteById(id);
    }
}
