package com.tarikc.ServiceBookingSystem.Services.Company;

import com.tarikc.ServiceBookingSystem.Dto.AdDto;
import com.tarikc.ServiceBookingSystem.Entity.Ad;
import com.tarikc.ServiceBookingSystem.Entity.User;
import com.tarikc.ServiceBookingSystem.Repository.AdRepository;
import com.tarikc.ServiceBookingSystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<AdDto> getAllAds(Long userId){
        return adRepository.findAllByUserId(userId).stream().map(Ad::getAdDto).collect(Collectors.toList());
    }
    public AdDto getAdById(Long adId){
        Optional<Ad> optionalAd = adRepository.findById(adId);
        return optionalAd.map(Ad::getAdDto).orElse(null);
    }
    public boolean updateAd(Long adId,AdDto adDto) throws IOException {
        Optional<Ad> optionalAd = adRepository.findById(adId);
        if(optionalAd.isPresent()){
            Ad ad = optionalAd.get();
            ad.setServiceName(adDto.getServiceName());
            ad.setDescription(adDto.getDescription());
            ad.setPrice(adDto.getPrice());
            if(adDto.getImg() != null){
                ad.setImg(adDto.getImg().getBytes());
            }
            adRepository.save(ad);
            return true;
        }
        else{
            return  false;
        }
    }
    public  boolean deleteAd(Long adId){
        Optional<Ad> optionalAd = adRepository.findById(adId);
        if(optionalAd.isPresent()){
            adRepository.delete(optionalAd.get());
            return  true;
        }
        return false;
    }
}
