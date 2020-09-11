package com.echo.mobileweb.controller;

import com.alibaba.fastjson.JSONObject;
import com.echo.mobileweb.common.Utils;
import com.echo.mobileweb.mapper.CwbzXsmxHyMapper;
import com.echo.mobileweb.result.CwbzXsmxHyResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;


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
    @GetMapping("achievementCompare")
    public String achievementCompare(){
        return "achievementCompare";
    }
    @ResponseBody
    @GetMapping("api/findCwbzXsmxHy")
    public Object findCwbzXsmxHy(
            @RequestParam("start")String start,
            @RequestParam("end")String end,
            @RequestParam(required = false)String shopName,
            @RequestParam(required = false)String qy,
            @RequestParam(required = false)String zj,
            @RequestParam(required = false)String jl,
            @RequestParam(required = false)String salesMan,
            @RequestParam(required = false)String dl

    ){
        shopName = addS(shopName);
        qy = addS(qy);
        zj = addS(zj);
        jl = addS(jl);
        salesMan = addS(salesMan);
        dl = addS(dl);
        List<CwbzXsmxHyResult> reList = new ArrayList<>();
        List<CwbzXsmxHyResult> list = cwbzXsmxHyMapper.selectCwbzXsmxHy(start,end,shopName,qy,zj,jl,salesMan,dl);
        List<String> daleiList = cwbzXsmxHyMapper.selectAllBigType(null);

        List<String > tempDalei = new ArrayList<>();
        tempDalei.addAll(daleiList);

        String type= "";
        BigDecimal saleNum = new BigDecimal(0);
        BigDecimal salePrice = new BigDecimal(0);
        BigDecimal vip = new BigDecimal(0);
        for (CwbzXsmxHyResult cwbzXsmxHyResult:list){
            if (!"".equals(type)&&!type.equals(cwbzXsmxHyResult.getShopName())){
                for (String s : tempDalei) {
                    CwbzXsmxHyResult t = new CwbzXsmxHyResult();
                    t.setSaleNum("0");
                    t.setSalePrice("0");
                    t.setcType(s);
                    t.setShopName(type);
                    t.setNumProportion("0");
                    t.setPriceProportion("0");
                    t.setSinglePrice("0");
                    t.setVipProportion("0");
                    reList.add(t);
                }
                    CwbzXsmxHyResult total = new CwbzXsmxHyResult();
                    total.setShopName("小计");
                    total.setSaleNum(String.valueOf(saleNum));
                    total.setSalePrice(String.valueOf(salePrice));
                    total.setVipPrice(String.valueOf(vip));
                    if (vip.compareTo(new BigDecimal(0))==0 || salePrice.compareTo(new BigDecimal(0))==0) {
                        total.setVipProportion("0");
                    }else {
                        total.setVipProportion(String.valueOf(vip.divide(salePrice,2,RoundingMode.HALF_UP).multiply(new BigDecimal(100))));
                    }


                    reList.add(total);
                    tempDalei.clear();
                    tempDalei.addAll(daleiList);
                    saleNum = new BigDecimal(cwbzXsmxHyResult.getSaleNum());
                    salePrice = new BigDecimal(cwbzXsmxHyResult.getSalePrice());
                    vip = new BigDecimal(cwbzXsmxHyResult.getVipPrice()==null?"0":cwbzXsmxHyResult.getVipPrice());
            }else {
                    saleNum = saleNum.add(new BigDecimal(cwbzXsmxHyResult.getSaleNum()));
                    salePrice = salePrice.add(new BigDecimal(cwbzXsmxHyResult.getSalePrice()));
                    vip = vip.add(new BigDecimal(cwbzXsmxHyResult.getVipPrice()==null?"0":cwbzXsmxHyResult.getVipPrice()));


            }
            tempDalei.remove(cwbzXsmxHyResult.getcType());
            reList.add(cwbzXsmxHyResult);
            type = cwbzXsmxHyResult.getShopName();

        }

        if (reList.size()>0){
            for (String s : tempDalei) {
                CwbzXsmxHyResult t = new CwbzXsmxHyResult();
                t.setSaleNum("0");
                t.setSalePrice("0");
                t.setcType(s);
                t.setShopName(type);
                t.setNumProportion("0");
                t.setPriceProportion("0");
                t.setSinglePrice("0");
                t.setVipProportion("0");
                reList.add(t);
            }
            CwbzXsmxHyResult xiaoji = new CwbzXsmxHyResult();
            xiaoji.setShopName("小计");
            xiaoji.setSaleNum(String.valueOf(saleNum));
            xiaoji.setSalePrice(String.valueOf(salePrice));
            xiaoji.setVipPrice(String.valueOf(vip));
            if (vip.compareTo(new BigDecimal(0))==0 || salePrice.compareTo(new BigDecimal(0))==0){
                xiaoji.setVipProportion("0");
            }else {
                xiaoji.setVipProportion(String.valueOf(vip.divide(salePrice,2,RoundingMode.HALF_UP).multiply(new BigDecimal(100))));
            }

            reList.add(xiaoji);


            List<CwbzXsmxHyResult> total = cwbzXsmxHyMapper.selectCwbzXsmxHyTotal(start, end, shopName, qy, zj, jl, salesMan, dl);
            Map<String,String> sumMap = cwbzXsmxHyMapper.selectCwbzXsmxHySum(start, end, shopName, qy, zj, jl, salesMan, dl);

            String totalSum = "0";
            String totalPrice = "0";
            if (sumMap!=null){
                totalSum = sumMap.get("数量")==null?"0":String.valueOf(sumMap.get("数量"));
                totalPrice = sumMap.get("金额")==null?"0":String.valueOf(sumMap.get("金额"));
            }
            BigDecimal vipTotal =  new BigDecimal(0);
            for (CwbzXsmxHyResult cwbzXsmxHyResult : total) {
                String price = cwbzXsmxHyResult.getSalePrice()==null?"0":cwbzXsmxHyResult.getSalePrice();
                String num = cwbzXsmxHyResult.getSaleNum()==null?"0":cwbzXsmxHyResult.getSaleNum();
                String vipNum = cwbzXsmxHyResult.getVipPrice()==null?"0":cwbzXsmxHyResult.getVipPrice();
                vipTotal = vipTotal.add(new BigDecimal(vipNum));
                if (totalPrice.equals("0")){
                    cwbzXsmxHyResult.setPriceProportion("0");
                    cwbzXsmxHyResult.setVipProportion("0");
                }else {
                    cwbzXsmxHyResult.setVipProportion(new BigDecimal(vipNum).divide(new BigDecimal(totalPrice),2,RoundingMode.UP).multiply(new BigDecimal(100)).toString());
                    cwbzXsmxHyResult.setPriceProportion(new BigDecimal(price).divide(new BigDecimal(totalPrice),2,RoundingMode.UP).multiply(new BigDecimal(100)).toString());
                }
                if (totalSum.equals("0")){
                    cwbzXsmxHyResult.setNumProportion("0");
                }else {
                    cwbzXsmxHyResult.setNumProportion(new BigDecimal(num).divide(new BigDecimal(totalSum),2,RoundingMode.UP).multiply(new BigDecimal(100)).toString());
                }
            }

            CwbzXsmxHyResult 操 = new CwbzXsmxHyResult();
            操.setSalePrice(totalPrice);
            操.setSaleNum(totalSum);
            操.setVipPrice(String.valueOf(vipTotal));

            if (vip.compareTo(new BigDecimal(0))==0 || salePrice.compareTo(new BigDecimal(0))==0) {
                操.setVipProportion("0");
            }else {
                操.setVipProportion(String.valueOf(vipTotal.divide(new BigDecimal(totalPrice),2,RoundingMode.HALF_UP).multiply(new BigDecimal(100))));
            }

            操.setShopName("汇总");
//        total.setShopName("总计");
//        total.setSaleNum(String.valueOf(saleNum));
//        total.setSalePrice(String.valueOf(salePrice));
            reList.addAll(total);
            reList.add(操);

        }


        return reList;
    }

    public static void main(String[] args) {
        List a = new ArrayList();
        a.add("a");
        a.add("b");
        a.add("c");
        System.out.println(a.remove("c"));
        System.out.println(a.remove("d"));
        System.out.println(a);
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
        area = addS(area);
        List<Map<String,String>> list = cwbzXsmxHyMapper.selectareaTypeData(start, end, area);
        List<String> daleiList = cwbzXsmxHyMapper.selectAllBigType(null);
        List<Map<String,String>> daleiTotal = cwbzXsmxHyMapper.selectDaleiTotal(start, end, area);

        Map<String,String> daleiSum = cwbzXsmxHyMapper.selectDaleiSum(start, end, area);
        List<String> tempDalei = new ArrayList<>();
        tempDalei.addAll(daleiList);
        List<Map<String,String>> reList = new ArrayList<>();
        BigDecimal totalNum = new BigDecimal(0); //总数量
        BigDecimal totalje = new BigDecimal(0);  //总金额
        BigDecimal sumNum = new BigDecimal(0);//数量占比和
        BigDecimal jeNum = new BigDecimal(0);//金额占比和

        String type="";
        Map<String,String> temp;
        String price="",num="";
        int index=0;
        int size = list.size();
        for (Map<String, String> map : list) {
            index = index+1;
            if (!"".equals(type)&&!type.equals(map.get("地区名称"))){
                for (String s : tempDalei) {
                    temp = new HashMap<>();
                    temp.put("地区名称",type);
                    temp.put("数量","0");
                    temp.put("金额","0");
                    temp.put("大类名称",s);
                    reList.add(temp);
                }
                temp = new HashMap<>();
                temp.put("地区名称","小计");
                temp.put("数量",num);
                temp.put("金额",price);
                temp.put("金额占比","1");
                temp.put("数量占比","1");
                reList.add(temp);
                tempDalei.clear();
                tempDalei.addAll(daleiList);
            }
            num = String.valueOf(map.get("总数量"));
            price = String.valueOf(map.get("总金额"));
            reList.add(map);
            type = map.get("地区名称");

            totalje = totalje.add(new BigDecimal(String.valueOf(map.get("金额"))));
            totalNum = totalNum.add(new BigDecimal(String.valueOf(map.get("数量"))));
            tempDalei.remove(String.valueOf(map.get("大类名称")));

            if (index==size){
                for (String s : tempDalei) {
                    temp = new HashMap<>();
                    temp.put("地区名称",type);
                    temp.put("数量","0");
                    temp.put("金额","0");
                    temp.put("大类名称",s);
                    reList.add(temp);
                }
            }

        }
        if (reList.size()>0){
            temp = new HashMap<>();
            temp.put("地区名称","小计");
            temp.put("数量",num);
            temp.put("金额",price);
            temp.put("金额占比","1");
            temp.put("数量占比","1");
            reList.add(temp);


//            Map<String,String>  zjMap = new HashMap();
//            zjMap.put("地区名称"," 总计");
//            zjMap.put("数量",String.valueOf(totalNum));
//            zjMap.put("金额",String.valueOf(totalje));
//            temp.put("金额占比","1");
//            temp.put("数量占比","1");
//            reList.add(zjMap);
        }

         /*
        for (Map<String,String> map:list){
            if (totalNum.compareTo(new BigDecimal(0))==1){
                BigDecimal num = new BigDecimal(String.valueOf(map.get("数量"))).divide(totalNum,4,BigDecimal.ROUND_HALF_UP);
                sumNum = sumNum.add(num);
                map.put("数量占比",String.valueOf(num));
            }else {
                map.put("数量占比","0%" );
            }
            if (totalje.compareTo(new BigDecimal(0))==1){
                BigDecimal je = new BigDecimal(String.valueOf(map.get("金额"))).divide(totalje,4,BigDecimal.ROUND_HALF_UP);
                jeNum = jeNum.add(je);
                map.put("金额占比",String.valueOf(je));
            }else {
                map.put("金额占比","0%" );
            }
        }

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
               totaljeprop = totaljeprop.add(new BigDecimal(String.valueOf(map.get("金额占比"))).setScale(2,BigDecimal.ROUND_HALF_UP)).setScale(2,BigDecimal.ROUND_HALF_UP);
           }else {
               map1 = new HashMap<>(4);
               map1.put("地区名称","总计");
               map1.put("数量",String.valueOf(totalNum));
               map1.put("金额",String.valueOf(totalje));
               map1.put("数量占比",String.valueOf(totalNumprop));
               map1.put("金额占比",String.valueOf(totalNumprop.doubleValue()));
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
        map1.put("金额占比",String.valueOf(totaljeprop));

        addMap.put(index,map1);
        Map<Integer,Map> result = addMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        int k=0,j;
        for (Map.Entry entry: result.entrySet()){
            Integer key = (Integer) entry.getKey();
            j = key;
            Map<String,String> m = (Map<String, String>) entry.getValue();
            list.add( (j+k),m);
            k++;
        }
         */
//        Map<String,String> totalMap = new HashMap<>();
//        totalMap.put("大类名称","总计");
//        totalMap.put("数量",String.valueOf(totalNum));
//        totalMap.put("金额",String.valueOf(totalje));
//        totalMap.put("数量占比",String.valueOf(sumNum));
//        totalMap.put("金额占比",String.valueOf(jeNum));
//        reList.add(totalMap);
        for (Map<String, String> map : daleiTotal) {
            String num1 = map.get("数量")==null?"0":String.valueOf(map.get("数量"));
            String price1 = map.get("金额")==null?"0": String.valueOf(map.get("金额"));
            BigDecimal b1  = new BigDecimal(num1);
            BigDecimal b2 = new BigDecimal(price1);
            if (daleiSum.get("数量")==null){
                map.put("数量占比","0");
            }else {
                map.put("数量占比",b1.divide(new BigDecimal(String.valueOf(daleiSum.get("数量"))),2, RoundingMode.UP)+"");
            }
           if (daleiSum.get("金额")==null){
               map.put("金额占比","0");
           }else {
               map.put("金额占比",b2.divide(new BigDecimal(String.valueOf(daleiSum.get("金额"))),2, RoundingMode.UP)+"");
           }

        }
        reList.addAll(daleiTotal);
        reList.add(daleiSum);
        return reList;
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
        if("".equals(shopName)){
            shopName=null;
        }
        List<Map<String,String>> list = cwbzXsmxHyMapper.selectVIPrangeData(start, end, shopName);
        return list;
    }

    @ResponseBody
    @GetMapping("api/findSalerangeData")
    public Object findSalerangeData(  @RequestParam("start")String start,
                                      @RequestParam("end")String end,
                                      @RequestParam(required = false)String shopCode){
        List<Map<String,String>> list = cwbzXsmxHyMapper.selectSalerangeData(start, end, shopCode);
        return list;
    }
    @ResponseBody
    @GetMapping("api/findDaySaleAvgData")
    public Object findDaySaleAvgData(
            @RequestParam("end")String end
    ) throws Exception {
          String firstDay = Utils.getFirstDay(end);
          Long dayNum = Utils.DaySubtractNum(firstDay,end);
          Long shopNum =  cwbzXsmxHyMapper.selectShopTotalNum(firstDay, end);
          Float saleJe =  cwbzXsmxHyMapper.selectShopSaleMoney(firstDay, end);
          saleJe = saleJe==null?0:saleJe;
          shopNum = shopNum==null?0L:shopNum;
          BigDecimal je = new BigDecimal(0);
          if (shopNum!=0&&saleJe!=0){
              je = new BigDecimal(saleJe).divide(new BigDecimal(shopNum),2, BigDecimal.ROUND_HALF_UP).divide(new BigDecimal(dayNum),2, BigDecimal.ROUND_HALF_UP);
          }

          Map<String,String> reMap = new HashMap<>(4);
          reMap.put("店铺数量",String.valueOf(shopNum));
          reMap.put("当前金额",String.valueOf(saleJe));

          reMap.put("当月天数",String.valueOf(dayNum));
          reMap.put("日销售金额",String.valueOf(je));

          System.out.println(saleJe);
          List reList = new ArrayList();
          reList.add(reMap);
          return reList;
    }
    @ResponseBody
    @GetMapping("/api/findAllSmallType")
    public Object findAllSmallType(){
        List<String> areaList = cwbzXsmxHyMapper.selectAllArea(null);

        List<String> zjList = cwbzXsmxHyMapper.selectAllZj(null);

        List<String> jlList = cwbzXsmxHyMapper.selectAllJl(null);

        List<String> salesManList  =  cwbzXsmxHyMapper.selectAllSalesMan(null);

        List<String> shopList = cwbzXsmxHyMapper.selectAllShop(null);

        List<String> typeList = cwbzXsmxHyMapper.findAllSmallType();

        List<String> daleiList = cwbzXsmxHyMapper.selectAllBigType(null);

        List<JSONObject> areaChildrenList = new ArrayList<>();
        List<JSONObject> zjChildrenList = new ArrayList<>();
        List<JSONObject> jlChildrenList = new ArrayList<>();
        List<JSONObject> salesManChildrenList = new ArrayList<>();
        List<JSONObject> shopChildrenList = new ArrayList<>();
        List<JSONObject> typeChildrenList = new ArrayList<>();
        List<JSONObject> daleiChildrenList = new ArrayList<>();
        JSONObject areaJson;
        JSONObject zjJson;
        JSONObject jlJson;
        JSONObject salesManJson;
        JSONObject shopJson;
        JSONObject typeJson;
        JSONObject daleiJson;

        for(String shop:shopList){
            shopJson = new JSONObject();
            shopJson.put("value",shop);
            shopJson.put("label",shop);
            shopChildrenList.add(shopJson);
        }
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

        for (String type:typeList){
            typeJson = new JSONObject();
            typeJson.put("value",type);
            typeJson.put("label",type);
            typeChildrenList.add(typeJson);
        }
        for (String type:daleiList){
            daleiJson = new JSONObject();
            daleiJson.put("value",type);
            daleiJson.put("label",type);
            daleiChildrenList.add(daleiJson);
        }

        JSONObject area = new JSONObject();
        area.put("value","quyu");
        area.put("label","区域");
        area.put("children",areaChildrenList);

        JSONObject zj = new JSONObject();
        zj.put("value","zongjian");
        zj.put("label","总监");
        zj.put("children",zjChildrenList);

        JSONObject jl = new JSONObject();
        jl.put("value","jingli");
        jl.put("label","经理");
        jl.put("children",jlChildrenList);

        JSONObject salesMan = new JSONObject();
        salesMan.put("value","dudao");
        salesMan.put("label","督导");
        salesMan.put("children",salesManChildrenList);

        JSONObject shop = new JSONObject();
        shop.put("value","zhongduan");
        shop.put("label","终端");
        shop.put("children",shopChildrenList);
        List<JSONObject> reList = new ArrayList<>();

        JSONObject type = new JSONObject();
        type.put("value","xiaolei");
        type.put("label","小类");
        type.put("children",typeChildrenList);

        JSONObject dalei = new JSONObject();
        dalei.put("value","dalei");
        dalei.put("label","大类");
        dalei.put("children",daleiChildrenList);

        reList.add(area);
        reList.add(zj);
        reList.add(jl);
        reList.add(salesMan);
        reList.add(shop);
        reList.add(dalei);
        reList.add(type);
        return reList;
    }
    @ResponseBody
    @GetMapping("/api/findMonthReportData")
    public Object findMonthReportData(
            @RequestParam("end")String end,
            @RequestParam(defaultValue = "1")Integer pageIndex,
            @RequestParam(defaultValue = "10")Integer pageSize,
            @RequestParam(required = false)String sType,
            @RequestParam(required = false)String areaParam,
            @RequestParam(required = false)String zjParam,
            @RequestParam(required = false)String jlParam,
            @RequestParam(required = false)String salesManParam,
            @RequestParam(required = false)String shopName,
            @RequestParam(required = false)String dalei
    ) throws Exception {
        String firstDay = Utils.getFirstDay(end);
        sType = addS(sType);
        areaParam = addS(areaParam);
        zjParam = addS(zjParam);
        jlParam = addS(jlParam);
        salesManParam = addS(salesManParam);
        shopName = addS(shopName);
        dalei = addS(dalei);

        Long dayNum = Utils.DaySubtractNum(firstDay,end);
//        PageHelper.startPage(pageIndex,pageSize);
        List<Map<String,String>> list = cwbzXsmxHyMapper.selectMonthReport(firstDay,end,sType,areaParam,zjParam,jlParam,salesManParam,shopName,dalei);


//        PageInfo<Map<String,String>> pageInfo = new PageInfo<Map<String,String>>(list);
//        List<Map<String,String>> typeList = pageInfo.getList();
        Long shopNum = cwbzXsmxHyMapper.selectShopTotalNum(firstDay,end);
        shopNum = shopNum==null?0L:shopNum;
        for (Map<String,String> map:list){
           String type =  map.get("小类名称");
           Long goodsNum = cwbzXsmxHyMapper.selectNum(type);
            map.put("库存数量",String.valueOf(goodsNum));
            map.put("当前店铺数量",String.valueOf(shopNum));
            map.put("当前天数",String.valueOf(dayNum));
            String shopSaleAvg = "0";
            try {
                shopSaleAvg = String.valueOf(new BigDecimal(String.valueOf(map.get("金额"))).divide(new BigDecimal(String.valueOf(shopNum)),2, BigDecimal.ROUND_HALF_UP));
            }catch (Exception e){
            }
            map.put("店均销售量",shopSaleAvg);
            String dayAvg = "0";
            try {
                dayAvg = String.valueOf(new BigDecimal(String.valueOf(map.get("金额"))).divide(new BigDecimal(String.valueOf(shopNum)),2, BigDecimal.ROUND_HALF_UP).divide(new BigDecimal(dayNum),2,BigDecimal.ROUND_HALF_UP));
            }catch (Exception e){
            }
            map.put("店均日销售",dayAvg);
            String shopAvg = "0";
            try {
              shopAvg =  String.valueOf(new BigDecimal(goodsNum).divide(new BigDecimal(shopNum),2,BigDecimal.ROUND_HALF_UP));
            }catch (Exception e){

            }
            map.put("店均库存",shopAvg);
        }
        return list;
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
        Long shopNum = 0L;
        String saleNum ="0";
        String salePrice = "0";
        String shopKcAvg = "0";
        String avgSaleNum = "0";
        String avgSalePrice = "0";
        for (String m:monthList){
            reMap = new HashMap<>();
            Long kc = cwbzXsmxHyMapper.selectStockNum(year,m);
            shopNum = cwbzXsmxHyMapper.shopShopNum(year,m);
            kc = kc==null?0L:kc;
            shopNum=shopNum==null?0L:shopNum;
            for (Map<String,String> map:List){
               String month = String.valueOf(map.get("月份"));
               if (m.equals(month)){
//                   shopNum = String.valueOf(map.get("店铺数量"));
                   saleNum = String.valueOf(map.get("总销数量"));
                   salePrice = String.valueOf(map.get("金额"));
                   break;
               }
            }
            reMap.put("月份",year+"-"+formartMonth(m));
            reMap.put("店铺数量",String.valueOf(shopNum));
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

            shopNum = 0L;
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
            @RequestParam(defaultValue = "10")Integer pageSize,
            @RequestParam(required = false)String sType,
            @RequestParam(required = false)String areaParam,
            @RequestParam(required = false)String zjParam,
            @RequestParam(required = false)String jlParam,
            @RequestParam(required = false)String salesManParam,
            @RequestParam(required = false)String shopName,
            @RequestParam(required = false)String dalei
    ){

        sType = addS(sType);
        areaParam = addS(areaParam);
        zjParam = addS(zjParam);
        jlParam = addS(jlParam);
        salesManParam = addS(salesManParam);
        shopName = addS(shopName);
        dalei = addS(dalei);

        PageHelper.startPage(pageIndex,pageSize);
        List<Map<String,String>> list = cwbzXsmxHyMapper.selectSmallTypeYearMonthData(start, end,sType,areaParam,zjParam,jlParam,salesManParam,shopName,dalei);
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
                             @RequestParam(defaultValue = "10")Integer pageSize,
                             @RequestParam(required = false)String shopName,
                             @RequestParam(required = false)String areaParam,
                             @RequestParam(required = false)String zjParam,
                             @RequestParam(required = false)String jlParam,
                             @RequestParam(required = false)String salesManParam
    ){

        shopName = addS(shopName);
        areaParam = addS(areaParam);
        zjParam = addS(zjParam);
        jlParam = addS(jlParam);
        salesManParam = addS(salesManParam);
        PageHelper.startPage(pageIndex,pageSize);
        List<Map<String,String>> list =cwbzXsmxHyMapper.inData(start, end,areaParam,zjParam,jlParam,salesManParam,shopName);
        PageInfo<Map<String,String>> pageInfo = new PageInfo<>(list);
       return pageInfo;
    }

    @ResponseBody
    @GetMapping("api/areaHz")
    public Object areaHz(
            @RequestParam("start")String start,
            @RequestParam("end")String end,
            @RequestParam(required = false)String area
    ){

        return cwbzXsmxHyMapper.selectAreaHz(start,end,area);
    }

    @ResponseBody
    @GetMapping("api/salesManHz")
    public Object salesManHz(  @RequestParam("start")String start,
                               @RequestParam("end")String end,
                               @RequestParam(defaultValue = "1")Integer pageIndex,
                               @RequestParam(defaultValue = "10")Integer pageSize,
                               @RequestParam(required = false)String area){

        return cwbzXsmxHyMapper.selectSalesmanHz(start, end, area);
    }
    @ResponseBody
    @GetMapping("api/shopNameHz")
    public Object shopNameHz(
                               @RequestParam("start")String start,
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
    public Object findZdOpention(
            @RequestParam(required = false)String dl,//大类 1 显示大类
            @RequestParam(required = false)String xl //小类  1 显示小类
    ){
        List<String> areaList = cwbzXsmxHyMapper.selectAllArea(null);

        List<String> zjList = cwbzXsmxHyMapper.selectAllZj(null);

        List<String> jlList = cwbzXsmxHyMapper.selectAllJl(null);

        List<String> salesManList  =  cwbzXsmxHyMapper.selectAllSalesMan(null);

        List<String> shopList = cwbzXsmxHyMapper.selectAllShop(null);

        List<String> daleiList = cwbzXsmxHyMapper.selectAllBigType(null);

        List<String> xiaoleiList = cwbzXsmxHyMapper.selectAllSmallType(null);




        List<JSONObject> areaChildrenList = new ArrayList<>();
        List<JSONObject> zjChildrenList = new ArrayList<>();
        List<JSONObject> jlChildrenList = new ArrayList<>();
        List<JSONObject> salesManChildrenList = new ArrayList<>();
        List<JSONObject> shopChildrenList = new ArrayList<>();
        List<JSONObject> dlChildrenList = new ArrayList<>();
        List<JSONObject> xlChildrenList = new ArrayList<>();


        JSONObject areaJson;
        JSONObject zjJson;
        JSONObject jlJson;
        JSONObject salesManJson;
        JSONObject shopJson;
        JSONObject dlJson;
        JSONObject xlJson;

        for(String shop:shopList){
            shopJson = new JSONObject();
            shopJson.put("value",shop);
            shopJson.put("label",shop);
            shopChildrenList.add(shopJson);
        }
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

        for (String bigType:daleiList){
            dlJson = new JSONObject();
            dlJson.put("value",bigType);
            dlJson.put("label",bigType);
            dlChildrenList.add(dlJson);
        }

        for (String smallType:xiaoleiList){
            xlJson  = new JSONObject();
            xlJson.put("value",smallType);
            xlJson.put("label",smallType);
            xlChildrenList.add(xlJson);
        }

        JSONObject area = new JSONObject();
        area.put("value","quyu");
        area.put("label","区域");
        area.put("children",areaChildrenList);

        JSONObject zj = new JSONObject();
        zj.put("value","zongjian");
        zj.put("label","总监");
        zj.put("children",zjChildrenList);

        JSONObject jl = new JSONObject();
        jl.put("value","jingli");
        jl.put("label","经理");
        jl.put("children",jlChildrenList);

        JSONObject salesMan = new JSONObject();
        salesMan.put("value","dudao");
        salesMan.put("label","督导");
        salesMan.put("children",salesManChildrenList);

        JSONObject shop = new JSONObject();
        shop.put("value","zhongduan");
        shop.put("label","终端");
        shop.put("children",shopChildrenList);


        JSONObject bigBype = new JSONObject();
        bigBype.put("value","dalei");
        bigBype.put("label","大类");
        bigBype.put("children",dlChildrenList);



        JSONObject smallType = new JSONObject();
        smallType.put("value","dalei");
        smallType.put("label","大类");
        smallType.put("children",xlChildrenList);


        List<JSONObject> reList = new ArrayList<>();
        reList.add(area);
        reList.add(zj);
        reList.add(jl);
        reList.add(salesMan);
        reList.add(shop);
        if ("1".equals(dl)){
            reList.add(bigBype);
        }
        if ("1".equals(xl)){
            reList.add(smallType);

        }
        return reList;
    }

    @ResponseBody
    @RequestMapping("api/findShop")
    public Object findShop(
            @RequestParam(required = false)String area,
            @RequestParam(required = false)String zj,
            @RequestParam(required = false)String jl,
            @RequestParam(required = false)String salesMan,
            @RequestParam(required = false) String shopName
    ){
        area = addS(area);
        zj = addS(zj);
        jl = addS(jl);
        shopName  = addS(shopName);
        salesMan = addS(salesMan);
        List<String> list = cwbzXsmxHyMapper.selectShop(area, zj, jl, salesMan,shopName);
        List<String> reList = new ArrayList<>();
        for (String str: list ){
            str = "'"+str+"'";
            reList.add(str);
        }
        return reList;
    }

    @ResponseBody
    @RequestMapping("api/achievementCompareData")
    public Object achievementCompareData(
            @RequestParam("start")String start,
            @RequestParam("end")String end,
            @RequestParam(defaultValue = "1")Integer pageIndex,
            @RequestParam(defaultValue = "10")Integer pageSize,
            @RequestParam(required = false)String area
    ){

        PageHelper.startPage(pageIndex,pageSize);
        List<Map<String,String>> list = cwbzXsmxHyMapper.selectAchievementCompareData(start, end,area);
        PageInfo<Map<String,String>> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }



    private String formartMonth(String month){
        if (month.length()<2){
            return "0"+month;
        }
        return month;
    }

    private String addS(String str){
        if (str==null||"".equals(str)){
            return null;
        }

        String re="";
        String[] arr =  str.split(",");
        for(String s:arr){
            re+="'"+s+"'";
            re+=",";
        }
        re = re.substring(0,re.length()-1);
        return re;
    }
}
