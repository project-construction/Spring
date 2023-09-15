package com.construction.constructionapi.Sign.Repository;

import com.construction.constructionapi.Sign.Domain.SelectedPDF;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SelectedRepository extends JpaRepository<SelectedPDF, Long> {

}
