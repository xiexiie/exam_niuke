package com.exam.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.vo.AnswerVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface AnswerMapper {
    @Select("select questionId,question, subject, score, section,level, \"选择题\" as type from multi_question " +
            "union select questionId, question, subject, score, section,level, \"判断题\" as type  from judge_question " +
            "union select questionId, question, subject, score, section,level, \"填空题\" as type from fill_question" +
            " union select questionId, question,subject,score,section,level,\"简答题\" as type from short_question"
    )
    IPage<AnswerVO> findAll(Page page);
}
