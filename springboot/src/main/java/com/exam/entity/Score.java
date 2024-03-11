package com.exam.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Score {
    private Integer examCode;//考试编号

    private Integer studentId;//学号

    private String subject;//课程名称

    private Integer ptScore;//平时成绩

    private Integer etScore;//期末成绩

    private Integer shortScore;//简答题总成绩

    private Integer score;//总成绩

    private Integer scoreId;//分数编号

    private String answerDate;//答题日期
}