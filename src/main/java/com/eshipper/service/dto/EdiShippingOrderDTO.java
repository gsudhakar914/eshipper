package com.eshipper.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.eshipper.domain.EdiShippingOrder} entity.
 */
public class EdiShippingOrderDTO implements Serializable {
    
    private Long id;


    private Long commissionStatusId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCommissionStatusId() {
        return commissionStatusId;
    }

    public void setCommissionStatusId(Long commissionStatusId) {
        this.commissionStatusId = commissionStatusId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EdiShippingOrderDTO)) {
            return false;
        }

        return id != null && id.equals(((EdiShippingOrderDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EdiShippingOrderDTO{" +
            "id=" + getId() +
            ", commissionStatusId=" + getCommissionStatusId() +
            "}";
    }
}
