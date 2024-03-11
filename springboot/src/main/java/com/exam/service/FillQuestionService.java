package com.exam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.FillQuestion;
import com.exam.entity.MultiQuestion;

import java.util.List;

public interface FillQuestionService {

    List<FillQuestion> findByIdAndType(Integer paperId);

    IPage<FillQuestion> findAll(Page<FillQuestion> page);

    FillQuestion findOnlyQuestionId();

    FillQuestion findById(Integer questionId);

    int deleteById(Integer questionId);

    int update(FillQuestion fillQuestion);

    int add(FillQuestion fillQuestion);

    List<Integer> findBySubject(String subject,Integer pageNo);
}
