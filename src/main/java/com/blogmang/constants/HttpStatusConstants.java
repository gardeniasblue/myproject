package com.blogmang.constants;

/**
 * @program: itoken
 * @classname: HttpStatusConstants
 * @description: Http状态常量
 * @author: 南街
 * @create: 2019-08-15 09:11
 **/
public enum  HttpStatusConstants {
    SUCCESS(100,"操作成功"),
    OK(200,"验证成功"),
    USERBEEXIST(205,"邮箱已被注册"),
    PASSWORD_ERROR(300,"密码不正确"),
    STATIC_ERROR(301,"账户锁定"),
    PASSWORDNUMBER_OUT(302,"用户名或密码错误次数过多"),
    USERORPASSWORD_ERROR(303,"用户名或密码错误"),
    UNKNOW_USER(304,"未知账户"),
    BAD_REQUEST(400,"请求参数有误"),
    UNAUTHORIZED(401,"登录失败"),
    INTERNAL_SERVER_ERROR(500,"服务器遇到了一个未曾预料的状况"),
    BAD_GATEWAY(502,"从上游服务器接收到无效的响应");

    private final int status;
    private final String content;

    HttpStatusConstants(int status, String content) {
        this.status = status;
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public String getContent() {
        return content;
    }
}
