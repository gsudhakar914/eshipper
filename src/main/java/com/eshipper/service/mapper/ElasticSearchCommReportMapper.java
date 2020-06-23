package com.eshipper.service.mapper;


import com.eshipper.domain.*;
import com.eshipper.service.dto.ElasticSearchCommReportDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ElasticSearchCommReport} and its DTO {@link ElasticSearchCommReportDTO}.
 */
@Mapper(componentModel = "spring", uses = {ElasticStatusMapper.class})
public interface ElasticSearchCommReportMapper extends EntityMapper<ElasticSearchCommReportDTO, ElasticSearchCommReport> {

    @Mapping(source = "status.id", target = "statusId")
    ElasticSearchCommReportDTO toDto(ElasticSearchCommReport elasticSearchCommReport);

    @Mapping(source = "statusId", target = "status")
    @Mapping(target = "commissionReport", ignore = true)
    ElasticSearchCommReport toEntity(ElasticSearchCommReportDTO elasticSearchCommReportDTO);

    default ElasticSearchCommReport fromId(Long id) {
        if (id == null) {
            return null;
        }
        ElasticSearchCommReport elasticSearchCommReport = new ElasticSearchCommReport();
        elasticSearchCommReport.setId(id);
        return elasticSearchCommReport;
    }
}
