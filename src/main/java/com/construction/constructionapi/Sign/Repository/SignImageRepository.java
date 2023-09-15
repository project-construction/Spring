package com.construction.constructionapi.Sign.Repository;

import com.construction.constructionapi.Sign.Domain.SignImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SignImageRepository extends JpaRepository<SignImage, Long> {
    List<SignImage> findAll();

}
