package com.exam.serviceimpl;

import com.exam.entity.Score;
import com.exam.entity.ShortAnswer;
import com.exam.mapper.ScoreMapper;
import com.exam.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreMapper scoreMapper;
    @Override
    public int add(Score score) {
        return scoreMapper.add(score);
    }

    @Override
    public List<Score> findAll() {
        return scoreMapper.findAll();
    }

    @Override
    public List<Score> findById(Integer studentId) {
        return scoreMapper.findById(studentId);
    }

    @Override
    public List<ShortAnswer> findById1(Integer studentId) {
        return scoreMapper.findById1(studentId);
    }

    @Override
    public List<Score> findByExamCode(Integer examCode) {
        return scoreMapper.findByExamCode(examCode);
    }
}
