package uz.es.company.entity.company;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import uz.es.entity.BaseEntity;

@Entity(name = "company")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CompanyEntity extends BaseEntity {
    @Column(unique = true, nullable = false)
    private String name;
    @Column(nullable = false)
    private String directorFullName;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String website;
    @Column(nullable = false)
    private String phoneNumber;

}
