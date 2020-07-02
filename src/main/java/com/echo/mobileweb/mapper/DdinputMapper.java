package com.echo.mobileweb.mapper;

import com.echo.mobileweb.entity.Ddinput;
import com.echo.mobileweb.model.ComparisonModel;
import com.echo.mobileweb.model.EveryAmountDto;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface DdinputMapper extends Mapper<Ddinput> {
    public List<EveryAmountDto> getEveryDayAmount(String starttime, String endtime, String inputtime,Boolean is_wanda,Boolean is_temai,String shopname);

    public List<EveryAmountDto> getShopAmountByKehu(String starttime, String endtime, String inputtime, String name);

    public List<EveryAmountDto> getDdRank(@Param("starttime")String starttime, @Param("endtime")String endtime,@Param("inputtime") String inputtime);

    public List<EveryAmountDto> getJlRank(@Param("starttime")String starttime, @Param("endtime")String endtime,@Param("inputtime") String inputtime);

    public List<EveryAmountDto> getZjRank(@Param("starttime")String starttime, @Param("endtime")String endtime,@Param("inputtime") String inputtime);

    public List<EveryAmountDto> getDqRank(@Param("starttime")String starttime, @Param("endtime")String endtime,@Param("inputtime") String inputtime);

    public List<ComparisonModel> getComparison(@Param("shopname") String shopname, @Param("shangqi_start") String shangqi_start, @Param("shangqi_end") String shangqi_end, @Param("benqi_start") String benqi_start, @Param("benqi_end") String benqi_end, @Param("shangqi_input") String shangqi_input, @Param("benqi_input") String benqi_input);

    public Double getAmountByDay(String starttime, String endtime);
}