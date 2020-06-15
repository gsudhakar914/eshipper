package com.eshipper.web.rest;

import com.eshipper.service.CommissionStatusService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.CommissionStatusDTO;

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
 * REST controller for managing {@link com.eshipper.domain.CommissionStatus}.
 */
@RestController
@RequestMapping("/api")
public class CommissionStatusResource {

    private final Logger log = LoggerFactory.getLogger(CommissionStatusResource.class);

    private static final String ENTITY_NAME = "commissionStatus";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CommissionStatusService commissionStatusService;

    public CommissionStatusResource(CommissionStatusService commissionStatusService) {
        this.commissionStatusService = commissionStatusService;
    }

    /**
     * {@code POST  /commission-statuses} : Create a new commissionStatus.
     *
     * @param commissionStatusDTO the commissionStatusDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new commissionStatusDTO, or with status {@code 400 (Bad Request)} if the commissionStatus has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/commission-statuses")
    public ResponseEntity<CommissionStatusDTO> createCommissionStatus(@RequestBody CommissionStatusDTO commissionStatusDTO) throws URISyntaxException {
        log.debug("REST request to save CommissionStatus : {}", commissionStatusDTO);
        if (commissionStatusDTO.getId() != null) {
            throw new BadRequestAlertException("A new commissionStatus cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CommissionStatusDTO result = commissionStatusService.save(commissionStatusDTO);
        return ResponseEntity.created(new URI("/api/commission-statuses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /commission-statuses} : Updates an existing commissionStatus.
     *
     * @param commissionStatusDTO the commissionStatusDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated commissionStatusDTO,
     * or with status {@code 400 (Bad Request)} if the commissionStatusDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the commissionStatusDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/commission-statuses")
    public ResponseEntity<CommissionStatusDTO> updateCommissionStatus(@RequestBody CommissionStatusDTO commissionStatusDTO) throws URISyntaxException {
        log.debug("REST request to update CommissionStatus : {}", commissionStatusDTO);
        if (commissionStatusDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CommissionStatusDTO result = commissionStatusService.save(commissionStatusDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, commissionStatusDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /commission-statuses} : get all the commissionStatuses.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of commissionStatuses in body.
     */
    @GetMapping("/commission-statuses")
    public ResponseEntity<List<CommissionStatusDTO>> getAllCommissionStatuses(Pageable pageable) {
        log.debug("REST request to get a page of CommissionStatuses");
        Page<CommissionStatusDTO> page = commissionStatusService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /commission-statuses/:id} : get the "id" commissionStatus.
     *
     * @param id the id of the commissionStatusDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the commissionStatusDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/commission-statuses/{id}")
    public ResponseEntity<CommissionStatusDTO> getCommissionStatus(@PathVariable Long id) {
        log.debug("REST request to get CommissionStatus : {}", id);
        Optional<CommissionStatusDTO> commissionStatusDTO = commissionStatusService.findOne(id);
        return ResponseUtil.wrapOrNotFound(commissionStatusDTO);
    }

    /**
     * {@code DELETE  /commission-statuses/:id} : delete the "id" commissionStatus.
     *
     * @param id the id of the commissionStatusDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/commission-statuses/{id}")
    public ResponseEntity<Void> deleteCommissionStatus(@PathVariable Long id) {
        log.debug("REST request to delete CommissionStatus : {}", id);
        commissionStatusService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
