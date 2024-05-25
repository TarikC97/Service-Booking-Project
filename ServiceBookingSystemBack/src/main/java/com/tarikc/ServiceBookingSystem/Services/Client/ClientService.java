package com.tarikc.ServiceBookingSystem.Services.Client;

import com.tarikc.ServiceBookingSystem.Dto.AdDto;

import java.util.List;

public interface ClientService {
    List<AdDto> getAllAds();
    List<AdDto> SearchAdByName(String name);
}
