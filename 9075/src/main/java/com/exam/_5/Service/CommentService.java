package com.exam._5.Service;

import com.exam._5.Models.Comment;
import com.exam._5.Repository.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    private CommentRepo commentRepo;
    public Comment saveComment(Comment comment){
        return commentRepo.save(comment);
    }
}
