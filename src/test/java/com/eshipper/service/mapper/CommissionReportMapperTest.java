package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CommissionReportMapperTest {

    private CommissionReportMapper commissionReportMapper;

    @BeforeEach
    public void setUp() {
        commissionReportMapper = new CommissionReportMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(commissionReportMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(commissionReportMapper.fromId(null)).isNull();
    }
}
