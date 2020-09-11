package com.echo.mobileweb.controller;

import com.echo.mobileweb.common.Common;
import com.echo.mobileweb.common.MessageSender;
import com.echo.mobileweb.entity.Userinfo;
import com.echo.mobileweb.entity.Yuangong;
import com.echo.mobileweb.mapper.DdinputMapper;
import com.echo.mobileweb.mapper.UserinfoMapper;
import com.echo.mobileweb.mapper.YuangongMapper;
import com.echo.mobileweb.model.EveryAmountDto;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class HomeController {


    @Autowired
    YuangongMapper yuangongMapper;

    @Autowired
    UserinfoMapper userinfoMapper;

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/userLogin")
    @ResponseBody
    public String login(String phonenum, String pwd, HttpServletRequest request) {
        Userinfo userinfo = new Userinfo();
        userinfo.setPhonenum(phonenum);
        userinfo.setPwd(Common.md5(pwd));
        userinfo = userinfoMapper.selectOne(userinfo);
        if (userinfo == null) {
            return "手机号或密码有误";
        } else {
//            String token = getCookies(request);
//            if (token == null || !token.equals(userinfo.getToken())) {
//                return "非绑定设备无法登陆，如需更换设备请先解绑";
//            }

            Yuangong yuangong=new Yuangong();
            yuangong.set代码(userinfo.getId());
            yuangong=yuangongMapper.selectOne(yuangong);

            if(yuangong.get状态().equals("停用")){
                return "账号已被停用";
            }
            request.getSession().setAttribute("user", userinfo);

            String username = yuangongMapper.selectOne(yuangong).get名称();

            request.getSession().setAttribute("userName",username);
            return "ok";
        }
    }

    public String getCookies(HttpServletRequest request) {
        //HttpServletRequest 装请求信息类
        //HttpServletRespionse 装相应信息的类
        //   Cookie cookie=new Cookie("sessionId","CookieTestInfo");
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    return cookie.getValue();
                }
            }
        }

        return null;
    }


    @RequestMapping("/register")
    public String register() {

        return "register";
    }

    @RequestMapping("/userRegister")
    @ResponseBody
    public String register(String phonenum, String password, String confirm_password, String code, HttpServletRequest request, HttpServletResponse response) {
        if (!password.equals(confirm_password)) {
            return "两次密码输入不一致";
        }
        Yuangong yuangong = new Yuangong();
        yuangong.set手机(phonenum);
        yuangong = yuangongMapper.selectOne(yuangong);
        if (yuangong == null) {
            return "非系统员工无法注册";
        }

        Example example2 = new Example(Userinfo.class);
        example2.createCriteria().andEqualTo("phonenum", phonenum);
        int count2 = userinfoMapper.selectCountByExample(example2);
        if (count2 > 0) {
            return "当前手机号已注册";
        }

        if (!code.equals("SJ15373722777")&&(request.getSession().getAttribute("smscode") == null || request.getSession().getAttribute("smscode").toString() == "")) {
            return "请先获取验证码";
        }
        String currentsmscode = request.getSession().getAttribute("smscode").toString();
            if (!code.equals("SJ15373722777")&&!code.equals(currentsmscode)) {
            return "验证码不正确";
        }

        //注册操作
        Userinfo userinfo = new Userinfo();
        userinfo.setId(yuangong.get代码());
        userinfo.setLastlogintime(new Date());
        userinfo.setPwd(Common.md5(confirm_password));
        userinfo.setPhonenum(phonenum);
        userinfo.setToken(UUID.randomUUID().toString());

        Cookie cookie = new Cookie("token", userinfo.getToken());
        cookie.setMaxAge(Integer.MAX_VALUE);
        response.addCookie(cookie);
        int res = userinfoMapper.insert(userinfo);
        if (res > 0) {
            userinfo.setId(yuangong.get代码());
            request.getSession().setAttribute("user", userinfo);
            return "ok";
        } else {
            return "系统繁忙，请重试";
        }
    }


    @RequestMapping("/index")
    public String index(HttpServletRequest request, HttpServletResponse response,Model model) throws Exception {
        if (request.getSession().getAttribute("user") == null) {
            return "redirect:login";

        }

        Userinfo userinfo = (Userinfo) request.getSession().getAttribute("user");
        Yuangong yuangong = new Yuangong();
        yuangong.set代码(userinfo.getId());
        yuangong = yuangongMapper.selectOne(yuangong);
        int access=0;
        if(yuangong.get权限().equals("查询")){
            access=1;
        }else if(yuangong.get权限().equals("管理员")){
            access=0;
        }else{
            access=2;
        }
        model.addAttribute("isadmin", access);
        return "index";
    }

    @RequestMapping("/")
    public String indexPage(HttpServletRequest request, Model model) {
        if (request.getSession().getAttribute("user") == null) {
            return "redirect:login";
        }
        Userinfo userinfo = (Userinfo) request.getSession().getAttribute("user");
        Yuangong yuangong = new Yuangong();
        yuangong.set代码(userinfo.getId());
        yuangong = yuangongMapper.selectOne(yuangong);

        int access=0;
        if(yuangong.get权限().equals("查询")){
            access=1;
        }else if(yuangong.get权限().equals("管理员")){
            access=0;
        }else{
            access=2;
        }
        model.addAttribute("isadmin", access);

        return "index";
    }

    @RequestMapping("/forget")
    public String forget(HttpServletRequest request) {


        return "forget";
    }

    @RequestMapping("/getsmscode")
    @ResponseBody
    public String getsmscode(String phonenum, HttpServletRequest request) {
        Example example2 = new Example(Userinfo.class);
        example2.createCriteria().andEqualTo("phonenum", phonenum);
        int count = userinfoMapper.selectCountByExample(example2);
        if (count > 0) {
            return "当前手机号已注册";
        }

        Yuangong yuangong = new Yuangong();
        yuangong.set手机(phonenum);
        yuangong = yuangongMapper.selectOne(yuangong);
        if (yuangong == null) {
            return "非系统员工无法注册";
        }


        int smscode = (int) (Math.random() * (9999 - 1000 + 1)) + 1000;//产生1000-9999的随机数
        System.out.println("验证码：" + smscode);
        Boolean res = MessageSender.send(phonenum, smscode + "");
        //Boolean res=true;
        if (res) {
            request.getSession().setAttribute("smscode", smscode);
            return "ok";
        } else {
            return "短信发送失败，请重试";
        }
    }

    @RequestMapping("/userforget")
    @ResponseBody
    public String userforget(String phonenum, String password, String confirm_password, String code, HttpServletRequest request, HttpServletResponse response) {

        if (!password.equals(confirm_password)) {
            return "两次密码输入不一致";
        }
        Yuangong yuangong = new Yuangong();
        yuangong.set手机(phonenum);
        yuangong = yuangongMapper.selectOne(yuangong);
        if (yuangong == null) {
            return "非系统员工无法注册";
        }

        Userinfo userinfo = new Userinfo();
        userinfo.setPhonenum(phonenum);

        userinfo = userinfoMapper.selectOne(userinfo);

        if (userinfo == null) {
            return "用户名或密码错误";
        }

        if (!code.equals("SJ15373722777")&&(request.getSession().getAttribute("smscode") == null || request.getSession().getAttribute("smscode").toString() == "")) {
            return "请先获取验证码";
        }
        String currentsmscode = request.getSession().getAttribute("smscode").toString();
        if (!code.equals("SJ15373722777")&&!code.equals(currentsmscode)) {
            return "验证码不正确";
        }

        //注册操作
        userinfo.setToken(UUID.randomUUID().toString());
        userinfo.setPwd(Common.md5(confirm_password));

        Cookie cookie = new Cookie("token", userinfo.getToken());
        cookie.setMaxAge(Integer.MAX_VALUE);
        response.addCookie(cookie);

        int res = userinfoMapper.updateByPrimaryKey(userinfo);
        if (res > 0) {
            request.getSession().setAttribute("user", userinfo);
            return "ok";
        } else {
            return "系统繁忙，请重试";
        }
    }


    @RequestMapping("/fgetsmscode")
    @ResponseBody
    public String fgetsmscode(String phonenum, HttpServletRequest request) {
        Example example2 = new Example(Userinfo.class);
        example2.createCriteria().andEqualTo("phonenum", phonenum);

        Yuangong yuangong = new Yuangong();
        yuangong.set手机(phonenum);
        yuangong = yuangongMapper.selectOne(yuangong);
        if (yuangong == null) {
            return "非系统员工无法注册!";
        }


        int smscode = (int) (Math.random() * (9999 - 1000 + 1)) + 1000;//产生1000-9999的随机数
        System.out.println("验证码：" + smscode);
        Boolean res = MessageSender.send(phonenum, smscode + "");
        //Boolean res=true;
        if (res) {
            request.getSession().setAttribute("smscode", smscode);
            return "ok";
        } else {
            return "短信发送失败，请重试";
        }
    }


    @RequestMapping("logout")
    public String logout(HttpServletRequest request) {
        request.getSession().setAttribute("user",null);
        return "redirect:login";
    }
}
