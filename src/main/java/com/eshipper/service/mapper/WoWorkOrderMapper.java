package com.eshipper.service.mapper;


import com.eshipper.domain.*;
import com.eshipper.service.dto.WoWorkOrderDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link WoWorkOrder} and its DTO {@link WoWorkOrderDTO}.
 */
@Mapper(componentModel = "spring", uses = {CommissionStatusMapper.class})
public interface WoWorkOrderMapper extends EntityMapper<WoWorkOrderDTO, WoWorkOrder> {

    @Mapping(source = "commissionStatus.id", target = "commissionStatusId")
    WoWorkOrderDTO toDto(WoWorkOrder woWorkOrder);

    @Mapping(source = "commissionStatusId", target = "commissionStatus")
    WoWorkOrder toEntity(WoWorkOrderDTO woWorkOrderDTO);

    default WoWorkOrder fromId(Long id) {
        if (id == null) {
            return null;
        }
        WoWorkOrder woWorkOrder = new WoWorkOrder();
        woWorkOrder.setId(id);
        return woWorkOrder;
    }
}
