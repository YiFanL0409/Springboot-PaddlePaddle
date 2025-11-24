package com.example.controller.admin;

import com.example.pojo.Grade;
import com.example.pojo.Word;
import com.example.service.GradeService;
import com.example.service.Impl.WordServiceImpl;
import com.example.service.WordService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UpdateWordController {
    @Autowired
    private WordService wordService;

    @Autowired
    private GradeService gradeService;

    /**
     * 查看管理
     */
    @RequestMapping("/admin/viewWord")
    public String viewWord(Model model,
                           @RequestParam(value = "page", defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "size", defaultValue = "6") Integer pageSize,
                           @RequestParam(value = "keywords", defaultValue = "") String keywords) {
        PageInfo<Word> pageInfo = wordService.queryAllWord(keywords, pageNum, pageSize);
        for (Word item : pageInfo.getList()) {
            item.setGradeDto(gradeService.queryGradeById(item.getGrade()));
        }
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("keywords", keywords);
        return "admin/Word-View";
    }


    /**
     * 修改单词
     */
    //去修改页面
    @RequestMapping("/admin/toUpdateWord/{wordId}")
    public String toUpdateWord(@PathVariable("wordId") Integer wordId, Model model) {

        List<Grade> grades = gradeService.queryAllGrade(null);
        model.addAttribute("grades", grades);

        Word word = wordService.queryWordById(wordId);
        model.addAttribute("word", word);
        return "admin/Word-Update";
    }

    //进行修改提交，回到view页面
    @RequestMapping("/admin/updateWord/{wordId}")
    public String updateNotice(@PathVariable("wordId") Integer wordId, Word word) {

        wordService.updateWord(word);
        return "redirect:/admin/viewWord";
    }

    /**
     * 删除单词
     */

    @RequestMapping("/admin/deleteWord/{wordId}")
    public String deleteNotice(@PathVariable("wordId") Integer wordId) {

        wordService.deleteWord(wordId);


        return "redirect:/admin/viewWord";
    }

    /**
     * 增加单词
     */
//
    @RequestMapping("/admin/toAddWord")
    public String toAddWord(Model model) {
        List<Grade> grades = gradeService.queryAllGrade(null);
        model.addAttribute("grades", grades);
        return "admin/Word-Add";
    }

    @PostMapping("/admin/addWord")
    public String addWord(Word word) {
        wordService.addWord(word);
        return "redirect:/admin/viewWord";
    }
}
