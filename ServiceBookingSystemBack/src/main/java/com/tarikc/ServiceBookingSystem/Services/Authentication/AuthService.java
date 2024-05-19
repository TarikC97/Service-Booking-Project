package com.tarikc.ServiceBookingSystem.Services.Authentication;

import com.tarikc.ServiceBookingSystem.Dto.SignupRequestDto;
import com.tarikc.ServiceBookingSystem.Dto.UserDto;

public interface AuthService {
    UserDto signupClient(SignupRequestDto signupRequestDto);
    Boolean presentByEmail(String email);
    UserDto signupCompany(SignupRequestDto signupRequestDto);
}
