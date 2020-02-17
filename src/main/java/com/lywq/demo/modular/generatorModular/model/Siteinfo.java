package com.lywq.demo.modular.generatorModular.model;

import javax.persistence.*;

public class Siteinfo {
    @Id
    private Integer id;

    @Column(name = "userId")
    private Integer userid;

    @Column(name = "siteName")
    private String sitename;

    @Column(name = "siteDescription")
    private String sitedescription;

    @Column(name = "siteContent")
    private String sitecontent;

    private String address;

    private String record;

    private String telephone;

    /**
     * @return id
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
     * @return userId
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * @param userid
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * @return siteName
     */
    public String getSitename() {
        return sitename;
    }

    /**
     * @param sitename
     */
    public void setSitename(String sitename) {
        this.sitename = sitename == null ? null : sitename.trim();
    }

    /**
     * @return siteDescription
     */
    public String getSitedescription() {
        return sitedescription;
    }

    /**
     * @param sitedescription
     */
    public void setSitedescription(String sitedescription) {
        this.sitedescription = sitedescription == null ? null : sitedescription.trim();
    }

    /**
     * @return siteContent
     */
    public String getSitecontent() {
        return sitecontent;
    }

    /**
     * @param sitecontent
     */
    public void setSitecontent(String sitecontent) {
        this.sitecontent = sitecontent == null ? null : sitecontent.trim();
    }

    /**
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * @return record
     */
    public String getRecord() {
        return record;
    }

    /**
     * @param record
     */
    public void setRecord(String record) {
        this.record = record == null ? null : record.trim();
    }

    /**
     * @return telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * @param telephone
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }
}