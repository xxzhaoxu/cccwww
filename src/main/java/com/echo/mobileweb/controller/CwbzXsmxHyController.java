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
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    @GetMapping("viprang")
    public String viprang(){
        return "viprang";
    }
    @GetMapping("areaType")
    public String areaType(){
        return "areaType";
    }
    @ResponseBody
    @GetMapping("api/findCwbzXsmxHy")
    public Object findCwbzXsmxHy(
            @RequestParam("start")String start,
            @RequestParam("end")String end,
            @RequestParam(defaultValue = "1")Integer pageIndex,
            @RequestParam(defaultValue = "10")Integer pageSize,
            @RequestParam(required = false)String shopName,
            @RequestParam(required = false)String order,
            @RequestParam(required = false)String prop
    ){

        String orderBy ="";
        if (order!=null){
            order =  order.replace("ending","");
             if ("cType".equals(prop)){
                orderBy =  "  t1.大类名称 ";
             }
             if ("saleNum".equals(prop)){
                 orderBy =  "  t1.数量 ";
             }
             if ("salePrice".equals(prop)){
                 orderBy =  " t1.金额 ";
             }
             if ("vipPrice".equals(prop)){
                 orderBy =  " t3.会员总金额 ";
             }
             if ("numProportion".equals(prop)){
                 orderBy =  "  数量占比 ";
             }
             if ("priceProportion".equals(prop)){
                 orderBy =  " 金额占比 ";
             }
             if ("singlePrice".equals(prop)){
                 orderBy =  " 单价 ";
             }
             orderBy+=order;
        }
        PageHelper.startPage(pageIndex,pageSize,orderBy);
        List<CwbzXsmxHyResult> list = cwbzXsmxHyMapper.selectCwbzXsmxHy(start,end,shopName);
        PageInfo<CwbzXsmxHyResult> pageInfo = new PageInfo<CwbzXsmxHyResult>(list);
        return pageInfo;
    }

    @ResponseBody
    @GetMapping("api/findareaTypeData")
    public Object findareaTypeData(
            @RequestParam("start")String start,
            @RequestParam("end")String end,
            @RequestParam(defaultValue = "1")Integer pageIndex,
            @RequestParam(defaultValue = "10")Integer pageSize,
            @RequestParam(required = false)String area
    ){
        PageHelper.startPage(pageIndex,pageSize);
        List<Map<String,String>> list = cwbzXsmxHyMapper.selectareaTypeData(start, end, area);
        String tmp="";
        BigDecimal totalNum = new BigDecimal(0);
        BigDecimal totalje = new BigDecimal(0);
        BigDecimal totalNumprop = new BigDecimal(0);
        BigDecimal totaljeprop = new BigDecimal(0);

        Map<String,String> map1;

        Map<String,Map<String,String>> addMap = new HashMap();
        int index=0;
        for (Map<String,String> map : list){
            index++;
           String areaName = map.get("地区名称");
           if (tmp.equals("")){
                tmp = areaName;
           }
           if (tmp.equals(areaName)){
               totalNum = totalNum.add(new BigDecimal(String.valueOf(map.get("数量"))).setScale(2,BigDecimal.ROUND_HALF_UP));
               totalje = totalje.add(new BigDecimal(String.valueOf(map.get("金额"))).setScale(2,BigDecimal.ROUND_HALF_UP));
               totalNumprop = totalNumprop.add(new BigDecimal(String.valueOf(map.get("数量占比"))).setScale(2,BigDecimal.ROUND_HALF_UP));
               totaljeprop = totaljeprop.add(new BigDecimal(String.valueOf(map.get("金额占比"))).setScale(2,BigDecimal.ROUND_HALF_UP));
           }else {
               map1 = new HashMap<>(4);
               map1.put("地区名称","总计");
               map1.put("数量",String.valueOf(totalNum));
               map1.put("金额",String.valueOf(totalje));
               map1.put("数量占比",String.valueOf(totalNumprop));
               map1.put("金额占比",String.valueOf(totalNumprop));
               System.out.println("totaljeprop : "+totaljeprop);
               totalNum = BigDecimal.ZERO;
               totalje = BigDecimal.ZERO;
               totalNumprop = BigDecimal.ZERO;
               totaljeprop = BigDecimal.ZERO;

               addMap.put(String.valueOf(index-1),map1);
           }
            tmp = areaName;

        }
        int j=0;
        for (Map.Entry<String, Map<String, String>> entry: addMap.entrySet()){
            String key = entry.getKey();
            Map<String,String> m = entry.getValue();
            list.add(Integer.parseInt(key),m);
            j++;
        }
        System.out.println(list.toString());
        PageInfo<Map<String,String>> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @ResponseBody
    @GetMapping("api/findAllarea")
    public Object findAllarea(){
        return cwbzXsmxHyMapper.selectAllArea();
    }
}
