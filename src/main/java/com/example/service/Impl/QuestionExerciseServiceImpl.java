package com.example.service.Impl;

import com.example.pojo.QuestionExercise;
import com.example.mapper.QuestionExerciseMapper;
import com.example.service.QuestionExerciseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 题目练习记录(QuestionExercise)表服务实现类
 *
 * @author makejava
 * @since 2023-05-11 23:09:42
 */
@Service("questionExerciseService")
public class QuestionExerciseServiceImpl implements QuestionExerciseService {
    @Resource
    private QuestionExerciseMapper questionExerciseDao;

    /**
     * 通过ID查询单条数据
     *
     * @param exerciseid 主键
     * @return 实例对象
     */
    @Override
    public QuestionExercise queryById(Integer exerciseid) {
        return this.questionExerciseDao.queryById(exerciseid);
    }

    /**
     * 分页查询
     *
     * @param questionExercise 筛选条件
     * @return 查询结果
     */
    @Override
    public List<QuestionExercise> queryAll(QuestionExercise questionExercise) {
        return this.questionExerciseDao.queryAllByLimit(questionExercise);
    }

    /**
     * 新增数据
     *
     * @param questionExercise 实例对象
     * @return 实例对象
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public QuestionExercise insert(QuestionExercise questionExercise) {
        // 先删除旧的
        List<QuestionExercise> list = questionExerciseDao.findByUserIdAndQuestionId(questionExercise.getUserid(), questionExercise.getQuestionid());
        for (QuestionExercise temp : list) {
            this.deleteById(temp.getExerciseid());
        }
        this.questionExerciseDao.insert(questionExercise);
        return questionExercise;
    }

    /**
     * 修改数据
     *
     * @param questionExercise 实例对象
     * @return 实例对象
     */
    @Override
    public QuestionExercise update(QuestionExercise questionExercise) {
        this.questionExerciseDao.update(questionExercise);
        return this.queryById(questionExercise.getExerciseid());
    }

    /**
     * 通过主键删除数据
     *
     * @param exerciseid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer exerciseid) {
        return this.questionExerciseDao.deleteById(exerciseid) > 0;
    }

    @Override
    public boolean deleteByUserIdAndGrade(String userId, Integer grade) {
        return questionExerciseDao.deleteByUserIdAndGrade(userId, grade);
    }

    @Override
    public QuestionExercise pageWrongByUserIdAndGrade(String userId, Integer grade, Integer pageNo) {
        return questionExerciseDao.pageWrongByUserIdAndGrade(userId, grade, pageNo);
    }

    @Override
    public Integer countWrongByUserIdAndGrade(String userId, Integer grade) {
        return questionExerciseDao.countWrongByUserIdAndGrade(userId, grade);
    }

    @Override
    public Integer countDaysByUserIdAndGrade(String userId, Integer grade) {
        return questionExerciseDao.countDaysByUserIdAndGrade(userId, grade);
    }
}
