package com.exam.entity;

import lombok.Data;

/**
 * 简答题
 */
@Data
public class ShortQuestion {
    private Integer questionId;
    private String subject;
    private String section;
    private String question;
    private String level;
    private String answer;
    private String analysis;
    private Integer score;


}
