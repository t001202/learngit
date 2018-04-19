package com.huali.ai.aiban.controller.user;

import com.huali.ai.aiban.constant.Constant;
import com.huali.ai.aiban.constant.Result;
import com.huali.ai.aiban.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created By WangYx
 * Date: 2018/4/19
 * Description:
 */
@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseController {

    @RequestMapping("/login" + Constant.GO)
    public Result userLogin(HttpServletRequest req, HttpServletResponse res){

        return null;
    }
}