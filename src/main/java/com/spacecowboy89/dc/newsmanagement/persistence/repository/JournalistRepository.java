package com.spacecowboy89.dc.newsmanagement.persistence.repository;

import com.spacecowboy89.dc.newsmanagement.persistence.entity.Journalist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JournalistRepository extends JpaRepository<Journalist, String> {
}
