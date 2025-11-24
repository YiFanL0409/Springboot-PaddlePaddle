package com.example.pojo;

import java.io.Serializable;

/**
 * 问题(Question)实体类
 *
 * @author makejava
 * @since 2023-05-11 23:09:42
 */
public class Question implements Serializable {
    private static final long serialVersionUID = 795513763548536029L;
    /**
     * 题目id
     */
    private Integer id;
    /**
     * 单词id
     */
    private Integer wordid;
    /**
     * 题目
     */
    private String question;
    /**
     * 选项A
     */
    private String optiona;
    /**
     * 选项B
     */
    private String optionb;
    /**
     * 选项C
     */
    private String optionc;
    /**
     * 选项D
     */
    private String optiond;
    /**
     * 答案
     */
    private String answer;

    // 单词名称，冗余字段
    private String wordName;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWordid() {
        return wordid;
    }

    public void setWordid(Integer wordid) {
        this.wordid = wordid;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOptiona() {
        return optiona;
    }

    public void setOptiona(String optiona) {
        this.optiona = optiona;
    }

    public String getOptionb() {
        return optionb;
    }

    public void setOptionb(String optionb) {
        this.optionb = optionb;
    }

    public String getOptionc() {
        return optionc;
    }

    public void setOptionc(String optionc) {
        this.optionc = optionc;
    }

    public String getOptiond() {
        return optiond;
    }

    public void setOptiond(String optiond) {
        this.optiond = optiond;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getWordName() {
        return wordName;
    }

    public void setWordName(String wordName) {
        this.wordName = wordName;
    }
}

