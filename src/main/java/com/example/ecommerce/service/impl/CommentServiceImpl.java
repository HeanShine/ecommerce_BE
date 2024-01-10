package com.example.ecommerce.service.impl;

import com.example.ecommerce.model.Account;
import com.example.ecommerce.model.Comment;
import com.example.ecommerce.model.dto.CommentResponse;
import com.example.ecommerce.repository.ICommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl {
    @Autowired
    private ICommentRepo commentRepo;

    @Autowired
    private AccountServiceImpl accountService;

    @Autowired
    private ProductServiceImpl productService;

    public CommentResponse convertCommentToCommentResponse(Comment comment){
        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setId(comment.getId());
        commentResponse.setContent(comment.getContent());
        commentResponse.setDate(comment.getDate());
        commentResponse.setName(comment.getAccount().getName());
        commentResponse.setAvatar(comment.getAccount().getAvatar());
        commentResponse.setProductId(comment.getProduct().getId());
        return commentResponse;
    }

    public List<CommentResponse> findCommentByProductId(Integer id){
        List<Comment> commentList = commentRepo.findCommentByProductId(id);
        List<CommentResponse> commentResponseList = new ArrayList<>();
        for (Comment comment : commentList) {
            commentResponseList.add(convertCommentToCommentResponse(comment));
        }
        return commentResponseList;
    }

    public void createCommentByProductId(Integer idProduct, Comment comment){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account accountLogin = accountService.findByUsername(userDetails.getUsername());
        comment.setAccount(accountLogin);
        comment.setDate(new Date());
        comment.setProduct(productService.getProductById(idProduct));
        commentRepo.save(comment);
    }
}
