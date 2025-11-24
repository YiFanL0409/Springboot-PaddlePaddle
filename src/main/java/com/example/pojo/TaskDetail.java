package com.example.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class TaskDetail {
    private Integer id;
    private Integer businessId;
    private Integer taskId;
    private String userId;
    private Date createTime;
}
