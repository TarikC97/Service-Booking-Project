package com.tarikc.ServiceBookingSystem.Controller;

import com.tarikc.ServiceBookingSystem.Dto.AdDto;
import com.tarikc.ServiceBookingSystem.Services.Company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping("/ad/{userId}")
    public ResponseEntity<?> postAd(@PathVariable Long userId, @ModelAttribute AdDto adDto) throws IOException {
        boolean success = companyService.postAd(userId,adDto);
        if(success){
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        else{
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/ads/{userId}")
    public  ResponseEntity<?> getAllAdsById(@PathVariable Long userId){
        return ResponseEntity.ok(companyService.getAllAds(userId));
    }

}
