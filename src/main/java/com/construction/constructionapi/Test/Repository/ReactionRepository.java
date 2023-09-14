package com.construction.constructionapi.Test.Repository;

import com.construction.constructionapi.Test.Domain.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReactionRepository extends JpaRepository<Reaction, Long> {
    Reaction findByUserIdAndDate(String userId, String date);


}
