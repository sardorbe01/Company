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

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String fatherName;

    @Column(nullable = false)
    private String position;

    @Column(unique = true, nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String address;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @ManyToOne
    @JoinColumn(name = "company_name")
    @Column(nullable = false)
    private CompanyEntity company;
}
