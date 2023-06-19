package uz.es.company.service.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.es.company.exception.DataNotFoundException;
import uz.es.company.repository.UserRepository;


@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService   {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {

        return userRepository.findUserEntitiesByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new DataNotFoundException("User not found")
                );
    }
}
