package com.atguigu.zhxy.controller;

import com.atguigu.zhxy.pojo.Clazz;
import com.atguigu.zhxy.service.ClazzService;
import com.atguigu.zhxy.utils.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "班级控制器")
@RestController
@RequestMapping("/sms/clazzController")
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    @ApiOperation("查询班级信息,分页带条件")
    @GetMapping("/getClazzsByOpr/{currentPage}/{pageSize}")
    public Result getClazzByOpr( @ApiParam("页码数")  @PathVariable("currentPage") Integer currentPage,
                                 @ApiParam("页大小")  @PathVariable("pageSize") Integer pageSize,
                                 @ApiParam("查询条件") Clazz clazz){
        //设置分页信息
        Page<Clazz> page = new Page<>(currentPage,pageSize);
        IPage<Clazz> clazzIPage = clazzService.getClazzByOpr(page,clazz);
        return Result.ok(clazzIPage);
    }

    @ApiOperation("保存或者修改班级信息")
    @PostMapping("/saveOrUpdateClazz")
    public Result saveOrUpdateClazz(@ApiParam("JSON转换后端Clazz数据模型") @RequestBody Clazz clazz){
        clazzService.saveOrUpdate(clazz);
        return Result.ok();
    }

    @ApiOperation("删除一个或者多个班级信息")
    @DeleteMapping("/deleteClazz")
    public Result deleteClazzByIds(@RequestBody List<Integer> ids){
        clazzService.removeByIds(ids);
        return Result.ok();
    };

    @ApiOperation("获取所有班级的JSON")
    @GetMapping("/getClazzs")
    public Result getClazzs(){
        List<Clazz> list = clazzService.list();
        return Result.ok(list);
    }
}
