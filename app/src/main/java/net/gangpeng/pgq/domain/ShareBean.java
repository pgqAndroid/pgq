package net.gangpeng.pgq.domain;

/**
 * className: ShareBean
 * function: 分享实体类
 *
 * @author pg
 *         created at 16/6/27 15:53
 */
public class ShareBean {
    /**
     * 分享名称
     */
    private String name;
    /**
     * 分享图片id
     */
    private int pictureId;

    /**
     * 分享平台，分享需要
     */
    private String platform;
    /**
     * 分享平台包名，用于判断是否安装
     */
    private String appPackage;
    /**
     * 分享平台应用名称，用于提示用户信息
     */
    private String appName;

    public ShareBean(String name, int pictureId, String platform, String appPackage, String appName) {
        this.name = name;
        this.pictureId = pictureId;
        this.platform = platform;
        this.appPackage = appPackage;
        this.appName = appName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPictureId() {
        return pictureId;
    }

    public void setPictureId(int pictureId) {
        this.pictureId = pictureId;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getAppPackage() {
        return appPackage;
    }

    public void setAppPackage(String appPackage) {
        this.appPackage = appPackage;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
}
