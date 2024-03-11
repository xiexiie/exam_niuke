package com.exam.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.ExamManage;
import com.exam.entity.FillQuestion;
import com.exam.entity.MultiQuestion;
import org.apache.ibatis.annotations.*;

import java.util.List;

//填空题
@Mapper
public interface FillQuestionMapper {

    @Select("select * from fill_question where questionId in (select questionId from paper_manage where questionType = 2 and paperId = #{paperId})")
    List<FillQuestion> findByIdAndType(Integer paperId);

    @Select("select * from fill_question")
    IPage<FillQuestion> findAll(Page page);

    /**
     * 查询最后一条questionId
     */
    @Select("select questionId from fill_question order by questionId desc limit 1")
    FillQuestion findOnlyQuestionId();

    @Select("select * from fill_question where questionId= #{questionId} ")
    FillQuestion findById(Integer questionId);

    @Delete("delete from fill_question where questionId = #{questionId}")
    int deleteById(Integer questionId);

    //修改
    @Update("update fill_question set question = #{question},section = #{section},analysis = #{analysis}," +
            "score = #{score},level = #{level} where questionId = #{questionId}")
    int update(FillQuestion fillQuestion);

    @Options(useGeneratedKeys = true,keyProperty ="questionId" )
    @Insert("insert into fill_question(subject,question,answer,analysis,level,section) values " +
            "(#{subject},#{question},#{answer},#{analysis},#{level},#{section})")
    int add(FillQuestion fillQuestion);

    @Select("select questionId from fill_question where subject = #{subject} order by rand() desc limit #{pageNo}")
    List<Integer> findBySubject(String subject,Integer pageNo);
}
