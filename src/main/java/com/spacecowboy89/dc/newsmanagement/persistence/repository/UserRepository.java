package com.spacecowboy89.dc.newsmanagement.persistence.repository;

import com.spacecowboy89.dc.newsmanagement.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository  <User,Long>{

    public Optional<User> findByUserCode(String userCode);

    public boolean existsByUserCode(String userCode);

    public Optional<List<User>>findByDeletedAtIsNotNull();
}
