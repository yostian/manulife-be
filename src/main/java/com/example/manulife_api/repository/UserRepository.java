package com.example.manulife_api.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.manulife_api.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByJenisUser(String jenisUser);
}
