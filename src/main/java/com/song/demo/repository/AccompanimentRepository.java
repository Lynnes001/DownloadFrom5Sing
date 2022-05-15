package com.song.demo.repository;

import com.song.demo.entity.Accompaniment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccompanimentRepository extends JpaRepository<Accompaniment, String> {
    Accompaniment findBySearchKeyword(String searchKeyword);
}
