package com.qixiu.common.zhoujinniao.data.bean;

public class DownloadBean {

    public String wall_name;
    public String description;
    public int image_id;

    public DownloadBean(String wall_name, String description, int image_id)
    {
        this.wall_name = wall_name;
        this.description = description;
        this.image_id = image_id;
    }

}
