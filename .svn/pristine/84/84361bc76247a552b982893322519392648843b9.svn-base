package com.echo.mobileweb.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

public class Ddinput {
    @Id
    @Column(name = "ID",insertable = false)
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "DDID")
    private String ddid;

    @Column(name = "DDName")
    private String ddname;

    @Column(name = "ShopID")
    private String shopid;

    @Column(name = "ShopName")
    private String shopname;

    @Column(name = "Amount")
    private BigDecimal amount;

    @Column(name = "SaleAmount")
    private BigDecimal saleamount;

    @Column(name = "AddTime")
    private Date addtime;

    @Column(name = "Remark")
    private String remark;

    /**
     * @return ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return DDID
     */
    public String getDdid() {
        return ddid;
    }

    /**
     * @param ddid
     */
    public void setDdid(String ddid) {
        this.ddid = ddid;
    }

    /**
     * @return DDName
     */
    public String getDdname() {
        return ddname;
    }

    /**
     * @param ddname
     */
    public void setDdname(String ddname) {
        this.ddname = ddname;
    }

    /**
     * @return ShopID
     */
    public String getShopid() {
        return shopid;
    }

    /**
     * @param shopid
     */
    public void setShopid(String shopid) {
        this.shopid = shopid;
    }

    /**
     * @return ShopName
     */
    public String getShopname() {
        return shopname;
    }

    /**
     * @param shopname
     */
    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    /**
     * @return Amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * @param amount
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * @return SaleAmount
     */
    public BigDecimal getSaleamount() {
        return saleamount;
    }

    /**
     * @param saleamount
     */
    public void setSaleamount(BigDecimal saleamount) {
        this.saleamount = saleamount;
    }

    /**
     * @return AddTime
     */
    public Date getAddtime() {
        return addtime;
    }

    /**
     * @param addtime
     */
    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    /**
     * @return Remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", ddid=").append(ddid);
        sb.append(", ddname=").append(ddname);
        sb.append(", shopid=").append(shopid);
        sb.append(", shopname=").append(shopname);
        sb.append(", amount=").append(amount);
        sb.append(", saleamount=").append(saleamount);
        sb.append(", addtime=").append(addtime);
        sb.append(", remark=").append(remark);
        sb.append("]");
        return sb.toString();
    }
}