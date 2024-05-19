package com.tarikc.ServiceBookingSystem.Controller;

import com.tarikc.ServiceBookingSystem.Dto.SignupRequestDto;
import com.tarikc.ServiceBookingSystem.Dto.UserDto;
import com.tarikc.ServiceBookingSystem.Services.Authentication.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthService authService;

    @PostMapping("/client/sign-up")
    public ResponseEntity<?> signupClient(@RequestBody SignupRequestDto signupRequestDto){
        if(authService.presentByEmail(signupRequestDto.getEmail())){
            return new ResponseEntity<>("Client already exists with this Email!", HttpStatus.NOT_ACCEPTABLE);
        }
        UserDto createdUser = authService.signupClient(signupRequestDto);
        return new ResponseEntity<>(createdUser,HttpStatus.OK);
    }

    @PostMapping("/company/sign-up")
    public ResponseEntity<?> signupCompany(@RequestBody SignupRequestDto signupRequestDto){
        if(authService.presentByEmail(signupRequestDto.getEmail())){
            return new ResponseEntity<>("Company already exists with this Email!", HttpStatus.NOT_ACCEPTABLE);
        }
        UserDto createdUser = authService.signupClient(signupRequestDto);
        return new ResponseEntity<>(createdUser,HttpStatus.OK);
    }

}
