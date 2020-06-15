package com.eshipper.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class CommissionReportDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CommissionReportDTO.class);
        CommissionReportDTO commissionReportDTO1 = new CommissionReportDTO();
        commissionReportDTO1.setId(1L);
        CommissionReportDTO commissionReportDTO2 = new CommissionReportDTO();
        assertThat(commissionReportDTO1).isNotEqualTo(commissionReportDTO2);
        commissionReportDTO2.setId(commissionReportDTO1.getId());
        assertThat(commissionReportDTO1).isEqualTo(commissionReportDTO2);
        commissionReportDTO2.setId(2L);
        assertThat(commissionReportDTO1).isNotEqualTo(commissionReportDTO2);
        commissionReportDTO1.setId(null);
        assertThat(commissionReportDTO1).isNotEqualTo(commissionReportDTO2);
    }
}
