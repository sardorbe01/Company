package uz.es.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.es.company.entity.user.UserEntity;

import java.security.Principal;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
Optional<UserEntity> findUserEntitiesByPhoneNumber(String phoneNumber);



}
