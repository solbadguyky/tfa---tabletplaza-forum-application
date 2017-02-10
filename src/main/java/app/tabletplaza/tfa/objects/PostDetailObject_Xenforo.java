package app.tabletplaza.tfa.objects;

import com.alibaba.fastjson.annotation.JSONType;

/**
 * Post Object customize lại dành riêng cho Xenforo
 * Xenforo có 2 dạng bài viết, Post hoặc Thread
 * Về cơ bản 2 loại này cùng là 1 bài đăng trong csdl của Xenforo, tuy nhiên Thread là 1 hình thức giản lược của post
 * 1 Thread có thẻ chứa nhiều Post và bài post đầu tiên của Thread đó có cùng ID với nhau
 * Vì vậy, ta sẽ dùng chung 1 object cho cả 2, đối với Thread thì sẽ giản lược lại những biến cần dùng
 * <p>
 * Created by SolbadguyKY on 16-Jan-17.
 */

@JSONType
public class PostDetailObject_Xenforo extends PostObject_Xenforo {
    ///xenforo_thread_detail parameters
    ///PostDetail có cấu trúc giống như một PostObject bình thường, tuy nhiên nó chứa nội dung
    // chi tiết của bài viết, nếu PostDetail chưa có dữ liệu, nó sẽ dùng dữ liệu truyền từ PostObject
    private String postDescription_full;

    public PostDetailObject_Xenforo(PostObject_Xenforo postObject_xenforo) {
        super(postObject_xenforo);
    }

    public void setPostDescription_full(String fullDescription) {
        this.postDescription_full = fullDescription;
    }

    public String getPostDescription_full() {
        return this.postDescription_full;
    }
}
