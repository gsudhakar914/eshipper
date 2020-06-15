package com.eshipper.service.impl;

import com.eshipper.service.CommissionStatusService;
import com.eshipper.domain.CommissionStatus;
import com.eshipper.repository.CommissionStatusRepository;
import com.eshipper.service.dto.CommissionStatusDTO;
import com.eshipper.service.mapper.CommissionStatusMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CommissionStatus}.
 */
@Service
@Transactional
public class CommissionStatusServiceImpl implements CommissionStatusService {

    private final Logger log = LoggerFactory.getLogger(CommissionStatusServiceImpl.class);

    private final CommissionStatusRepository commissionStatusRepository;

    private final CommissionStatusMapper commissionStatusMapper;

    public CommissionStatusServiceImpl(CommissionStatusRepository commissionStatusRepository, CommissionStatusMapper commissionStatusMapper) {
        this.commissionStatusRepository = commissionStatusRepository;
        this.commissionStatusMapper = commissionStatusMapper;
    }

    /**
     * Save a commissionStatus.
     *
     * @param commissionStatusDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CommissionStatusDTO save(CommissionStatusDTO commissionStatusDTO) {
        log.debug("Request to save CommissionStatus : {}", commissionStatusDTO);
        CommissionStatus commissionStatus = commissionStatusMapper.toEntity(commissionStatusDTO);
        commissionStatus = commissionStatusRepository.save(commissionStatus);
        return commissionStatusMapper.toDto(commissionStatus);
    }

    /**
     * Get all the commissionStatuses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CommissionStatusDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CommissionStatuses");
        return commissionStatusRepository.findAll(pageable)
            .map(commissionStatusMapper::toDto);
    }


    /**
     * Get one commissionStatus by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CommissionStatusDTO> findOne(Long id) {
        log.debug("Request to get CommissionStatus : {}", id);
        return commissionStatusRepository.findById(id)
            .map(commissionStatusMapper::toDto);
    }

    /**
     * Delete the commissionStatus by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CommissionStatus : {}", id);
        commissionStatusRepository.deleteById(id);
    }
}
