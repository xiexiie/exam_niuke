package com.exam.serviceimpl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.MultiQuestion;
import com.exam.entity.ShortQuestion;
import com.exam.mapper.ShortQuestionMapper;
import com.exam.service.ShortQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 简答题serviceImpl层
 */
@Service
public class ShortQuestionServiceImpl implements ShortQuestionService {

    @Autowired
    private ShortQuestionMapper shortQuestionMapper;

    @Override
    public List<ShortQuestion> findByIdAndType(Integer paperId) {
        return shortQuestionMapper.findByIdAndType(paperId);
    }

    @Override
    public ShortQuestion findById(Integer questionId) {
        return shortQuestionMapper.findById(questionId);
    }

    @Override
    public int deleteById(Integer questionId){
        return shortQuestionMapper.deleteById(questionId);
    }

    @Override
    public int update(ShortQuestion shortQuestion) {
        return shortQuestionMapper.update(shortQuestion);
    }

    @Override
    public IPage<ShortQuestion> findAll(Page<ShortQuestion> page) {
        return shortQuestionMapper.findAll(page);
    }

    @Override
    public ShortQuestion findOnlyQuestionId() {
        return shortQuestionMapper.findOnlyQuestionId();
    }

    @Override
    public int add(ShortQuestion shortQuestion) {
        return shortQuestionMapper.add(shortQuestion);
    }

    @Override
    public List<Integer> findBySubject(String subject, Integer pageNo) {
        return shortQuestionMapper.findBySubject(subject,pageNo);
    }
}
