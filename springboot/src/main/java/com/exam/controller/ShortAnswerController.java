package com.exam.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.ApiResult;
import com.exam.entity.ShortAnswer;
import com.exam.serviceimpl.ShortAnswerServiceImpl;
import com.exam.util.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 学生提交简答题
 */
@RestController
public class ShortAnswerController {

    @Autowired
    private ShortAnswerServiceImpl shortAnswerService;

    /**
     * 学生回答简答题
     */
    @PostMapping("/shortAnswer")
    public ApiResult save(@RequestBody ShortAnswer shortAnswer){
        System.out.println(shortAnswer.toString());
        int res = shortAnswerService.save(shortAnswer);
        if(res != 0){
            return ApiResultHandler.buildApiResult(200,"添加成功",res);
        }
        return ApiResultHandler.buildApiResult(400,"添加失败",res);
    }

    /**
     * 分页查找所有未批改的
     */
    @GetMapping("/shortAnswer/{page}/{size}")
    public ApiResult findAll(@PathVariable Integer page,@PathVariable Integer size){
        Page<ShortAnswer> shortAnswerPage = new Page<>(page, size);
        IPage<ShortAnswer> res = shortAnswerService.findAll(shortAnswerPage);
        System.out.println(res);
        return ApiResultHandler.buildApiResult(200,"分页查询所有未批改的",res);
    }

    /**
     * 分页查找所有已经批改的
     */
    @GetMapping("/shortAnswer1/{page}/{size}")
    public ApiResult findAll1(@PathVariable Integer page,@PathVariable Integer size){
        Page<ShortAnswer> shortAnswerPage = new Page<>(page, size);
        IPage<ShortAnswer> res = shortAnswerService.findAll1(shortAnswerPage);
        System.out.println(res);
        return ApiResultHandler.buildApiResult(200,"分页查询所有未批改的",res);
    }

    /**
     * 查找用户信息
     */
    @GetMapping("/shortAnswer/{questionId}")
    public ApiResult findById(@PathVariable("questionId")Integer questionId){
        ShortAnswer res = shortAnswerService.findById(questionId);
        if (res != null) {
            return ApiResultHandler.buildApiResult(200,"请求成功",res);
        } else {
            return ApiResultHandler.buildApiResult(404,"查询的用户不存在",null);
        }
    }

    /**
     * 删除简答题提交
     */
    @DeleteMapping("/shortAnswer/{questionId}")
    public ApiResult deleteById(@PathVariable("questionId") Integer questionId) {
        return ApiResultHandler.buildApiResult(200,"删除成功",shortAnswerService.deleteById(questionId));
    }

    /**
     * 老师批改简答题分数
     */
    @PutMapping("/updateScore")
    public ApiResult push(@RequestBody ShortAnswer shortAnswer){
        System.out.println(shortAnswer.toString());
        int res = shortAnswerService.updateScore(shortAnswer);
        if(res != 0){
            return ApiResultHandler.buildApiResult(200,"添加成功",res);
        }
        return ApiResultHandler.buildApiResult(400,"添加失败",res);
    }

    /**
     * 修改简答题的状态
     */
    @PutMapping("/updateState")
    public ApiResult updateState(@RequestBody ShortAnswer shortAnswer){
        System.out.println("进入修改简答题的状态");
        int res = shortAnswerService.updateState(shortAnswer);
        if(res != 0){
            return ApiResultHandler.buildApiResult(200,"修改状态成功",res);
        }
        return ApiResultHandler.buildApiResult(400,"修改状态失败",res);
    }

    /**
     * 计算简答题分数和
     */
    @GetMapping("/sumShort/{studentId}")
    public ApiResult sumShort(@PathVariable("studentId")Integer studentId){
        ShortAnswer res = shortAnswerService.sumShort(studentId);
        if (res != null) {
            return ApiResultHandler.buildApiResult(200,"请求成功",res);
        } else {
            return ApiResultHandler.buildApiResult(404,"查询的用户不存在",null);
        }
    }
}
