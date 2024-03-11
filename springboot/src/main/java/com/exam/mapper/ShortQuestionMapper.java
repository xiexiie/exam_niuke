package com.exam.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.FillQuestion;
import com.exam.entity.MultiQuestion;
import com.exam.entity.ShortQuestion;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 简答题Mapper
 */
@Mapper
public interface ShortQuestionMapper {

    @Select("select * from short_question where questionId in (select questionId from paper_manage where questionType = 4 and paperId = #{paperId})")
    List<ShortQuestion> findByIdAndType(Integer paperId);

    @Select("select * from short_question")
    IPage<ShortQuestion> findAll(Page page);

    @Select("select questionId from short_question order by questionId desc limit 1")
    ShortQuestion findOnlyQuestionId();

    @Options(useGeneratedKeys = true,keyProperty ="questionId" )
    @Insert("insert into short_question(subject,question,answer,analysis,level,section,score) values " +
            "(#{subject},#{question},#{answer},#{analysis},#{level},#{section},#{score})")
    int add(ShortQuestion shortQuestion);

//    @Options(useGeneratedKeys = true,keyProperty ="questionId" )
//    @Insert("insert into fill_question(subject,question,answer,analysis,level,section) values " +
//            "(#{subject,},#{question},#{answer},#{analysis},#{level},#{section})")
//    int add1(ShortQuestion shortQuestion);

    @Select("select questionId from short_question where subject = #{subject} order by rand() desc limit #{pageNo}")
    List<Integer> findBySubject(String subject,Integer pageNo);

    @Select("select * from short_question where questionId= #{questionId} ")
    ShortQuestion findById(Integer questionId);

    @Delete("delete from short_question where questionId=#{questionId}")
    int deleteById(Integer questionId);

    //修改
    @Update("update short_question set question = #{question},section = #{section},analysis = #{analysis}," +
            "score = #{score},level = #{level} where questionId = #{questionId}")
    int update(ShortQuestion shortQuestion);
}
