package com.construction.constructionapi.Check.Repository;

import com.construction.constructionapi.Check.Domain.Score;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ScoreRepository extends JpaRepository<Score, String> {
    boolean existsByUserIdAndDate(String userId, Date date);

    Score findByUserId(String userId);

    Score findByUserIdAndDate(String userId, String date);
}
