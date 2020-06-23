package com.eshipper.service.mapper;


import com.eshipper.domain.*;
import com.eshipper.service.dto.CommissionReportDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CommissionReport} and its DTO {@link CommissionReportDTO}.
 */
@Mapper(componentModel = "spring", uses = {ElasticSearchCommReportMapper.class, WoSalesAgentMapper.class, CommissionReportStatusMapper.class})
public interface CommissionReportMapper extends EntityMapper<CommissionReportDTO, CommissionReport> {

    @Mapping(source = "elasticSearchCommReport.id", target = "elasticSearchCommReportId")
    @Mapping(source = "woSalesAgent.id", target = "woSalesAgentId")
    @Mapping(source = "commissionReportStatus.id", target = "commissionReportStatusId")
    CommissionReportDTO toDto(CommissionReport commissionReport);

    @Mapping(source = "elasticSearchCommReportId", target = "elasticSearchCommReport")
    @Mapping(source = "woSalesAgentId", target = "woSalesAgent")
    @Mapping(source = "commissionReportStatusId", target = "commissionReportStatus")
    CommissionReport toEntity(CommissionReportDTO commissionReportDTO);

    default CommissionReport fromId(Long id) {
        if (id == null) {
            return null;
        }
        CommissionReport commissionReport = new CommissionReport();
        commissionReport.setId(id);
        return commissionReport;
    }
}
