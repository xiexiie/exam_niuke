package com.exam.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.FillQuestion;
import com.exam.entity.MultiQuestion;
import org.apache.ibatis.annotations.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

//选择题
@Mapper
public interface MultiQuestionMapper {

    @Select("select * from multi_question where questionId in (select questionId from paper_manage where questionType = 1 and paperId = #{paperId})")
    List<MultiQuestion> findByIdAndType(Integer PaperId);

    @Select("select * from multi_question")
    IPage<MultiQuestion> findAll(Page page);

    @Select("select * from multi_question where questionId= #{questionId} ")
    MultiQuestion findById(Integer questionId);

    @Delete("delete from multi_question where questionId = #{questionId}")
    int deleteById(Integer questionId);

    //修改
    @Update("update multi_question set question = #{question},section = #{section},analysis = #{analysis}," +
            "score = #{score},level = #{level} where questionId = #{questionId}")
    int update(MultiQuestion multiQuestion);

    /**
     * 查询最后一条记录的questionId
     */
    @Select("select questionId from multi_question order by questionId desc limit 1")
    MultiQuestion findOnlyQuestionId();

    @Options(useGeneratedKeys = true,keyProperty = "questionId")
    @Insert("insert into multi_question(subject,question,answerA,answerB,answerC,answerD,rightAnswer,analysis,section,level) " +
            "values(#{subject},#{question},#{answerA},#{answerB},#{answerC},#{answerD},#{rightAnswer},#{analysis},#{section},#{level})")
    int add(MultiQuestion multiQuestion);

    @Select("select questionId from multi_question  where subject =#{subject} order by rand() desc limit #{pageNo}")
    List<Integer> findBySubject(String subject,Integer pageNo);


}
