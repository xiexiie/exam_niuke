package com.exam.controller;

import com.exam.entity.ApiResult;
import com.exam.entity.FillQuestion;
import com.exam.serviceimpl.FillQuestionServiceImpl;
import com.exam.util.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class FillQuestionController {

    @Autowired
    private FillQuestionServiceImpl fillQuestionService;

    @PostMapping("/fillQuestion")
    public ApiResult add(@RequestBody FillQuestion fillQuestion) {
        int res = fillQuestionService.add(fillQuestion);
        if (res != 0) {
            return ApiResultHandler.buildApiResult(200,"添加成功",res);
        }
        return ApiResultHandler.buildApiResult(400,"添加失败",res);
    }

    @GetMapping("/fillQuestionId")
    public ApiResult findOnlyQuestionId() {
        FillQuestion res = fillQuestionService.findOnlyQuestionId();
        return ApiResultHandler.buildApiResult(200,"查询成功",res);
    }

    @GetMapping("/fillQuestion/{questionId}")
    public ApiResult findById(@PathVariable("questionId") Integer questionId){
        System.out.println("根据ID查找");
        FillQuestion res = fillQuestionService.findById(questionId);
        if(res == null) {
            return ApiResultHandler.buildApiResult(10000,"考试编号不存在",null);
        }
        return ApiResultHandler.buildApiResult(200,"请求成功！",res);
    }

    @DeleteMapping("/fillQuestion/{questionId}")
    public ApiResult deleteById(@PathVariable("questionId") Integer questionId){
        System.out.println("根据ID删除");
        int res = fillQuestionService.deleteById(questionId);
        return ApiResultHandler.buildApiResult(200,"删除成功",res);
    }

    @PutMapping("/fillQuestion")
    public ApiResult update(@RequestBody FillQuestion fillQuestion){
        int res = fillQuestionService.update(fillQuestion);
        System.out.print("更新操作执行---");
        return ApiResultHandler.buildApiResult(200,"更新成功",res);
    }
}
