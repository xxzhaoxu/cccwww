package com.echo.mobileweb.controller;

import com.echo.mobileweb.entity.Ddinput;
import com.echo.mobileweb.entity.Kehu;
import com.echo.mobileweb.entity.Userinfo;
import com.echo.mobileweb.entity.Yuangong;
import com.echo.mobileweb.mapper.DdinputMapper;
import com.echo.mobileweb.mapper.KehuMapper;
import com.echo.mobileweb.mapper.QtlsdmxMapper;
import com.echo.mobileweb.mapper.YuangongMapper;
import com.echo.mobileweb.model.EveryAmountDto;
import com.echo.mobileweb.model.ShopDuDaoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
public class DDController {
    @Autowired
    KehuMapper kehuMapper;
    @Autowired
    YuangongMapper yuangongMapper;
    @Autowired
    QtlsdmxMapper qtlsdmxMapper;
    @Autowired
    DdinputMapper ddinputMapper;

    @RequestMapping("ddindex")
    public String ddIndex(Model model, HttpServletRequest request) {
        Userinfo currentUserInfo = (Userinfo) request.getSession().getAttribute("user");
        if (currentUserInfo == null) {
            return "login";
        }


        Yuangong yuangong = new Yuangong();
        yuangong.set代码(currentUserInfo.getId());
        yuangong = yuangongMapper.select(yuangong).get(0);


        Kehu kehu = new Kehu();
        kehu.set督导名称(yuangong.get名称());
        kehu.set状态("启用");
        List<Kehu> kehuList = kehuMapper.select(kehu);

        String starttime = LocalDate.now().plusDays(-1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd   22:00:00"));
        String endtime = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd   22:00:00"));
        String inputtime = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        if ((LocalDateTime.now().getHour() == 22 && LocalDateTime.now().getMinute() > 30) || LocalDateTime.now().getHour() > 22) {
            starttime = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd   22:00:00"));
            endtime = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd   22:00:00"));
            //inputtime = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
        String name = yuangong.get名称();


        List<EveryAmountDto> everyAmountDtos = ddinputMapper.getShopAmountByKehu(starttime, endtime, inputtime, name);
//        List<ShopDuDaoDto> shopDuDaoDtoList = new ArrayList<>();
//        kehuList.stream().forEach((x) -> {
//            ShopDuDaoDto shopDuDaoDto = new ShopDuDaoDto();
//            shopDuDaoDto.setDdName(x.get督导名称());
//            shopDuDaoDto.setShopName(x.get商店名称());
//            shopDuDaoDto.setShopID(x.get商店代码());
//            shopDuDaoDto.setLocalName(x.get地区名称());
//
//            shopDuDaoDtoList.add(shopDuDaoDto);
//        });
        double amount = 0.00;
        if (everyAmountDtos != null) {
            for (int i = 0; i < everyAmountDtos.size(); i++) {
                if (everyAmountDtos.get(i).getSysamount() != null) {
                    amount += everyAmountDtos.get(i).getSysamount();
                }else{
                    everyAmountDtos.get(i).setSysamount(0.00);
                }
            }

        }
        model.addAttribute("shoplist", everyAmountDtos);
        model.addAttribute("amount", amount);
        model.addAttribute("username", name);
        return "ddindex";

    }

    @RequestMapping("ddinput")
    public String ddinput(String shopid, Model model, HttpServletRequest request) throws Exception {
        Userinfo currentUserInfo = (Userinfo) request.getSession().getAttribute("user");
        if (currentUserInfo == null) {
            return "login";
        }

        String starttime = LocalDate.now().plusDays(-1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd   22:00:00"));
        String endtime = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd   22:00:00"));
        String inputtime = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        if ((LocalDateTime.now().getHour() == 22 && LocalDateTime.now().getMinute() > 30) || LocalDateTime.now().getHour() > 22) {
            starttime = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd   22:00:00"));
            endtime = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd   22:00:00"));
            inputtime = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }

        //获取商店的相关信息
        Kehu kehu = kehuMapper.selectByPrimaryKey(shopid);
        model.addAttribute("shopname", kehu.get商店名称());

        //获取当日商店的营业总额
        Double amount = qtlsdmxMapper.selectAmountToday(starttime, endtime, shopid);
        DecimalFormat df = new DecimalFormat("#.00");
        model.addAttribute("amount", amount==null?0:amount);
        model.addAttribute("shopid", shopid);

        Ddinput ddinput = new Ddinput();
        Ddinput ddinput2 = new Ddinput();
        ddinput.setShopid(shopid);
        ddinput.setDdid(currentUserInfo.getId());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String toDay = sdf.format(new Date());
        ddinput.setAddtime(sdf.parse(toDay));
        ddinput2 = ddinputMapper.selectOne(ddinput);
        if (ddinput2 != null) {
                model.addAttribute("ddinput", ddinput2);
        } else {
            model.addAttribute("ddinput", ddinput);
        }

        return "ddinput";
    }

    @RequestMapping("/ddinputAction")
    @ResponseBody
    public String ddinputAction(String shopid, String shopname, String amount, String saleAmount, String remark, HttpServletRequest request) throws Exception {
        Userinfo currentUserInfo = (Userinfo) request.getSession().getAttribute("user");
        if (currentUserInfo == null) {
            return "login";
        }
        BigDecimal mAmount = null;
        BigDecimal mSaleAmount = null;

        if (!amount.equals("")) {
            mAmount = new BigDecimal(amount);
        } else {
            mAmount = null;
        }
        if (!saleAmount.equals("")) {
            mSaleAmount = new BigDecimal(saleAmount);
        } else {
            mSaleAmount = null;
        }

        Ddinput query = new Ddinput();
        query.setShopid(shopid);
        query.setDdid(currentUserInfo.getId());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String toDay = sdf.format(new Date());
        query.setAddtime(sdf.parse(toDay));
        query = ddinputMapper.selectOne(query);
        int res = 0;
        try {
            //新增数据
            if (query == null) {
                Ddinput ddinput = new Ddinput();
                ddinput.setDdid(currentUserInfo.getId());
                ddinput.setAddtime(new Date());
                ddinput.setShopid(shopid);
                ddinput.setShopname(shopname);
                ddinput.setAmount(mAmount);
                ddinput.setSaleamount(mSaleAmount);
                ddinput.setRemark(remark);
//                ddinput.setDdid(currentUserInfo.getId());

                Yuangong yuangong = new Yuangong();
                yuangong.set代码(currentUserInfo.getId());
                yuangong = yuangongMapper.select(yuangong).get(0);
                ddinput.setDdname(yuangong.get名称());
                res = ddinputMapper.insert(ddinput);

            } else {
                query.setAmount(mAmount);
                query.setSaleamount(mSaleAmount);
                query.setRemark(remark);
                res = ddinputMapper.updateByPrimaryKey(query);
            }

            if (res > 0) {
                return "ok";
            } else {
                return "系统繁忙，请重试";
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return "系统异常";
        }
    }
}
