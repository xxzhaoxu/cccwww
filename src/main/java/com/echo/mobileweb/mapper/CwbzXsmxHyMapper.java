package com.echo.mobileweb.mapper;

import com.echo.mobileweb.entity.CwbzXsmxHy;
import com.echo.mobileweb.result.CwbzXsmxHyResult;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

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
}