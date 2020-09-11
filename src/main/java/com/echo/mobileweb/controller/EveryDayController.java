package com.echo.mobileweb.controller;

import com.echo.mobileweb.entity.Kehu;
import com.echo.mobileweb.entity.Userinfo;
import com.echo.mobileweb.entity.Yuangong;
import com.echo.mobileweb.mapper.DdinputMapper;
import com.echo.mobileweb.mapper.KehuMapper;
import com.echo.mobileweb.mapper.YuangongMapper;
import com.echo.mobileweb.model.DayDto;
import com.echo.mobileweb.model.DayZj;
import com.echo.mobileweb.model.EveryAmountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class EveryDayController {
    @Autowired
    DdinputMapper ddinputMapper;
    @Autowired
    KehuMapper kehuMapper;
    @Autowired
    YuangongMapper yuangongMapper;

    @RequestMapping("/everyday")
    public String everyDay(HttpServletRequest request, Model model) {
        Userinfo currentUserInfo = (Userinfo) request.getSession().getAttribute("user");
        if (currentUserInfo == null) {
            return "login";
        }

        Yuangong yuangong1=new Yuangong();
        yuangong1.set代码(currentUserInfo.getId());
        yuangong1=yuangongMapper.selectOne(yuangong1);

        if(!yuangong1.get权限().equals("管理员")&&!yuangong1.get权限().equals("查询")){
            return "redirect:login";
        }

        Example example = new Example(Kehu.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("状态", "启用");
        Map<String,String>kehuMap=new HashMap<>();

        List<Kehu> kehuList = kehuMapper.selectByExample(example);

        kehuList.forEach(x->{
            kehuMap.put(x.get商店名称(),x.get商店代码());
        });
        model.addAttribute("date",LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        model.addAttribute("kehuMap", kehuMap);

        Yuangong yuangong=new Yuangong();
        yuangong.set代码(currentUserInfo.getId());
        String username=yuangongMapper.selectOne(yuangong).get名称();
        model.addAttribute("username",username);
        return "everyday";
    }

    @ResponseBody
    @RequestMapping("/getEveryDayData")
    public List<EveryAmountDto> getEveryDayData(Integer selectindex,String shopname,String date) {
        if(selectindex!=0||shopname.equals("全部商户")){
            shopname=null;
        }
        LocalDate day=LocalDate.parse(date);
        String starttime = day.plusDays(-1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd   22:00:00"));
        String endtime = day.format(DateTimeFormatter.ofPattern("yyyy-MM-dd   22:00:00"));
        String inputtime = day.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//        if ((LocalDateTime.now().getHour() == 22 && LocalDateTime.now().getMinute() > 30) || LocalDateTime.now().getHour() > 22) {
//            starttime = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd   22:00:00"));
//            endtime = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd   22:00:00"));
//            inputtime = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//        }
        List<EveryAmountDto> list = ddinputMapper.getEveryDayAmount(starttime, endtime, inputtime, selectindex == 1, selectindex == 2,shopname);
        return list;
    }
}
