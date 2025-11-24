package com.example.service;

import com.example.pojo.StrangeWord;
import com.example.pojo.Word;

import java.util.List;

public interface StrangeWordService {
    //查询生词
    StrangeWord queryStrangeWordById(Integer id);

    //增加生词
    int addStrangeWord(StrangeWord strangeWord);

    //删除生词
    int deleteStrangeWord(Integer id);

    //修改生词
    int updateStrangeWord(StrangeWord strangeWord);

    //查看生词所有信息
    List<StrangeWord> queryAllStrangeWord();

    //查看最新生词的内容
    StrangeWord queryNewStrangeWordById();


    StrangeWord findByWordIdAndUserIdAndTaskId(Integer wordId, String userId, Integer taskId);

    int deleteByWordIdAndUserIdAndTaskId(Integer wordId, String userId, Integer taskId);

    List<Word> findStrangeWordByUserId(String userId, Integer taskId);

}
