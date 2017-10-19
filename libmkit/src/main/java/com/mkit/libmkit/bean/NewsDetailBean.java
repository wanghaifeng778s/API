package com.mkit.libmkit.bean;

/**
 * Created by WHF.Javas on 2017/10/19.
 */

public class NewsDetailBean {
    /**
     * code : 0
     * data : {"atype":0,"content":"<p><strong>3- एंकिल तक की पट्टियों को कहें बाय बाय-<\/strong><br>\nएंकिल तक की पट्टियों वाले कपड़ों और जूतों को आप अपने से दूर रखें क्योंकि इससे आप और छोटे लग सकते हैं। अगर आपको एंकिल तक की पट्टियों वाले फुटवियर पसंद हैं तो उसमें हील जरुर हों।<\/p>","tid":"4ac7b29368bd612129aa7f8958526ea2"}
     * msg : SUCCESS
     * status : 200
     * succ : true
     */

    private int code;
    private DataBean data;
    private String msg;
    private int status;
    private boolean succ;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isSucc() {
        return succ;
    }

    public void setSucc(boolean succ) {
        this.succ = succ;
    }

    public static class DataBean {
        /**
         * atype : 0
         * content : <p><strong>3- एंकिल तक की पट्टियों को कहें बाय बाय-</strong><br>
         एंकिल तक की पट्टियों वाले कपड़ों और जूतों को आप अपने से दूर रखें क्योंकि इससे आप और छोटे लग सकते हैं। अगर आपको एंकिल तक की पट्टियों वाले फुटवियर पसंद हैं तो उसमें हील जरुर हों।</p>
         * tid : 4ac7b29368bd612129aa7f8958526ea2
         */

        private int atype;
        private String content;
        private String tid;

        public int getAtype() {
            return atype;
        }

        public void setAtype(int atype) {
            this.atype = atype;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTid() {
            return tid;
        }

        public void setTid(String tid) {
            this.tid = tid;
        }
    }
}
