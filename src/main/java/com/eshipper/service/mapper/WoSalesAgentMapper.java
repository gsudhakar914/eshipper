package com.eshipper.service.mapper;


import com.eshipper.domain.*;
import com.eshipper.service.dto.WoSalesAgentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link WoSalesAgent} and its DTO {@link WoSalesAgentDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WoSalesAgentMapper extends EntityMapper<WoSalesAgentDTO, WoSalesAgent> {



    default WoSalesAgent fromId(Long id) {
        if (id == null) {
            return null;
        }
        WoSalesAgent woSalesAgent = new WoSalesAgent();
        woSalesAgent.setId(id);
        return woSalesAgent;
    }
}
