package com.rungroup.web.repository;

import com.rungroup.web.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClubRepository extends JpaRepository<Club, Long> {
    Optional<Club> findByPhotoUrl(String url);
}
