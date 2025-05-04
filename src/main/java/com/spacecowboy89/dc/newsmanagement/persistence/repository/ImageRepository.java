package com.spacecowboy89.dc.newsmanagement.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.spacecowboy89.dc.newsmanagement.persistence.entity.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image,Long> {}

