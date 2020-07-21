package com.echo.mobileweb.mapper;

import com.echo.mobileweb.entity.CwbzXsmxHy;
import com.echo.mobileweb.result.CwbzXsmxHyResult;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface CwbzXsmxHyMapper extends Mapper<CwbzXsmxHy> {
    List<CwbzXsmxHyResult> selectCwbzXsmxHy(
            @Param("start")String start,
            @Param("end")String end,
            @Param("shopName")String shopName
//            @Param("order")String order,
//            @Param("prop")String prop
    );


    List<CwbzXsmxHy> selectAll();

    List<String> selectAllArea();

    List<Map<String,String>> selectareaTypeData(  @Param("start")String start,
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

    Long selectShopTotalNum(@Param("start")String start,
                                  @Param("end")String end);
    Float selectShopSaleMoney(@Param("start")String start,
                                  @Param("end")String end);

    /**
     * <h1>小类月报</h1>
     * @param start
     * @param end
     * @return
     */
    List<Map<String,String>> selectMonthReport(@Param("start")String start, @Param("end")String end);

    Long selectNum(@Param("sType")String stype);


}