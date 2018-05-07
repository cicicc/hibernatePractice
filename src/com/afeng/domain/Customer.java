package com.afeng.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Customer implements Serializable {

    /*
    CREATE TABLE `customer` (
      `cust_id` int(64) NOT NULL AUTO_INCREMENT,
      `cust_name` varchar(50) DEFAULT NULL,
      `cust_source` varchar(50) DEFAULT NULL,
      `cust_industry` varchar(50) DEFAULT NULL,
      `cust_level` varchar(20) DEFAULT NULL,
      `cust_phone` varchar(30) DEFAULT NULL,
      `cust_mobile` varchar(30) DEFAULT NULL,
      PRIMARY KEY (`cust_id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8
     */

    private Integer cust_id;
    private String cust_name;
    private String cust_source;
    private String cust_industry;
    private String cust_level;
    private String cust_phone;
    private String cust_mobile;
    //此处set必须创建一个子类对象 否则会报错
    private Set<LinkMan> linkMans = new HashSet<>();

    public Customer() {
    }

    public Integer getCust_id() {
        return cust_id;
    }

    public void setCust_id(Integer cust_id) {
        this.cust_id = cust_id;
    }

    public String getCust_name() {
        return cust_name;
    }

    public void setCust_name(String cust_name) {
        this.cust_name = cust_name;
    }

    public String getCust_source() {
        return cust_source;
    }

    public void setCust_source(String cust_source) {
        this.cust_source = cust_source;
    }

    public String getCust_industry() {
        return cust_industry;
    }

    public void setCust_industry(String cust_industry) {
        this.cust_industry = cust_industry;
    }

    public String getCust_level() {
        return cust_level;
    }

    public void setCust_level(String cust_level) {
        this.cust_level = cust_level;
    }

    public String getCust_phone() {
        return cust_phone;
    }

    public void setCust_phone(String cust_phone) {
        this.cust_phone = cust_phone;
    }

    public String getCust_mobile() {
        return cust_mobile;
    }

    public void setCust_mobile(String cust_mobile) {
        this.cust_mobile = cust_mobile;
    }

    public Set<LinkMan> getLinkMans() {
        return linkMans;
    }

    public void setLinkMans(Set<LinkMan> linkMans) {
        this.linkMans = linkMans;
    }
    @Override
    public String toString() {
        return "Customer{" +
                "cust_id=" + cust_id +
                ", cust_name='" + cust_name + '\'' +
                ", cust_source='" + cust_source + '\'' +
                ", cust_industry='" + cust_industry + '\'' +
                ", cust_level='" + cust_level + '\'' +
                ", cust_phone='" + cust_phone + '\'' +
                ", cust_mobile='" + cust_mobile + '\'' +
                '}';
    }
}
