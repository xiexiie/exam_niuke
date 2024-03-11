package com.exam.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.ShortAnswer;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 学生提交简答题Mapper
 */
@Mapper
public interface ShortAnswerMapper {

    @Insert("insert into short_answer(source,questionId,questionNumber,questionMessage,answer,studentId,studentName,examCode) values (#{source},#{questionId},#{questionNumber},#{questionMessage},#{answer},#{studentId},#{studentName},#{examCode})")
    int save(ShortAnswer shortAnswer);

    /**
     * 分页查询未批改的简答题
     */
    @Select("select * from short_answer where state = 0")
    IPage<ShortAnswer> findAll(Page page);

    /**
     * 分页查询已经批改的简答题
     */
    @Select("select * from short_answer where state = 1")
    IPage<ShortAnswer> findAll1(Page page);

    /**
     * 根据questionId查找相关信息
     */
    @Select("select * from short_answer where questionId = #{questionId}")
    ShortAnswer findById(Integer questionId);

    /**
     * 根据ID删除简答题
     */
    @Delete("delete from short_answer where questionId = #{questionId}")
    int deleteById(Integer questionId);

    /**
     * 老师提交批改分数
     */
    @Update("update short_answer set shortScore = #{shortScore} where questionId = #{questionId} ")
    int updateScore(ShortAnswer shortAnswer);

    /**
     * 修改简答题状态
     */
    @Update("update short_answer set state = 1 where questionId = #{questionId} ")
    int updateState(ShortAnswer shortAnswer);

    /**
     * 计算学生简答题分数和
     */
    @Select("select sum(shortScore) from short_answer where studentId = #{studentId}")
    ShortAnswer sumShort(Integer studentId);
}