package com.example.controller;

import com.example.dto.GradeQuestionVO;
import com.example.pojo.*;
import com.example.service.*;
import com.example.util.MathUtil;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class UserCenterController {


    @Autowired
    private WordService wordService;

    @Autowired
    private BookService bookService;


    @Autowired
    private ListenService listenService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private GradeService gradeService;

    @Autowired
    private StrangeWordService strangeWordService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestionExerciseService questionExerciseService;

    public static Map<String, String> typeMap = new HashMap<>();

    static {
        typeMap.put("book", "阅读");
        typeMap.put("listen", "听力");
        typeMap.put("word", "单词");
        typeMap.put("question", "题目");
    }


    /**
     * 用户中心
     *
     * @return
     */
    @GetMapping("/userCenter")
    public String manage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loginUser");
        if (user == null) {
            return "/login";
        }
        model.addAttribute("user", user);

        // 学习进度
        List<Task> taskList = taskService.findByUserId(user.getUserId());
        for (Task task : taskList) {
            Integer total = 0;
            if ("word".equals(task.getType())) {
                total = wordService.queryAllWordNumberByGrade(task.getGrade());
            } else if ("book".equals(task.getType())) {
                total = bookService.countByGrade(task.getGrade());
            } else if ("listen".equals(task.getType())) {
                total = listenService.countByGrade(task.getGrade());
            }
            Integer finish = taskService.countByTask(task);
            task.setTotal(total);
            task.setFinish(finish);
            task.setPercent(MathUtil.toPercent(finish, total));

            task.setStudyDays(taskService.countDaysByTaskIdAndUserId(task.getId(), user.getUserId()));
        }

        model.addAttribute("taskList", taskList);
        return "userCenter";
    }

    /**
     * 选择页面
     *
     * @return
     */
    @GetMapping("/userCenter/{type}")
    public String manage(@PathVariable("type") String type, HttpSession session, Model model) {
        User user = (User) session.getAttribute("loginUser");
        if (user == null) {
            return "/login";
        }
        model.addAttribute("user", user);
        List<Grade> grades = gradeService.queryAllGrade(null);
        model.addAttribute("type", type);
        model.addAttribute("typeName", typeMap.get(type));

        if ("question".equals(type)) {
            List<GradeQuestionVO> questionVOS = new ArrayList<>();
            for (Grade grade : grades) {
                GradeQuestionVO gradeQuestionVO = new GradeQuestionVO();
                BeanUtils.copyProperties(grade, gradeQuestionVO);

                Integer hadFinish = questionService.getHadFinish(user.getUserId(), grade.getGradeId());
                Integer notFinish = questionService.getNotFinish(user.getUserId(), grade.getGradeId());
                gradeQuestionVO.setQuestionHadFinish(hadFinish);
                gradeQuestionVO.setQuestionNotFinish(notFinish);

                Integer total = hadFinish + notFinish;
                gradeQuestionVO.setQuestionTotal(total);
                gradeQuestionVO.setQuestionPercent(MathUtil.toPercent(hadFinish, total));
                gradeQuestionVO.setStudyDays(questionExerciseService.countDaysByUserIdAndGrade(user.getUserId(), grade.getGradeId()));
                questionVOS.add(gradeQuestionVO);
            }
            model.addAttribute("gradeList", questionVOS);
        } else {
            model.addAttribute("gradeList", grades);
        }
        return "userCenterSelect";
    }

    /**
     * 选择提交
     *
     * @return
     */
    @GetMapping("/userCenter/doSelect")
    public String doSelect(@RequestParam("grade") Integer grade,
                           @RequestParam("type") String type, HttpSession session, Model model) {
        User user = (User) session.getAttribute("loginUser");
        if (user == null) {
            return "/login";
        }

        Grade grade1 = gradeService.queryGradeById(grade);
        String gradeName = grade1 != null ? grade1.getGradeName() : "";

        Task task = new Task();
        task.setType(type);
        task.setGrade(grade);
        task.setUserId(user.getUserId());
        task.setName(typeMap.get(type) + gradeName);
        taskService.addTask(task);
        return "redirect:/userCenter";
    }

    /**
     * 取消进度
     *
     * @param id
     * @param session
     * @return
     */
    @GetMapping("/userCenter/task/cancel")
    public String cancelTask(@RequestParam("id") Integer id, HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        if (user == null) {
            return "/login";
        }

        Task task = taskService.queryTaskById(id);
        if (task == null || !Objects.equals(task.getUserId(), user.getUserId())) {
            return "redirect:/userCenter";
        }
        // 删除任务进度
        taskService.deleteTask(id);

        return "redirect:/userCenter";
    }

    /**
     * 完成一个
     *
     * @param session
     * @return
     */
    @GetMapping("/userCenter/task/finishDetail")
    public String finishTaskDetail(@RequestParam("taskId") Integer taskId,
                                   @RequestParam("businessId") Integer businessId,
                                   HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        if (user == null) {
            return "/login";
        }


        TaskDetail taskDetail = new TaskDetail();
        taskDetail.setBusinessId(businessId);
        taskDetail.setUserId(user.getUserId());
        taskDetail.setTaskId(taskId);
        taskDetail.setCreateTime(new Date());
        taskService.addTaskDetail(taskDetail);

        return "redirect:/userCenter/task/" + taskId;
    }

    /**
     * 取消完成一个
     *
     * @param session
     * @return
     */
    @GetMapping("/userCenter/task/cancelFinishDetail")
    public String cancelFinishTaskDetail(@RequestParam("taskId") Integer taskId,
                                         @RequestParam("businessId") Integer businessId,
                                         HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        if (user == null) {
            return "/login";
        }

        taskService.deleteTaskDetail(taskId, businessId);
        return "redirect:/userCenter/task/" + taskId;
    }

    @GetMapping("/userCenter/task/restart/{taskId}")
    public String restart(@PathVariable("taskId") Integer taskId, HttpSession session, Model model) {
        User user = (User) session.getAttribute("loginUser");
        if (user == null) {
            return "/login";
        }
        taskService.deleteTaskDetail(taskId);
        return "redirect:/userCenter/task/" + taskId;
    }

    /**
     * 用户中心
     *
     * @return
     */
    @GetMapping("/userCenter/task/{taskId}")
    public String taskDetail(@PathVariable("taskId") Integer taskId, HttpSession session, Model model) {
        User user = (User) session.getAttribute("loginUser");
        if (user == null) {
            return "/login";
        }
        model.addAttribute("user", user);

        // 学习进度
        Task task = taskService.queryTaskById(taskId);
        if (task == null) {
            return "redirect:/";
        }

        if ("word".equals(task.getType())) {
            Integer total = wordService.queryAllWordNumberByGrade(task.getGrade());
            Integer finish = taskService.countByTask(task);
            task.setTotal(total);
            task.setFinish(finish);
            if (finish.equals(total)) {
                return "redirect:/userCenter";
            }
            // 查询未完成的，下一个
            Word word = wordService.getNext(taskId);
            if (word == null) {
                return "redirect:/userCenter";
            }

            model.addAttribute("word", word);
            model.addAttribute("businessId", word.getWordId());
            task.setPercent(MathUtil.toPercent(finish, total));

            model.addAttribute("task", task);
            model.addAttribute("isTask", true);

            // 判断单词是不是在生词本中
            StrangeWord strangeWord = strangeWordService.findByWordIdAndUserIdAndTaskId(word.getWordId(), user.getUserId(), taskId);
            model.addAttribute("strangeWord", strangeWord);


            return "wordDetail";
        } else if ("book".equals(task.getType())) {
            Integer total = bookService.countByGrade(task.getGrade());
            Integer finish = taskService.countByTask(task);
            task.setTotal(total);
            task.setFinish(finish);
            if (finish.equals(total)) {
                return "redirect:/userCenter";
            }
            // 查询未完成的，下一个
            Book book = bookService.getNext(taskId);
            if (book == null) {
                return "redirect:/userCenter";
            }
            book.setContent(book.getContent().replace("\r\n", "<br/>").replace("\n", "<br/>"));

            model.addAttribute("businessId", book.getBookId());
            model.addAttribute("book", book);
            task.setPercent(MathUtil.toPercent(finish, total));
            model.addAttribute("task", task);

            model.addAttribute("isTask", true);
            return "bookDetail";
        } else if ("listen".equals(task.getType())) {
            Integer total = listenService.countByGrade(task.getGrade());
            Integer finish = taskService.countByTask(task);
            task.setTotal(total);
            task.setFinish(finish);
            if (finish.equals(total)) {
                return "redirect:/userCenter";
            }
            // 查询未完成的，下一个
            Listen listen = listenService.getNext(taskId);
            if (listen == null) {
                return "redirect:/userCenter";
            }
            listen.setContent(listen.getContent().replace("\r\n", "<br/>").replace("\n", "<br/>"));

            model.addAttribute("listen", listen);
            model.addAttribute("businessId", listen.getListenId());

            task.setPercent(MathUtil.toPercent(finish, total));
            model.addAttribute("task", task);

            model.addAttribute("isTask", true);
            return "listenDetail";
        }
        return "redirect:/userCenter";
    }


    /**
     * 添加生词本
     *
     * @param session
     * @return
     */
    @GetMapping("/userCenter/strangeWord/add")
    public String addStrangeWord(@RequestParam("taskId") Integer taskId,
                                 @RequestParam("wordId") Integer wordId,
                                 HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        if (user == null) {
            return "/login";
        }


        // 完成任务
        TaskDetail taskDetail = new TaskDetail();
        taskDetail.setBusinessId(wordId);
        taskDetail.setUserId(user.getUserId());
        taskDetail.setTaskId(taskId);
        taskDetail.setCreateTime(new Date());
        taskService.addTaskDetail(taskDetail);

        // 加入生词本
        StrangeWord strangeWord = new StrangeWord();
        strangeWord.setWordId(wordId);
        strangeWord.setTaskId(taskId);
        strangeWord.setUserId(user.getUserId());
        strangeWord.setCreateTime(new Date());
        strangeWordService.addStrangeWord(strangeWord);


        return "redirect:/userCenter/task/" + taskId;
    }

    /**
     * 移除生词本
     *
     * @param session
     * @return
     */
    @GetMapping("/userCenter/strangeWord/remove")
    public String removeStrangeWord(@RequestParam("taskId") Integer taskId,
                                    @RequestParam("wordId") Integer wordId,
                                    HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        if (user == null) {
            return "/login";
        }

        strangeWordService.deleteByWordIdAndUserIdAndTaskId(wordId, user.getUserId(), taskId);
        return "redirect:/userCenter/task/" + taskId;
    }


    /**
     * 任务统计图
     *
     * @param session
     * @return
     */
    @GetMapping("/userCenter/taskChart")
    public String taskChart(@RequestParam("taskId") Integer taskId,
                            Model model,
                            HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        if (user == null) {
            return "/login";
        }
        Task task = taskService.queryTaskById(taskId);
        if (task == null || !Objects.equals(task.getUserId(), user.getUserId())) {
            return "/userCenter";
        }

        model.addAttribute("task", task);

        // 查询所有单词字母数量
        List<Word> wordList = wordService.queryByGradeId(task.getGrade(), null);
        String[] letterArr = new String[26];
        Integer[] letterNumArr = new Integer[26];
        char a = 'A';
        for (int i = 0; i < 26; i++) {
            letterArr[i] = String.valueOf(a++);
            letterNumArr[i] = 0;
        }

        for (Word word : wordList) {
            int i = 0;
            for (String name : letterArr) {
                if (word.getWordName() != null && (word.getWordName().startsWith(name) || word.getWordName().startsWith(name.toLowerCase()))) {
                    letterNumArr[i]++;
                }
                i++;
            }
        }
        model.addAttribute("letterArr", letterArr);
        model.addAttribute("letterNumArr", letterNumArr);

        // 查询学习进度
        Integer total = 0;
        if ("word".equals(task.getType())) {
            total = wordService.queryAllWordNumberByGrade(task.getGrade());
        } else if ("book".equals(task.getType())) {
            total = bookService.countByGrade(task.getGrade());
        } else if ("listen".equals(task.getType())) {
            total = listenService.countByGrade(task.getGrade());
        }
        Integer finish = taskService.countByTask(task);

        Integer[] studyNum = {finish, total - finish};
        model.addAttribute("studyNum", studyNum);


        // 折线图
        List<String> sevenDates = getSevenDate();
        model.addAttribute("dateArr", sevenDates.toArray());
        Integer[] dateNumArr = new Integer[7];
        for (int i = 0; i < 7; i++) {
            dateNumArr[i] = taskService.countByDays(taskId, user.getUserId(), 6 - i);
        }
        model.addAttribute("dateNumArr", dateNumArr);

        return "taskChart";
    }

    // 获得最近1周的日期
    public List<String> getSevenDate() {
        List<String> dateList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");

        for (int i = 0; i < 7; i++) {
            Date date = DateUtils.addDays(new Date(), -i);
            String formatDate = sdf.format(date);
            dateList.add(0, formatDate);
        }
        return dateList;
    }


    /***
     * 开始做题
     * @return
     */
    @GetMapping("/userCenter/doQuestion")
    public String doQuestion(@RequestParam("grade") Integer grade, HttpSession session, Model model) {
        Grade gradeObj = gradeService.queryGradeById(grade);

        User user = (User) session.getAttribute("loginUser");
        Question question = questionService.getNextQuestionByUserIdAndGrade(user.getUserId(), grade);
        if(question == null) {
            return "redirect:/userCenter/question";
        }

        model.addAttribute("question", question);

        model.addAttribute("grade", gradeObj != null ? gradeObj : new Grade());

        model.addAttribute("notFinish", questionService.getNotFinish(user.getUserId(), grade));
        model.addAttribute("hadFinish", questionService.getHadFinish(user.getUserId(), grade));
        return "doQuestion";
    }

    /***
     * 回答做题
     * @return
     */
    @GetMapping("/userCenter/doQuestion/answer")
    public String questionAnswer(@RequestParam("questionId") Integer questionId,
                                 @RequestParam("grade") Integer grade,
                                 @RequestParam("answer") String answer,
                                 HttpSession session, Model model) {
        Question question = questionService.queryById(questionId);
        if (question == null) {
            question = new Question();
        }

        Grade gradeObj = gradeService.queryGradeById(grade);

        User user = (User) session.getAttribute("loginUser");

        QuestionExercise questionExercise = new QuestionExercise();
        questionExercise.setQuestionid(questionId);
        questionExercise.setWordid(question.getWordid());
        questionExercise.setUserid(user.getUserId());
        questionExercise.setAnswer(answer);
        questionExercise.setFlag(Objects.equals(question.getAnswer(), answer) ? 1 : 0);
        questionExercise.setCreatetime(new Date());
        questionExerciseService.insert(questionExercise);

        model.addAttribute("question", question);
        model.addAttribute("questionExercise", questionExercise);
        model.addAttribute("grade", gradeObj != null ? gradeObj : new Grade());

        model.addAttribute("notFinish", questionService.getNotFinish(user.getUserId(), grade));
        model.addAttribute("hadFinish", questionService.getHadFinish(user.getUserId(), grade));
        return "doQuestionAnswer";
    }

    /***
     * 错题记录
     * @return
     */
    @GetMapping("/userCenter/doQuestion/wrongBook")
    public String wrongQuestion(@RequestParam("grade") Integer grade,
                                @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo, HttpSession session, Model model) {
        Grade gradeObj = gradeService.queryGradeById(grade);

        User user = (User) session.getAttribute("loginUser");
        QuestionExercise questionExercise = questionExerciseService.pageWrongByUserIdAndGrade(user.getUserId(), grade, pageNo - 1);
        model.addAttribute("questionExercise", questionExercise);

        if (questionExercise != null) {
            Question question = questionService.queryById(questionExercise.getQuestionid());
            model.addAttribute("question", question);
        }

        model.addAttribute("grade", gradeObj != null ? gradeObj : new Grade());

        model.addAttribute("notFinish", questionService.getNotFinish(user.getUserId(), grade));
        model.addAttribute("hadFinish", questionService.getHadFinish(user.getUserId(), grade));
        model.addAttribute("total", questionExerciseService.countWrongByUserIdAndGrade(user.getUserId(), grade));
        model.addAttribute("pageNo", pageNo);
        return "doQuestionWrong";
    }

    /***
     * 清空学习记录
     * @return
     */
    @GetMapping("/userCenter/doQuestion/clear")
    public String clear(@RequestParam("grade") Integer grade, HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        questionExerciseService.deleteByUserIdAndGrade(user.getUserId(), grade);
        return "redirect:/userCenter/question";
    }

}
