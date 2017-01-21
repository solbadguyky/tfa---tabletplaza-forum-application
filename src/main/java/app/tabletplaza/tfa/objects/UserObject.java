package app.tabletplaza.tfa.objects;

/**
 * Created by SolbadguyKY on 18-Jan-17.
 */

public class UserObject extends BaseObject {
    private Long userId;
    private String username;
    private String userUrl;
    private String userDisplayname;
    private String userAvatar;
    private String usermail;
    private String location;
    private Long createdDate, lastActived;
    private int favoritesCount, follwersCount, postCount, friendsCount;
    private boolean following, isFriended;
    private String userSelfDescription;


    @Override
    public Long getId() {
        return this.userId;
    }

    @Override
    public void setId(Long id) {
        this.userId = id;
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
        return this.createdDate;
    }

    @Override
    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public Long getLastModified() {
        return this.lastActived;
    }

    @Override
    public void setLastModified(Long lastModified) {
        this.lastActived = lastModified;
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
