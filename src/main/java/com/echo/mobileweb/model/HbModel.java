package com.echo.mobileweb.model;

import com.echo.mobileweb.common.Utils;
import javafx.util.Builder;

import java.math.BigDecimal;

/**
 * 月 环比 同比 返回数据模型
 */
public class HbModel {
    private String zd;
    private BigDecimal sqje;
    private BigDecimal bqje;
    private String sqtqxsj;
    private String hb;
    private String tb;
    private BigDecimal sqmb;
    private BigDecimal bqmb;
    private String wcl;

    public String getZd() {
        return zd;
    }

    public void setZd(String zd) {
        this.zd = zd;
    }

    public BigDecimal getSqje() {
        return sqje;
    }

    public void setSqje(BigDecimal sqje) {
        this.sqje = sqje;
    }

    public BigDecimal getBqje() {
        return bqje;
    }

    public void setBqje(BigDecimal bqje) {
        this.bqje = bqje;
    }

    public String getSqtqxsj() {
        return sqtqxsj;
    }

    public void setSqtqxsj(String sqtqxsj) {
        this.sqtqxsj = sqtqxsj;
    }

    public String getHb() {
        return hb;
    }

    public void setHb(String hb) {
        this.hb = hb;
    }

    public String getTb() {
        return tb;
    }

    public void setTb(String tb) {
        this.tb = tb;
    }

    public BigDecimal getSqmb() {
        return sqmb;
    }

    public void setSqmb(BigDecimal sqmb) {
        this.sqmb = sqmb;
    }

    public BigDecimal getBqmb() {
        return bqmb;
    }

    public void setBqmb(BigDecimal bqmb) {
        this.bqmb = bqmb;
    }

    public String getWcl() {
        return wcl;
    }

    public void setWcl(String wcl) {
        this.wcl = wcl;
    }

    public HbModel(){}
    public HbModel(String zd, BigDecimal sqje, BigDecimal bqje, String sqtqxsj, String hb, String tb, BigDecimal sqmb, BigDecimal bqmb, String wcl) {
        this.zd = zd;
        this.sqje = sqje;
        this.bqje = bqje;
        this.sqtqxsj = sqtqxsj;
        this.hb = hb;
        this.tb = tb;
        this.sqmb = sqmb;
        this.bqmb = bqmb;
        this.wcl = wcl;
    }

    @Override
    public String toString() {
        return "HbModel{" +
                "zd='" + zd + '\'' +
                ", sqje=" + sqje +
                ", bqje=" + bqje +
                ", sqtqxsj='" + sqtqxsj + '\'' +
                ", hb='" + hb + '\'' +
                ", tb='" + tb + '\'' +
                ", sqmb=" + sqmb +
                ", bqmb=" + bqmb +
                ", wcl='" + wcl + '\'' +
                '}';
    }

    public static HbModel builder(String zd,BigDecimal sqje,BigDecimal bqje,String sqtqxsj,BigDecimal sqmb,BigDecimal bqmb ){
        HbModel hbModel = new HbModel();
        hbModel.setZd(zd);
        hbModel.setSqje(sqje);
        hbModel.setBqje(bqje);
        hbModel.setSqtqxsj(sqtqxsj);
        hbModel.setHb(Utils.hb(bqje,sqje));
        hbModel.setTb(Utils.tb(bqje,new BigDecimal(sqtqxsj)));
        hbModel.setSqmb(sqmb);
        hbModel.setBqmb(bqmb);
        hbModel.setWcl(Utils.wcl(bqje,bqmb));
        return hbModel;
    }
}
