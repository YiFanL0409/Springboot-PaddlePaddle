package com.example.service.Impl;

import com.example.mapper.TaskDetailMapper;
import com.example.mapper.TaskMapper;
import com.example.pojo.Task;
import com.example.pojo.TaskDetail;
import com.example.pojo.Word;
import com.example.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private TaskDetailMapper taskDetailMapper;

    @Override
    public Task queryTaskById(Integer taskId) {
        return taskMapper.queryTaskById(taskId);
    }

    @Override
    public int addTask(Task task) {
        // 判断有没有已经选过了
        List<Task> byUserIdAndTypeAndGrade = taskMapper.findByUserIdAndTypeAndGrade(task.getUserId(), task.getType(), task.getGrade());
        if (byUserIdAndTypeAndGrade.size() > 0) {
            return 0;
        }
        return taskMapper.addTask(task);
    }

    @Override
    public int deleteTask(Integer taskId) {
        return taskMapper.deleteTask(taskId);
    }

    @Override
    public int updateTask(Task task) {
        return taskMapper.updateTask(task);
    }

    @Override
    public List<Task> queryAllTask() {
        return taskMapper.queryAllTask();
    }

    @Override
    public List<Task> findByUserId(String userId) {
        return taskMapper.findByUserId(userId);
    }


    @Override
    public Integer countByTask(Task task) {
        return taskDetailMapper.countByTask(task);
    }

    @Override
    public Integer countByTaskToday(Task task) {
        return taskDetailMapper.countByTaskToday(task);
    }


    @Override
    public void addTaskDetail(TaskDetail taskDetail) {
        try {
            taskDetailMapper.addTaskDetail(taskDetail);
        } catch (Exception e) {

        }
    }

    @Override
    public void deleteTaskDetail(Integer taskId, Integer businessId) {
        taskDetailMapper.deleteTaskDetailByTaskIdAndBusinessId(taskId, businessId);
    }

    @Override
    public void deleteTaskDetail(Integer taskId) {
        taskDetailMapper.deleteTaskDetailByTaskId(taskId);
    }


    @Override
    public Integer countDaysByTaskIdAndUserId(Integer taskId, String userId) {
        return taskDetailMapper.countDaysByTaskIdAndUserId(taskId, userId);
    }

    @Override
    public List<Word> getTodayStudyWord(Integer taskId, String userId) {
        return taskDetailMapper.getTodayStudyWord(taskId, userId);
    }

    @Override
    public Integer countByDays(Integer taskId, String userId, Integer days) {
        return taskDetailMapper.countByDays(taskId, userId, days);
    }
}
