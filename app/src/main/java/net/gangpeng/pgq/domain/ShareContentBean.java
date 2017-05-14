package net.gangpeng.pgq.domain;

/**
 * className: ShareContentBean
 * function: 分享内容
 * <p/> QQ和QQ空间所需字段：title,titleUrl,text,imageUrl
 * <p/> 微信好友所需字段：title，text，imageUrl，url
 * <p/> 微信朋友圈所需字段：title，imageUrl，url
 * <p/> 微博所需字段：text，imageUrl
 * created at 16/7/7 17:28
 *
 * @author pg
 */
public class ShareContentBean {
    /**
     * title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
     */
    private String title;
    /**
     * titleUrl是标题的网络链接，仅在人人网和QQ空间和QQ使用
     */
    private String titleUrl;
    /**
     * text是分享文本，所有平台都需要这个字段
     */
    private String text;
    /**
     * url仅在微信（包括好友和朋友圈）中使用
     */
    private String url;
    /**
     * comment是我对这条分享的评论，仅在人人网和QQ空间使用
     */
    private String comment;
    /**
     * site是分享此内容的网站名称，仅在QQ空间使用
     */
    private String site;
    /**
     * siteUrl是分享此内容的网站地址，仅在QQ空间使用
     */
    private String siteUrl;
    /**
     * 显示的图片地址
     */
    private String imageUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleUrl() {
        return titleUrl;
    }

    public void setTitleUrl(String titleUrl) {
        this.titleUrl = titleUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
