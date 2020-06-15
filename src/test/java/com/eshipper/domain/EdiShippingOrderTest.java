package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class EdiShippingOrderTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EdiShippingOrder.class);
        EdiShippingOrder ediShippingOrder1 = new EdiShippingOrder();
        ediShippingOrder1.setId(1L);
        EdiShippingOrder ediShippingOrder2 = new EdiShippingOrder();
        ediShippingOrder2.setId(ediShippingOrder1.getId());
        assertThat(ediShippingOrder1).isEqualTo(ediShippingOrder2);
        ediShippingOrder2.setId(2L);
        assertThat(ediShippingOrder1).isNotEqualTo(ediShippingOrder2);
        ediShippingOrder1.setId(null);
        assertThat(ediShippingOrder1).isNotEqualTo(ediShippingOrder2);
    }
}
