package com.example.ecommerce.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponse {
    private int id;
    private String content;
    private Date date;
    private String name;
    private String avatar;
    private int productId;
}
