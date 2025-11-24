package com.example.service;

import com.example.pojo.Question;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 问题(Question)表服务接口
 *
 * @author makejava
 * @since 2023-05-11 23:09:43
 */
public interface QuestionService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Question queryById(Integer id);

    /**
     * 分页查询
     *
     * @param question 筛选条件
     * @return 查询结果
     */
    PageInfo<Question> queryAll(Question question, Integer pageNum, Integer pageSize);

    /**
     * 新增数据
     *
     * @param question 实例对象
     * @return 实例对象
     */
    Question insert(Question question);

    /**
     * 修改数据
     *
     * @param question 实例对象
     * @return 实例对象
     */
    Question update(Question question);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 获取下一个问题
     * @param userId
     * @param grade
     * @return
     */
    Question getNextQuestionByUserIdAndGrade(String userId, Integer grade);

    Integer getNotFinish(String userId, Integer grade);

    Integer getHadFinish(String userId, Integer grade);
}
