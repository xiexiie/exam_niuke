package com.exam.entity;

import lombok.Data;

/**
 * 简答题答案
 */
@Data
public class ShortAnswer {

    private String source;//试卷名称

    private Integer questionId;//问题ID

    private Integer questionNumber;//试题编号

    private String questionMessage;//试题题目

    private Integer shortScore;//老师批改的分数

    private String answer;//学生提交的答案

    private Integer state;//该问题的状态（1.批改，0.未批改）

    private Integer studentId;//学生ID

    private String studentName;//学生姓名

    private Integer examCode; //试卷编号
}
