package com.exam.serviceimpl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.ShortAnswer;
import com.exam.mapper.ShortAnswerMapper;
import com.exam.service.ShortAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 学生提交简答题
 */

@Service
public class ShortAnswerServiceImpl implements ShortAnswerService {

    @Autowired
    private ShortAnswerMapper shortAnswerMapper;

    @Override
    public int save(ShortAnswer shortAnswer) {
        return shortAnswerMapper.save(shortAnswer);
    }

    /**
     * 分页查找未批改的
     * @param page
     * @return
     */
    @Override
    public IPage<ShortAnswer> findAll(Page<ShortAnswer> page) {
        return shortAnswerMapper.findAll(page);
    }

    /**
     * 分页查找已经批改的
     */
    @Override
    public IPage<ShortAnswer> findAll1(Page<ShortAnswer> page) {
        return shortAnswerMapper.findAll1(page);
    }

    @Override
    public ShortAnswer findById(Integer questionId) {
        return shortAnswerMapper.findById(questionId);
    }

    @Override
    public int deleteById(Integer questionId) {
        return shortAnswerMapper.deleteById(questionId);
    }

    @Override
    public int updateScore(ShortAnswer shortAnswer) {
        return shortAnswerMapper.updateScore(shortAnswer);
    }

    @Override
    public int updateState(ShortAnswer shortAnswer) {
        return shortAnswerMapper.updateState(shortAnswer);
    }

    /**
     * 计算简答题分数和
     */
    @Override
    public ShortAnswer sumShort(Integer studentId) {
        return shortAnswerMapper.sumShort(studentId);
    }

}
