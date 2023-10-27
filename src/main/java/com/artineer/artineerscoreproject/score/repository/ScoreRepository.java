package com.artineer.artineerscoreproject.score.repository;

import com.artineer.artineerscoreproject.score.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ScoreRepository extends JpaRepository<Score, Long> {

    Optional<Score> findByName(String name);

    boolean existsByName(String name);

}
