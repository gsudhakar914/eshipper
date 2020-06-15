package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EdiShippingOrderMapperTest {

    private EdiShippingOrderMapper ediShippingOrderMapper;

    @BeforeEach
    public void setUp() {
        ediShippingOrderMapper = new EdiShippingOrderMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(ediShippingOrderMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(ediShippingOrderMapper.fromId(null)).isNull();
    }
}
