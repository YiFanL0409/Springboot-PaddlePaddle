package com.example.service.Impl;

import com.example.mapper.StrangeWordMapper;
import com.example.pojo.StrangeWord;
import com.example.pojo.Word;
import com.example.service.StrangeWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StrangeWordServiceImpl implements StrangeWordService {
    @Autowired
    private StrangeWordMapper strangeWordMapper;

    @Override
    public StrangeWord queryStrangeWordById(Integer id) {
        return strangeWordMapper.queryStrangeWordById(id);
    }

    @Override
    public int addStrangeWord(StrangeWord strangeWord) {
        return strangeWordMapper.addStrangeWord(strangeWord);
    }

    @Override
    public int deleteStrangeWord(Integer id) {
        return strangeWordMapper.deleteStrangeWord(id);
    }

    @Override
    public int updateStrangeWord(StrangeWord strangeWord) {
        return strangeWordMapper.updateStrangeWord(strangeWord);
    }

    @Override
    public List<StrangeWord> queryAllStrangeWord() {
        return strangeWordMapper.queryAllStrangeWord();
    }

    @Override
    public StrangeWord queryNewStrangeWordById() {
        return strangeWordMapper.queryNewStrangeWordById();
    }

    @Override
    public StrangeWord findByWordIdAndUserIdAndTaskId(Integer wordId, String userId, Integer taskId) {
        return strangeWordMapper.findByWordIdAndUserIdAndTaskId(wordId, userId, taskId);
    }

    @Override
    public int deleteByWordIdAndUserIdAndTaskId(Integer wordId, String userId, Integer taskId) {
        return strangeWordMapper.deleteByWordIdAndUserIdAndTaskId(wordId, userId, taskId);
    }

    @Override
    public List<Word> findStrangeWordByUserId(String userId, Integer taskId) {
        return strangeWordMapper.findStrangeWordByUserId(userId, taskId);
    }
}
