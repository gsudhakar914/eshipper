package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class ElasticSearchCommReportTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ElasticSearchCommReport.class);
        ElasticSearchCommReport elasticSearchCommReport1 = new ElasticSearchCommReport();
        elasticSearchCommReport1.setId(1L);
        ElasticSearchCommReport elasticSearchCommReport2 = new ElasticSearchCommReport();
        elasticSearchCommReport2.setId(elasticSearchCommReport1.getId());
        assertThat(elasticSearchCommReport1).isEqualTo(elasticSearchCommReport2);
        elasticSearchCommReport2.setId(2L);
        assertThat(elasticSearchCommReport1).isNotEqualTo(elasticSearchCommReport2);
        elasticSearchCommReport1.setId(null);
        assertThat(elasticSearchCommReport1).isNotEqualTo(elasticSearchCommReport2);
    }
}
