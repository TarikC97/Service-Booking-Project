package com.tarikc.ServiceBookingSystem.Services.Company;

import com.tarikc.ServiceBookingSystem.Dto.AdDto;
import com.tarikc.ServiceBookingSystem.Entity.Ad;
import com.tarikc.ServiceBookingSystem.Entity.User;
import com.tarikc.ServiceBookingSystem.Repository.AdRepository;
import com.tarikc.ServiceBookingSystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements  CompanyService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdRepository adRepository;

    public boolean postAd(Long userId, AdDto adDto) throws IOException {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()){
            Ad ad = new Ad();
            ad.setServiceName(adDto.getServiceName());
            ad.setDescription(adDto.getDescription());
            ad.setImg(adDto.getImg().getBytes());
            ad.setPrice(adDto.getPrice());
            ad.setUser(optionalUser.get());

            adRepository.save(ad);
            return  true;
        }
        return  false;
    }


}
