package com.echo.mobileweb.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "T_UserInfo")
public class TUserinfo {
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "PhoneNum")
    private String phonenum;

    @Column(name = "PassWord")
    private String password;

    @Column(name = "LastLoginTime")
    private Date lastlogintime;

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
     * @return PhoneNum
     */
    public String getPhonenum() {
        return phonenum;
    }

    /**
     * @param phonenum
     */
    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    /**
     * @return PassWord
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return LastLoginTime
     */
    public Date getLastlogintime() {
        return lastlogintime;
    }

    /**
     * @param lastlogintime
     */
    public void setLastlogintime(Date lastlogintime) {
        this.lastlogintime = lastlogintime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", phonenum=").append(phonenum);
        sb.append(", password=").append(password);
        sb.append(", lastlogintime=").append(lastlogintime);
        sb.append("]");
        return sb.toString();
    }
}