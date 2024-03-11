package com.exam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.ShortAnswer;


/**
 * 学生提交简答题答案
 */
public interface ShortAnswerService {

    int save(ShortAnswer shortAnswer);

    IPage<ShortAnswer> findAll(Page<ShortAnswer> page);

    IPage<ShortAnswer> findAll1(Page<ShortAnswer> page);

    ShortAnswer findById(Integer questionId);

    int deleteById(Integer questionId);

    int updateScore(ShortAnswer shortAnswer);

    int updateState(ShortAnswer shortAnswer);

    ShortAnswer sumShort(Integer studentId);
}
