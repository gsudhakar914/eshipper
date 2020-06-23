package com.eshipper.web.rest;

import com.eshipper.service.ElasticSearchCommReportService;
import com.eshipper.web.rest.errors.BadRequestAlertException;
import com.eshipper.service.dto.ElasticSearchCommReportDTO;

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
import java.util.stream.StreamSupport;

/**
 * REST controller for managing {@link com.eshipper.domain.ElasticSearchCommReport}.
 */
@RestController
@RequestMapping("/api")
public class ElasticSearchCommReportResource {

    private final Logger log = LoggerFactory.getLogger(ElasticSearchCommReportResource.class);

    private static final String ENTITY_NAME = "elasticSearchCommReport";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ElasticSearchCommReportService elasticSearchCommReportService;

    public ElasticSearchCommReportResource(ElasticSearchCommReportService elasticSearchCommReportService) {
        this.elasticSearchCommReportService = elasticSearchCommReportService;
    }

    /**
     * {@code POST  /elastic-search-comm-reports} : Create a new elasticSearchCommReport.
     *
     * @param elasticSearchCommReportDTO the elasticSearchCommReportDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new elasticSearchCommReportDTO, or with status {@code 400 (Bad Request)} if the elasticSearchCommReport has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/elastic-search-comm-reports")
    public ResponseEntity<ElasticSearchCommReportDTO> createElasticSearchCommReport(@RequestBody ElasticSearchCommReportDTO elasticSearchCommReportDTO) throws URISyntaxException {
        log.debug("REST request to save ElasticSearchCommReport : {}", elasticSearchCommReportDTO);
        if (elasticSearchCommReportDTO.getId() != null) {
            throw new BadRequestAlertException("A new elasticSearchCommReport cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ElasticSearchCommReportDTO result = elasticSearchCommReportService.save(elasticSearchCommReportDTO);
        return ResponseEntity.created(new URI("/api/elastic-search-comm-reports/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /elastic-search-comm-reports} : Updates an existing elasticSearchCommReport.
     *
     * @param elasticSearchCommReportDTO the elasticSearchCommReportDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated elasticSearchCommReportDTO,
     * or with status {@code 400 (Bad Request)} if the elasticSearchCommReportDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the elasticSearchCommReportDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/elastic-search-comm-reports")
    public ResponseEntity<ElasticSearchCommReportDTO> updateElasticSearchCommReport(@RequestBody ElasticSearchCommReportDTO elasticSearchCommReportDTO) throws URISyntaxException {
        log.debug("REST request to update ElasticSearchCommReport : {}", elasticSearchCommReportDTO);
        if (elasticSearchCommReportDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ElasticSearchCommReportDTO result = elasticSearchCommReportService.save(elasticSearchCommReportDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, elasticSearchCommReportDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /elastic-search-comm-reports} : get all the elasticSearchCommReports.
     *
     * @param pageable the pagination information.
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of elasticSearchCommReports in body.
     */
    @GetMapping("/elastic-search-comm-reports")
    public ResponseEntity<List<ElasticSearchCommReportDTO>> getAllElasticSearchCommReports(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("commissionreport-is-null".equals(filter)) {
            log.debug("REST request to get all ElasticSearchCommReports where commissionReport is null");
            return new ResponseEntity<>(elasticSearchCommReportService.findAllWhereCommissionReportIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of ElasticSearchCommReports");
        Page<ElasticSearchCommReportDTO> page = elasticSearchCommReportService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /elastic-search-comm-reports/:id} : get the "id" elasticSearchCommReport.
     *
     * @param id the id of the elasticSearchCommReportDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the elasticSearchCommReportDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/elastic-search-comm-reports/{id}")
    public ResponseEntity<ElasticSearchCommReportDTO> getElasticSearchCommReport(@PathVariable Long id) {
        log.debug("REST request to get ElasticSearchCommReport : {}", id);
        Optional<ElasticSearchCommReportDTO> elasticSearchCommReportDTO = elasticSearchCommReportService.findOne(id);
        return ResponseUtil.wrapOrNotFound(elasticSearchCommReportDTO);
    }

    /**
     * {@code DELETE  /elastic-search-comm-reports/:id} : delete the "id" elasticSearchCommReport.
     *
     * @param id the id of the elasticSearchCommReportDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/elastic-search-comm-reports/{id}")
    public ResponseEntity<Void> deleteElasticSearchCommReport(@PathVariable Long id) {
        log.debug("REST request to delete ElasticSearchCommReport : {}", id);
        elasticSearchCommReportService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
