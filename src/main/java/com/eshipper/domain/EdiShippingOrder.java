package com.eshipper.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A EdiShippingOrder.
 */
@Entity
@Table(name = "edi_shipping_order")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EdiShippingOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnoreProperties(value = "ediShippingOrders", allowSetters = true)
    private CommissionStatus commissionStatus;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CommissionStatus getCommissionStatus() {
        return commissionStatus;
    }

    public EdiShippingOrder commissionStatus(CommissionStatus commissionStatus) {
        this.commissionStatus = commissionStatus;
        return this;
    }

    public void setCommissionStatus(CommissionStatus commissionStatus) {
        this.commissionStatus = commissionStatus;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EdiShippingOrder)) {
            return false;
        }
        return id != null && id.equals(((EdiShippingOrder) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EdiShippingOrder{" +
            "id=" + getId() +
            "}";
    }
}
