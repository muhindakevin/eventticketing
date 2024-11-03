package bk.rw.eventticketing.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import bk.rw.eventticketing.model.User;

public interface UserRepository extends JpaRepository<User,Long> {
    Boolean existsByEmail(String email);
    void deleteByEmail(String email);
    Optional<User>findByEmail(String email);

}
