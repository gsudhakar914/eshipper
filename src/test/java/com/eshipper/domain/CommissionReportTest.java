package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class CommissionReportTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CommissionReport.class);
        CommissionReport commissionReport1 = new CommissionReport();
        commissionReport1.setId(1L);
        CommissionReport commissionReport2 = new CommissionReport();
        commissionReport2.setId(commissionReport1.getId());
        assertThat(commissionReport1).isEqualTo(commissionReport2);
        commissionReport2.setId(2L);
        assertThat(commissionReport1).isNotEqualTo(commissionReport2);
        commissionReport1.setId(null);
        assertThat(commissionReport1).isNotEqualTo(commissionReport2);
    }
}
