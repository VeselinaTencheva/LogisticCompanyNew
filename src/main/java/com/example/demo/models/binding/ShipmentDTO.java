package com.example.demo.models.binding;

import com.example.demo.entities.Customer;
import com.example.demo.entities.Employee;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.List;

public class ShipmentDTO {
    private String id;
    private String address;
    private double weight;
    private double price;
    private String recipientId;
    private String senderId;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @NotBlank(message = " - Error: Address must not be blank!")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @NotNull(message = " - Error: Weight cannot be null")
    @Positive(message = " - Error: Minimal weight must be greater than 0")
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @NotNull(message = " - Error: Price must not be null")
    @Min(message = " - Error: Minimal price is set to 1",value = 1)
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(String recipientId) {
        this.recipientId = recipientId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }


}
