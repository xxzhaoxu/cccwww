package com.echo.mobileweb.controller;

import com.echo.mobileweb.entity.Userinfo;
import com.echo.mobileweb.entity.Yuangong;
import com.echo.mobileweb.mapper.DdinputMapper;
import com.echo.mobileweb.mapper.YuangongMapper;
import com.echo.mobileweb.model.EveryAmountDto;
import org.bouncycastle.math.raw.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class RankController {
    @Autowired
    DdinputMapper ddinputMapper;
    @Autowired
    YuangongMapper yuangongMapper;

    @RequestMapping("ddrank")
    public String ddRank(String qDate, HttpServletRequest request, Model model) {
        Userinfo currentUserInfo = (Userinfo) request.getSession().getAttribute("user");
        if (currentUserInfo == null) {
            return "login";
        }
        Yuangong yuangong=new Yuangong();
        yuangong.set代码(currentUserInfo.getId());
        yuangong=yuangongMapper.selectOne(yuangong);

        if(!yuangong.get权限().equals("管理员")&&!yuangong.get权限().equals("查询")){
            return "redirect:login";
        }

        model.addAttribute("username",yuangong.get名称());
        String today=LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        model.addAttribute("today",today);
//        //查询日期
//        if (qDate != null && qDate != "") {
//
//        } else {
//
//        }
//
//
//        List<EveryAmountDto> list = ddinputMapper.getDdRank("2019-07-01 00:00:00", "2019-07-02 00:00:00", "2019-07-01");
//        model.addAttribute("list", list);

        return "rank_dd";
    }

    @RequestMapping("jlrank")
    public String jlrank(HttpServletRequest request, Model model) {
//        Userinfo currentUserInfo = (Userinfo) request.getSession().getAttribute("user");
//        if (currentUserInfo == null) {
//            return "login";
//        }
//
//        List<EveryAmountDto> list = ddinputMapper.getJlRank();
//        model.addAttribute("list", list);
        return "rank_jl";
    }


    @RequestMapping("zjrank")
    public String zjrank(HttpServletRequest request, Model model) {
//        Userinfo currentUserInfo = (Userinfo) request.getSession().getAttribute("user");
//        if (currentUserInfo == null) {
//            return "login";
//        }
//
//        List<EveryAmountDto> list = ddinputMapper.getZjRank();
//        model.addAttribute("list", list);
        return "rank_zj";
    }

    @RequestMapping("dqrank")
    public String dqrank(HttpServletRequest request, Model model) {
//        Userinfo currentUserInfo = (Userinfo) request.getSession().getAttribute("user");
//        if (currentUserInfo == null) {
//            return "login";
//        }
//
//        List<EveryAmountDto> list = ddinputMapper.getDqRank();
//        model.addAttribute("list", list);
        return "rank_dq";
    }

    @ResponseBody
    @RequestMapping("/getRankData")
    public List<EveryAmountDto> getRankData(String date, Integer select) throws Exception {

        LocalDate inputDate=LocalDate.parse(date);
        String starttime= inputDate.plusDays(-1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd   22:00:00"));
        String endtime= inputDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd   22:00:00"));
        String inputtime= inputDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd") );

//        if ((LocalDateTime.now().getHour() == 22 && LocalDateTime.now().getMinute() > 30) || LocalDateTime.now().getHour() > 22) {
//            starttime = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd   22:00:00"));
//            endtime = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd   22:00:00"));
//            inputtime = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//        }
        List<EveryAmountDto> list=new ArrayList<>();
        if (select == 0) {
            list= ddinputMapper.getDdRank(starttime, endtime, inputtime);
        }
        if (select == 1) {
            list= ddinputMapper.getJlRank(starttime, endtime, inputtime);
        }
        if (select == 2) {
            list= ddinputMapper.getZjRank(starttime, endtime, inputtime);
        }
        if (select == 3) {
            list= ddinputMapper.getDqRank(starttime, endtime, inputtime);
        }

        for (EveryAmountDto item:list){
            if(item.getSysamount()==null){
                item.setSysamount(0.00);
            }
            if(item.getShopcount()==null){
                item.setShopcount(0);
            }
        }
        return list;


    }
}
