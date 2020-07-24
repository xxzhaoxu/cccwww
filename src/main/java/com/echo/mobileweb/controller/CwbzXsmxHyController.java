package com.echo.mobileweb.controller;

import com.alibaba.fastjson.JSONObject;
import com.echo.mobileweb.common.Common;
import com.echo.mobileweb.common.Utils;
import com.echo.mobileweb.mapper.CwbzXsmxHyMapper;
import com.echo.mobileweb.result.CwbzXsmxHyResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;


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
    @GetMapping("/saleRange")
    public String saleRange(){
        return "saleRange";
    }

    @GetMapping("dayAvg")
    public String dayAvg(){
        return "saleAvg";
    }

    @GetMapping("monthReport")
    public String monthReport(){
        return "monthReport";
    }

    @GetMapping("bigyearreport")
    public String BIgYearReport(){
        return "bigYearReport";
    }
    @GetMapping("smallTypeMonth")
    public String smallTypeMonth(){
        return "smallTypeMonth";
    }

    @GetMapping("inData")
    public String inData(){
        return "inData";
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

        Map<Integer, Map<String, String>> addMap = new TreeMap<>();

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

               addMap.put(index-1,map1);

               totalje = totalje.add(new BigDecimal(String.valueOf(map.get("金额"))).setScale(2,BigDecimal.ROUND_HALF_UP));
               totalNum = totalNum.add(new BigDecimal(String.valueOf(map.get("数量"))).setScale(2,BigDecimal.ROUND_HALF_UP));
               totalNumprop = totalNumprop.add(new BigDecimal(String.valueOf(map.get("数量占比"))).setScale(2,BigDecimal.ROUND_HALF_UP));
               totaljeprop = totaljeprop.add(new BigDecimal(String.valueOf(map.get("金额占比"))).setScale(2,BigDecimal.ROUND_HALF_UP));
           }
            tmp = areaName;
        }

        map1 = new HashMap<>(4);
        map1.put("地区名称","总计");
        map1.put("数量",String.valueOf(totalNum));
        map1.put("金额",String.valueOf(totalje));
        map1.put("数量占比",String.valueOf(totalNumprop));
        map1.put("金额占比",String.valueOf(totalNumprop));

        addMap.put(index,map1);
        Map<Integer,Map> result = addMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        int j=0;
        int k=0;
        for (Map.Entry entry: result.entrySet()){
            Integer key = (Integer) entry.getKey();
            System.out.println(key);
            j = key;
            Map<String,String> m = (Map<String, String>) entry.getValue();
            list.add( (j+k),m);
            k++;
        }
        System.out.println(list.toString());
        PageInfo<Map<String,String>> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @ResponseBody
    @GetMapping("api/findAllarea")
    public Object findAllarea(@RequestParam(required = false)String area){
        return cwbzXsmxHyMapper.selectAllArea(area);
    }

    @ResponseBody
    @GetMapping("api/vipRangeData")
    public Object vipRangeData(
            @RequestParam("start")String start,
            @RequestParam("end")String end,
            @RequestParam(defaultValue = "1")Integer pageIndex,
            @RequestParam(defaultValue = "10")Integer pageSize,
            @RequestParam(required = false)String shopName
    ){
        PageHelper.startPage(pageIndex,pageSize);
        List<Map<String,String>> list = cwbzXsmxHyMapper.selectVIPrangeData(start, end, shopName);
        PageInfo<Map<String,String>> pageInfo = new PageInfo<Map<String, String>>(list);
        return pageInfo;
    }

    @ResponseBody
    @GetMapping("api/findSalerangeData")
    public Object findSalerangeData(  @RequestParam("start")String start,
                                      @RequestParam("end")String end,
                                      @RequestParam(defaultValue = "1")Integer pageIndex,
                                      @RequestParam(defaultValue = "10")Integer pageSize,
                                      @RequestParam(required = false)String shopCode){
        PageHelper.startPage(pageIndex,pageSize);
        List<Map<String,String>> list = cwbzXsmxHyMapper.selectSalerangeData(start, end, shopCode);
        PageInfo<Map<String,String>> pageInfo = new PageInfo<Map<String, String>>(list);
        return pageInfo;
    }
    @ResponseBody
    @GetMapping("api/findDaySaleAvgData")
    public Object findDaySaleAvgData(
//            @RequestParam("start")String start,
            @RequestParam("end")String end
    ) throws Exception {

          String firstDay = Utils.getFirstDay(end);
          Long dayNum = Utils.DaySubtractNum(firstDay,end);
          Long shopNum =  cwbzXsmxHyMapper.selectShopTotalNum(firstDay, end);
          Float saleJe =  cwbzXsmxHyMapper.selectShopSaleMoney(firstDay, end);
          saleJe = saleJe==null?0:saleJe;
          BigDecimal je = new BigDecimal(0);
          if (shopNum!=0&&saleJe!=0){
              je = new BigDecimal(saleJe).divide(new BigDecimal(shopNum),2, BigDecimal.ROUND_HALF_UP).divide(new BigDecimal(dayNum),2, BigDecimal.ROUND_HALF_UP);
          }

          Map<String,String> reMap = new HashMap<>(4);
          reMap.put("店铺数量",String.valueOf(shopNum));
          reMap.put("当前金额",String.valueOf(saleJe));

          reMap.put("当月天数",String.valueOf(dayNum));
          reMap.put("日销售金额",String.valueOf(je));

          System.out.println(shopNum);
          System.out.println(saleJe);
          List reList = new ArrayList();
          reList.add(reMap);
          return reList;
    }
    @ResponseBody
    @GetMapping("/api/findMonthReportData")
    public Object findMonthReportData(
            @RequestParam("end")String end,
            @RequestParam(defaultValue = "1")Integer pageIndex,
            @RequestParam(defaultValue = "10")Integer pageSize
    ) throws Exception {
        String firstDay = Utils.getFirstDay(end);
        Long dayNum = Utils.DaySubtractNum(firstDay,end);
        PageHelper.startPage(pageIndex,pageSize);
        List<Map<String,String>> list = cwbzXsmxHyMapper.selectMonthReport(firstDay,end);


        PageInfo<Map<String,String>> pageInfo = new PageInfo<Map<String,String>>(list);
        List<Map<String,String>> typeList = pageInfo.getList();
        Long shopNum = cwbzXsmxHyMapper.selectShopTotalNum(firstDay,end);
        for (Map<String,String> map:typeList){
           String type =  map.get("小类名称");
           Long goodsNum = cwbzXsmxHyMapper.selectNum(type);
            System.out.println(goodsNum);
            map.put("库存数量",String.valueOf(goodsNum));
            map.put("当前店铺数量",String.valueOf(shopNum));
            map.put("当前天数",String.valueOf(dayNum));
            String shopSaleAvg = "0";
            try {
                shopSaleAvg = String.valueOf(new BigDecimal(String.valueOf(map.get("金额"))).divide(new BigDecimal(String.valueOf(shopNum)),2, BigDecimal.ROUND_HALF_UP));
            }catch (ArithmeticException e){
            }
            map.put("店均销售量",shopSaleAvg);
            String dayAvg = "0";
            try {
                dayAvg = String.valueOf(new BigDecimal(String.valueOf(map.get("金额"))).divide(new BigDecimal(String.valueOf(shopNum)),2, BigDecimal.ROUND_HALF_UP).divide(new BigDecimal(dayNum),2,BigDecimal.ROUND_HALF_UP));
            }catch (ArithmeticException e){
            }
            map.put("店均日销售",dayAvg);
            String shopAvg = "0";
            try {
              shopAvg =  String.valueOf(new BigDecimal(goodsNum).divide(new BigDecimal(shopNum),2,BigDecimal.ROUND_HALF_UP));
            }catch (ArithmeticException e){
            }
            map.put("店均库存",shopAvg);
        }
        return pageInfo;
    }

    @ResponseBody
    @GetMapping("api/BIgYearReportData")
    public Object BIgYearReportData(
            @RequestParam("year")String year,
            @RequestParam(defaultValue = "1")Integer pageIndex,
            @RequestParam(defaultValue = "10")Integer pageSize
    ){
        List<String> monthList = new ArrayList<String>(){
            {
                add("1");
                add("2");
                add("3");
                add("4");
                add("5");
                add("6");
                add("7");
                add("8");
                add("9");
                add("10");
                add("11");
                add("12");
            }
        };
        List reList = new ArrayList();
        Map<String,String> reMap;
        List<Map<String,String>> List = cwbzXsmxHyMapper.selectBigYearReportData(year);
        String shopNum="0";
        String saleNum ="0";
        String salePrice = "0";
        String shopKcAvg = "0";
        String avgSaleNum = "0";
        String avgSalePrice = "0";
        for (String m:monthList){
            Boolean h =false;
            reMap = new HashMap<>();

            Long kc = cwbzXsmxHyMapper.selectStockNum(year,m);
            kc = kc==null?0L:kc;
            System.out.println("库存："+kc);
            for (Map<String,String> map:List){
               String month = String.valueOf(map.get("月份"));
               if (m.equals(month)){
                   System.out.println("月份:"+month);
                   shopNum = String.valueOf(map.get("店铺数量"));
                   saleNum = String.valueOf(map.get("总销数量"));
                   salePrice = String.valueOf(map.get("金额"));
                   break;
               }
            }
            reMap.put("月份",year+"-"+formartMonth(m));
            reMap.put("店铺数量",shopNum);
            reMap.put("总销数量",saleNum);
            reMap.put("总销金额",salePrice);
            reMap.put("库存总量",String.valueOf(kc));

            try {
                shopKcAvg = String.valueOf( new BigDecimal(kc).divide(new BigDecimal(shopNum),2,BigDecimal.ROUND_HALF_UP));
            }catch (ArithmeticException e){

            }

            try {
                avgSaleNum = String.valueOf(new BigDecimal(saleNum).divide(new BigDecimal(shopNum),2,BigDecimal.ROUND_HALF_UP));
            }catch (ArithmeticException e){

            }

            try {
                avgSalePrice = String.valueOf(new BigDecimal(salePrice).divide(new BigDecimal(shopNum),2,BigDecimal.ROUND_HALF_UP));
            }catch (ArithmeticException e){

            }

            reMap.put("店均库存",shopKcAvg);
            reMap.put("店均销售数量",avgSaleNum);
            reMap.put("店均销售金额",avgSalePrice);

            shopNum = "0";
            saleNum = "0";
            salePrice = "0";
            shopKcAvg = "";
            avgSaleNum = "";
            avgSalePrice = "";
            reList.add(reMap);
        }
        return reList;
    }

    @ResponseBody
    @GetMapping("api/smallTypeMonthData")
    public Object smallTypeMonthData(
            @RequestParam("start")String start,
            @RequestParam("end")String end,
            @RequestParam(defaultValue = "1")Integer pageIndex,
            @RequestParam(defaultValue = "10")Integer pageSize
    ){
        PageHelper.startPage(pageIndex,pageSize);
        List<Map<String,String>> list = cwbzXsmxHyMapper.selectSmallTypeYearMonthData(start, end);
        PageInfo<Map<String,String>> pageInfo = new PageInfo<>(list);
        List<Map<String,String>> pageList = pageInfo.getList();
        for (Map<String,String> map:pageList){
            String goodsCode = map.get("商品代码");
            Long num = cwbzXsmxHyMapper.selectStockNumByShopCode(goodsCode);
            num = num==null?0L:num;
            map.put("库存数量",String.valueOf(num));
        }
        return pageInfo;
    }

    @ResponseBody
    @GetMapping("api/getInData")
    public Object getInData( @RequestParam("start")String start,
                             @RequestParam("end")String end,
                             @RequestParam(defaultValue = "1")Integer pageIndex,
                             @RequestParam(defaultValue = "10")Integer pageSize
    ){
        Integer pageStart = (pageIndex-1)*pageSize;
        PageHelper.startPage(pageIndex,pageSize);
        List<Map<String,String>> list =cwbzXsmxHyMapper.inData(start, end,pageSize,pageStart);
//
        PageInfo<Map<String,String>> pageInfo = new PageInfo<>(list);
       return pageInfo;
    }

    @ResponseBody
    @GetMapping("api/areaHz")
    public Object areaHz(
            @RequestParam("start")String start,
            @RequestParam("end")String end,
            @RequestParam(defaultValue = "1")Integer pageIndex,
            @RequestParam(defaultValue = "10")Integer pageSize,
            @RequestParam(required = false)String area
    ){

//        PageHelper.startPage(pageIndex,pageSize);
//        List<Map<String,String>> list =cwbzXsmxHyMapper.selectAreaHz(start,end,area);
//        PageInfo<Map<String,String>> pageInfo = new PageInfo<>(list);
        return cwbzXsmxHyMapper.selectAreaHz(start,end,area);
    }

    @ResponseBody
    @GetMapping("api/salesManHz")
    public Object salesManHz(  @RequestParam("start")String start,
                               @RequestParam("end")String end,
                               @RequestParam(defaultValue = "1")Integer pageIndex,
                               @RequestParam(defaultValue = "10")Integer pageSize,
                               @RequestParam(required = false)String area){
//        PageHelper.startPage(pageIndex,pageSize);
//        List<Map<String,String>> list =cwbzXsmxHyMapper.selectSalesmanHz(start, end, area);
//        PageInfo<Map<String,String>> pageInfo = new PageInfo<>(list);
        return cwbzXsmxHyMapper.selectSalesmanHz(start, end, area);
    }
    @ResponseBody
    @GetMapping("api/shopNameHz")
    public Object shopNameHz(  @RequestParam("start")String start,
                               @RequestParam("end")String end,
                               @RequestParam(defaultValue = "1")Integer pageIndex,
                               @RequestParam(defaultValue = "10")Integer pageSize,
                               @RequestParam(required = false)String area){
//        PageHelper.startPage(pageIndex,pageSize);
//        List<Map<String,String>> list =cwbzXsmxHyMapper.selectShopNameHz(start, end, area);
//        PageInfo<Map<String,String>> pageInfo = new PageInfo<>(list);
        return cwbzXsmxHyMapper.selectShopNameHz(start, end, area);
    }

    @ResponseBody
    @GetMapping("api/findZdOpention")
    public Object findZdOpention(){
        List<String> areaList = cwbzXsmxHyMapper.selectAllArea(null);

        List<String> zjList = cwbzXsmxHyMapper.selectAllZj(null);

        List<String> jlList = cwbzXsmxHyMapper.selectAllJl(null);

        List<String> salesManList  =  cwbzXsmxHyMapper.selectAllSalesMan(null);

        List<JSONObject> areaChildrenList = new ArrayList<>();
        List<JSONObject> zjChildrenList = new ArrayList<>();
        List<JSONObject> jlChildrenList = new ArrayList<>();
        List<JSONObject> salesManChildrenList = new ArrayList<>();
        JSONObject areaJson;
        JSONObject zjJson;
        JSONObject jlJson;
        JSONObject salesManJson;
        for (String area:areaList){
            areaJson = new JSONObject();
            areaJson.put("value",area);
            areaJson.put("label",area);
            areaChildrenList.add(areaJson);
        }

        for (String zj:zjList){
            zjJson = new JSONObject();
            zjJson.put("value",zj);
            zjJson.put("label",zj);
            zjChildrenList.add(zjJson);
        }

        for (String jl:jlList){
            jlJson = new JSONObject();
            jlJson.put("value",jl);
            jlJson.put("label",jl);
            jlChildrenList.add(jlJson);
        }

        for (String salesMan:salesManList){
            salesManJson = new JSONObject();
            salesManJson.put("value",salesMan);
            salesManJson.put("label",salesMan);
            salesManChildrenList.add(salesManJson);
        }

        JSONObject area = new JSONObject();
        area.put("value","quyu");
        area.put("label","区域");
        area.put("children",areaChildrenList);

        JSONObject zj = new JSONObject();
        zj.put("value","quyu");
        zj.put("label","区域");
        zj.put("children",zjChildrenList);

        JSONObject jl = new JSONObject();
        jl.put("value","quyu");
        jl.put("label","区域");
        jl.put("children",jlChildrenList);

        JSONObject salesMan = new JSONObject();
        salesMan.put("value","quyu");
        salesMan.put("label","区域");
        salesMan.put("children",salesManChildrenList);


        List<JSONObject> reList = new ArrayList<>();
        reList.add(area);
        reList.add(zj);
        reList.add(salesMan);
        reList.add(jl);


        return reList;
    }
    private String formartMonth(String month){
        if (month.length()<2){
            return "0"+month;
        }
        return month;
    }
}
