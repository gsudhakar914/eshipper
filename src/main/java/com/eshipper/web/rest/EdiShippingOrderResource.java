package com.eshipper.web.rest;

import com.eshipper.service.EdiShippingOrderService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.EdiShippingOrderDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.eshipper.domain.EdiShippingOrder}.
 */
@RestController
@RequestMapping("/api")
public class EdiShippingOrderResource {

    private final Logger log = LoggerFactory.getLogger(EdiShippingOrderResource.class);

    private static final String ENTITY_NAME = "ediShippingOrder";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EdiShippingOrderService ediShippingOrderService;

    public EdiShippingOrderResource(EdiShippingOrderService ediShippingOrderService) {
        this.ediShippingOrderService = ediShippingOrderService;
    }

    /**
     * {@code POST  /edi-shipping-orders} : Create a new ediShippingOrder.
     *
     * @param ediShippingOrderDTO the ediShippingOrderDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ediShippingOrderDTO, or with status {@code 400 (Bad Request)} if the ediShippingOrder has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/edi-shipping-orders")
    public ResponseEntity<EdiShippingOrderDTO> createEdiShippingOrder(@RequestBody EdiShippingOrderDTO ediShippingOrderDTO) throws URISyntaxException {
        log.debug("REST request to save EdiShippingOrder : {}", ediShippingOrderDTO);
        if (ediShippingOrderDTO.getId() != null) {
            throw new BadRequestAlertException("A new ediShippingOrder cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EdiShippingOrderDTO result = ediShippingOrderService.save(ediShippingOrderDTO);
        return ResponseEntity.created(new URI("/api/edi-shipping-orders/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /edi-shipping-orders} : Updates an existing ediShippingOrder.
     *
     * @param ediShippingOrderDTO the ediShippingOrderDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ediShippingOrderDTO,
     * or with status {@code 400 (Bad Request)} if the ediShippingOrderDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ediShippingOrderDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/edi-shipping-orders")
    public ResponseEntity<EdiShippingOrderDTO> updateEdiShippingOrder(@RequestBody EdiShippingOrderDTO ediShippingOrderDTO) throws URISyntaxException {
        log.debug("REST request to update EdiShippingOrder : {}", ediShippingOrderDTO);
        if (ediShippingOrderDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EdiShippingOrderDTO result = ediShippingOrderService.save(ediShippingOrderDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ediShippingOrderDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /edi-shipping-orders} : get all the ediShippingOrders.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ediShippingOrders in body.
     */
    @GetMapping("/edi-shipping-orders")
    public ResponseEntity<List<EdiShippingOrderDTO>> getAllEdiShippingOrders(Pageable pageable) {
        log.debug("REST request to get a page of EdiShippingOrders");
        Page<EdiShippingOrderDTO> page = ediShippingOrderService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /edi-shipping-orders/:id} : get the "id" ediShippingOrder.
     *
     * @param id the id of the ediShippingOrderDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ediShippingOrderDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/edi-shipping-orders/{id}")
    public ResponseEntity<EdiShippingOrderDTO> getEdiShippingOrder(@PathVariable Long id) {
        log.debug("REST request to get EdiShippingOrder : {}", id);
        Optional<EdiShippingOrderDTO> ediShippingOrderDTO = ediShippingOrderService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ediShippingOrderDTO);
    }

    /**
     * {@code DELETE  /edi-shipping-orders/:id} : delete the "id" ediShippingOrder.
     *
     * @param id the id of the ediShippingOrderDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/edi-shipping-orders/{id}")
    public ResponseEntity<Void> deleteEdiShippingOrder(@PathVariable Long id) {
        log.debug("REST request to delete EdiShippingOrder : {}", id);
        ediShippingOrderService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
