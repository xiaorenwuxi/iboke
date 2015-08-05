package com.git.iboke.model;

public class Img {
    private Integer id;

    private Integer tId;

    private String imgname;

    private String imgurl;

    private String imgdesc;

    private String imginfo;

    private Boolean imgtype;

    private String time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer gettId() {
        return tId;
    }

    public void settId(Integer tId) {
        this.tId = tId;
    }

    public String getImgname() {
        return imgname;
    }

    public void setImgname(String imgname) {
        this.imgname = imgname == null ? null : imgname.trim();
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl == null ? null : imgurl.trim();
    }

    public String getImgdesc() {
        return imgdesc;
    }

    public void setImgdesc(String imgdesc) {
        this.imgdesc = imgdesc == null ? null : imgdesc.trim();
    }

    public String getImginfo() {
        return imginfo;
    }

    public void setImginfo(String imginfo) {
        this.imginfo = imginfo == null ? null : imginfo.trim();
    }

    public Boolean getImgtype() {
        return imgtype;
    }

    public void setImgtype(Boolean imgtype) {
        this.imgtype = imgtype;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }
}