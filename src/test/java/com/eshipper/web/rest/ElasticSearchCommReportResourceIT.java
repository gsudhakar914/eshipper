package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.ElasticSearchCommReport;
import com.eshipper.repository.ElasticSearchCommReportRepository;
import com.eshipper.service.ElasticSearchCommReportService;
import com.eshipper.service.dto.ElasticSearchCommReportDTO;
import com.eshipper.service.mapper.ElasticSearchCommReportMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ElasticSearchCommReportResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ElasticSearchCommReportResourceIT {

    @Autowired
    private ElasticSearchCommReportRepository elasticSearchCommReportRepository;

    @Autowired
    private ElasticSearchCommReportMapper elasticSearchCommReportMapper;

    @Autowired
    private ElasticSearchCommReportService elasticSearchCommReportService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restElasticSearchCommReportMockMvc;

    private ElasticSearchCommReport elasticSearchCommReport;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ElasticSearchCommReport createEntity(EntityManager em) {
        ElasticSearchCommReport elasticSearchCommReport = new ElasticSearchCommReport();
        return elasticSearchCommReport;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ElasticSearchCommReport createUpdatedEntity(EntityManager em) {
        ElasticSearchCommReport elasticSearchCommReport = new ElasticSearchCommReport();
        return elasticSearchCommReport;
    }

    @BeforeEach
    public void initTest() {
        elasticSearchCommReport = createEntity(em);
    }

    @Test
    @Transactional
    public void createElasticSearchCommReport() throws Exception {
        int databaseSizeBeforeCreate = elasticSearchCommReportRepository.findAll().size();
        // Create the ElasticSearchCommReport
        ElasticSearchCommReportDTO elasticSearchCommReportDTO = elasticSearchCommReportMapper.toDto(elasticSearchCommReport);
        restElasticSearchCommReportMockMvc.perform(post("/api/elastic-search-comm-reports")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(elasticSearchCommReportDTO)))
            .andExpect(status().isCreated());

        // Validate the ElasticSearchCommReport in the database
        List<ElasticSearchCommReport> elasticSearchCommReportList = elasticSearchCommReportRepository.findAll();
        assertThat(elasticSearchCommReportList).hasSize(databaseSizeBeforeCreate + 1);
        ElasticSearchCommReport testElasticSearchCommReport = elasticSearchCommReportList.get(elasticSearchCommReportList.size() - 1);
    }

    @Test
    @Transactional
    public void createElasticSearchCommReportWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = elasticSearchCommReportRepository.findAll().size();

        // Create the ElasticSearchCommReport with an existing ID
        elasticSearchCommReport.setId(1L);
        ElasticSearchCommReportDTO elasticSearchCommReportDTO = elasticSearchCommReportMapper.toDto(elasticSearchCommReport);

        // An entity with an existing ID cannot be created, so this API call must fail
        restElasticSearchCommReportMockMvc.perform(post("/api/elastic-search-comm-reports")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(elasticSearchCommReportDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ElasticSearchCommReport in the database
        List<ElasticSearchCommReport> elasticSearchCommReportList = elasticSearchCommReportRepository.findAll();
        assertThat(elasticSearchCommReportList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllElasticSearchCommReports() throws Exception {
        // Initialize the database
        elasticSearchCommReportRepository.saveAndFlush(elasticSearchCommReport);

        // Get all the elasticSearchCommReportList
        restElasticSearchCommReportMockMvc.perform(get("/api/elastic-search-comm-reports?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(elasticSearchCommReport.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getElasticSearchCommReport() throws Exception {
        // Initialize the database
        elasticSearchCommReportRepository.saveAndFlush(elasticSearchCommReport);

        // Get the elasticSearchCommReport
        restElasticSearchCommReportMockMvc.perform(get("/api/elastic-search-comm-reports/{id}", elasticSearchCommReport.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(elasticSearchCommReport.getId().intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingElasticSearchCommReport() throws Exception {
        // Get the elasticSearchCommReport
        restElasticSearchCommReportMockMvc.perform(get("/api/elastic-search-comm-reports/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateElasticSearchCommReport() throws Exception {
        // Initialize the database
        elasticSearchCommReportRepository.saveAndFlush(elasticSearchCommReport);

        int databaseSizeBeforeUpdate = elasticSearchCommReportRepository.findAll().size();

        // Update the elasticSearchCommReport
        ElasticSearchCommReport updatedElasticSearchCommReport = elasticSearchCommReportRepository.findById(elasticSearchCommReport.getId()).get();
        // Disconnect from session so that the updates on updatedElasticSearchCommReport are not directly saved in db
        em.detach(updatedElasticSearchCommReport);
        ElasticSearchCommReportDTO elasticSearchCommReportDTO = elasticSearchCommReportMapper.toDto(updatedElasticSearchCommReport);

        restElasticSearchCommReportMockMvc.perform(put("/api/elastic-search-comm-reports")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(elasticSearchCommReportDTO)))
            .andExpect(status().isOk());

        // Validate the ElasticSearchCommReport in the database
        List<ElasticSearchCommReport> elasticSearchCommReportList = elasticSearchCommReportRepository.findAll();
        assertThat(elasticSearchCommReportList).hasSize(databaseSizeBeforeUpdate);
        ElasticSearchCommReport testElasticSearchCommReport = elasticSearchCommReportList.get(elasticSearchCommReportList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingElasticSearchCommReport() throws Exception {
        int databaseSizeBeforeUpdate = elasticSearchCommReportRepository.findAll().size();

        // Create the ElasticSearchCommReport
        ElasticSearchCommReportDTO elasticSearchCommReportDTO = elasticSearchCommReportMapper.toDto(elasticSearchCommReport);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restElasticSearchCommReportMockMvc.perform(put("/api/elastic-search-comm-reports")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(elasticSearchCommReportDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ElasticSearchCommReport in the database
        List<ElasticSearchCommReport> elasticSearchCommReportList = elasticSearchCommReportRepository.findAll();
        assertThat(elasticSearchCommReportList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteElasticSearchCommReport() throws Exception {
        // Initialize the database
        elasticSearchCommReportRepository.saveAndFlush(elasticSearchCommReport);

        int databaseSizeBeforeDelete = elasticSearchCommReportRepository.findAll().size();

        // Delete the elasticSearchCommReport
        restElasticSearchCommReportMockMvc.perform(delete("/api/elastic-search-comm-reports/{id}", elasticSearchCommReport.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ElasticSearchCommReport> elasticSearchCommReportList = elasticSearchCommReportRepository.findAll();
        assertThat(elasticSearchCommReportList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
