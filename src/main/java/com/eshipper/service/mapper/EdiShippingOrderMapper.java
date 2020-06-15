package com.eshipper.service.mapper;


import com.eshipper.domain.*;
import com.eshipper.service.dto.EdiShippingOrderDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EdiShippingOrder} and its DTO {@link EdiShippingOrderDTO}.
 */
@Mapper(componentModel = "spring", uses = {CommissionStatusMapper.class})
public interface EdiShippingOrderMapper extends EntityMapper<EdiShippingOrderDTO, EdiShippingOrder> {

    @Mapping(source = "commissionStatus.id", target = "commissionStatusId")
    EdiShippingOrderDTO toDto(EdiShippingOrder ediShippingOrder);

    @Mapping(source = "commissionStatusId", target = "commissionStatus")
    EdiShippingOrder toEntity(EdiShippingOrderDTO ediShippingOrderDTO);

    default EdiShippingOrder fromId(Long id) {
        if (id == null) {
            return null;
        }
        EdiShippingOrder ediShippingOrder = new EdiShippingOrder();
        ediShippingOrder.setId(id);
        return ediShippingOrder;
    }
}
