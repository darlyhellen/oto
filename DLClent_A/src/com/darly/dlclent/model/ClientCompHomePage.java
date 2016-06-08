package com.darly.dlclent.model;

public class ClientCompHomePage {
    private Integer id;

    private String compIcon;

    private String compFirstname;

    private String compSecname;

    private Integer compDescription;

    private String compUrl;

    private Integer compAction;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompIcon() {
        return compIcon;
    }

    public void setCompIcon(String compIcon) {
        this.compIcon = compIcon == null ? null : compIcon.trim();
    }

    public String getCompFirstname() {
        return compFirstname;
    }

    public void setCompFirstname(String compFirstname) {
        this.compFirstname = compFirstname == null ? null : compFirstname.trim();
    }

    public String getCompSecname() {
        return compSecname;
    }

    public void setCompSecname(String compSecname) {
        this.compSecname = compSecname == null ? null : compSecname.trim();
    }

    public Integer getCompDescription() {
        return compDescription;
    }

    public void setCompDescription(Integer compDescription) {
        this.compDescription = compDescription;
    }

    public String getCompUrl() {
        return compUrl;
    }

    public void setCompUrl(String compUrl) {
        this.compUrl = compUrl == null ? null : compUrl.trim();
    }

    public Integer getCompAction() {
        return compAction;
    }

    public void setCompAction(Integer compAction) {
        this.compAction = compAction;
    }
}