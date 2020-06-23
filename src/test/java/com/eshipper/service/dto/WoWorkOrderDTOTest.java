package com.eshipper.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class WoWorkOrderDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(WoWorkOrderDTO.class);
        WoWorkOrderDTO woWorkOrderDTO1 = new WoWorkOrderDTO();
        woWorkOrderDTO1.setId(1L);
        WoWorkOrderDTO woWorkOrderDTO2 = new WoWorkOrderDTO();
        assertThat(woWorkOrderDTO1).isNotEqualTo(woWorkOrderDTO2);
        woWorkOrderDTO2.setId(woWorkOrderDTO1.getId());
        assertThat(woWorkOrderDTO1).isEqualTo(woWorkOrderDTO2);
        woWorkOrderDTO2.setId(2L);
        assertThat(woWorkOrderDTO1).isNotEqualTo(woWorkOrderDTO2);
        woWorkOrderDTO1.setId(null);
        assertThat(woWorkOrderDTO1).isNotEqualTo(woWorkOrderDTO2);
    }
}
