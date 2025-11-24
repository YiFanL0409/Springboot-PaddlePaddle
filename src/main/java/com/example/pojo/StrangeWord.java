package com.example.pojo;

import lombok.Data;

import java.util.Date;

/**
 * 生词
 */
@Data
public class StrangeWord {
    private Integer id;
    private Integer wordId;
    private String userId;
    private Integer taskId;
    private Date createTime;
}
