package com.mkit.libmkit.bean;


/**
 * 2016/5/28.
 */
public class HolgaDetail {

    private String atime;
    private String title;
    private int imageCount;
    private String url;
    private String tcontent;
    private String domain;
    private String author;
    private String category;
    private String uuid;
    private String cid;
    private String image;
    private String rTime;
    private String taCount;
    private boolean isRead;//记录新闻阅读状态
    private String subscribeJson;
    private String sid;
    private boolean isLike;
    private String importImage;

    @Override
    public String toString() {
        return "HolgaItem{" +
                "atime='" + atime + '\'' +
                ", title='" + title + '\'' +
                ", imageCount=" + imageCount +
                ", url='" + url + '\'' +
                ", tcontent='" + tcontent + '\'' +
                ", domain='" + domain + '\'' +
                ", author='" + author + '\'' +
                ", category='" + category + '\'' +
                ", uuid='" + uuid + '\'' +
                ", cid='" + cid + '\'' +
                ", image='" + image + '\'' +
                ", rTime='" + rTime + '\'' +
                ", taCount='" + taCount + '\'' +
                ", isRead=" + isRead +
                ", subscribeJson='" + subscribeJson + '\'' +
                ", sid='" + sid + '\'' +
                ", isLike=" + isLike +
                ", importImage='" + importImage + '\'' +
                '}';
    }

    public String getImportImage() {
        return importImage;
    }

    public void setImportImage(String importImage) {
        this.importImage = importImage;
    }

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public int getImageCount() {
        return imageCount;
    }

    public void setImageCount(int imageCount) {
        this.imageCount = imageCount;
    }

    public String getSubscribeJson() {
        return subscribeJson;
    }

    public void setSubscribeJson(String subscribeJson) {
        this.subscribeJson = subscribeJson;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public String getTcontent() {
        return tcontent;
    }

    public void setTcontent(String tcontent) {
        this.tcontent = tcontent;
    }

    public String getrTime() {
        return rTime;
    }

    public void setrTime(String rTime) {
        this.rTime = rTime;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTaCount() {
        return taCount;
    }

    public void setTaCount(String taCount) {
        this.taCount = taCount;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getAtime() {
        return atime;
    }

    public void setAtime(String atime) {
        this.atime = atime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getImagecount() {
        return imageCount;
    }

    public void setImagecount(int imagecount) {
        this.imageCount = imagecount;
    }


}
