package com.echo.mobileweb.mapper;

import com.echo.mobileweb.entity.CwbzXiaoshou;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 35086
 */
public interface CwbzXiaoshouMapper extends Mapper<CwbzXiaoshou> {
    CwbzXiaoshou selectCwbzXiaoshouById(@Param("id")String id);
    List<CwbzXiaoshou> selectCwbzXiaoshouList(
            @Param("start")String start,
            @Param("end")String end,
            @Param("pageSize")Integer pageSize,
            @Param("pageStart")Integer pageStart);

    List<Map<String,Object>> selectCwbzXiaoshou(  @Param("start")String start,
                                                  @Param("end")String end,
                                                  @Param("shopName")String shopName,
                                                  @Param("pageSize")Integer pageSize,
                                                  @Param("pageStart")Integer pageStart);

    Map<String,Object> selectCwbzXiaoshouByName(@Param("shopName")String shopName, @Param("start")String start,
                                                @Param("end")String end);

    List<Map<String,String>> selectAllShopName(@Param("shopName")String shopName);

    String selectLastYearCharge(@Param("shopName")String shopName,@Param("start")String start,@Param("end")String end);
}