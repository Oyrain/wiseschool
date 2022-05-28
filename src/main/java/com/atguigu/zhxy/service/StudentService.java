package com.atguigu.zhxy.service;

import com.atguigu.zhxy.pojo.Admin;
import com.atguigu.zhxy.pojo.LoginForm;
import com.atguigu.zhxy.pojo.Student;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;


public interface StudentService extends IService<Student> {

    /**
     * 登录
     * @param loginForm
     * @return
     */
    Student login(LoginForm loginForm);

    Student getStudentById(Long userId);

    Page<Student> getStudentByOpr(Page<Student> page, Student student);
}
