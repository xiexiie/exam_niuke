package com.exam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.FillQuestion;
import com.exam.entity.MultiQuestion;
import com.exam.entity.ShortQuestion;

import java.util.List;

/**
 * 简答Service接口
 */
public interface ShortQuestionService {

    List<ShortQuestion> findByIdAndType(Integer paperId);

    IPage<ShortQuestion> findAll(Page<ShortQuestion> page);

    ShortQuestion findOnlyQuestionId();

    ShortQuestion findById(Integer questionId);

    int deleteById(Integer questionId);

    int update(ShortQuestion shortQuestion);

    int add(ShortQuestion ShortQuestion);

    List<Integer> findBySubject(String subject, Integer pageNo);
}
