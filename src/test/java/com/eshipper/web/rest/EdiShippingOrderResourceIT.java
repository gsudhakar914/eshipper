package com.eshipper.web.rest;

import com.eshipper.EshipperApp;
import com.eshipper.domain.EdiShippingOrder;
import com.eshipper.repository.EdiShippingOrderRepository;
import com.eshipper.service.EdiShippingOrderService;
import com.eshipper.service.dto.EdiShippingOrderDTO;
import com.eshipper.service.mapper.EdiShippingOrderMapper;

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
 * Integration tests for the {@link EdiShippingOrderResource} REST controller.
 */
@SpringBootTest(classes = EshipperApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class EdiShippingOrderResourceIT {

    @Autowired
    private EdiShippingOrderRepository ediShippingOrderRepository;

    @Autowired
    private EdiShippingOrderMapper ediShippingOrderMapper;

    @Autowired
    private EdiShippingOrderService ediShippingOrderService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEdiShippingOrderMockMvc;

    private EdiShippingOrder ediShippingOrder;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EdiShippingOrder createEntity(EntityManager em) {
        EdiShippingOrder ediShippingOrder = new EdiShippingOrder();
        return ediShippingOrder;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EdiShippingOrder createUpdatedEntity(EntityManager em) {
        EdiShippingOrder ediShippingOrder = new EdiShippingOrder();
        return ediShippingOrder;
    }

    @BeforeEach
    public void initTest() {
        ediShippingOrder = createEntity(em);
    }

    @Test
    @Transactional
    public void createEdiShippingOrder() throws Exception {
        int databaseSizeBeforeCreate = ediShippingOrderRepository.findAll().size();
        // Create the EdiShippingOrder
        EdiShippingOrderDTO ediShippingOrderDTO = ediShippingOrderMapper.toDto(ediShippingOrder);
        restEdiShippingOrderMockMvc.perform(post("/api/edi-shipping-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ediShippingOrderDTO)))
            .andExpect(status().isCreated());

        // Validate the EdiShippingOrder in the database
        List<EdiShippingOrder> ediShippingOrderList = ediShippingOrderRepository.findAll();
        assertThat(ediShippingOrderList).hasSize(databaseSizeBeforeCreate + 1);
        EdiShippingOrder testEdiShippingOrder = ediShippingOrderList.get(ediShippingOrderList.size() - 1);
    }

    @Test
    @Transactional
    public void createEdiShippingOrderWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ediShippingOrderRepository.findAll().size();

        // Create the EdiShippingOrder with an existing ID
        ediShippingOrder.setId(1L);
        EdiShippingOrderDTO ediShippingOrderDTO = ediShippingOrderMapper.toDto(ediShippingOrder);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEdiShippingOrderMockMvc.perform(post("/api/edi-shipping-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ediShippingOrderDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EdiShippingOrder in the database
        List<EdiShippingOrder> ediShippingOrderList = ediShippingOrderRepository.findAll();
        assertThat(ediShippingOrderList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEdiShippingOrders() throws Exception {
        // Initialize the database
        ediShippingOrderRepository.saveAndFlush(ediShippingOrder);

        // Get all the ediShippingOrderList
        restEdiShippingOrderMockMvc.perform(get("/api/edi-shipping-orders?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ediShippingOrder.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getEdiShippingOrder() throws Exception {
        // Initialize the database
        ediShippingOrderRepository.saveAndFlush(ediShippingOrder);

        // Get the ediShippingOrder
        restEdiShippingOrderMockMvc.perform(get("/api/edi-shipping-orders/{id}", ediShippingOrder.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ediShippingOrder.getId().intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingEdiShippingOrder() throws Exception {
        // Get the ediShippingOrder
        restEdiShippingOrderMockMvc.perform(get("/api/edi-shipping-orders/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEdiShippingOrder() throws Exception {
        // Initialize the database
        ediShippingOrderRepository.saveAndFlush(ediShippingOrder);

        int databaseSizeBeforeUpdate = ediShippingOrderRepository.findAll().size();

        // Update the ediShippingOrder
        EdiShippingOrder updatedEdiShippingOrder = ediShippingOrderRepository.findById(ediShippingOrder.getId()).get();
        // Disconnect from session so that the updates on updatedEdiShippingOrder are not directly saved in db
        em.detach(updatedEdiShippingOrder);
        EdiShippingOrderDTO ediShippingOrderDTO = ediShippingOrderMapper.toDto(updatedEdiShippingOrder);

        restEdiShippingOrderMockMvc.perform(put("/api/edi-shipping-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ediShippingOrderDTO)))
            .andExpect(status().isOk());

        // Validate the EdiShippingOrder in the database
        List<EdiShippingOrder> ediShippingOrderList = ediShippingOrderRepository.findAll();
        assertThat(ediShippingOrderList).hasSize(databaseSizeBeforeUpdate);
        EdiShippingOrder testEdiShippingOrder = ediShippingOrderList.get(ediShippingOrderList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingEdiShippingOrder() throws Exception {
        int databaseSizeBeforeUpdate = ediShippingOrderRepository.findAll().size();

        // Create the EdiShippingOrder
        EdiShippingOrderDTO ediShippingOrderDTO = ediShippingOrderMapper.toDto(ediShippingOrder);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEdiShippingOrderMockMvc.perform(put("/api/edi-shipping-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ediShippingOrderDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EdiShippingOrder in the database
        List<EdiShippingOrder> ediShippingOrderList = ediShippingOrderRepository.findAll();
        assertThat(ediShippingOrderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEdiShippingOrder() throws Exception {
        // Initialize the database
        ediShippingOrderRepository.saveAndFlush(ediShippingOrder);

        int databaseSizeBeforeDelete = ediShippingOrderRepository.findAll().size();

        // Delete the ediShippingOrder
        restEdiShippingOrderMockMvc.perform(delete("/api/edi-shipping-orders/{id}", ediShippingOrder.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EdiShippingOrder> ediShippingOrderList = ediShippingOrderRepository.findAll();
        assertThat(ediShippingOrderList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
