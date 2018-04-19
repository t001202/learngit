package com.huali.ai.aiban.enums;

public enum ResultEnum implements IResultEnum {

    /*****RESULT 1 START *****/
    LOGIN_SUCCESS(11, "登陆成功"),

    PARSE_SUCCESS(12, "解析成功"),

    FLUSH_SUCCESS(13, "刷新成功"),


    /*****RESULT 1 END *****/


    /*****RESULT 0 START *****/

//    PASSWORD_ERROR(01, "密码输入错误"),
//
//    USER_NOT_EXIST(02, "用户不存在"),
//
//    LOGIN_FAIL(03, "登陆失败"),
//
//    YZM_REFRESH_FAIL(04, "请你重新刷新验证码!"),

//    QUERY_FAIL(05, "查询失败"),

    /*****RESULT 0 END *****/

    SUCCESS(1, "返回成功"),

    FAIL(0, "返回失败"),

    ERROR(-1, "未知异常"),

    LOGIN_FAILURE(500, "login failure"),

    PARAMETERS_EMPTY(461, "login parameters can't be empty"),

    NETWORK_ERROR(462, "网络异常，请稍后再试"),

    PARAMTER_ERROR(463, "非法参数"),

    SIGNATURE_NOT_PASS(465, "签名验证不通过"),

    NOT_BIND_ACCOUNT(466, "当前用户未绑定账户"),

    AUTH_GJJ_ACCOUNT_NOT_EXIST_ERROR(1011500, "认证账号不存在"),

    AUTH_USER_NOT_AUTHED_ERROR(1011501, "未认证，请认证后再试"),

    AUTH_BASIC_INFO_ERROR(1011503, "请输入正确的个人信息"),


    ;


    ResultEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private Integer code;
    private String desc;

    @Override
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
