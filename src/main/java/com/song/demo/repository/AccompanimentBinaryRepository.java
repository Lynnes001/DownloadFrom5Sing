package com.song.demo.repository;

import com.song.demo.entity.AccompanimentBinaryArchive;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccompanimentBinaryRepository extends JpaRepository<AccompanimentBinaryArchive, String> {
}
