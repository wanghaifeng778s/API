package com.mkit.libmkit.bean;


import java.util.List;

/**
 * 2016/5/28.
 */
public class HolgaItem implements IHolgaItem {

    /**
     * addtime : 1508381479255
     * atype : 0
     * author : {"avatar":"http://img.masala-sg.goldenmob.com/avatar/prashikshan.jpg","describe":"marathmoli gostin cha sangrah","name":"Riya"}
     * imagecount : 0
     * images : ["http://img.masala-sg.goldenmob.com/img/4ac7b29368bd612129aa7f8958526ea2/i_0_imagesqtbnANd9GcRyeW6VmhSphnJ3ipYzdsfSZs7Vb5Zl-208","http://img.masala-sg.goldenmob.com/img/4ac7b29368bd612129aa7f8958526ea2/i_1_Makeverticalstripesyourbestfriends-208.jpg","http://img.masala-sg.goldenmob.com/img/4ac7b29368bd612129aa7f8958526ea2/i_2_AvoidwearinganythingofCalflength-208.jpg"]
     * tid : 4ac7b29368bd612129aa7f8958526ea2
     * title : दीपिका पादूकोण पर सब अच्छा लग सकता हैं तो मुझ पर क्यों नहीं? ये सवाल । लेकिन अब आप चिंता ना करें,!!!
     */

    private long addtime;
    private int atype;
    private AuthorBean author;
    private int imagecount;
    private String tid;
    private String title;
    private List<String> images;

    public long getAddtime() {
        return addtime;
    }

    public void setAddtime(long addtime) {
        this.addtime = addtime;
    }

    public int getAtype() {
        return atype;
    }

    public void setAtype(int atype) {
        this.atype = atype;
    }

    public AuthorBean getAuthor() {
        return author;
    }

    public void setAuthor(AuthorBean author) {
        this.author = author;
    }

    public int getImagecount() {
        return imagecount;
    }

    public void setImagecount(int imagecount) {
        this.imagecount = imagecount;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public static class AuthorBean {
        /**
         * avatar : http://img.masala-sg.goldenmob.com/avatar/prashikshan.jpg
         * describe : marathmoli gostin cha sangrah
         * name : Riya
         */

        private String avatar;
        private String describe;
        private String name;

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getDescribe() {
            return describe;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
