package app.tabletplaza.tfa.objects;

/**
 * Created by SolbadguyKY on 18-Jan-17.
 */

public class UserObject_Xenforo extends BaseObject {
    ///Xenforo params
    private Long user_id;
    private String username;
    private String userUrl;
    private Long register_date, last_activity;
    private String custom_title;
    private String email;
    private int like_count;
    private String gender;

    ///params chưa khả dụng
    private String userAvatar;
    private String location;
    private int follwersCount, postCount, friendsCount;
    private boolean following, isFriended;
    private String userSelfDescription;


    @Override
    public Long getId() {
        return this.user_id;
    }

    @Override
    public void setId(Long id) {
        this.user_id = id;
    }

    @Override
    public String getName() {
        return this.username;
    }

    @Override
    public void setName(String name) {
        this.username = name;
    }

    @Override
    public Long getCreatedDate() {
        return this.register_date;
    }

    @Override
    public void setCreatedDate(Long createdDate) {
        this.register_date = createdDate;
    }

    @Override
    public Long getLastModified() {
        return this.last_activity;
    }

    @Override
    public void setLastModified(Long lastModified) {
        this.last_activity = lastModified;
    }

    @Override
    public String getImage() {
        return this.userAvatar;
    }

    @Override
    public void setImage(String image) {
        this.userAvatar = image;
    }

    @Override
    public String getUrl() {
        return this.userUrl;
    }

    @Override
    public void setUrl(String url) {
        this.userUrl = url;
    }


}
