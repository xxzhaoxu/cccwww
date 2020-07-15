package com.echo.mobileweb.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "cwbz_xsmx_hy")
public class CwbzXsmxHy {
    @GeneratedValue(generator = "JDBC")
    private String 小票号;

    private Date 日期;

    private String 店铺代码;

    private String 店铺名称;

    private String 经理名称;

    private String 督导名称;

    private String 总监名称;

    private String 地区名称;

    private String 商品代码;

    private Double 数量;

    private Double 金额;

    private String 大类名称;

    private String 小类名称;

    @Column(name = "VIP卡号")
    private String vip卡号;

    private String 顾客名字;

    private String 顾客手机;

    private BigDecimal 累计消费;

    private String 厂家;

    private String 吊牌价;

    /**
     * @return 小票号
     */
    public String get小票号() {
        return 小票号;
    }

    /**
     * @param 小票号
     */
    public void set小票号(String 小票号) {
        this.小票号 = 小票号;
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
     * @return 经理名称
     */
    public String get经理名称() {
        return 经理名称;
    }

    /**
     * @param 经理名称
     */
    public void set经理名称(String 经理名称) {
        this.经理名称 = 经理名称;
    }

    /**
     * @return 督导名称
     */
    public String get督导名称() {
        return 督导名称;
    }

    /**
     * @param 督导名称
     */
    public void set督导名称(String 督导名称) {
        this.督导名称 = 督导名称;
    }

    /**
     * @return 总监名称
     */
    public String get总监名称() {
        return 总监名称;
    }

    /**
     * @param 总监名称
     */
    public void set总监名称(String 总监名称) {
        this.总监名称 = 总监名称;
    }

    /**
     * @return 地区名称
     */
    public String get地区名称() {
        return 地区名称;
    }

    /**
     * @param 地区名称
     */
    public void set地区名称(String 地区名称) {
        this.地区名称 = 地区名称;
    }

    /**
     * @return 商品代码
     */
    public String get商品代码() {
        return 商品代码;
    }

    /**
     * @param 商品代码
     */
    public void set商品代码(String 商品代码) {
        this.商品代码 = 商品代码;
    }

    /**
     * @return 数量
     */
    public Double get数量() {
        return 数量;
    }

    /**
     * @param 数量
     */
    public void set数量(Double 数量) {
        this.数量 = 数量;
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

    /**
     * @return 大类名称
     */
    public String get大类名称() {
        return 大类名称;
    }

    /**
     * @param 大类名称
     */
    public void set大类名称(String 大类名称) {
        this.大类名称 = 大类名称;
    }

    /**
     * @return 小类名称
     */
    public String get小类名称() {
        return 小类名称;
    }

    /**
     * @param 小类名称
     */
    public void set小类名称(String 小类名称) {
        this.小类名称 = 小类名称;
    }

    /**
     * @return VIP卡号
     */
    public String getVip卡号() {
        return vip卡号;
    }

    /**
     * @param vip卡号
     */
    public void setVip卡号(String vip卡号) {
        this.vip卡号 = vip卡号;
    }

    /**
     * @return 顾客名字
     */
    public String get顾客名字() {
        return 顾客名字;
    }

    /**
     * @param 顾客名字
     */
    public void set顾客名字(String 顾客名字) {
        this.顾客名字 = 顾客名字;
    }

    /**
     * @return 顾客手机
     */
    public String get顾客手机() {
        return 顾客手机;
    }

    /**
     * @param 顾客手机
     */
    public void set顾客手机(String 顾客手机) {
        this.顾客手机 = 顾客手机;
    }

    /**
     * @return 累计消费
     */
    public BigDecimal get累计消费() {
        return 累计消费;
    }

    /**
     * @param 累计消费
     */
    public void set累计消费(BigDecimal 累计消费) {
        this.累计消费 = 累计消费;
    }

    /**
     * @return 厂家
     */
    public String get厂家() {
        return 厂家;
    }

    /**
     * @param 厂家
     */
    public void set厂家(String 厂家) {
        this.厂家 = 厂家;
    }

    /**
     * @return 吊牌价
     */
    public String get吊牌价() {
        return 吊牌价;
    }

    /**
     * @param 吊牌价
     */
    public void set吊牌价(String 吊牌价) {
        this.吊牌价 = 吊牌价;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", 小票号=").append(小票号);
        sb.append(", 日期=").append(日期);
        sb.append(", 店铺代码=").append(店铺代码);
        sb.append(", 店铺名称=").append(店铺名称);
        sb.append(", 经理名称=").append(经理名称);
        sb.append(", 督导名称=").append(督导名称);
        sb.append(", 总监名称=").append(总监名称);
        sb.append(", 地区名称=").append(地区名称);
        sb.append(", 商品代码=").append(商品代码);
        sb.append(", 数量=").append(数量);
        sb.append(", 金额=").append(金额);
        sb.append(", 大类名称=").append(大类名称);
        sb.append(", 小类名称=").append(小类名称);
        sb.append(", vip卡号=").append(vip卡号);
        sb.append(", 顾客名字=").append(顾客名字);
        sb.append(", 顾客手机=").append(顾客手机);
        sb.append(", 累计消费=").append(累计消费);
        sb.append(", 厂家=").append(厂家);
        sb.append(", 吊牌价=").append(吊牌价);
        sb.append("]");
        return sb.toString();
    }
}