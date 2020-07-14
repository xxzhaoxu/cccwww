package com.echo.mobileweb.mapper;

import com.echo.mobileweb.entity.CwbzDyyj;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface CwbzDyyjMapper extends Mapper<CwbzDyyj> {

    List<Map<String,Object>> selectSaleRank(@Param("start") String start, @Param("end") String end, @Param("lastStart")String lastStart, @Param("lastEnd")String lastEnd);
}