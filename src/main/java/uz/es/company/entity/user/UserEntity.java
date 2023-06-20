package uz.es.company.entity.user;

import jakarta.persistence.*;
import lombok.*;
import uz.es.entity.BaseEntity;
import uz.es.entity.company.CompanyEntity;
import uz.es.entity.user.role.UserRole;

@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class UserEntity extends BaseEntity {
    @Column(unique = true, nullable = false)
    private String passportNumber;

    private String surname;

    private String password;

    private String name;

    private String fatherName;

    private String position;

    private String phoneNumber;

    private String address;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @ManyToOne
    @JoinColumn(name = "company_name")
    private CompanyEntity company;
}
