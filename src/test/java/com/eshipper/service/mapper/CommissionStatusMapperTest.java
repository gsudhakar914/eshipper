package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CommissionStatusMapperTest {

    private CommissionStatusMapper commissionStatusMapper;

    @BeforeEach
    public void setUp() {
        commissionStatusMapper = new CommissionStatusMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(commissionStatusMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(commissionStatusMapper.fromId(null)).isNull();
    }
}
