package app.tabletplaza.tfa.objects;

/**
 * Created by SolbadguyKY on 16-Jan-17.
 */

public class PostObject extends BaseObject {
    private Long postId;
    private String postTitle;
    private String postThumbnail;
    private Long postCreatedDate;
    private Long postLastModified;
    private String postUrl;
    private String postDescription;
    private int postViewCount;

    @Override
    public Long getId() {
        return this.postId;
    }

    @Override
    public void setId(Long id) {
        this.postId = id;
    }

    @Override
    public String getName() {
        return this.postTitle;
    }

    @Override
    public void setName(String name) {
        this.postTitle = name;
    }

    @Override
    public Long getCreatedDate() {
        return this.postCreatedDate;
    }

    @Override
    public void setCreatedDate(Long createdDate) {
        this.postCreatedDate = createdDate;
    }

    @Override
    public Long getLastModified() {
        return this.postLastModified;
    }

    @Override
    public void setLastModified(Long lastModified) {
        this.postLastModified = lastModified;
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
        return this.postUrl;
    }

    @Override
    public void setUrl(String url) {
        this.postUrl = url;
    }

    public String getPostDescription() {
        return this.postDescription;
    }

    public void setPostDescription(String description) {
        this.postDescription = description;
    }

    public int getPostViewCount() {
        return this.postViewCount;
    }

    public void setPostViewCount(int postViewCount) {
        this.postViewCount = postViewCount;
    }
}
