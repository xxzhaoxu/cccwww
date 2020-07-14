package com.echo.mobileweb.controller;

import com.echo.mobileweb.common.Utils;
import com.echo.mobileweb.entity.CwbzXiaoshou;
import com.echo.mobileweb.mapper.CwbzXiaoshouMapper;
import com.echo.mobileweb.model.HbModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 35086
 */
@Controller
public class CompareXsController {

    @Resource
    private CwbzXiaoshouMapper cwbzXiaoshouMapper;
    @GetMapping("compareXs")
    public String CompareXs(){
        return "compareXs";
    }

    @ResponseBody
    @GetMapping("/api/hbtbData")
    public List<HbModel> test(
            @RequestParam("bqDate")String bqDate,
            @RequestParam("sqDate")String sqDate,
            @RequestParam(required = false)String name,
            @RequestParam("pageSize")Integer pageSize,
            @RequestParam("pageIndex")Integer pageIndex
    ){
        System.out.println(cwbzXiaoshouMapper.selectCwbzXiaoshouById("2019-01-01_1001"));
        Integer start = (pageIndex-1)*pageSize;
        String bqStart = "";
        String bqEnd =  "";

        String sqStart = "";
        String sqEnd = "";
        name = "".equals(name)?null:name;
        try {
            bqStart = bqDate.split(",")[0];
            bqEnd = bqDate.split(",")[1];

            sqStart = sqDate.split(",")[0];
            sqEnd = sqDate.split(",")[1];
        }catch (Exception e){
            e.printStackTrace();
        }


        List<HbModel> reList = new ArrayList<>();
        List<Map<String,Object>> bqList = cwbzXiaoshouMapper.selectCwbzXiaoshou(bqStart,bqEnd,name,pageSize,start);

        HbModel hbModel;
        for (Map<String,Object> map : bqList){
            String shopName = map.get("shopName").toString();
            String  tqxse  = cwbzXiaoshouMapper.selectLastYearCharge(shopName, Utils.getNowOfLastYear(bqStart),Utils.getNowOfLastYear(bqEnd));
                Map<String,Object> map1 = cwbzXiaoshouMapper.selectCwbzXiaoshouByName(shopName,sqStart,sqEnd);
                if (map1==null){
                    hbModel = HbModel.builder(shopName,
                            new BigDecimal(0),
                            new BigDecimal(0),
                            "0",
                            new BigDecimal(0),
                            new BigDecimal(0)
                    );
                }else {
                    hbModel = HbModel.builder(shopName,
                            new BigDecimal(String.valueOf(map1.get("xsje")==null?"0":map1.get("xsje"))),
                            new BigDecimal(String.valueOf(map.get("xsje")==null?"0":map.get("xsje"))),
                              tqxse==null?"0":tqxse,
                            new BigDecimal(String.valueOf(map1.get("mbje")==null?"0":map1.get("mbje"))),
                            new BigDecimal(String.valueOf(map.get("mbje")==null?"0":map.get("mbje")))
                    );
                }

                    reList.add(hbModel);



        }
        return reList;
    }

    @ResponseBody
    @GetMapping("/api/findAllShopName")
    public List<Map<String,String>> findAllShop(){
        return cwbzXiaoshouMapper.selectAllShopName();
    }
}
