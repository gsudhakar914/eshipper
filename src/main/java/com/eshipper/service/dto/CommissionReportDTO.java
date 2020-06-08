package com.eshipper.service.dto;

import java.time.LocalDate;
import java.io.Serializable;

/**
 * A DTO for the {@link com.eshipper.domain.CommissionReport} entity.
 */
public class CommissionReportDTO implements Serializable {
    
    private Long id;

    private LocalDate cutOffDate;

    private String additionalComment;

    private LocalDate created;

    private Integer workOrders;

    private Float totalAmount;


    private Long woSalesAgentId;

    private Long commissionReportStatusId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCutOffDate() {
        return cutOffDate;
    }

    public void setCutOffDate(LocalDate cutOffDate) {
        this.cutOffDate = cutOffDate;
    }

    public String getAdditionalComment() {
        return additionalComment;
    }

    public void setAdditionalComment(String additionalComment) {
        this.additionalComment = additionalComment;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public Integer getWorkOrders() {
        return workOrders;
    }

    public void setWorkOrders(Integer workOrders) {
        this.workOrders = workOrders;
    }

    public Float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Long getWoSalesAgentId() {
        return woSalesAgentId;
    }

    public void setWoSalesAgentId(Long woSalesAgentId) {
        this.woSalesAgentId = woSalesAgentId;
    }

    public Long getCommissionReportStatusId() {
        return commissionReportStatusId;
    }

    public void setCommissionReportStatusId(Long commissionReportStatusId) {
        this.commissionReportStatusId = commissionReportStatusId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CommissionReportDTO)) {
            return false;
        }

        return id != null && id.equals(((CommissionReportDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CommissionReportDTO{" +
            "id=" + getId() +
            ", cutOffDate='" + getCutOffDate() + "'" +
            ", additionalComment='" + getAdditionalComment() + "'" +
            ", created='" + getCreated() + "'" +
            ", workOrders=" + getWorkOrders() +
            ", totalAmount=" + getTotalAmount() +
            ", woSalesAgentId=" + getWoSalesAgentId() +
            ", commissionReportStatusId=" + getCommissionReportStatusId() +
            "}";
    }
}
