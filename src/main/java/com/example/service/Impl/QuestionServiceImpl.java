package com.example.service.Impl;

import com.example.mapper.WordMapper;
import com.example.pojo.Question;
import com.example.mapper.QuestionMapper;
import com.example.pojo.Word;
import com.example.service.QuestionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 问题(Question)表服务实现类
 *
 * @author makejava
 * @since 2023-05-11 23:09:43
 */
@Service("questionService")
public class QuestionServiceImpl implements QuestionService {
    @Resource
    private QuestionMapper questionDao;

    @Resource
    private WordMapper wordMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Question queryById(Integer id) {
        Question question = this.questionDao.queryById(id);
        if (question != null) {
            Word word = wordMapper.queryWordById(question.getWordid());
            question.setWordName(word != null ? word.getWordName() : null);
        }
        return question;
    }

    /**
     * 分页查询
     *
     * @param question 筛选条件
     * @return 查询结果
     */
    @Override
    public PageInfo<Question> queryAll(Question question, Integer pageNum, Integer pageSize) {
        // 设置分页参数
        PageHelper.startPage(pageNum, pageSize);
        List<Question> list = this.questionDao.queryAllByLimit(question);
        return new PageInfo<>(list);
    }

    /**
     * 新增数据
     *
     * @param question 实例对象
     * @return 实例对象
     */
    @Override
    public Question insert(Question question) {
        Word word = wordMapper.queryWordByName(question.getWordName());
        if (word != null) {
            question.setWordid(word.getWordId());
        }
        this.questionDao.insert(question);
        return question;
    }

    /**
     * 修改数据
     *
     * @param question 实例对象
     * @return 实例对象
     */
    @Override
    public Question update(Question question) {
        Word word = wordMapper.queryWordByName(question.getWordName());
        if (word != null) {
            question.setWordid(word.getWordId());
        }
        this.questionDao.update(question);
        return this.queryById(question.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.questionDao.deleteById(id) > 0;
    }

    @Override
    public Question getNextQuestionByUserIdAndGrade(String userId, Integer grade) {
        return questionDao.getNextQuestionByUserIdAndGrade(userId, grade);
    }

    @Override
    public Integer getNotFinish(String userId, Integer grade) {
        return questionDao.getNotFinish(userId, grade);
    }

    @Override
    public Integer getHadFinish(String userId, Integer grade) {
        return questionDao.getHadFinish(userId, grade);
    }
}
