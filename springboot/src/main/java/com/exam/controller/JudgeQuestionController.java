package com.exam.controller;

import com.exam.entity.ApiResult;
import com.exam.entity.JudgeQuestion;
import com.exam.serviceimpl.JudgeQuestionServiceImpl;
import com.exam.util.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class JudgeQuestionController {

    @Autowired
    private JudgeQuestionServiceImpl judgeQuestionService;

    @PostMapping("/judgeQuestion")
    public ApiResult add(@RequestBody JudgeQuestion judgeQuestion) {
        int res = judgeQuestionService.add(judgeQuestion);
        if (res != 0) {
            return ApiResultHandler.buildApiResult(200,"添加成功",res);
        }
        return ApiResultHandler.buildApiResult(400,"添加失败",res);
    }

    @GetMapping("/judgeQuestionId")
    public ApiResult findOnlyQuestionId() {
        JudgeQuestion res = judgeQuestionService.findOnlyQuestionId();
        return  ApiResultHandler.buildApiResult(200,"查询成功",res);
    }

    @GetMapping("/judgeQuestion/{questionId}")
    public ApiResult findById(@PathVariable("questionId") Integer questionId){
        System.out.println("根据ID查找");
        JudgeQuestion res = judgeQuestionService.findById(questionId);
        if(res == null) {
            return ApiResultHandler.buildApiResult(10000,"考试编号不存在",null);
        }
        return ApiResultHandler.buildApiResult(200,"请求成功！",res);
    }

    @DeleteMapping("/judgeQuestion/{questionId}")
    public ApiResult deleteById(@PathVariable("questionId") Integer questionId){
        System.out.println("根据ID删除");
        int res = judgeQuestionService.deleteById(questionId);
        return ApiResultHandler.buildApiResult(200,"删除成功",res);
    }

    @PutMapping("/judgeQuestion")
    public ApiResult update(@RequestBody JudgeQuestion judgeQuestion){
        int res = judgeQuestionService.update(judgeQuestion);
        System.out.print("更新操作执行---");
        return ApiResultHandler.buildApiResult(200,"更新成功",res);
    }
}
