package com.echo.mobileweb.mapper;

import com.echo.mobileweb.entity.Qtlsdmx;
import tk.mybatis.mapper.common.Mapper;

public interface QtlsdmxMapper extends Mapper<Qtlsdmx> {
    Double selectAmountToday(String starttime, String endtime, String shopid);
}