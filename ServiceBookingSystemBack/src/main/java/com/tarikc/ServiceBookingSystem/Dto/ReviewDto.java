package com.tarikc.ServiceBookingSystem.Dto;

import lombok.Data;

import java.util.Date;

@Data
public class ReviewDto {

    private Long id;

    private Date reviewDate;

    private String review;

    private Long rating;

    private Long userId;

    private Long adId;

    private String clientName;

    private String serviceName;

    private Long bookId;
}
