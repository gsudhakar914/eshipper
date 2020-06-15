package com.eshipper.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.eshipper.web.rest.TestUtil;

public class WoWorkOrderTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WoWorkOrder.class);
        WoWorkOrder woWorkOrder1 = new WoWorkOrder();
        woWorkOrder1.setId(1L);
        WoWorkOrder woWorkOrder2 = new WoWorkOrder();
        woWorkOrder2.setId(woWorkOrder1.getId());
        assertThat(woWorkOrder1).isEqualTo(woWorkOrder2);
        woWorkOrder2.setId(2L);
        assertThat(woWorkOrder1).isNotEqualTo(woWorkOrder2);
        woWorkOrder1.setId(null);
        assertThat(woWorkOrder1).isNotEqualTo(woWorkOrder2);
    }
}
