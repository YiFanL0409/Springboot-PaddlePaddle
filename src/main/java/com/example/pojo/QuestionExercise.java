package com.example.pojo;

import java.util.Date;
import java.io.Serializable;

/**
 * 题目练习记录(QuestionExercise)实体类
 *
 * @author makejava
 * @since 2023-05-11 23:09:34
 */
public class QuestionExercise implements Serializable {
    private static final long serialVersionUID = 678490809899409148L;
    /**
     * 练习id
     */
    private Integer exerciseid;

    /**
     * 题目id
     */
    private Integer questionid;
    /**
     * 用户id
     */
    private String userid;
    /**
     * 单词id
     */
    private Integer wordid;
    /**
     * 用户练习答案
     */
    private String answer;
    /**
     * 答案结果，1正确，0错误
     */
    private Integer flag;
    /**
     * 创建时间
     */
    private Date createtime;


    public Integer getExerciseid() {
        return exerciseid;
    }

    public void setExerciseid(Integer exerciseid) {
        this.exerciseid = exerciseid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Integer getWordid() {
        return wordid;
    }

    public void setWordid(Integer wordid) {
        this.wordid = wordid;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }


    public Integer getQuestionid() {
        return questionid;
    }

    public void setQuestionid(Integer questionid) {
        this.questionid = questionid;
    }
}

