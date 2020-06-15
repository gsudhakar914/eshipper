package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.WoWorkOrder;
import com.eshipper.repository.WoWorkOrderRepository;
import com.eshipper.service.WoWorkOrderService;
import com.eshipper.service.dto.WoWorkOrderDTO;
import com.eshipper.service.mapper.WoWorkOrderMapper;

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
 * Integration tests for the {@link WoWorkOrderResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class WoWorkOrderResourceIT {

    @Autowired
    private WoWorkOrderRepository woWorkOrderRepository;

    @Autowired
    private WoWorkOrderMapper woWorkOrderMapper;

    @Autowired
    private WoWorkOrderService woWorkOrderService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restWoWorkOrderMockMvc;

    private WoWorkOrder woWorkOrder;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WoWorkOrder createEntity(EntityManager em) {
        WoWorkOrder woWorkOrder = new WoWorkOrder();
        return woWorkOrder;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WoWorkOrder createUpdatedEntity(EntityManager em) {
        WoWorkOrder woWorkOrder = new WoWorkOrder();
        return woWorkOrder;
    }

    @BeforeEach
    public void initTest() {
        woWorkOrder = createEntity(em);
    }

    @Test
    @Transactional
    public void createWoWorkOrder() throws Exception {
        int databaseSizeBeforeCreate = woWorkOrderRepository.findAll().size();
        // Create the WoWorkOrder
        WoWorkOrderDTO woWorkOrderDTO = woWorkOrderMapper.toDto(woWorkOrder);
        restWoWorkOrderMockMvc.perform(post("/api/wo-work-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(woWorkOrderDTO)))
            .andExpect(status().isCreated());

        // Validate the WoWorkOrder in the database
        List<WoWorkOrder> woWorkOrderList = woWorkOrderRepository.findAll();
        assertThat(woWorkOrderList).hasSize(databaseSizeBeforeCreate + 1);
        WoWorkOrder testWoWorkOrder = woWorkOrderList.get(woWorkOrderList.size() - 1);
    }

    @Test
    @Transactional
    public void createWoWorkOrderWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = woWorkOrderRepository.findAll().size();

        // Create the WoWorkOrder with an existing ID
        woWorkOrder.setId(1L);
        WoWorkOrderDTO woWorkOrderDTO = woWorkOrderMapper.toDto(woWorkOrder);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWoWorkOrderMockMvc.perform(post("/api/wo-work-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(woWorkOrderDTO)))
            .andExpect(status().isBadRequest());

        // Validate the WoWorkOrder in the database
        List<WoWorkOrder> woWorkOrderList = woWorkOrderRepository.findAll();
        assertThat(woWorkOrderList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllWoWorkOrders() throws Exception {
        // Initialize the database
        woWorkOrderRepository.saveAndFlush(woWorkOrder);

        // Get all the woWorkOrderList
        restWoWorkOrderMockMvc.perform(get("/api/wo-work-orders?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(woWorkOrder.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getWoWorkOrder() throws Exception {
        // Initialize the database
        woWorkOrderRepository.saveAndFlush(woWorkOrder);

        // Get the woWorkOrder
        restWoWorkOrderMockMvc.perform(get("/api/wo-work-orders/{id}", woWorkOrder.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(woWorkOrder.getId().intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingWoWorkOrder() throws Exception {
        // Get the woWorkOrder
        restWoWorkOrderMockMvc.perform(get("/api/wo-work-orders/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWoWorkOrder() throws Exception {
        // Initialize the database
        woWorkOrderRepository.saveAndFlush(woWorkOrder);

        int databaseSizeBeforeUpdate = woWorkOrderRepository.findAll().size();

        // Update the woWorkOrder
        WoWorkOrder updatedWoWorkOrder = woWorkOrderRepository.findById(woWorkOrder.getId()).get();
        // Disconnect from session so that the updates on updatedWoWorkOrder are not directly saved in db
        em.detach(updatedWoWorkOrder);
        WoWorkOrderDTO woWorkOrderDTO = woWorkOrderMapper.toDto(updatedWoWorkOrder);

        restWoWorkOrderMockMvc.perform(put("/api/wo-work-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(woWorkOrderDTO)))
            .andExpect(status().isOk());

        // Validate the WoWorkOrder in the database
        List<WoWorkOrder> woWorkOrderList = woWorkOrderRepository.findAll();
        assertThat(woWorkOrderList).hasSize(databaseSizeBeforeUpdate);
        WoWorkOrder testWoWorkOrder = woWorkOrderList.get(woWorkOrderList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingWoWorkOrder() throws Exception {
        int databaseSizeBeforeUpdate = woWorkOrderRepository.findAll().size();

        // Create the WoWorkOrder
        WoWorkOrderDTO woWorkOrderDTO = woWorkOrderMapper.toDto(woWorkOrder);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWoWorkOrderMockMvc.perform(put("/api/wo-work-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(woWorkOrderDTO)))
            .andExpect(status().isBadRequest());

        // Validate the WoWorkOrder in the database
        List<WoWorkOrder> woWorkOrderList = woWorkOrderRepository.findAll();
        assertThat(woWorkOrderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteWoWorkOrder() throws Exception {
        // Initialize the database
        woWorkOrderRepository.saveAndFlush(woWorkOrder);

        int databaseSizeBeforeDelete = woWorkOrderRepository.findAll().size();

        // Delete the woWorkOrder
        restWoWorkOrderMockMvc.perform(delete("/api/wo-work-orders/{id}", woWorkOrder.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<WoWorkOrder> woWorkOrderList = woWorkOrderRepository.findAll();
        assertThat(woWorkOrderList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
