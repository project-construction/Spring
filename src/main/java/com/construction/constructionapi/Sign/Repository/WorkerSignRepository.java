package com.construction.constructionapi.Sign.Repository;

import com.construction.constructionapi.Sign.Domain.workerSignedImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerSignRepository extends JpaRepository<workerSignedImage, Long> {

}
