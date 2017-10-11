package com.mkit.libmkit.utils;


import org.json.JSONArray;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;


/**
 * 2016/7/11 0011.
 */
public class ReplaceHtmlTag {

    public static String replaceImgSrc(String content, String replaceHttp) {

        if (content != null) {
            Document document = Jsoup.parse(content);

            Element div = document.select("img").first();

            Element head = document.select("head").first();
            head.html("<script type='text/javascript'>" + "function displaymessage()"
                    + "{"
                    + "javascriptInterface.openImage(https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png);"
                    + "}" +
                    "</script>");

            div.attr("src", replaceHttp);
            // div.attr("onclick","add");
            //  div.attr("src", replaceHttps.get(i));
            div.attr("srcset", replaceHttp);
            div.removeAttr("srcset");

            div.attr("onclick", "displaymessage()");
            //  div.attr("onclick","this.src=='https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png'?'window.open(file:///android_asset/html.png)':'this.src=https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png'");
            //  div.attr("onclick","this.src='https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png'");
            // ToastUtils.showLong(div+"");
            content = document.toString();
            //   Logger.e(content+"");
        }
        return content;


    }

    public static String replaceImgSrcs(String content, List<String> replaceHttps) {
        Document document;
        for (int i = 0; i < replaceHttps.size(); i++) {
            if (content != null) {
                document = Jsoup.parse(content);

                Element div = document.select("img").get(i);


    /*        <script type="text/javascript">


                    function displaymessage(id,imgSrc){
                if(document.getElementById(id).src != imgSrc){
                    document.getElementById(id).src = imgSrc;
                }else{
                    alert("bb")
                }
            }

            </script>*/
          /*      Element head=document.select("head").first();
                head.html("<script type='text/javascript'>"+"function displaymessage(" +
                        "" +i+","+replaceHttps+
                        ")"
                        +"{"
                        +   " if(document.getElementById(id).src != "+replaceHttps.get(i)+")"+
                "{"+
                  "  document.getElementById(id).src ="+replaceHttps.get(i)+";"+
               " }"+"else{"+
                        "javascriptInterface.openImage("+replaceHttps+");"
               +" }"


                        +"}"+
                        "</script>");
*/


                div.attr("src", "file://" + replaceHttps.get(i));
                // div.attr("id",i+"");
                //div.attr("onclick","displaymessage('"+i+"','"+replaceHttps.get(i)+"')");
                div.removeAttr("srcset");
                // div.attr("srcset","file://"+replaceHttps.get(i));
                //div.attr("srcset", replaceHttps.get(i));

                // div.attr("onclick","this.src='https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png'");
                //  div.attr("onclick","this.src=='https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png'?'window.open(file:///android_asset/html.png)':'this.src=https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png'");
                //<img src="a.jpg" name="anniu" onClick="anniu.src='b.jpg'" onClick="anniu.src='a.jpg'" >
                //<img src="a.jpg" onclick="this.src = this.src.indexOf('a.jpg') == -1 ? 'a.jpg' : 'b.jpg';" />
                // ToastUtils.showLong(div+"");
                content = document.toString();
                // Logger.e(content + "");

            }

        }
        return content;


    }

    public static String replaceSmallSrcs(String content, List<String> replaceHttps, List<String> heights, List<String> widths) throws Exception {

        String dis = "1080";
        int size;
        List<String> listsrc = new ArrayList<>();
        if (Integer.valueOf(dis) < 500) {
            size = 290;
        } else if (Integer.valueOf(dis) < 900) {
            size = 360;
        } else {
            size = 400;
        }
        Document document;
        document = Jsoup.parse(content);

        for (int i = 0; i < replaceHttps.size(); i++) {
            if (content != null) {


                Element div = document.select("img").get(i);
                String src = div.attr("src");

                div.attr("src", replaceHttps.get(i));

                //div.wrap("<div align=\"center\" style=\" background-color: #ff6600 ;width:208;height:"+"150"+";text-align: center;margin: 0 auto;\"></div>");
                div.attr("srcset", replaceHttps.get(i));
                div.removeAttr("srcset");


                int index = src.lastIndexOf(".");
                String fimg = src.substring(0, index - 4) + "-" + size + src.substring(index);
                listsrc.add(fimg);


                div.attr("style", "display:block;");
                div.attr("id", i + "");
                div.attr("onclick", "displaymessage('" + i + "','" + fimg + "')");
                div.removeAttr("srcset");

            }


        }
        Element head = document.select("head").first();
        JSONArray jsonArray = new JSONArray(listsrc);
        head.html("<script type='text/javascript'>" +

                "function displaymessage(" +
                "id" + "," + "srb" +
                ")"
                + "{"
                + " if(document.getElementById(id).src != srb)" +
                "{" +
                "  document.getElementById(id).src =srb;" +
                " }" + "else{" +
                "javascriptInterface.openImage('" + listsrc + "',id+srb);"
                + " }"

                + "}" +
                "</script>");
        content = document.toString();
        return content;


    }

    public static String replaceSmallSrcsGif(String content, List<String> replaceHttps, List<String> heights, List<String> widths, List<Integer> list) throws Exception {

        String dis = "1080";
        int size;
        List<String> listsrc = new ArrayList<>();
        if (Integer.valueOf(dis) < 500) {
            size = 290;
        } else if (Integer.valueOf(dis) < 900) {
            size = 360;
        } else {
            size = 400;
        }
        Document document;
        document = Jsoup.parse(content);

        for (int i = 0; i < replaceHttps.size(); i++) {
            if (content != null) {


                Element div = document.select("img").get(i * 2);
                String src = div.attr("src");
                boolean small = false;
                if (Integer.valueOf(heights.get(i)) <= 120) {
                    small = true;
                }

                div.attr("src", replaceHttps.get(i));
                div.attr("datasrc", replaceHttps.get(i));

                int index = src.lastIndexOf(".");
                String fimg;
                if (list.get(i) == 0) {
                    if (small) {
                        fimg = src.substring(0, index - 4);
                    } else {

                        fimg = src.substring(0, index - 4) + "-" + size + src.substring(index);

                    }
                    listsrc.add(fimg);

                } else {
                    fimg = src.substring(0, index - 4);
                    listsrc.add(fimg);
                }

                div.attr("srcset", replaceHttps.get(i));
                div.removeAttr("srcset");
                if (!small) {
                    if (list.get(i) == 1) {
                        div.append("<img src=\"file:///android_asset/gif.png\" class=\"gif\"/>");
                    } else {
                        div.append("<img src=\"file:///android_asset/waiting.png\" class=\"gif\"/>");
                    }
                }

                if (!small) {
                    div.wrap("<div><div class=\"position\" ontouchend=\"displaymessage('" + i + "','" + fimg + "')\"></div></div>");
                } else {
                    div.attr("src", fimg);
                }

                if (!small) {
                    div.attr("style", "display:block;");
                    div.attr("openImgFlag", "true");
                }
                div.attr("id", i + "");
                div.attr("class", "picture");               // div.attr("onerror","this.src="+replaceHttps.get(i));

                div.removeAttr("srcset");

            }


        }
        Element head = document.select("head").first();
        head.html(
                "<script type=\"text/javascript\" src=\"file:///android_asset/zepto.js\" ></script>"
                        + "<script type='text/javascript'>" + "var gifFlags=" + list + "</script>"
                        + "<script type='text/javascript'src=\"file:///android_asset/smallImg.js\">" +
                        "</script>");
        content = document.toString();

        return content;


    }

    public static String replacePlaceHolderSrcs(String content, List<String> replaceHttps, List<String> heights, List<String> widths, List<Integer> list) throws Exception {
        String dis = "1080";
        int size;
        List<String> listsrc = new ArrayList<>();
        if (Integer.valueOf(dis) < 500) {
            size = 290;
        } else if (Integer.valueOf(dis) < 900) {
            size = 360;
        } else {
            size = 400;
        }

        Document document;
        document = Jsoup.parse(content);

        for (int i = 0; i < replaceHttps.size(); i++) {
            if (content != null) {


                Element div = document.select("img").get(i * 2);
                String src = div.attr("src");
                boolean small = false;
                if (Integer.valueOf(heights.get(i)) <= 120) {

                    small = true;
                }
                // div.attr("style","vertical-align: middle;");

                if (small) {
                    div.attr("src", src.substring(0, src.lastIndexOf(".") - 4));
                } else {
                    div.attr("src", "file:///android_asset/trans.png");
                    div.attr("datasrc", "file:///android_asset/trans.png");
                }
                String height = heights.get(i);
                float fnum = Float.valueOf(heights.get(i)) / Float.valueOf(widths.get(i));
                String fheight;
                String fwidth;
                if ((height.equals("150") && widths.equals("208") || small)) {
                    fheight = "auto";
                    fwidth = "auto";
                } else {
                    fheight = (int) (fnum * 320) + "";
                    fwidth = "100%";
                }
                if (!small) {
                    if (list.get(i) == 1) {
                        div.append("<img src=\"file:///android_asset/gif.png\" class=\"gif\"/>");
                    } else {
                        div.append("<img src=\"file:///android_asset/waiting.png\" class=\"gif\"/>");
                    }
                }
                String fimg;
                int index = src.lastIndexOf(".");
                if (small) {
                    fimg = src.substring(0, index - 4);

                    listsrc.add(fimg);

                    div.removeAttr("srcset");

                } else {
                    fimg = src.substring(0, index - 4) + "-" + size + src.substring(index);
                    listsrc.add(fimg);

                    div.removeAttr("srcset");
                }
              /*  if(!small){
                    div.wrap("<div><div class=\"position\" ontouchend=\"displaymessage('"+i+"','"+fimg+"')\"></div></div>");}*/
                if (!small) {
                    div.wrap("<div><div class=\"position\" ontouchend=\"displaymessage('" + i + "','" + fimg + "')\" align=\"center\" style=\" background-color: #f2f2f2 ;width:" + fwidth + ";height:" + fheight + ";text-align: center;margin: 0 auto;\"></div></div>");
                } else {
                    div.attr("src", fimg);
                }

                div.attr("height", fheight + "");
                div.attr("width", fwidth);
                if (!small) {
                    div.attr("style", "display:block;");
                }
                div.attr("srcset", "");
                div.removeAttr("srcset");
                div.removeAttr("alt");


                div.attr("id", i + "");
                if (!small) {
                    div.attr("class", "picture");
                    div.attr("openImgFlag", "true");
                }
                // div.attr("onerror","this.src='file:///android_asset/trans.png'");


            }

        }
        Element head = document.select("head").first();
        head.html(
                "<script type=\"text/javascript\" src=\"file:///android_asset/zepto.js\" ></script>"
                        + "<script type='text/javascript'>" + "var gifFlags=" + list + "</script>"
                        + "<script type='text/javascript'src=\"file:///android_asset/nopicImg.js\">" +
                        "</script>");

        content = document.toString();

        return content;


    }


    public static List<String> sendBackdescribe(String content, int size) throws Exception {
        List<String> describes = new ArrayList<>();
        Document document;
        document = Jsoup.parse(content);
        for (int i = 0; i < size; i++) {
            Element div = document.select("img").get(i);
            Element div2 = document.select("figcaption").get(i);
            String describe = div2.text();
            if (describe.length() == 0) {
                describe = div.attr("alt");
                if (describe.length() == 0) {
                    describe = div.attr("title");
                }
            }
            //String describe=div.attr("alt");


            describes.add(describe);

        }


        return describes;
    }

    public static List<String> sendBackImage(String content, int size) throws Exception {
        List<String> describes = new ArrayList<>();
        Document document;
        document = Jsoup.parse(content);
        for (int i = 0; i < size; i++) {
            Element div = document.select("img").get(i);
            //String img=div.getElementsByAttribute("src").toString();
            String img = div.attr("src").toString();
            describes.add(img);

        }


        return describes;
    }

    public static String replaceSampleSrcs(String content, List<String> replaceHttps, List<String> heights, List<String> widths, List<Integer> list) throws Exception {
        List<String> listsrc = new ArrayList<>();
        String dis = "1080";
        int size;

        if (Integer.valueOf(dis) < 500) {
            size = 290;
        } else if (Integer.valueOf(dis) < 900) {
            size = 360;
        } else {
            size = 400;
        }
        Document document;
        document = Jsoup.parse(content);
        for (int i = 0; i < replaceHttps.size(); i++) {
            if (content != null) {
                boolean small = false;
                if (Integer.valueOf(heights.get(i)) <= 120) {
                    small = true;
                }
                Element div = document.select("img").get(i);
                String src = div.attr("src");
                String height = heights.get(i);
                float fnum = Float.valueOf(heights.get(i)) / Float.valueOf(widths.get(i));
                String fheight;
                String fwidth;
                if ((height.equals("150") && widths.equals("208")) || small) {
                    fheight = "auto";
                    fwidth = "auto";

                } else {
//                    fheight = (int) (fnum * 320) + "";
                    fheight = "auto";
                    fwidth = "100%";
                }

                if (!small) {
                    div.wrap("<div align=\"center\" style=\" background-color: #f2f2f2 ;width:" + fwidth + ";height:" + fheight + ";text-align: center;margin: 0 auto;\"></div>");
                }


                div.attr("height", fheight + "");
                div.attr("width", fwidth);
                if (!small) {
                    div.attr("style", "display:block;");
                }
                div.removeAttr("srcset");
                div.attr("onerror", "this.src='file:///android_asset/trans.png'");

                int index = src.lastIndexOf(".");
                String fimg;
                if (list.get(i) == 0) {
                    if (small) {
                        fimg = src.substring(0, index - 4);
                    } else {
                        fimg = src.substring(0, index - 4) + "-" + size + src.substring(index);
                    }
                    listsrc.add(fimg);
                } else {

                    fimg = src.substring(0, index - 4);
                    listsrc.add(fimg);
                }

                div.attr("src", fimg);

                div.attr("id", i + "");
                if (!small) {
                    div.attr("onclick", "displaymessage('" + i + "','" + fimg + "')");
                }

            }


        }
        Element head = document.select("head").first();
        head.html("<script type='text/javascript'>" + "function displaymessage(" +
                        "id" + "," + "srb" +
                        ")"
                        + "{"
                        +
                        "javascriptInterface.openImage(id,id+srb);"


                        + "}" +
                        "</script>"
                //  +"<script type='text/javascript'>"+"function showzzz(){javascriptInterface.showtoast();}"+"</script>" send act to native

        );


        content = document.toString();
        return content;


    }

    public static String replaceSampleSrcsToNative(String content, List<String> replaceHttps, List<String> heights, List<String> widths, List<Integer> list) throws Exception {
        List<String> listsrc = new ArrayList<>();
        String dis = "1080";
        int size;

        if (Integer.valueOf(dis) < 500) {
            size = 290;
        } else if (Integer.valueOf(dis) < 900) {
            size = 360;
        } else {
            size = 400;
        }
        Document document;
        document = Jsoup.parse(content);
        for (int i = 0; i < replaceHttps.size(); i++) {
            if (content != null) {
                boolean small = false;
                if (Integer.valueOf(heights.get(i)) <= 120) {
                    small = true;
                }
                Element div = document.select("img").get(i);
                String src = div.attr("src");
                String height = heights.get(i);
                float fnum = Float.valueOf(heights.get(i)) / Float.valueOf(widths.get(i));
                String fheight;
                String fwidth;
                if ((height.equals("150") && widths.equals("208")) || small) {
                    fheight = "auto";
                    fwidth = "auto";

                } else {
                    fheight = (int) (fnum * 320) + "";
                    fwidth = "100%";
                }

                if (!small) {
                    div.wrap("<div align=\"center\" style=\" background-color: #f2f2f2 ;width:" + fwidth + ";height:" + fheight + ";text-align: center;margin: 0 auto;\"></div>");
                }


                div.attr("height", fheight + "");
                div.attr("width", fwidth);
                if (!small) {
                    div.attr("style", "display:block;");
                }
                div.removeAttr("srcset");
                div.attr("onerror", "this.src='file:///android_asset/trans.png'");

                int index = src.lastIndexOf(".");
                String fimg;
                if (list.get(i) == 0) {
                    if (small) {
                        fimg = src.substring(0, index - 4);
                    } else {
                        fimg = src.substring(0, index - 4) + "-" + size + src.substring(index);
                    }
                    listsrc.add(fimg);
                } else {

                    fimg = src.substring(0, index - 4);
                    listsrc.add(fimg);
                }

                div.attr("src", fimg);

                div.attr("id", i + "");
                if (!small) {
                    div.attr("onclick", "displaymessage('" + i + "','" + fimg + "')");
                }

            }


        }
        Element head = document.select("head").first();
        head.html("<script type='text/javascript'>" + "function displaymessage(" +
                "id" + "," + "srb" +
                ")"
                + "{"
                +
                "javascriptInterface.openImage(id,id+srb);"


                + "}" +
                "</script>");


        content = document.toString();
        return content;


    }


}
