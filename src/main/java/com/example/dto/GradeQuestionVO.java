package com.example.dto;

import com.example.pojo.Grade;
import lombok.Data;

/**
 * 题目等级VO
 * @since 2023/5/12 11:43
 */
@Data
public class GradeQuestionVO extends Grade {




    /**
     * 记录题目的未完成数量
     */
    Integer questionHadFinish;
    /**
     * 记录题目的已完成数量
     */
    Integer questionNotFinish;

    /**
     * 完成百分比
     */
    private String questionPercent;


    private Integer questionTotal;

    /**
     * 学习天数
     */
    private Integer studyDays;


}
