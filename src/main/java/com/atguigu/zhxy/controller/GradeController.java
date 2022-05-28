package com.atguigu.zhxy.controller;

import com.atguigu.zhxy.pojo.Grade;
import com.atguigu.zhxy.service.GradeService;
import com.atguigu.zhxy.utils.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "年级控制器")
@RestController
@RequestMapping("/sms/gradeController")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @ApiOperation("分页查询年级信息")
    @GetMapping("/getGrades/{currentPage}/{pageSize}")
    public Result getGradeByOpr(@ApiParam("当前页") @PathVariable("currentPage") Integer currentPage,
                                @ApiParam("显示数据条数") @PathVariable("pageSize") Integer pageSize,
                                @ApiParam("分页查询条件") String gradeName){

        //System.out.println(currentPage+""+pageSize+""+gradeName);
        //设置分页信息
        Page<Grade> page = new Page<>(currentPage,pageSize);
        IPage<Grade> grades = gradeService.getGradeByOpr(page,gradeName);
        return Result.ok(grades);
    }

    @ApiOperation("添加或者修改年级信息")
    @PostMapping("saveOrUpdateGrade")
    public Result saveOrUpdate(@ApiParam("JSON的grade对象转换后台数据模型") @RequestBody Grade grade){
        //调用服务层方法,实现添加或者修改年级信息
        gradeService.saveOrUpdate(grade);
        return Result.ok();
    }

    @ApiOperation("删除一个或者多个grade信息")
    @DeleteMapping("/deleteGrade")
    public Result deleteGrades(@ApiParam("JSON的年级id集合,映射为后台List<Integer>") @RequestBody List<Integer> ids){
        gradeService.removeByIds(ids);
        return Result.ok();
    }

    @ApiOperation("获取所有Grade信息")
    @GetMapping("/getGrades")
    public Result getGrades(){
        List<Grade> list = gradeService.list();
        return Result.ok(list);
    }

}
