package com.eshipper.service.mapper;


import com.eshipper.domain.*;
import com.eshipper.service.dto.CommissionStatusDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CommissionStatus} and its DTO {@link CommissionStatusDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CommissionStatusMapper extends EntityMapper<CommissionStatusDTO, CommissionStatus> {



    default CommissionStatus fromId(Long id) {
        if (id == null) {
            return null;
        }
        CommissionStatus commissionStatus = new CommissionStatus();
        commissionStatus.setId(id);
        return commissionStatus;
    }
}
