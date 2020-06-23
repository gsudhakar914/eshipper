package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.CommissionStatus;
import com.eshipper.repository.CommissionStatusRepository;
import com.eshipper.service.CommissionStatusService;
import com.eshipper.service.dto.CommissionStatusDTO;
import com.eshipper.service.mapper.CommissionStatusMapper;

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
 * Integration tests for the {@link CommissionStatusResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CommissionStatusResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private CommissionStatusRepository commissionStatusRepository;

    @Autowired
    private CommissionStatusMapper commissionStatusMapper;

    @Autowired
    private CommissionStatusService commissionStatusService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCommissionStatusMockMvc;

    private CommissionStatus commissionStatus;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CommissionStatus createEntity(EntityManager em) {
        CommissionStatus commissionStatus = new CommissionStatus()
            .name(DEFAULT_NAME);
        return commissionStatus;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CommissionStatus createUpdatedEntity(EntityManager em) {
        CommissionStatus commissionStatus = new CommissionStatus()
            .name(UPDATED_NAME);
        return commissionStatus;
    }

    @BeforeEach
    public void initTest() {
        commissionStatus = createEntity(em);
    }

    @Test
    @Transactional
    public void createCommissionStatus() throws Exception {
        int databaseSizeBeforeCreate = commissionStatusRepository.findAll().size();
        // Create the CommissionStatus
        CommissionStatusDTO commissionStatusDTO = commissionStatusMapper.toDto(commissionStatus);
        restCommissionStatusMockMvc.perform(post("/api/commission-statuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commissionStatusDTO)))
            .andExpect(status().isCreated());

        // Validate the CommissionStatus in the database
        List<CommissionStatus> commissionStatusList = commissionStatusRepository.findAll();
        assertThat(commissionStatusList).hasSize(databaseSizeBeforeCreate + 1);
        CommissionStatus testCommissionStatus = commissionStatusList.get(commissionStatusList.size() - 1);
        assertThat(testCommissionStatus.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createCommissionStatusWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = commissionStatusRepository.findAll().size();

        // Create the CommissionStatus with an existing ID
        commissionStatus.setId(1L);
        CommissionStatusDTO commissionStatusDTO = commissionStatusMapper.toDto(commissionStatus);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCommissionStatusMockMvc.perform(post("/api/commission-statuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commissionStatusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CommissionStatus in the database
        List<CommissionStatus> commissionStatusList = commissionStatusRepository.findAll();
        assertThat(commissionStatusList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCommissionStatuses() throws Exception {
        // Initialize the database
        commissionStatusRepository.saveAndFlush(commissionStatus);

        // Get all the commissionStatusList
        restCommissionStatusMockMvc.perform(get("/api/commission-statuses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(commissionStatus.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }
    
    @Test
    @Transactional
    public void getCommissionStatus() throws Exception {
        // Initialize the database
        commissionStatusRepository.saveAndFlush(commissionStatus);

        // Get the commissionStatus
        restCommissionStatusMockMvc.perform(get("/api/commission-statuses/{id}", commissionStatus.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(commissionStatus.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }
    @Test
    @Transactional
    public void getNonExistingCommissionStatus() throws Exception {
        // Get the commissionStatus
        restCommissionStatusMockMvc.perform(get("/api/commission-statuses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCommissionStatus() throws Exception {
        // Initialize the database
        commissionStatusRepository.saveAndFlush(commissionStatus);

        int databaseSizeBeforeUpdate = commissionStatusRepository.findAll().size();

        // Update the commissionStatus
        CommissionStatus updatedCommissionStatus = commissionStatusRepository.findById(commissionStatus.getId()).get();
        // Disconnect from session so that the updates on updatedCommissionStatus are not directly saved in db
        em.detach(updatedCommissionStatus);
        updatedCommissionStatus
            .name(UPDATED_NAME);
        CommissionStatusDTO commissionStatusDTO = commissionStatusMapper.toDto(updatedCommissionStatus);

        restCommissionStatusMockMvc.perform(put("/api/commission-statuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commissionStatusDTO)))
            .andExpect(status().isOk());

        // Validate the CommissionStatus in the database
        List<CommissionStatus> commissionStatusList = commissionStatusRepository.findAll();
        assertThat(commissionStatusList).hasSize(databaseSizeBeforeUpdate);
        CommissionStatus testCommissionStatus = commissionStatusList.get(commissionStatusList.size() - 1);
        assertThat(testCommissionStatus.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingCommissionStatus() throws Exception {
        int databaseSizeBeforeUpdate = commissionStatusRepository.findAll().size();

        // Create the CommissionStatus
        CommissionStatusDTO commissionStatusDTO = commissionStatusMapper.toDto(commissionStatus);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCommissionStatusMockMvc.perform(put("/api/commission-statuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(commissionStatusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CommissionStatus in the database
        List<CommissionStatus> commissionStatusList = commissionStatusRepository.findAll();
        assertThat(commissionStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCommissionStatus() throws Exception {
        // Initialize the database
        commissionStatusRepository.saveAndFlush(commissionStatus);

        int databaseSizeBeforeDelete = commissionStatusRepository.findAll().size();

        // Delete the commissionStatus
        restCommissionStatusMockMvc.perform(delete("/api/commission-statuses/{id}", commissionStatus.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CommissionStatus> commissionStatusList = commissionStatusRepository.findAll();
        assertThat(commissionStatusList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
