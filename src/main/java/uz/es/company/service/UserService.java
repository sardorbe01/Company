package uz.es.company.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.es.company.dto.request.LoginRequestDto;
import uz.es.company.dto.request.UserRequestDto;
import uz.es.company.dto.response.JwtResponse;
import uz.es.company.entity.user.UserEntity;
import uz.es.company.entity.user.role.UserRole;
import uz.es.company.exception.DataNotFoundException;
import uz.es.company.repository.UserRepository;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserEntity save(UserRequestDto userRequestDto, List<UserRole> roles) {
        UserEntity user = modelMapper.map(userRequestDto, UserEntity.class);
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);

    }

    public JwtResponse login(LoginRequestDto login) {
        UserEntity userEntity = userRepository.findUserEntitiesByPhoneNumber(login.getPhoneNumber())
                .orElseThrow(
                        () -> new DataNotFoundException("user not found")
                );

        if (passwordEncoder.matches(login.getPassword(), userEntity.getPassword())) {
            String accessToken = jwtService.generateAccessToken(userEntity);
            return JwtResponse.builder().accessToken(accessToken).build();
        }
        throw new DataNotFoundException("user not found");
    }

    public UserEntity getById(UUID userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new DataNotFoundException("User not found")
        );
    }

    public UserEntity update(UserRequestDto userRequestDto, Principal principal) {
        UserEntity user = userRepository.findUserEntitiesByPhoneNumber(principal.getName()).orElseThrow(
                () -> new DataNotFoundException("User not found"));
        modelMapper.map(userRequestDto, user);
        return userRepository.save(user);
    }

    public void deleteById(UUID userId) {
        userRepository.deleteById(userId);
    }

}
