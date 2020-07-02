package com.echo.mobileweb.entity;

import java.util.Date;
import javax.persistence.*;

public class Qtlsdmx {
    @Id
    private String 单据编号;

    private Date 日期;

    private String 商店代码;

    private String 商店名称;

    private Double 金额;

    /**
     * @return 单据编号
     */
    public String get单据编号() {
        return 单据编号;
    }

    /**
     * @param 单据编号
     */
    public void set单据编号(String 单据编号) {
        this.单据编号 = 单据编号;
    }

    /**
     * @return 日期
     */
    public Date get日期() {
        return 日期;
    }

    /**
     * @param 日期
     */
    public void set日期(Date 日期) {
        this.日期 = 日期;
    }

    /**
     * @return 商店代码
     */
    public String get商店代码() {
        return 商店代码;
    }

    /**
     * @param 商店代码
     */
    public void set商店代码(String 商店代码) {
        this.商店代码 = 商店代码;
    }

    /**
     * @return 商店名称
     */
    public String get商店名称() {
        return 商店名称;
    }

    /**
     * @param 商店名称
     */
    public void set商店名称(String 商店名称) {
        this.商店名称 = 商店名称;
    }

    /**
     * @return 金额
     */
    public Double get金额() {
        return 金额;
    }

    /**
     * @param 金额
     */
    public void set金额(Double 金额) {
        this.金额 = 金额;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", 单据编号=").append(单据编号);
        sb.append(", 日期=").append(日期);
        sb.append(", 商店代码=").append(商店代码);
        sb.append(", 商店名称=").append(商店名称);
        sb.append(", 金额=").append(金额);
        sb.append("]");
        return sb.toString();
    }
}