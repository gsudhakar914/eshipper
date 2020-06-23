package com.eshipper.web.rest;

import com.eshipper.service.CommissionReportService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.CommissionReportDTO;

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
 * REST controller for managing {@link com.eshipper.domain.CommissionReport}.
 */
@RestController
@RequestMapping("/api")
public class CommissionReportResource {

    private final Logger log = LoggerFactory.getLogger(CommissionReportResource.class);

    private static final String ENTITY_NAME = "commissionReport";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CommissionReportService commissionReportService;

    public CommissionReportResource(CommissionReportService commissionReportService) {
        this.commissionReportService = commissionReportService;
    }

    /**
     * {@code POST  /commission-reports} : Create a new commissionReport.
     *
     * @param commissionReportDTO the commissionReportDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new commissionReportDTO, or with status {@code 400 (Bad Request)} if the commissionReport has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/commission-reports")
    public ResponseEntity<CommissionReportDTO> createCommissionReport(@RequestBody CommissionReportDTO commissionReportDTO) throws URISyntaxException {
        log.debug("REST request to save CommissionReport : {}", commissionReportDTO);
        if (commissionReportDTO.getId() != null) {
            throw new BadRequestAlertException("A new commissionReport cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CommissionReportDTO result = commissionReportService.save(commissionReportDTO);
        return ResponseEntity.created(new URI("/api/commission-reports/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /commission-reports} : Updates an existing commissionReport.
     *
     * @param commissionReportDTO the commissionReportDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated commissionReportDTO,
     * or with status {@code 400 (Bad Request)} if the commissionReportDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the commissionReportDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/commission-reports")
    public ResponseEntity<CommissionReportDTO> updateCommissionReport(@RequestBody CommissionReportDTO commissionReportDTO) throws URISyntaxException {
        log.debug("REST request to update CommissionReport : {}", commissionReportDTO);
        if (commissionReportDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CommissionReportDTO result = commissionReportService.save(commissionReportDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, commissionReportDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /commission-reports} : get all the commissionReports.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of commissionReports in body.
     */
    @GetMapping("/commission-reports")
    public ResponseEntity<List<CommissionReportDTO>> getAllCommissionReports(Pageable pageable) {
        log.debug("REST request to get a page of CommissionReports");
        Page<CommissionReportDTO> page = commissionReportService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /commission-reports/:id} : get the "id" commissionReport.
     *
     * @param id the id of the commissionReportDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the commissionReportDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/commission-reports/{id}")
    public ResponseEntity<CommissionReportDTO> getCommissionReport(@PathVariable Long id) {
        log.debug("REST request to get CommissionReport : {}", id);
        Optional<CommissionReportDTO> commissionReportDTO = commissionReportService.findOne(id);
        return ResponseUtil.wrapOrNotFound(commissionReportDTO);
    }

    /**
     * {@code DELETE  /commission-reports/:id} : delete the "id" commissionReport.
     *
     * @param id the id of the commissionReportDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/commission-reports/{id}")
    public ResponseEntity<Void> deleteCommissionReport(@PathVariable Long id) {
        log.debug("REST request to delete CommissionReport : {}", id);
        commissionReportService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
