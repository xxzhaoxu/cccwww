package com.echo.mobileweb.filter;


import com.echo.mobileweb.entity.Userinfo;
import com.echo.mobileweb.mapper.UserinfoMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author 35086
 */
@Component
public class UserInterceptor implements HandlerInterceptor {
    @Resource
    private UserinfoMapper userinfoMapper;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        List<Userinfo> userInfos = userinfoMapper.selectAll();
        String uri = request.getRequestURI();
        System.out.println(uri);
        Userinfo currentUserInfo = (Userinfo) request.getSession().getAttribute("user");
        if (currentUserInfo==null){
            response.sendRedirect(request.getContextPath()+"/login");
            return false;
        }
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
