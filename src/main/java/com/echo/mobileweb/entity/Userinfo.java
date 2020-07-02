package com.echo.mobileweb.entity;

import java.util.Date;
import javax.persistence.*;

public class Userinfo {
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "JDBC")
    private String id;

    @Column(name = "PhoneNum")
    private String phonenum;

    @Column(name = "Pwd")
    private String pwd;

    @Column(name = "LastLoginTime")
    private Date lastlogintime;

    @Column(name = "Token")
    private String token;

    /**
     * @return ID
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
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
     * @return Pwd
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * @param pwd
     */
    public void setPwd(String pwd) {
        this.pwd = pwd;
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

    /**
     * @return Token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token
     */
    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", phonenum=").append(phonenum);
        sb.append(", pwd=").append(pwd);
        sb.append(", lastlogintime=").append(lastlogintime);
        sb.append(", token=").append(token);
        sb.append("]");
        return sb.toString();
    }
}