package com.echo.mobileweb.controller;

import com.echo.mobileweb.mapper.CwbzXsmxHyMapper;
import com.echo.mobileweb.result.CwbzXsmxHyResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author 35086
 */
@Controller
public class CwbzXsmxHyController {
    @Resource
    private CwbzXsmxHyMapper cwbzXsmxHyMapper;
    @GetMapping("cwbzXsmxHy")
    public String CwbzXsmxHy(){
        return "cwbzXsmxHy";
    }
    @ResponseBody
    @GetMapping("api/findCwbzXsmxHy")
    public Object findCwbzXsmxHy(
            @RequestParam("start")String start,
            @RequestParam("end")String end,
            @RequestParam(defaultValue = "1")Integer pageIndex,
            @RequestParam(defaultValue = "10")Integer pageSize,
            @RequestParam(required = false)String shopName){
        PageHelper.startPage(pageIndex,pageSize);
        List<CwbzXsmxHyResult> list = cwbzXsmxHyMapper.selectCwbzXsmxHy(start,end,shopName);
        System.out.println(pageSize +"---"+pageIndex);
        PageInfo<CwbzXsmxHyResult> pageInfo = new PageInfo<CwbzXsmxHyResult>(list);
        return pageInfo;
    }
}
