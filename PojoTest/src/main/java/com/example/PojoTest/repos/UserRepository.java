package com.example.PojoTest.repos;

import com.example.PojoTest.modles.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
