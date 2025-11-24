package com.example.controller.admin;

import com.example.pojo.Grade;
import com.example.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Controller
public class UpdateGradeController {
    @Autowired
    private GradeService gradeService;

    /**
     * 查看公告
     */
    @RequestMapping("/admin/viewGrade")
    public String viewGrade(Model model,
                            @RequestParam(value = "keywords", defaultValue = "") String keywords) {
        List<Grade> grade = gradeService.queryAllGrade(keywords);
        model.addAttribute("grade", grade);
        model.addAttribute("keywords", keywords);
        return "admin/Grade-View";
    }


    /**
     * 修改公告
     */
    //去修改页面
    @RequestMapping("/admin/toUpdateGrade/{gradeId}")
    public String toUpdateGrade(@PathVariable("gradeId") Integer gradeId, Model model) {

        Grade grade = gradeService.queryGradeById(gradeId);
        model.addAttribute("grade", grade);
        return "admin/Grade-Update";
    }

    //进行修改提交，回到view页面
    @RequestMapping("/admin/updateGrade/{gradeId}")
    public String updateGrade(@PathVariable("gradeId") Integer gradeId, Grade grade) {
        grade.setGradeId(gradeId);
        gradeService.updateGrade(grade);
        return "redirect:/admin/viewGrade";
    }

    /**
     * 删除公告
     */

    @RequestMapping("/admin/deleteGrade/{gradeId}")
    public String deleteGrade(@PathVariable("gradeId") Integer gradeId) {

        gradeService.deleteGrade(gradeId);

        return "redirect:/admin/viewGrade";
    }

    /**
     * 增加公告
     */

    @RequestMapping("/admin/toAddGrade")
    public String toAaddGrade() {
        return "admin/Grade-Add";
    }

    @PostMapping("/admin/addGrade")
    public String addGrade(Grade grade) throws ParseException {
        gradeService.addGrade(grade);
        return "redirect:/admin/viewGrade";
    }

}
