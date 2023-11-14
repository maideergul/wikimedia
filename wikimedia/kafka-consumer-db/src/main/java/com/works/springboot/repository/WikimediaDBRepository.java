package com.works.springboot.repository;

import com.works.springboot.entity.WikimediaDB;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WikimediaDBRepository extends JpaRepository<WikimediaDB,Long> {
}
