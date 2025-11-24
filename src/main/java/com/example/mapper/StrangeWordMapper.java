package com.example.mapper;

import com.example.pojo.StrangeWord;
import com.example.pojo.Word;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StrangeWordMapper {

    //根据ID查询一条生词
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

    StrangeWord findByWordIdAndUserIdAndTaskId(@Param("wordId") Integer wordId,
                                               @Param("userId") String userId,
                                               @Param("taskId") Integer taskId);

    int deleteByWordIdAndUserIdAndTaskId(@Param("wordId") Integer wordId,
                                         @Param("userId") String userId,
                                         @Param("taskId") Integer taskId);


    List<Word> findStrangeWordByUserId(@Param("userId") String userId,
                                       @Param("taskId") Integer taskId);

}
