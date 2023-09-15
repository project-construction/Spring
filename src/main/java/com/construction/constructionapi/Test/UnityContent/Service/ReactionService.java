package com.construction.constructionapi.Test.UnityContent.Service;

import com.construction.constructionapi.Test.UnityContent.DTO.TestDTO.ResponseReactionDTO;
import com.construction.constructionapi.Test.UnityContent.DTO.TestDTO.TestAndReactionAllDTO;
import com.construction.constructionapi.Test.UnityContent.DTO.TestDTO.TestScoreDTO;
import com.construction.constructionapi.Test.UnityContent.Service.DAO.ReactionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReactionService {
    ReactionDAO reactionDAO;
    @Autowired
    public ReactionService(ReactionDAO reactionDAO){
        this.reactionDAO = reactionDAO;
    }

    public void saveReaction(String email, TestScoreDTO testScoreDTO){
        reactionDAO.saveReaction(email, testScoreDTO);
    }

    public ResponseReactionDTO getAllReaction(String email){
        return reactionDAO.getAllReaction(email);
    }

}
