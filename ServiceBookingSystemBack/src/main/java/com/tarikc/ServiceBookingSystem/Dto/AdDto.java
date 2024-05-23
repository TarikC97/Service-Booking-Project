package com.tarikc.ServiceBookingSystem.Dto;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import lombok.Data;

@Data
public class AdDto {

    private Long id;
    private String serviceName;
    private String description;
    private Double price;
    private byte[] img;
    private  Long userId;
    private String companyName;

}
