package uz.es.company.entity.user;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.es.company.entity.BaseEntity;
import uz.es.company.entity.company.CompanyEntity;
import uz.es.company.entity.user.role.UserRole;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class UserEntity extends BaseEntity implements UserDetails {
//    @Column(unique = true, nullable = false)
    private String passportNumber;

    private String surname;

    private String password;

    private String name;

    private String fatherName;

    private String position;

    private String phoneNumber;

    private String address;

    @Enumerated(EnumType.STRING)
    private List<UserRole> roles;

    @ManyToOne
    @JoinColumn(name = "company_name")
    private CompanyEntity company;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String ROLE = "ROLE_";
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (UserRole role : roles) {
            authorities.add(new SimpleGrantedAuthority(ROLE + role.name()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return phoneNumber;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
