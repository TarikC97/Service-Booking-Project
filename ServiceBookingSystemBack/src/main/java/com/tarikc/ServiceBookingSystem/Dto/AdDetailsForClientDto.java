package com.tarikc.ServiceBookingSystem.Dto;

import lombok.Data;

import java.util.List;

@Data
public class AdDetailsForClientDto {
    private AdDto adDto;

    private List<ReviewDto> reviewDtoList;
}
