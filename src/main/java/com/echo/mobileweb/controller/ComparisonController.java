package com.echo.mobileweb.controller;

import com.echo.mobileweb.entity.Userinfo;
import com.echo.mobileweb.entity.Yuangong;
import com.echo.mobileweb.mapper.DdinputMapper;
import com.echo.mobileweb.mapper.YuangongMapper;
import com.echo.mobileweb.model.DayAmount;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class ComparisonController {
    @Resource
    DdinputMapper ddinputMapper;
    @Resource
    YuangongMapper yuangongMapper;

    @RequestMapping("comparison")
    public String comparison(Model model, HttpServletRequest request) {
        Userinfo currentUserInfo = (Userinfo) request.getSession().getAttribute("user");
        if (currentUserInfo == null) {
            return "redirect:login";
        }

        Yuangong yuangong1=new Yuangong();
        yuangong1.set代码(currentUserInfo.getId());
        yuangong1=yuangongMapper.selectOne(yuangong1);

        if(!yuangong1.get权限().equals("管理员")&&!yuangong1.get权限().equals("查询")){
            return "redirect:login";
        }


        String shangqi_start = LocalDate.now().plusDays(-2).format(DateTimeFormatter.ofPattern("yyyy-MM-dd   22:00:00"));
        String shangqi_end = LocalDate.now().plusDays(-1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd   22:00:00"));
        String benqi_start = LocalDate.now().plusDays(-1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd   22:00:00"));
        String benqi_end = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd   22:00:00"));
        String shangqi_input = LocalDate.now().plusDays(-2).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String benqi_input = LocalDate.now().plusDays(-1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        //List<ComparisonModel> list = ddinputMapper.getComparison(null,shangqi_start,shangqi_end,benqi_start,benqi_end,shangqi_input,benqi_input);
        model.addAttribute("shangqi_start", shangqi_start);
        model.addAttribute("shangqi_end", shangqi_end);
        model.addAttribute("benqi_start", benqi_start);
        model.addAttribute("benqi_end", benqi_end);
        //model.addAttribute("list",list);
//        Integer shangqi_amount =ddinputMapper.getAmountByDay(shangqi_start, shangqi_end).intValue();
//        model.addAttribute("shangqi_amount", shangqi_amount);
//        Integer benqi_amount = ddinputMapper.getAmountByDay(benqi_start, benqi_end).intValue();
//        model.addAttribute("benqi_amount", benqi_amount);
//        model.addAttribute("chayi", shangqi_amount-benqi_amount);
//        DecimalFormat df = new DecimalFormat("#.00");
//        String bfb=df.format ((Double.parseDouble(shangqi_amount+"") - benqi_amount)/shangqi_amount*100);
//        model.addAttribute("bfb", bfb+"%");
        return "comparison";
    }

    @RequestMapping("getComparisonData")
    @ResponseBody
    public DayAmount getComparisonData(String shangqi_start,String shangqi_end,String benqi_start,String benqi_end) {
//        if (shopname.equals("")) {
//            shopname = null;
//        }
//        String shangqi_start = LocalDate.now().plusDays(-2).format(DateTimeFormatter.ofPattern("yyyy-MM-dd   22:00:00"));
//        String shangqi_end = LocalDate.now().plusDays(-1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd   22:00:00"));
//        String benqi_start = LocalDate.now().plusDays(-1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd   22:00:00"));
//        String benqi_end = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd   22:00:00"));
//        String shangqi_input = LocalDate.now().plusDays(-2).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//        String benqi_input = LocalDate.now().plusDays(-1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//        List<ComparisonModel> list = ddinputMapper.getComparison(shopname, shangqi_start, shangqi_end, benqi_start, benqi_end, shangqi_input, benqi_input);

        //List<ComparisonModel> list = ddinputMapper.getComparison(null,shangqi_start,shangqi_end,benqi_start,benqi_end,shangqi_input,benqi_input);

        //model.addAttribute("list",list);
        Double sq = ddinputMapper.getAmountByDay(shangqi_start, shangqi_end);
        Double bq =  ddinputMapper.getAmountByDay(benqi_start, benqi_end);
        Integer shangqi_amount =sq==null?0:sq.intValue();

        Integer benqi_amount = bq==null?0:bq.intValue();

        DecimalFormat df = new DecimalFormat("#.00");
        String bfb=df.format ((Double.parseDouble(benqi_amount+"") - shangqi_amount)/shangqi_amount*100);

        DayAmount dayAmount=new DayAmount();
        dayAmount.setShangqi_amount(shangqi_amount);
        dayAmount.setBenqi_amount(benqi_amount);
        dayAmount.setChayi(benqi_amount-shangqi_amount);
        dayAmount.setBfb(bfb+"%");
        return dayAmount;
    }
}
