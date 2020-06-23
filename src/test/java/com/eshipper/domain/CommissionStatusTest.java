package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class CommissionStatusTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CommissionStatus.class);
        CommissionStatus commissionStatus1 = new CommissionStatus();
        commissionStatus1.setId(1L);
        CommissionStatus commissionStatus2 = new CommissionStatus();
        commissionStatus2.setId(commissionStatus1.getId());
        assertThat(commissionStatus1).isEqualTo(commissionStatus2);
        commissionStatus2.setId(2L);
        assertThat(commissionStatus1).isNotEqualTo(commissionStatus2);
        commissionStatus1.setId(null);
        assertThat(commissionStatus1).isNotEqualTo(commissionStatus2);
    }
}
