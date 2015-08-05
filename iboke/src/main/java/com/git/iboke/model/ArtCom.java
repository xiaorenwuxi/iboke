package com.git.iboke.model;

public class ArtCom {
    private Integer id;

    private Integer aId;

    private String aUser;

    private String aComment;

    private String aTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getaId() {
        return aId;
    }

    public void setaId(Integer aId) {
        this.aId = aId;
    }

    public String getaUser() {
        return aUser;
    }

    public void setaUser(String aUser) {
        this.aUser = aUser == null ? null : aUser.trim();
    }

    public String getaComment() {
        return aComment;
    }

    public void setaComment(String aComment) {
        this.aComment = aComment == null ? null : aComment.trim();
    }

    public String getaTime() {
        return aTime;
    }

    public void setaTime(String aTime) {
        this.aTime = aTime == null ? null : aTime.trim();
    }
}