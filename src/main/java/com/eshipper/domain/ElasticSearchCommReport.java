package com.eshipper.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A ElasticSearchCommReport.
 */
@Entity
@Table(name = "elastic_search_comm_report")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ElasticSearchCommReport implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnoreProperties(value = "elasticSearchCommReports", allowSetters = true)
    private ElasticStatus status;

    @OneToOne(mappedBy = "elasticSearchCommReport")
    @JsonIgnore
    private CommissionReport commissionReport;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ElasticStatus getStatus() {
        return status;
    }

    public ElasticSearchCommReport status(ElasticStatus elasticStatus) {
        this.status = elasticStatus;
        return this;
    }

    public void setStatus(ElasticStatus elasticStatus) {
        this.status = elasticStatus;
    }

    public CommissionReport getCommissionReport() {
        return commissionReport;
    }

    public ElasticSearchCommReport commissionReport(CommissionReport commissionReport) {
        this.commissionReport = commissionReport;
        return this;
    }

    public void setCommissionReport(CommissionReport commissionReport) {
        this.commissionReport = commissionReport;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ElasticSearchCommReport)) {
            return false;
        }
        return id != null && id.equals(((ElasticSearchCommReport) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ElasticSearchCommReport{" +
            "id=" + getId() +
            "}";
    }
}
