package com.exam.controller;

import com.exam.entity.ApiResult;
import com.exam.entity.ShortQuestion;
import com.exam.serviceimpl.ShortQuestionServiceImpl;
import com.exam.util.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 简答题Controller层
 */
@RestController
public class ShortQuestionController {

    @Autowired
    private ShortQuestionServiceImpl ShortQuestionService;

    @PostMapping("/shortQuestion")
    public ApiResult add(@RequestBody ShortQuestion ShortQuestion) {
        System.out.println(ShortQuestion.toString());
        int res = ShortQuestionService.add(ShortQuestion);
        if (res != 0) {
            return ApiResultHandler.buildApiResult(200,"添加成功",res);
        }
        return ApiResultHandler.buildApiResult(400,"添加失败",res);
    }

    @GetMapping("/shortQuestionId")
    public ApiResult findOnlyQuestionId() {
        ShortQuestion res = ShortQuestionService.findOnlyQuestionId();
        return ApiResultHandler.buildApiResult(200,"查询成功",res);
    }

    @GetMapping("/shortQuestion/{questionId}")
    public ApiResult findById(@PathVariable("questionId") Integer questionId){
        System.out.println("根据ID查找");
        ShortQuestion res = ShortQuestionService.findById(questionId);
        if(res == null) {
            return ApiResultHandler.buildApiResult(10000,"考试编号不存在",null);
        }
        return ApiResultHandler.buildApiResult(200,"请求成功！",res);
    }

    @DeleteMapping("/shortQuestion/{questionId}")
    public ApiResult deleteById(@PathVariable("questionId") Integer questionId){
        int res=ShortQuestionService.deleteById(questionId);
        System.out.println("删除成功");
        return ApiResultHandler.buildApiResult(200,"删除成功",res);
    }

    @PutMapping("/shortQuestion")
    public ApiResult update(@RequestBody ShortQuestion shortQuestion){
        int res = ShortQuestionService.update(shortQuestion);
        System.out.print("更新操作执行---");
        return ApiResultHandler.buildApiResult(200,"更新成功",res);
    }
}
