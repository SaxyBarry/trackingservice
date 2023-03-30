package edu.iu.c322.trackingservice.model;

import jakarta.validation.constraints.NotEmpty;

public class UpdateRequest {
    @NotEmpty(message = "Reason cannot be empty")
    private String status;
    private int itemId;


    public String getStatus() {
        return status;
    }

    public void setStatus(String reason) {
        this.status = reason;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
}
