package com.eshipper.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class WoWorkOrderMapperTest {

    private WoWorkOrderMapper woWorkOrderMapper;

    @BeforeEach
    public void setUp() {
        woWorkOrderMapper = new WoWorkOrderMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(woWorkOrderMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(woWorkOrderMapper.fromId(null)).isNull();
    }
}
