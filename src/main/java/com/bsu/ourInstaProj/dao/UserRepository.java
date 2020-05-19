package com.bsu.ourInstaProj.dao;

import com.bsu.ourInstaProj.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

    User getByUsername(String username);

    User getById(Long id);
}
