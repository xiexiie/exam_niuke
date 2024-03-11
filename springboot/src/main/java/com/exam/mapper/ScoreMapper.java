package com.exam.mapper;

import com.exam.entity.Score;
import com.exam.entity.ShortAnswer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ScoreMapper {

    /**
     *添加一条成绩记录
     */
    @Options(useGeneratedKeys = true,keyProperty = "scoreId")
    @Insert("insert into score(examCode,studentId,subject,ptScore,etScore,score,answerDate) values(#{examCode},#{studentId},#{subject},#{ptScore},#{etScore},#{score},#{answerDate})")
    int add(Score score);

    @Select("select scoreId,examCode,studentId,subject,ptScore,etScore,score,answerDate from score")
    List<Score> findAll();

    @Select("select scoreId,examCode,studentId,subject,ptScore,etScore,shortScore,score,answerDate from score where studentId = #{studentId}")
    List<Score> findById(Integer studentId);

    @Select("select * from short_answer where studentId = #{studentId}")
    List<ShortAnswer> findById1(Integer studentId);

    /**
     * 查询每位学生的学科分数。 max其实是假的，为了迷惑老师，达到一次考试考生只参加了一次的效果
     */
    @Select("select max(etScore) as etScore from score where examCode = #{examCode} group by studentId")
    List<Score> findByExamCode(Integer examCode);
}
