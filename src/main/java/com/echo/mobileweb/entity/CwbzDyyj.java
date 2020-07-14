package com.echo.mobileweb.entity;

import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "cwbz_dyyj")
public class CwbzDyyj {
    @GeneratedValue(generator = "JDBC")
    private String 唯一码;

    private String 日期;

    private String 店铺代码;

    private String 店铺名称;

    private String 店员代码;

    private String 店员名称;

    private Long 目标金额;

    private BigDecimal 销售金额;

    /**
     * @return 唯一码
     */
    public String get唯一码() {
        return 唯一码;
    }

    /**
     * @param 唯一码
     */
    public void set唯一码(String 唯一码) {
        this.唯一码 = 唯一码;
    }

    /**
     * @return 日期
     */
    public String get日期() {
        return 日期;
    }

    /**
     * @param 日期
     */
    public void set日期(String 日期) {
        this.日期 = 日期;
    }

    /**
     * @return 店铺代码
     */
    public String get店铺代码() {
        return 店铺代码;
    }

    /**
     * @param 店铺代码
     */
    public void set店铺代码(String 店铺代码) {
        this.店铺代码 = 店铺代码;
    }

    /**
     * @return 店铺名称
     */
    public String get店铺名称() {
        return 店铺名称;
    }

    /**
     * @param 店铺名称
     */
    public void set店铺名称(String 店铺名称) {
        this.店铺名称 = 店铺名称;
    }

    /**
     * @return 店员代码
     */
    public String get店员代码() {
        return 店员代码;
    }

    /**
     * @param 店员代码
     */
    public void set店员代码(String 店员代码) {
        this.店员代码 = 店员代码;
    }

    /**
     * @return 店员名称
     */
    public String get店员名称() {
        return 店员名称;
    }

    /**
     * @param 店员名称
     */
    public void set店员名称(String 店员名称) {
        this.店员名称 = 店员名称;
    }

    /**
     * @return 目标金额
     */
    public Long get目标金额() {
        return 目标金额;
    }

    /**
     * @param 目标金额
     */
    public void set目标金额(Long 目标金额) {
        this.目标金额 = 目标金额;
    }

    /**
     * @return 销售金额
     */
    public BigDecimal get销售金额() {
        return 销售金额;
    }

    /**
     * @param 销售金额
     */
    public void set销售金额(BigDecimal 销售金额) {
        this.销售金额 = 销售金额;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", 唯一码=").append(唯一码);
        sb.append(", 日期=").append(日期);
        sb.append(", 店铺代码=").append(店铺代码);
        sb.append(", 店铺名称=").append(店铺名称);
        sb.append(", 店员代码=").append(店员代码);
        sb.append(", 店员名称=").append(店员名称);
        sb.append(", 目标金额=").append(目标金额);
        sb.append(", 销售金额=").append(销售金额);
        sb.append("]");
        return sb.toString();
    }
}