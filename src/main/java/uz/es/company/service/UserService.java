package uz.es.company.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.es.company.dto.request.UserRequestDto;
import uz.es.company.entity.user.UserEntity;
import uz.es.company.entity.user.role.UserRole;
import uz.es.company.exception.DataNotFoundException;
import uz.es.company.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserEntity save(UserRequestDto userRequestDto, List<UserRole> roles) {
        UserEntity user = modelMapper.map(userRequestDto, UserEntity.class);
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user;
    }
    public Optional<UserEntity> getById(UUID userId){
        return Optional.ofNullable(userRepository.findById(userId).orElseThrow(
                () -> new DataNotFoundException("User not found")
        ));
    }
    public UserEntity update(UserRequestDto userRequestDto, UUID userId){
        UserEntity user = userRepository.findById(userId).orElseThrow(
                ()-> new DataNotFoundException("User not found"));
        modelMapper.map(userRequestDto,user);
        return userRepository.save(user);
    }
    public void deleteById(UUID userId){
        userRepository.deleteById(userId);
    }

}
