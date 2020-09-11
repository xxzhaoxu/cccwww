package com.echo.mobileweb.controller;

import com.echo.mobileweb.common.Utils;
import com.echo.mobileweb.mapper.CwbzDyyjMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author 35086
 */
@Controller
public class dyyjController {

    @Resource
    private CwbzDyyjMapper cwbzDyyjMapper;
    @ResponseBody
    @GetMapping("/api/findSaleRank")
    public Object findSaleRank(
            @RequestParam("date")String date,
            @RequestParam(defaultValue = "")String shopName,
            @RequestParam(defaultValue = "1")Integer pageIndex,
            @RequestParam(defaultValue = "10")Integer pageSize
    ){
        String name = shopName.equals("")?null:shopName;
        String start = date.split(",")[0];
        String end = date.split(",")[1];

        String lastStart = Utils.getNowOfLastMonth(start);
        String lastEnd = Utils.getNowOfLastMonth(end);

//        String orderBy = "t1.销售金额 desc";
//        PageHelper.startPage(pageIndex,pageSize,orderBy);

        List<Map<String,String>> list = cwbzDyyjMapper.selectSaleRank(start,end,lastStart,lastEnd,name);
//        PageInfo<Map<String,String>> pageInfo = new PageInfo<>(list);
        return list;
    }

    @GetMapping("CwbzDyyj")
    public String CwbzDyyj(){
        return "CwbzDyyj";
    }
}
