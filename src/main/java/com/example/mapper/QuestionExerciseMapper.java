package com.example.mapper;

import com.example.pojo.QuestionExercise;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 题目练习记录(QuestionExercise)表数据库访问层
 *
 * @author makejava
 * @since 2023-05-11 23:09:29
 */
@Mapper
public interface QuestionExerciseMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param exerciseid 主键
     * @return 实例对象
     */
    QuestionExercise queryById(Integer exerciseid);

    /**
     * 查询指定行数据
     *
     * @param questionExercise 查询条件
     * @return 对象列表
     */
    List<QuestionExercise> queryAllByLimit(QuestionExercise questionExercise);

    /**
     * 统计总行数
     *
     * @param questionExercise 查询条件
     * @return 总行数
     */
    long count(QuestionExercise questionExercise);

    /**
     * 新增数据
     *
     * @param questionExercise 实例对象
     * @return 影响行数
     */
    int insert(QuestionExercise questionExercise);


    List<QuestionExercise> findByUserIdAndQuestionId(@Param("userId") String userId,
                                                     @Param("questionId") Integer questionId);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<QuestionExercise> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<QuestionExercise> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<QuestionExercise> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<QuestionExercise> entities);

    /**
     * 修改数据
     *
     * @param questionExercise 实例对象
     * @return 影响行数
     */
    int update(QuestionExercise questionExercise);

    /**
     * 通过主键删除数据
     *
     * @param exerciseid 主键
     * @return 影响行数
     */
    int deleteById(Integer exerciseid);

    /**
     * 清空学习记录
     *
     * @param userId
     * @param grade
     * @return
     */
    boolean deleteByUserIdAndGrade(@Param("userId") String userId,
                                   @Param("grade") Integer grade);

    /**
     * 获取下一个问题
     *
     * @param userId
     * @param grade
     * @return
     */
    QuestionExercise pageWrongByUserIdAndGrade(@Param("userId") String userId,
                                          @Param("grade") Integer grade,
                                          @Param("pageNo") Integer pageNo);

    Integer countWrongByUserIdAndGrade(@Param("userId") String userId, @Param("grade") Integer grade);

    Integer countDaysByUserIdAndGrade(@Param("userId") String userId, @Param("grade") Integer grade);
}

