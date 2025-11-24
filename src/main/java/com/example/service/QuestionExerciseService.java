package com.example.service;

import com.example.pojo.Question;
import com.example.pojo.QuestionExercise;

import java.util.List;

/**
 * 题目练习记录(QuestionExercise)表服务接口
 *
 * @author makejava
 * @since 2023-05-11 23:09:42
 */
public interface QuestionExerciseService {

    /**
     * 通过ID查询单条数据
     *
     * @param exerciseid 主键
     * @return 实例对象
     */
    QuestionExercise queryById(Integer exerciseid);

    /**
     * 查询
     *
     * @param questionExercise 筛选条件
     * @return 查询结果
     */
    List<QuestionExercise> queryAll(QuestionExercise questionExercise);

    /**
     * 新增数据
     *
     * @param questionExercise 实例对象
     * @return 实例对象
     */
    QuestionExercise insert(QuestionExercise questionExercise);

    /**
     * 修改数据
     *
     * @param questionExercise 实例对象
     * @return 实例对象
     */
    QuestionExercise update(QuestionExercise questionExercise);

    /**
     * 通过主键删除数据
     *
     * @param exerciseid 主键
     * @return 是否成功
     */
    boolean deleteById(Integer exerciseid);

    /**
     * 清空学习记录
     *
     * @param userId
     * @param grade
     * @return
     */
    boolean deleteByUserIdAndGrade(String userId, Integer grade);

    /**
     * 获取下一个问题
     *
     * @param userId
     * @param grade
     * @return
     */
    QuestionExercise pageWrongByUserIdAndGrade(String userId,
                                          Integer grade,
                                          Integer pageNo);

    Integer countWrongByUserIdAndGrade(String userId, Integer grade);

    Integer countDaysByUserIdAndGrade(String userId, Integer grade);

}
