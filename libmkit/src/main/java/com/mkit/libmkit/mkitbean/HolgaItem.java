package com.mkit.libmkit.mkitbean;


import java.util.List;

/**
 * 2016/5/28.
 */
public class HolgaItem implements IHolgaItem {
    private Long id;

    private int way;
    private long batch;
    private Integer otheritem;

    private String atype;

    private String atime;

    private String title;

    private int imageCount;

    private String url;

    private String tcontent;

    private String playUrl;

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

    public boolean isLike;

    private String importImage;

    private String fhot;

    private String ftop;

    private String subCategory;

    private String flagExplor;

    private String videoInfo;

    //当前item的广告在缓存的广告中的index

    private int index_of_ads = -1;
    //当前的广告样式是否是大图样式

    private boolean big_layout = false;
    //广告文字内容

    private String mark;
    private String strategyId;
    private int vedioplay = -1;

    private String likeCount;

    private Integer sourceId;



    /*
    * ad广告Bean
    * */
    private String actType;
    private String adId;
    private String channelId;
    private String content;

    public List<CoverBean> covers;

    public class CoverBean {
        public String url;

    }


    private String coverArray;
    private String endTime;
    private String exActType;
    private String exActUrl;
    private String exActTitle;
    private String landingUrl;
    private String langMode;
    private String location;
    private String priority;
    private String showTime;
    private String showType;
    private String sponsor;
    private String sponsorAvatar;
    private String subtitle;


    /*
    * facebook ad tag
    * */

    public boolean isAdLoaded=false;

    public Integer localAdId;



    public HolgaItem(Long id, int way, long batch, Integer otheritem, String atype,
                     String atime, String title, int imageCount, String url, String tcontent,
                     String playUrl, String domain, String author, String category,
                     String uuid, String cid, String image, String rTime, String taCount,
                     boolean isRead, String subscribeJson, String sid, boolean isLike,
                     String importImage, String fhot, String ftop, String subCategory,
                     String flagExplor, String videoInfo, int index_of_ads,
                     boolean big_layout, String mark, String strategyId, int vedioplay,
                     String likeCount, Integer sourceId, String actType, String adId,
                     String channelId, String content, String coverArray, String endTime,
                     String exActType, String exActUrl, String exActTitle, String landingUrl,
                     String langMode, String location, String priority, String showTime,
                     String showType, String sponsor, String sponsorAvatar,
                     String subtitle) {
        this.id = id;
        this.way = way;
        this.batch = batch;
        this.otheritem = otheritem;
        this.atype = atype;
        this.atime = atime;
        this.title = title;
        this.imageCount = imageCount;
        this.url = url;
        this.tcontent = tcontent;
        this.playUrl = playUrl;
        this.domain = domain;
        this.author = author;
        this.category = category;
        this.uuid = uuid;
        this.cid = cid;
        this.image = image;
        this.rTime = rTime;
        this.taCount = taCount;
        this.isRead = isRead;
        this.subscribeJson = subscribeJson;
        this.sid = sid;
        this.isLike = isLike;
        this.importImage = importImage;
        this.fhot = fhot;
        this.ftop = ftop;
        this.subCategory = subCategory;
        this.flagExplor = flagExplor;
        this.videoInfo = videoInfo;
        this.index_of_ads = index_of_ads;
        this.big_layout = big_layout;
        this.mark = mark;
        this.strategyId = strategyId;
        this.vedioplay = vedioplay;
        this.likeCount = likeCount;
        this.sourceId = sourceId;
        this.actType = actType;
        this.adId = adId;
        this.channelId = channelId;
        this.content = content;
        this.coverArray = coverArray;
        this.endTime = endTime;
        this.exActType = exActType;
        this.exActUrl = exActUrl;
        this.exActTitle = exActTitle;
        this.landingUrl = landingUrl;
        this.langMode = langMode;
        this.location = location;
        this.priority = priority;
        this.showTime = showTime;
        this.showType = showType;
        this.sponsor = sponsor;
        this.sponsorAvatar = sponsorAvatar;
        this.subtitle = subtitle;
    }
    public HolgaItem() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getWay() {
        return this.way;
    }
    public void setWay(int way) {
        this.way = way;
    }
    public long getBatch() {
        return this.batch;
    }
    public void setBatch(long batch) {
        this.batch = batch;
    }
    public Integer getOtheritem() {
        return this.otheritem;
    }
    public void setOtheritem(Integer otheritem) {
        this.otheritem = otheritem;
    }
    public String getAtype() {
        return this.atype;
    }
    public void setAtype(String atype) {
        this.atype = atype;
    }
    public String getAtime() {
        return this.atime;
    }
    public void setAtime(String atime) {
        this.atime = atime;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getImageCount() {
        return this.imageCount;
    }
    public void setImageCount(int imageCount) {
        this.imageCount = imageCount;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getTcontent() {
        return this.tcontent;
    }
    public void setTcontent(String tcontent) {
        this.tcontent = tcontent;
    }
    public String getPlayUrl() {
        return this.playUrl;
    }
    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }
    public String getDomain() {
        return this.domain;
    }
    public void setDomain(String domain) {
        this.domain = domain;
    }
    public String getAuthor() {
        return this.author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getCategory() {
        return this.category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getUuid() {
        return this.uuid;
    }
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    public String getCid() {
        return this.cid;
    }
    public void setCid(String cid) {
        this.cid = cid;
    }
    public String getImage() {
        return this.image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getRTime() {
        return this.rTime;
    }
    public void setRTime(String rTime) {
        this.rTime = rTime;
    }
    public String getTaCount() {
        return this.taCount;
    }
    public void setTaCount(String taCount) {
        this.taCount = taCount;
    }
    public boolean getIsRead() {
        return this.isRead;
    }
    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }
    public String getSubscribeJson() {
        return this.subscribeJson;
    }
    public void setSubscribeJson(String subscribeJson) {
        this.subscribeJson = subscribeJson;
    }
    public String getSid() {
        return this.sid;
    }
    public void setSid(String sid) {
        this.sid = sid;
    }
    public boolean getIsLike() {
        return this.isLike;
    }
    public void setIsLike(boolean isLike) {
        this.isLike = isLike;
    }
    public String getImportImage() {
        return this.importImage;
    }
    public void setImportImage(String importImage) {
        this.importImage = importImage;
    }
    public String getFhot() {
        return this.fhot;
    }
    public void setFhot(String fhot) {
        this.fhot = fhot;
    }
    public String getFtop() {
        return this.ftop;
    }
    public void setFtop(String ftop) {
        this.ftop = ftop;
    }
    public String getSubCategory() {
        return this.subCategory;
    }
    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }
    public String getFlagExplor() {
        return this.flagExplor;
    }
    public void setFlagExplor(String flagExplor) {
        this.flagExplor = flagExplor;
    }
    public String getVideoInfo() {
        return this.videoInfo;
    }
    public void setVideoInfo(String videoInfo) {
        this.videoInfo = videoInfo;
    }
    public int getIndex_of_ads() {
        return this.index_of_ads;
    }
    public void setIndex_of_ads(int index_of_ads) {
        this.index_of_ads = index_of_ads;
    }
    public boolean getBig_layout() {
        return this.big_layout;
    }
    public void setBig_layout(boolean big_layout) {
        this.big_layout = big_layout;
    }
    public String getMark() {
        return this.mark;
    }
    public void setMark(String mark) {
        this.mark = mark;
    }
    public String getStrategyId() {
        return this.strategyId;
    }
    public void setStrategyId(String strategyId) {
        this.strategyId = strategyId;
    }
    public int getVedioplay() {
        return this.vedioplay;
    }
    public void setVedioplay(int vedioplay) {
        this.vedioplay = vedioplay;
    }
    public String getLikeCount() {
        return this.likeCount;
    }
    public void setLikeCount(String likeCount) {
        this.likeCount = likeCount;
    }
    public Integer getSourceId() {
        return this.sourceId;
    }
    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }
    public String getActType() {
        return this.actType;
    }
    public void setActType(String actType) {
        this.actType = actType;
    }
    public String getAdId() {
        return this.adId;
    }
    public void setAdId(String adId) {
        this.adId = adId;
    }
    public String getChannelId() {
        return this.channelId;
    }
    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getCoverArray() {
        return this.coverArray;
    }
    public void setCoverArray(String coverArray) {
        this.coverArray = coverArray;
    }
    public String getEndTime() {
        return this.endTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    public String getExActType() {
        return this.exActType;
    }
    public void setExActType(String exActType) {
        this.exActType = exActType;
    }
    public String getExActUrl() {
        return this.exActUrl;
    }
    public void setExActUrl(String exActUrl) {
        this.exActUrl = exActUrl;
    }
    public String getExActTitle() {
        return this.exActTitle;
    }
    public void setExActTitle(String exActTitle) {
        this.exActTitle = exActTitle;
    }
    public String getLandingUrl() {
        return this.landingUrl;
    }
    public void setLandingUrl(String landingUrl) {
        this.landingUrl = landingUrl;
    }
    public String getLangMode() {
        return this.langMode;
    }
    public void setLangMode(String langMode) {
        this.langMode = langMode;
    }
    public String getLocation() {
        return this.location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getPriority() {
        return this.priority;
    }
    public void setPriority(String priority) {
        this.priority = priority;
    }
    public String getShowTime() {
        return this.showTime;
    }
    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }
    public String getShowType() {
        return this.showType;
    }
    public void setShowType(String showType) {
        this.showType = showType;
    }
    public String getSponsor() {
        return this.sponsor;
    }
    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }
    public String getSponsorAvatar() {
        return this.sponsorAvatar;
    }
    public void setSponsorAvatar(String sponsorAvatar) {
        this.sponsorAvatar = sponsorAvatar;
    }
    public String getSubtitle() {
        return this.subtitle;
    }
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }




}
