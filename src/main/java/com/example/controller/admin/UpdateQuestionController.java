package com.example.controller.admin;

import com.example.pojo.Question;
import com.example.pojo.Word;
import com.example.service.QuestionService;
import com.example.service.WordService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class UpdateQuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private WordService wordService;

    /**
     * 查看问题
     */
    @RequestMapping("/admin/viewQuestion")
    public String viewQuestion(Model model,
                               @RequestParam(value = "page", defaultValue = "1") Integer pageNum,
                               @RequestParam(value = "size", defaultValue = "6") Integer pageSize,
                               @RequestParam(value = "keywords", defaultValue = "") String keywords) {

        Question temp = new Question();
        temp.setQuestion(keywords);
        PageInfo<Question> pageInfo = questionService.queryAll(temp, pageNum, pageSize);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("keywords", keywords);
        return "admin/Question-View";
    }


    /**
     * 修改问题
     */
    //去修改页面
    @RequestMapping("/admin/toUpdateQuestion/{questionId}")
    public String toUpdateQuestion(@PathVariable("questionId") Integer questionId, Model model) {

        Question question = questionService.queryById(questionId);

        model.addAttribute("question", question);
        return "admin/Question-Update";
    }

    //进行修改提交，回到view页面
    @RequestMapping("/admin/updateQuestion/{id}")
    public String updateQuestion(@PathVariable("id") Integer id, Question question) {
        questionService.update(question);
        return "redirect:/admin/viewQuestion";
    }

    /**
     * 删除问题
     */

    @RequestMapping("/admin/deleteQuestion/{questionId}")
    public String deleteQuestion(@PathVariable("questionId") Integer questionId) {

        questionService.deleteById(questionId);

        return "redirect:/admin/viewQuestion";
    }

    /**
     * 增加问题
     */

    @RequestMapping("/admin/toAddQuestion")
    public String toAaddQuestion() {
        return "admin/Question-Add";
    }

    @PostMapping("/admin/addQuestion")
    public String addQuestion(Question question) throws ParseException {
        questionService.insert(question);
        return "redirect:/admin/viewQuestion";
    }

//    /**
//     * 创建测试数据
//     *
//     * @return
//     * @throws ParseException
//     */
//    @GetMapping("/admin/question/build")
//    @ResponseBody
//    public String build() throws ParseException {
//
//
//        List<Word> words = wordService.queryAllWord("");
//        List<String> explainList = words.stream().map(p-> p.getExplanation()).collect(Collectors.toList());
//        for (Word word : words) {
//            Question question = new Question();
//            question.setWordid(word.getWordId());
//            question.setQuestion(word.getWordName());
//
//
//            // 设置答案
//            if (word.getWordId() % 4 == 0) {
//                question.setOptiona(word.getExplanation());
//                question.setAnswer("A");
//            }
//            if (word.getWordId() % 4 == 1) {
//                question.setOptionb(word.getExplanation());
//                question.setAnswer("B");
//            }
//            if (word.getWordId() % 4 == 2) {
//                question.setOptionc(word.getExplanation());
//                question.setAnswer("C");
//            }
//            if (word.getWordId() % 4 == 3) {
//                question.setOptiond(word.getExplanation());
//                question.setAnswer("D");
//            }
//
//            // 设置其他选项
//            Set<Integer> setList  = new HashSet<>();
//            if(question.getOptiona() == null) {
//                int x = new Random().nextInt(explainList.size());
//                while (setList.contains(x)) {
//                    x = new Random().nextInt(explainList.size());
//                }
//                setList.add(x);
//                question.setOptiona(explainList.get(x));
//
//            }
//            if(question.getOptionb() == null) {
//                int x = new Random().nextInt(explainList.size());
//                while (setList.contains(x)) {
//                    x = new Random().nextInt(explainList.size());
//                }
//                setList.add(x);
//                question.setOptionb(explainList.get(x));
//            }
//            if(question.getOptionc() == null) {
//                int x = new Random().nextInt(explainList.size());
//                while (setList.contains(x)) {
//                    x = new Random().nextInt(explainList.size());
//                }
//                setList.add(x);
//                question.setOptionc(explainList.get(x));
//            }
//            if(question.getOptiond() == null) {
//                int x = new Random().nextInt(explainList.size());
//                while (setList.contains(x)) {
//                    x = new Random().nextInt(explainList.size());
//                }
//                setList.add(x);
//                question.setOptiond(explainList.get(x));
//            }
//
//            questionService.insert(question);
//
//        }
//        return "success";
//    }

//    /**
//     * 创建测试数据
//     *
//     * @return
//     * @throws ParseException
//     */
//    @GetMapping("/admin/question/build2")
//    @ResponseBody
//    public String build2() throws ParseException {
//
//
//        List<Word> words = wordService.queryAllWord("");
//        List<String> nameList = words.stream().map(p-> p.getWordName()).collect(Collectors.toList());
//        for (Word word : words) {
//            Question question = new Question();
//            question.setWordid(word.getWordId());
//            question.setQuestion(word.getExplanation());
//
//
//            // 设置答案
//            if (word.getWordId() % 4 == 0) {
//                question.setOptiona(word.getWordName());
//                question.setAnswer("A");
//            }
//            if (word.getWordId() % 4 == 1) {
//                question.setOptionb(word.getWordName());
//                question.setAnswer("B");
//            }
//            if (word.getWordId() % 4 == 2) {
//                question.setOptionc(word.getWordName());
//                question.setAnswer("C");
//            }
//            if (word.getWordId() % 4 == 3) {
//                question.setOptiond(word.getWordName());
//                question.setAnswer("D");
//            }
//
//            // 设置其他选项
//            Set<Integer> setList  = new HashSet<>();
//            if(question.getOptiona() == null) {
//                int x = new Random().nextInt(nameList.size());
//                while (setList.contains(x)) {
//                    x = new Random().nextInt(nameList.size());
//                }
//                setList.add(x);
//                question.setOptiona(nameList.get(x));
//
//            }
//            if(question.getOptionb() == null) {
//                int x = new Random().nextInt(nameList.size());
//                while (setList.contains(x)) {
//                    x = new Random().nextInt(nameList.size());
//                }
//                setList.add(x);
//                question.setOptionb(nameList.get(x));
//            }
//            if(question.getOptionc() == null) {
//                int x = new Random().nextInt(nameList.size());
//                while (setList.contains(x)) {
//                    x = new Random().nextInt(nameList.size());
//                }
//                setList.add(x);
//                question.setOptionc(nameList.get(x));
//            }
//            if(question.getOptiond() == null) {
//                int x = new Random().nextInt(nameList.size());
//                while (setList.contains(x)) {
//                    x = new Random().nextInt(nameList.size());
//                }
//                setList.add(x);
//                question.setOptiond(nameList.get(x));
//            }
//
//            questionService.insert(question);
//
//        }
//        return "success";
//    }

}
