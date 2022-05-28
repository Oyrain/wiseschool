package com.atguigu.zhxy.controller;

import com.atguigu.zhxy.pojo.Student;
import com.atguigu.zhxy.service.StudentService;
import com.atguigu.zhxy.utils.MD5;
import com.atguigu.zhxy.utils.Result;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api(tags = "学生控制器")
@RestController
@RequestMapping("/sms/studentController")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/getStudentByOpr/{currentPage}/{pageSize}")
    public Result getStudentByOpr( @ApiParam("页码数") @PathVariable("currentPage")Integer currentPage,
                                   @ApiParam("页大小") @PathVariable("pageSize")Integer pageSize,
                                   @ApiParam("查询条件转换后端数据模型") Student student){

        //设置分页条件
        Page<Student> page = new Page<>(currentPage,pageSize);
        //获取分页的学生信息
        Page<Student> studnets = studentService.getStudentByOpr(page,student);
        //返回学生信息
        return Result.ok(studnets);
    }

    @ApiOperation("增加学生信息")
    @PostMapping("/addOrUpdateStudent")
    public Result addOrUpdateStudent(@RequestBody Student student){
        //对学生的密码进行加密
        if (!Strings.isEmpty(student.getPassword())) {
            student.setPassword(MD5.encrypt(student.getPassword()));
        }
        //保存学生信息进入数据库
        studentService.saveOrUpdate(student);
        return Result.ok();
    }

}
