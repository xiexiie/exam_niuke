package com.exam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.FillQuestion;
import com.exam.entity.MultiQuestion;

import java.util.List;

public interface MultiQuestionService {

    List<MultiQuestion> findByIdAndType(Integer PaperId);

    IPage<MultiQuestion> findAll(Page<MultiQuestion> page);

    MultiQuestion findById(Integer questionId);

    int deleteById(Integer questionId);

    int update(MultiQuestion multiQuestion);

    MultiQuestion findOnlyQuestionId();

    int add(MultiQuestion multiQuestion);

    List<Integer> findBySubject(String subject,Integer pageNo);
}
