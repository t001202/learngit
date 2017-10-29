package com.example.controller;

import com.example.domain.User;
import com.example.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by WangYx on 2017/9/12.
 */
@Controller
public class HomeController {

    @Autowired
    private UserService service;

    User user = new User();

    //入口
    @RequestMapping(value = "/home")
    public String home(Model model) {
        model.addAttribute("user",user);
        return "a";
    }

    //提交表单后进行数据读取，并将数据传出
    @RequestMapping(value = "/bb",method = RequestMethod.POST)
    public String bb(User user,Model model) {
        Page page = PageHelper.startPage(1, 5);
        List<User> users = service.selectAllUser();
        PageInfo<User> userPageInfo = new PageInfo<>(users);
        List<User> userList = userPageInfo.getList();
        model.addAttribute("user", user);
        model.addAttribute("message", user.getMsg());
        return "b";
    }
}
