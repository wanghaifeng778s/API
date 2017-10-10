package com.feng.com.api.bean;

import java.util.List;

/**
 * Created by WHF.Javas on 2017/9/30.
 */

public class ResBean {

    private DataBean data;
    private boolean succ;
    private String msg;
    private int code;
    private int status;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public boolean isSucc() {
        return succ;
    }

    public void setSucc(boolean succ) {
        this.succ = succ;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class DataBean {

        private int size;
        private int total;
        private List<ItemsBean> items;

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public static class ItemsBean {

            private String uuid;
            private int lang;
            private Object content;
            private int contentLen;
            private int atype;
            private Object images;
            private int likeCount;
            private int dislikeCount;
            private int commentCount;
            private int recommendCount;
            private int readCount;
            private String playUrl;
            private long addTime;
            private Object comments;
            private int sourceId;
            private Object flagOff;
            private AuthorBean author;
            private int strategyId;
            private boolean liked;
            private String atypeEnum;
            private String langEnum;
            private String source;
            private List<CoversBean> covers;

            public String getUuid() {
                return uuid;
            }

            public void setUuid(String uuid) {
                this.uuid = uuid;
            }

            public int getLang() {
                return lang;
            }

            public void setLang(int lang) {
                this.lang = lang;
            }

            public Object getContent() {
                return content;
            }

            public void setContent(Object content) {
                this.content = content;
            }

            public int getContentLen() {
                return contentLen;
            }

            public void setContentLen(int contentLen) {
                this.contentLen = contentLen;
            }

            public int getAtype() {
                return atype;
            }

            public void setAtype(int atype) {
                this.atype = atype;
            }

            public Object getImages() {
                return images;
            }

            public void setImages(Object images) {
                this.images = images;
            }

            public int getLikeCount() {
                return likeCount;
            }

            public void setLikeCount(int likeCount) {
                this.likeCount = likeCount;
            }

            public int getDislikeCount() {
                return dislikeCount;
            }

            public void setDislikeCount(int dislikeCount) {
                this.dislikeCount = dislikeCount;
            }

            public int getCommentCount() {
                return commentCount;
            }

            public void setCommentCount(int commentCount) {
                this.commentCount = commentCount;
            }

            public int getRecommendCount() {
                return recommendCount;
            }

            public void setRecommendCount(int recommendCount) {
                this.recommendCount = recommendCount;
            }

            public int getReadCount() {
                return readCount;
            }

            public void setReadCount(int readCount) {
                this.readCount = readCount;
            }

            public String getPlayUrl() {
                return playUrl;
            }

            public void setPlayUrl(String playUrl) {
                this.playUrl = playUrl;
            }

            public long getAddTime() {
                return addTime;
            }

            public void setAddTime(long addTime) {
                this.addTime = addTime;
            }

            public Object getComments() {
                return comments;
            }

            public void setComments(Object comments) {
                this.comments = comments;
            }

            public int getSourceId() {
                return sourceId;
            }

            public void setSourceId(int sourceId) {
                this.sourceId = sourceId;
            }

            public Object getFlagOff() {
                return flagOff;
            }

            public void setFlagOff(Object flagOff) {
                this.flagOff = flagOff;
            }

            public AuthorBean getAuthor() {
                return author;
            }

            public void setAuthor(AuthorBean author) {
                this.author = author;
            }

            public int getStrategyId() {
                return strategyId;
            }

            public void setStrategyId(int strategyId) {
                this.strategyId = strategyId;
            }

            public boolean isLiked() {
                return liked;
            }

            public void setLiked(boolean liked) {
                this.liked = liked;
            }

            public String getAtypeEnum() {
                return atypeEnum;
            }

            public void setAtypeEnum(String atypeEnum) {
                this.atypeEnum = atypeEnum;
            }

            public String getLangEnum() {
                return langEnum;
            }

            public void setLangEnum(String langEnum) {
                this.langEnum = langEnum;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public List<CoversBean> getCovers() {
                return covers;
            }

            public void setCovers(List<CoversBean> covers) {
                this.covers = covers;
            }

            public static class AuthorBean {
                /**
                 * nickname : Mary Green
                 * avatar : http://img.masala-sg.goldenmob.com/avatar/marygreen.webp
                 * uid : 3e18811377fc11e79f3300163e001ff2
                 */

                private String nickname;
                private String avatar;
                private String uid;

                public String getNickname() {
                    return nickname;
                }

                public void setNickname(String nickname) {
                    this.nickname = nickname;
                }

                public String getAvatar() {
                    return avatar;
                }

                public void setAvatar(String avatar) {
                    this.avatar = avatar;
                }

                public String getUid() {
                    return uid;
                }

                public void setUid(String uid) {
                    this.uid = uid;
                }
            }

            public static class CoversBean {
                /**
                 * s : 18743
                 * w : 650
                 * h : 366
                 * url : http://img.ugc.goldenmob.com/rozbuzzpro/9219e64166b8417c83249413598e3490.jpg-400.webp
                 */

                private int s;
                private int w;
                private int h;
                private String url;

                public int getS() {
                    return s;
                }

                public void setS(int s) {
                    this.s = s;
                }

                public int getW() {
                    return w;
                }

                public void setW(int w) {
                    this.w = w;
                }

                public int getH() {
                    return h;
                }

                public void setH(int h) {
                    this.h = h;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }
            }
        }
    }
}
