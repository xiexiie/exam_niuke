package com.exam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.FillQuestion;
import com.exam.entity.JudgeQuestion;
import com.exam.entity.MultiQuestion;

import java.util.List;

public interface JudgeQuestionService {

    List<JudgeQuestion> findByIdAndType(Integer paperId);

    IPage<JudgeQuestion> findAll(Page<JudgeQuestion> page);

    JudgeQuestion findOnlyQuestionId();

    int add(JudgeQuestion judgeQuestion);

    JudgeQuestion findById(Integer questionId);

    int deleteById(Integer questionId);

    int update(JudgeQuestion judgeQuestion);

    List<Integer> findBySubject(String subject,Integer pageNo);
}
