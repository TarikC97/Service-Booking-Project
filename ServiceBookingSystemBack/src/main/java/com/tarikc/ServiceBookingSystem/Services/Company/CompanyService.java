package com.tarikc.ServiceBookingSystem.Services.Company;

import com.tarikc.ServiceBookingSystem.Dto.AdDto;
import com.tarikc.ServiceBookingSystem.Dto.ReservationDto;

import java.io.IOException;
import java.util.List;

public interface CompanyService {

    boolean postAd(Long userId, AdDto adDto) throws IOException;
    List<AdDto> getAllAds(Long userId);
    AdDto getAdById(Long adId);
    boolean updateAd(Long adId,AdDto adDto) throws IOException;
    boolean deleteAd(Long adId);
    List<ReservationDto> getAllAdBookings(Long companyId);
    boolean changeBookingStatus(Long bookingId,String status);

}
