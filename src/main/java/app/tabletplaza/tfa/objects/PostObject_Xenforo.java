package app.tabletplaza.tfa.objects;

import com.alibaba.fastjson.annotation.JSONType;

/**
 * Created by SolbadguyKY on 16-Jan-17.
 */

@JSONType
public class PostObject_Xenforo extends BaseObject {
    ///xenforo parameters
    private Long post_id;
    private Long thread_id;
    private Long user_id;
    private Long post_date;
    private Long last_edit_date;
    private String username;
    private String title;
    private String absolute_url;
    private String message;

    ///Những tham sớ còn thiếu
    private String postThumbnail;
    private int postViewCount;

    public PostObject_Xenforo() {

    }

    @Override
    public Long getId() {
        return this.post_id;
    }

    @Override
    public void setId(Long id) {
        this.post_id = id;
    }

    public Long getThreadId() {
        return this.thread_id;
    }

    public void setThreadId(Long id) {
        this.thread_id = id;
    }

    @Override
    public String getName() {
        return this.title;
    }

    @Override
    public void setName(String name) {
        this.title = name;
    }

    @Override
    public Long getCreatedDate() {
        return this.post_date;
    }

    @Override
    public void setCreatedDate(Long createdDate) {
        this.post_date = createdDate;
    }

    @Override
    public Long getLastModified() {
        return this.last_edit_date;
    }

    @Override
    public void setLastModified(Long lastModified) {
        this.last_edit_date = lastModified;
    }

    @Override
    public String getImage() {
        return this.postThumbnail;
    }

    @Override
    public void setImage(String image) {
        this.postThumbnail = image;
    }

    @Override
    public String getUrl() {
        return this.absolute_url;
    }

    @Override
    public void setUrl(String url) {
        this.absolute_url = url;
    }

    public String getPostDescription() {
        return this.message;
    }

    public void setPostDescription(String description) {
        this.message = description;
    }

    public int getPostViewCount() {
        return this.postViewCount;
    }

    public void setPostViewCount(int postViewCount) {
        this.postViewCount = postViewCount;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getUserId() {
        return this.user_id;
    }

    public void setUserId(Long userId) {
        this.user_id = userId;
    }
}
