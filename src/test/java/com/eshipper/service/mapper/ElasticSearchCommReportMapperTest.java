package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ElasticSearchCommReportMapperTest {

    private ElasticSearchCommReportMapper elasticSearchCommReportMapper;

    @BeforeEach
    public void setUp() {
        elasticSearchCommReportMapper = new ElasticSearchCommReportMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(elasticSearchCommReportMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(elasticSearchCommReportMapper.fromId(null)).isNull();
    }
}
