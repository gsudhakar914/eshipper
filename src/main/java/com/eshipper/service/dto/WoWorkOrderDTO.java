package com.eshipper.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.eshipper.domain.WoWorkOrder} entity.
 */
public class WoWorkOrderDTO implements Serializable {
    
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
        if (!(o instanceof WoWorkOrderDTO)) {
            return false;
        }

        return id != null && id.equals(((WoWorkOrderDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WoWorkOrderDTO{" +
            "id=" + getId() +
            ", commissionStatusId=" + getCommissionStatusId() +
            "}";
    }
}
