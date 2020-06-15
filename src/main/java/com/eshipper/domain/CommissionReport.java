package com.eshipper.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A CommissionReport.
 */
@Entity
@Table(name = "commission_report")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CommissionReport implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cut_off_date")
    private LocalDate cutOffDate;

    @Column(name = "additional_comment")
    private String additionalComment;

    @Column(name = "created")
    private LocalDate created;

    @Column(name = "work_orders")
    private Integer workOrders;

    @Column(name = "total_amount")
    private Float totalAmount;

    @ManyToOne
    @JsonIgnoreProperties(value = "commissionReports", allowSetters = true)
    private WoSalesAgent woSalesAgent;

    @ManyToOne
    @JsonIgnoreProperties(value = "commissionReports", allowSetters = true)
    private CommissionReportStatus commissionReportStatus;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCutOffDate() {
        return cutOffDate;
    }

    public CommissionReport cutOffDate(LocalDate cutOffDate) {
        this.cutOffDate = cutOffDate;
        return this;
    }

    public void setCutOffDate(LocalDate cutOffDate) {
        this.cutOffDate = cutOffDate;
    }

    public String getAdditionalComment() {
        return additionalComment;
    }

    public CommissionReport additionalComment(String additionalComment) {
        this.additionalComment = additionalComment;
        return this;
    }

    public void setAdditionalComment(String additionalComment) {
        this.additionalComment = additionalComment;
    }

    public LocalDate getCreated() {
        return created;
    }

    public CommissionReport created(LocalDate created) {
        this.created = created;
        return this;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public Integer getWorkOrders() {
        return workOrders;
    }

    public CommissionReport workOrders(Integer workOrders) {
        this.workOrders = workOrders;
        return this;
    }

    public void setWorkOrders(Integer workOrders) {
        this.workOrders = workOrders;
    }

    public Float getTotalAmount() {
        return totalAmount;
    }

    public CommissionReport totalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }

    public void setTotalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public WoSalesAgent getWoSalesAgent() {
        return woSalesAgent;
    }

    public CommissionReport woSalesAgent(WoSalesAgent woSalesAgent) {
        this.woSalesAgent = woSalesAgent;
        return this;
    }

    public void setWoSalesAgent(WoSalesAgent woSalesAgent) {
        this.woSalesAgent = woSalesAgent;
    }

    public CommissionReportStatus getCommissionReportStatus() {
        return commissionReportStatus;
    }

    public CommissionReport commissionReportStatus(CommissionReportStatus commissionReportStatus) {
        this.commissionReportStatus = commissionReportStatus;
        return this;
    }

    public void setCommissionReportStatus(CommissionReportStatus commissionReportStatus) {
        this.commissionReportStatus = commissionReportStatus;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CommissionReport)) {
            return false;
        }
        return id != null && id.equals(((CommissionReport) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CommissionReport{" +
            "id=" + getId() +
            ", cutOffDate='" + getCutOffDate() + "'" +
            ", additionalComment='" + getAdditionalComment() + "'" +
            ", created='" + getCreated() + "'" +
            ", workOrders=" + getWorkOrders() +
            ", totalAmount=" + getTotalAmount() +
            "}";
    }
}
