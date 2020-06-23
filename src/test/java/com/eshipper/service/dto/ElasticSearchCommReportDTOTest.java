package com.eshipper.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class ElasticSearchCommReportDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ElasticSearchCommReportDTO.class);
        ElasticSearchCommReportDTO elasticSearchCommReportDTO1 = new ElasticSearchCommReportDTO();
        elasticSearchCommReportDTO1.setId(1L);
        ElasticSearchCommReportDTO elasticSearchCommReportDTO2 = new ElasticSearchCommReportDTO();
        assertThat(elasticSearchCommReportDTO1).isNotEqualTo(elasticSearchCommReportDTO2);
        elasticSearchCommReportDTO2.setId(elasticSearchCommReportDTO1.getId());
        assertThat(elasticSearchCommReportDTO1).isEqualTo(elasticSearchCommReportDTO2);
        elasticSearchCommReportDTO2.setId(2L);
        assertThat(elasticSearchCommReportDTO1).isNotEqualTo(elasticSearchCommReportDTO2);
        elasticSearchCommReportDTO1.setId(null);
        assertThat(elasticSearchCommReportDTO1).isNotEqualTo(elasticSearchCommReportDTO2);
    }
}
