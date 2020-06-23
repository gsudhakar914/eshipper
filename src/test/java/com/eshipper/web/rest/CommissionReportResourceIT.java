package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.CommissionReport;
import com.eshipper.repository.CommissionReportRepository;
import com.eshipper.service.CommissionReportService;
import com.eshipper.service.dto.CommissionReportDTO;
import com.eshipper.service.mapper.CommissionReportMapper;

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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link CommissionReportResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CommissionReportResourceIT {

    private static final LocalDate DEFAULT_CUT_OFF_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CUT_OFF_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_ADDITIONAL_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_ADDITIONAL_COMMENT = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATED = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_WORK_ORDERS = 1;
    private static final Integer UPDATED_WORK_ORDERS = 2;

    private static final Float DEFAULT_TOTAL_AMOUNT = 1F;
    private static final Float UPDATED_TOTAL_AMOUNT = 2F;

    @Autowired
    private CommissionReportRepository commissionReportRepository;

    @Autowired
    private CommissionReportMapper commissionReportMapper;

    @Autowired
    private CommissionReportService commissionReportService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCommissionReportMockMvc;

    private CommissionReport commissionReport;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CommissionReport createEntity(EntityManager em) {
        CommissionReport commissionReport = new CommissionReport()
            .cutOffDate(DEFAULT_CUT_OFF_DATE)
            .additionalComment(DEFAULT_ADDITIONAL_COMMENT)
            .created(DEFAULT_CREATED)
            .workOrders(DEFAULT_WORK_ORDERS)
            .totalAmount(DEFAULT_TOTAL_AMOUNT);
        return commissionReport;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CommissionReport createUpdatedEntity(EntityManager em) {
        CommissionReport commissionReport = new CommissionReport()
            .cutOffDate(UPDATED_CUT_OFF_DATE)
            .additionalComment(UPDATED_ADDITIONAL_COMMENT)
            .created(UPDATED_CREATED)
            .workOrders(UPDATED_WORK_ORDERS)
            .totalAmount(UPDATED_TOTAL_AMOUNT);
        return commissionReport;
    }

    @BeforeEach
    public void initTest() {
        commissionReport = createEntity(em);
    }

    @Test
    @Transactional
    public void createCommissionReport() throws Exception {
        int databaseSizeBeforeCreate = commissionReportRepository.findAll().size();
        // Create the CommissionReport
        CommissionReportDTO commissionReportDTO = commissionReportMapper.toDto(commissionReport);
        restCommissionReportMockMvc.perform(post("/api/commission-reports")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commissionReportDTO)))
            .andExpect(status().isCreated());

        // Validate the CommissionReport in the database
        List<CommissionReport> commissionReportList = commissionReportRepository.findAll();
        assertThat(commissionReportList).hasSize(databaseSizeBeforeCreate + 1);
        CommissionReport testCommissionReport = commissionReportList.get(commissionReportList.size() - 1);
        assertThat(testCommissionReport.getCutOffDate()).isEqualTo(DEFAULT_CUT_OFF_DATE);
        assertThat(testCommissionReport.getAdditionalComment()).isEqualTo(DEFAULT_ADDITIONAL_COMMENT);
        assertThat(testCommissionReport.getCreated()).isEqualTo(DEFAULT_CREATED);
        assertThat(testCommissionReport.getWorkOrders()).isEqualTo(DEFAULT_WORK_ORDERS);
        assertThat(testCommissionReport.getTotalAmount()).isEqualTo(DEFAULT_TOTAL_AMOUNT);
    }

    @Test
    @Transactional
    public void createCommissionReportWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = commissionReportRepository.findAll().size();

        // Create the CommissionReport with an existing ID
        commissionReport.setId(1L);
        CommissionReportDTO commissionReportDTO = commissionReportMapper.toDto(commissionReport);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCommissionReportMockMvc.perform(post("/api/commission-reports")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commissionReportDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CommissionReport in the database
        List<CommissionReport> commissionReportList = commissionReportRepository.findAll();
        assertThat(commissionReportList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCommissionReports() throws Exception {
        // Initialize the database
        commissionReportRepository.saveAndFlush(commissionReport);

        // Get all the commissionReportList
        restCommissionReportMockMvc.perform(get("/api/commission-reports?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(commissionReport.getId().intValue())))
            .andExpect(jsonPath("$.[*].cutOffDate").value(hasItem(DEFAULT_CUT_OFF_DATE.toString())))
            .andExpect(jsonPath("$.[*].additionalComment").value(hasItem(DEFAULT_ADDITIONAL_COMMENT)))
            .andExpect(jsonPath("$.[*].created").value(hasItem(DEFAULT_CREATED.toString())))
            .andExpect(jsonPath("$.[*].workOrders").value(hasItem(DEFAULT_WORK_ORDERS)))
            .andExpect(jsonPath("$.[*].totalAmount").value(hasItem(DEFAULT_TOTAL_AMOUNT.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getCommissionReport() throws Exception {
        // Initialize the database
        commissionReportRepository.saveAndFlush(commissionReport);

        // Get the commissionReport
        restCommissionReportMockMvc.perform(get("/api/commission-reports/{id}", commissionReport.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(commissionReport.getId().intValue()))
            .andExpect(jsonPath("$.cutOffDate").value(DEFAULT_CUT_OFF_DATE.toString()))
            .andExpect(jsonPath("$.additionalComment").value(DEFAULT_ADDITIONAL_COMMENT))
            .andExpect(jsonPath("$.created").value(DEFAULT_CREATED.toString()))
            .andExpect(jsonPath("$.workOrders").value(DEFAULT_WORK_ORDERS))
            .andExpect(jsonPath("$.totalAmount").value(DEFAULT_TOTAL_AMOUNT.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingCommissionReport() throws Exception {
        // Get the commissionReport
        restCommissionReportMockMvc.perform(get("/api/commission-reports/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCommissionReport() throws Exception {
        // Initialize the database
        commissionReportRepository.saveAndFlush(commissionReport);

        int databaseSizeBeforeUpdate = commissionReportRepository.findAll().size();

        // Update the commissionReport
        CommissionReport updatedCommissionReport = commissionReportRepository.findById(commissionReport.getId()).get();
        // Disconnect from session so that the updates on updatedCommissionReport are not directly saved in db
        em.detach(updatedCommissionReport);
        updatedCommissionReport
            .cutOffDate(UPDATED_CUT_OFF_DATE)
            .additionalComment(UPDATED_ADDITIONAL_COMMENT)
            .created(UPDATED_CREATED)
            .workOrders(UPDATED_WORK_ORDERS)
            .totalAmount(UPDATED_TOTAL_AMOUNT);
        CommissionReportDTO commissionReportDTO = commissionReportMapper.toDto(updatedCommissionReport);

        restCommissionReportMockMvc.perform(put("/api/commission-reports")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commissionReportDTO)))
            .andExpect(status().isOk());

        // Validate the CommissionReport in the database
        List<CommissionReport> commissionReportList = commissionReportRepository.findAll();
        assertThat(commissionReportList).hasSize(databaseSizeBeforeUpdate);
        CommissionReport testCommissionReport = commissionReportList.get(commissionReportList.size() - 1);
        assertThat(testCommissionReport.getCutOffDate()).isEqualTo(UPDATED_CUT_OFF_DATE);
        assertThat(testCommissionReport.getAdditionalComment()).isEqualTo(UPDATED_ADDITIONAL_COMMENT);
        assertThat(testCommissionReport.getCreated()).isEqualTo(UPDATED_CREATED);
        assertThat(testCommissionReport.getWorkOrders()).isEqualTo(UPDATED_WORK_ORDERS);
        assertThat(testCommissionReport.getTotalAmount()).isEqualTo(UPDATED_TOTAL_AMOUNT);
    }

    @Test
    @Transactional
    public void updateNonExistingCommissionReport() throws Exception {
        int databaseSizeBeforeUpdate = commissionReportRepository.findAll().size();

        // Create the CommissionReport
        CommissionReportDTO commissionReportDTO = commissionReportMapper.toDto(commissionReport);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCommissionReportMockMvc.perform(put("/api/commission-reports")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commissionReportDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CommissionReport in the database
        List<CommissionReport> commissionReportList = commissionReportRepository.findAll();
        assertThat(commissionReportList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCommissionReport() throws Exception {
        // Initialize the database
        commissionReportRepository.saveAndFlush(commissionReport);

        int databaseSizeBeforeDelete = commissionReportRepository.findAll().size();

        // Delete the commissionReport
        restCommissionReportMockMvc.perform(delete("/api/commission-reports/{id}", commissionReport.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CommissionReport> commissionReportList = commissionReportRepository.findAll();
        assertThat(commissionReportList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
