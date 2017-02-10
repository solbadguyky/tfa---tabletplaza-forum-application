package app.tabletplaza.tfa.objects;

import java.util.ArrayList;

/**
 * Thread Object tương tự như Post Object nhưng sẽ thêm/bớt một số thông tin
 * Created by SolbadguyKY on 16-Jan-17.
 */
public class ThreadObject extends BaseObject {
    public static final String TAB = "ThreadObject";

    public static enum ThreadObjectKey {
        THREAD_ID("threadId"), THREAD_TITLE("threadTitle"), THREAD_THUMBNAIL("threadThumbnail"),
        THREAD_CREATEDDATE("threadCreatedDate"), THREAD_LASTMODIFIED("threadLastModified"),
        THREAD_URL("threadUrl"), THREAD_DESCRIPTION("threadDescription"), THREAD_CONTENT("threadContent"),
        THREAD_VIEWCOUNT("viewCount"), THREAD_REPLYCOUNT("replyCount"),
        THREAD_USER_ID("userId"), THREAD_USER_NAME("username"), THREAD_USER_AVATAR("userAvatar");

        private String key;

        ThreadObjectKey(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }
    }

    private Long threadId;
    private String threadTitle;
    private String threadThumbnail;
    private Long threadCreatedDate;
    private Long threadLastModified;
    private String threadUrl;
    private String threadDescription;
    private int threadViewCount, threadReplyCount;
    private UserObject userObject;

    private ArrayList<PostObject> postObjectArrayList;

    public ThreadObject() {

    }

    public ThreadObject(ThreadObject object) {
        this.threadId = object.getId();
        this.threadCreatedDate = object.getCreatedDate();
        this.threadLastModified = object.getLastModified();
        this.threadTitle = object.getName();
        this.threadUrl = object.getUrl();
        this.threadDescription = object.getPostDescription();
    }

    @Override
    public Long getId() {
        return this.threadId;
    }

    @Override
    public void setId(Long id) {
        this.threadId = id;
    }

    @Override
    public String getName() {
        return this.threadTitle;
    }

    @Override
    public void setName(String name) {
        this.threadTitle = name;
    }

    @Override
    public Long getCreatedDate() {
        return this.threadCreatedDate;
    }

    @Override
    public void setCreatedDate(Long createdDate) {
        this.threadCreatedDate = createdDate;
    }

    @Override
    public Long getLastModified() {
        return this.threadLastModified;
    }

    @Override
    public void setLastModified(Long lastModified) {
        this.threadLastModified = lastModified;
    }

    @Override
    public String getImage() {
        return this.threadThumbnail;
    }

    @Override
    public void setImage(String image) {
        this.threadThumbnail = image;
    }

    @Override
    public String getUrl() {
        return this.threadUrl;
    }

    @Override
    public void setUrl(String url) {
        this.threadUrl = url;
    }

    public String getPostDescription() {
        return this.threadDescription;
    }

    public void setPostDescription(String description) {
        this.threadDescription = description;
    }

    public int getThreadViewCount() {
        return this.threadViewCount;
    }

    public int getThreadReplyCount() {
        return this.threadReplyCount;
    }

    public void setThreadViewCount(int postViewCount) {
        this.threadViewCount = postViewCount;
    }

    public void setThreadReplyCount(int replyCount) {
        this.threadReplyCount = replyCount;
    }

    public void setThreadPosts(ArrayList<PostObject> posts) {
        this.postObjectArrayList = posts;
    }


    public ArrayList<PostObject> getPostObjectArrayList() {
        if (this.postObjectArrayList == null) {
            this.postObjectArrayList = new ArrayList<>();
        }

        return this.postObjectArrayList;
    }

    public void setUserObject(UserObject userObject) {
        this.userObject = userObject;
    }

    public UserObject getUserObject() {
        return this.userObject;
    }
}
