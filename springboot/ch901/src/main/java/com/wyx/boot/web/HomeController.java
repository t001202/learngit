package com.wyx.boot.web;

import com.wyx.boot.domain.Msg;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by WangYx on 2017/9/1.
 */
@Controller
public class HomeController {

    @RequestMapping("/")
    public String index(Model model){
        Msg msg = new Msg("测试标题", "测试内容", "额外信息,只对管理员希显示");
        model.addAttribute(msg);
        return "home";
    }
}
