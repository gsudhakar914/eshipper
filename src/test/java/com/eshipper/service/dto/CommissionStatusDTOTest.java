package com.eshipper.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class CommissionStatusDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CommissionStatusDTO.class);
        CommissionStatusDTO commissionStatusDTO1 = new CommissionStatusDTO();
        commissionStatusDTO1.setId(1L);
        CommissionStatusDTO commissionStatusDTO2 = new CommissionStatusDTO();
        assertThat(commissionStatusDTO1).isNotEqualTo(commissionStatusDTO2);
        commissionStatusDTO2.setId(commissionStatusDTO1.getId());
        assertThat(commissionStatusDTO1).isEqualTo(commissionStatusDTO2);
        commissionStatusDTO2.setId(2L);
        assertThat(commissionStatusDTO1).isNotEqualTo(commissionStatusDTO2);
        commissionStatusDTO1.setId(null);
        assertThat(commissionStatusDTO1).isNotEqualTo(commissionStatusDTO2);
    }
}
