package com.echo.mobileweb.controller;

import com.echo.mobileweb.common.Utils;
import com.echo.mobileweb.mapper.CwbzDyyjMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.annotation.Resource;

/**
 * @author 35086
 */
@Controller
public class dyyjController {

    @Resource
    private CwbzDyyjMapper cwbzDyyjMapper;
    @ResponseBody
    @GetMapping("/api/findSaleRank")
    public Object findSaleRank(@RequestParam("date")String date){
        String start = date.split(",")[0];
        String end = date.split(",")[1];

        String lastStart = Utils.getNowOfLastMonth(start);
        String lastEnd = Utils.getNowOfLastMonth(end);

        return cwbzDyyjMapper.selectSaleRank(start,end,lastStart,lastEnd);
    }

    @GetMapping("CwbzDyyj")
    public String CwbzDyyj(){
        return "CwbzDyyj";
    }
}
