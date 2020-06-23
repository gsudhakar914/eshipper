package com.eshipper.service.impl;

import com.eshipper.service.EdiShippingOrderService;
import com.eshipper.domain.EdiShippingOrder;
import com.eshipper.repository.EdiShippingOrderRepository;
import com.eshipper.service.dto.EdiShippingOrderDTO;
import com.eshipper.service.mapper.EdiShippingOrderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link EdiShippingOrder}.
 */
@Service
@Transactional
public class EdiShippingOrderServiceImpl implements EdiShippingOrderService {

    private final Logger log = LoggerFactory.getLogger(EdiShippingOrderServiceImpl.class);

    private final EdiShippingOrderRepository ediShippingOrderRepository;

    private final EdiShippingOrderMapper ediShippingOrderMapper;

    public EdiShippingOrderServiceImpl(EdiShippingOrderRepository ediShippingOrderRepository, EdiShippingOrderMapper ediShippingOrderMapper) {
        this.ediShippingOrderRepository = ediShippingOrderRepository;
        this.ediShippingOrderMapper = ediShippingOrderMapper;
    }

    /**
     * Save a ediShippingOrder.
     *
     * @param ediShippingOrderDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EdiShippingOrderDTO save(EdiShippingOrderDTO ediShippingOrderDTO) {
        log.debug("Request to save EdiShippingOrder : {}", ediShippingOrderDTO);
        EdiShippingOrder ediShippingOrder = ediShippingOrderMapper.toEntity(ediShippingOrderDTO);
        ediShippingOrder = ediShippingOrderRepository.save(ediShippingOrder);
        return ediShippingOrderMapper.toDto(ediShippingOrder);
    }

    /**
     * Get all the ediShippingOrders.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EdiShippingOrderDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EdiShippingOrders");
        return ediShippingOrderRepository.findAll(pageable)
            .map(ediShippingOrderMapper::toDto);
    }


    /**
     * Get one ediShippingOrder by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EdiShippingOrderDTO> findOne(Long id) {
        log.debug("Request to get EdiShippingOrder : {}", id);
        return ediShippingOrderRepository.findById(id)
            .map(ediShippingOrderMapper::toDto);
    }

    /**
     * Delete the ediShippingOrder by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EdiShippingOrder : {}", id);
        ediShippingOrderRepository.deleteById(id);
    }
}
