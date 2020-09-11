package com.echo.mobileweb.mapper;

import com.echo.mobileweb.entity.CwbzXsmxHy;
import com.echo.mobileweb.result.CwbzXsmxHyResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;
import tk.mybatis.mapper.common.Mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface CwbzXsmxHyMapper extends Mapper<CwbzXsmxHy> {
    List<CwbzXsmxHyResult> selectCwbzXsmxHy(
            @Param("start")String start,
            @Param("end")String end,
            @Param("shopName")String shopName,
            @Param("qy")String qy,
            @Param("zj")String zj,
            @Param("jl")String jl,
            @Param("salesMan")String salesMan,
            @Param("dl")String dl
    );

    List<CwbzXsmxHyResult> selectCwbzXsmxHyTotal(
                @Param("start")String start,
                @Param("end")String end,
                @Param("shopName")String shopName,
                @Param("qy")String qy,
                @Param("zj")String zj,
                @Param("jl")String jl,
                @Param("salesMan")String salesMan,
                @Param("dl")String dl
        );

    /**
     * 大类销售占比-会员占比
     * 查询总金额，数量
     * @param start
     * @param end
     * @param shopName
     * @param qy
     * @param zj
     * @param jl
     * @param salesMan
     * @param dl
     * @return
     */
    Map<String,String> selectCwbzXsmxHySum(
                @Param("start")String start,
                @Param("end")String end,
                @Param("shopName")String shopName,
                @Param("qy")String qy,
                @Param("zj")String zj,
                @Param("jl")String jl,
                @Param("salesMan")String salesMan,
                @Param("dl")String dl
        );

    List<CwbzXsmxHy> selectAll();

    List<String> selectAllArea(@Param("area")String area);

    /**
     * 查询所有总监
     * @param name
     * @return
     */
    List<String> selectAllZj(@Param("name")String name);

    List<String> selectAllJl(@Param("name")String name);

    List<String> selectAllSalesMan(@Param("name")String name);

    List<String> selectAllShop(@Param("name")String name);

    /**
     * 查询所有大类
     * @param name
     * @return
     */
    List<String> selectAllBigType(@Param("name")String name);

    List<String> selectAllSmallType(@Param("name")String name);

    List<String> selectShop(@Param("area")String area,@Param("zj")String zj,@Param("jl")String jl,@Param("salesMan")String salesman,@Param("shopName")String shopName);

    List<Map<String,String>> selectareaTypeData(  @Param("start")String start,
                                                  @Param("end")String end,
                                                  @Param("area")String area);

    /**
     * 大类分组求和
     * @return
     */
    List<Map<String,String>> selectDaleiTotal(
                                            @Param("start")String start,
                                            @Param("end")String end,
                                            @Param("area")String area);

    Map<String,String> selectDaleiSum( @Param("start")String start,
                                       @Param("end")String end,
                                       @Param("area")String area);

    Map<String,String> selectareaTypeDataSum(
                                                    @Param("start")String start,
                                                    @Param("end")String end,
                                                    @Param("area")String area);
    List<Map<String,String>> selectVIPrangeData(  @Param("start")String start,
                                                  @Param("end")String end,
                                                  @Param("shopName")String shopName);
    List<Map<String,String>> selectSalerangeData(  @Param("start")String start,
                                                  @Param("end")String end,
                                                  @Param("shopName")String shopName);

    List<Map<String,String>> selectSaleDayAve(  @Param("start")String start,
                                                @Param("end")String end
                                                );

    Long selectShopTotalNum(@Param("start")String start, @Param("end")String end);

    Float selectShopSaleMoney(@Param("start")String start, @Param("end")String end);

    /**
     * <h1>小类月报</h1>
     * @param start
     * @param end
     * @return
     */
    List<Map<String,String>> selectMonthReport(
                            @Param("start")String start,
                            @Param("end")String end,
                            @Param("stype")String stype,
                            @Param("areaParam")String areaParam,
                            @Param("zjParam")String zjParam,
                            @Param("jlParam")String jlParam,
                            @Param("salesManParam")String salesManParam,
                            @Param("shopName")String shopName,
                            @Param("dalei")String dalei
                        );
    List<String> findAllSmallType();
    Long selectNum(@Param("sType")String stype);

    List<Map<String,String>> selectBigYearReportData(@Param("year")String year);


    /**
     * <h1>库存数量</h1>
     * @param year 年
     * @param month 月
     * @return
     */
    Long selectStockNum(@Param("year")String year,@Param("month")String month);

    Long shopShopNum(@Param("year")String year,@Param("month")String month);
    Long selectStockNumByShopCode(@Param("shopCode")String shopCode);

    /**
     *  <h1>小类月周期</h1>
     * @param start 开始时间
     * @param end 结束时间
     * @return
     */
    List<Map<String,String>> selectSmallTypeYearMonthData(
            @Param("start")String start,
            @Param("end")String end,
            @Param("stype")String stype,
            @Param("areaParam")String areaParam,
            @Param("zjParam")String zjParam,
            @Param("jlParam")String jlParam,
            @Param("salesManParam")String salesManParam,
            @Param("shopName")String shopName,
            @Param("dalei")String dalei

    );

    /**
     * <h1>进销存分析</h1>
     * @param start
     * @param end
     * @return
     */
    List<Map<String,String>> inData(
                                @Param("start")String start,
                                @Param("end")String end,
                                @Param("areaParam")String areaParam,
                                @Param("zjParam")String zjParam,
                                @Param("jlParam")String jlParam,
                                @Param("salesManParam")String salesManParam,
                                @Param("shopName")String shopName
                        );

    Long inDataCount(@Param("start")String start, @Param("end")String end);

    /**
     * <h1>区域汇总</h1>
     * @param start
     * @param end
     * @param area
     * @return
     */
    List<Map<String,String>> selectAreaHz(@Param("start")String start, @Param("end")String end, @Param("area")String area);

    /**
     * <h1>业务员汇总</h1>
     * @param start
     * @param end
     * @param area
     * @return
     */
    List<Map<String,String>> selectSalesmanHz(@Param("start")String start, @Param("end")String end, @Param("area")String area);
    /**
     * <h1>店铺汇总</h1>
     * @param start
     * @param end
     * @param area
     * @return
     */
    List<Map<String,String>> selectShopNameHz(@Param("start")String start, @Param("end")String end, @Param("area")String area);

    /**
     * 业绩对比
     * @param start
     * @param end
     * @param area
     * @return
     */
    List<Map<String,String>> selectAchievementCompareData(@Param("start")String start, @Param("end")String end, @Param("area")String area);
}