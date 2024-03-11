package com.exam.controller;

import com.exam.entity.ApiResult;
import com.exam.entity.Score;
import com.exam.entity.ShortAnswer;
import com.exam.serviceimpl.ScoreServiceImpl;
import com.exam.util.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ScoreController {
    @Autowired
    private ScoreServiceImpl scoreService;

    @GetMapping("/scores")
    public ApiResult findAll() {
        List<Score> res = scoreService.findAll();
        return ApiResultHandler.buildApiResult(200,"查询所有学生成绩",res);
    }

    /**
     * 根据学生ID查询有关该学生的成绩，显示在我的成绩界面。
     * @param studentId
     * @return
     */
    @GetMapping("/score/{studentId}")
    public ApiResult findById(@PathVariable("studentId") Integer studentId) {
        List<Score> res = scoreService.findById(studentId);
        if (!res.isEmpty()) {
            return ApiResultHandler.buildApiResult(200,"根据ID查询成绩",res);
        }else {
            return ApiResultHandler.buildApiResult(400,"ID不存在",res);
        }
    }

    @GetMapping("/shortScore/{studentId}")
    public ApiResult findById1(@PathVariable("studentId") Integer studentId) {
        List<ShortAnswer> res = scoreService.findById1(studentId);
        //20154084
        System.out.println(res);
        if(!res.isEmpty()){
            return ApiResultHandler.buildApiResult(200,"根据id查找简答题成绩",res);
        }else{
            return ApiResultHandler.buildApiResult(400,"ID不存在！",res);
        }
    }

    @PostMapping("/score")
    public ApiResult add(@RequestBody Score score) {
        int res = scoreService.add(score);
        if (res == 0) {
            return ApiResultHandler.buildApiResult(400,"成绩添加失败",res);
        }else {
            return ApiResultHandler.buildApiResult(200,"成绩添加成功",res);
        }
    }

    @GetMapping("/scores/{examCode}")
    public ApiResult findByExamCode(@PathVariable("examCode") Integer examCode) {
        List<Score> scores = scoreService.findByExamCode(examCode);
        return ApiResultHandler.buildApiResult(200,"查询成功",scores);
    }
}
