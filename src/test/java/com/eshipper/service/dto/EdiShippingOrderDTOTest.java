package com.eshipper.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class EdiShippingOrderDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EdiShippingOrderDTO.class);
        EdiShippingOrderDTO ediShippingOrderDTO1 = new EdiShippingOrderDTO();
        ediShippingOrderDTO1.setId(1L);
        EdiShippingOrderDTO ediShippingOrderDTO2 = new EdiShippingOrderDTO();
        assertThat(ediShippingOrderDTO1).isNotEqualTo(ediShippingOrderDTO2);
        ediShippingOrderDTO2.setId(ediShippingOrderDTO1.getId());
        assertThat(ediShippingOrderDTO1).isEqualTo(ediShippingOrderDTO2);
        ediShippingOrderDTO2.setId(2L);
        assertThat(ediShippingOrderDTO1).isNotEqualTo(ediShippingOrderDTO2);
        ediShippingOrderDTO1.setId(null);
        assertThat(ediShippingOrderDTO1).isNotEqualTo(ediShippingOrderDTO2);
    }
}
