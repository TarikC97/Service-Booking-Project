package com.tarikc.ServiceBookingSystem.Services.Authentication;

import com.tarikc.ServiceBookingSystem.Dto.SignupRequestDto;
import com.tarikc.ServiceBookingSystem.Dto.UserDto;
import com.tarikc.ServiceBookingSystem.Entity.User;
import com.tarikc.ServiceBookingSystem.Enum.UserRole;
import com.tarikc.ServiceBookingSystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;

    public UserDto signupClient(SignupRequestDto signupRequestDto){
        User user = new User();
        user.setName(signupRequestDto.getName());
        user.setName(signupRequestDto.getLastname());
        user.setName(signupRequestDto.getEmail());
        user.setName(signupRequestDto.getPhone());
        user.setName(signupRequestDto.getPassword());
        user.setRole(UserRole.CLIENT);
        return  userRepository.save(user).getDto();
    }

    public Boolean presentByEmail(String email){
        return  userRepository.findFirstByEmail(email)!=null;
    }

    public UserDto signupCompany(SignupRequestDto signupRequestDto){
        User user = new User();
        user.setName(signupRequestDto.getName());
        user.setName(signupRequestDto.getEmail());
        user.setName(signupRequestDto.getPhone());
        user.setName(signupRequestDto.getPassword());
        user.setRole(UserRole.COMPANY);
        return  userRepository.save(user).getDto();
    }


}
