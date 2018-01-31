package com.github.peterchen82.mybatis.entity;

import java.io.Serializable;
import java.util.Date;
import com.alibaba.fastjson.JSON;

public class ArtcleEntity implements Serializable {

    private static final long serialVersionUID = 2629983876059197650L;

    private Long id;
    private Date created;
    private String title;
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    @Override
    public String toString(){
        return JSON.toJSONString(this);
    }
}
