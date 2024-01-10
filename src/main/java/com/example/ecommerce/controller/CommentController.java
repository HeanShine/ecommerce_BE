package com.example.ecommerce.controller;

import com.example.ecommerce.model.Comment;
import com.example.ecommerce.model.dto.CommentResponse;
import com.example.ecommerce.service.impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentServiceImpl commentService;

    @GetMapping("/{idProduct}")
    public ResponseEntity<List<CommentResponse>> getAllCommentById(@PathVariable Integer idProduct) {
        List<CommentResponse> commentList = commentService.findCommentByProductId(idProduct);
        if (commentService.findCommentByProductId(idProduct).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(commentList, HttpStatus.OK);
    }

    @PostMapping("/createComment/{idProduct}")
    public ResponseEntity<String> createCommentByIdProduct(@PathVariable int idProduct, @RequestBody Comment comment) {
        commentService.createCommentByProductId(idProduct, comment);
        return new ResponseEntity<>("Create comment success", HttpStatus.OK);
    }
}
