package app.tabletplaza.tfa.objects;

import java.io.Serializable;

/**
 * Created by SolbadguyKY on 17-Jan-17.
 */

public abstract class BaseObject implements Serializable {

    public enum Type {
        Post, Thread
    }

    public static final String TAG = "BaseObject";

    public abstract Long getId();

    public abstract void setId(Long id);

    public abstract String getName();

    public abstract void setName(String name);

    public abstract Long getCreatedDate();

    public abstract void setCreatedDate(Long createdDate);

    public abstract Long getLastModified();

    public abstract void setLastModified(Long lastModified);

    public abstract String getImage();

    public abstract void setImage(String image);


    public abstract String getUrl();

    public abstract void setUrl(String url);

}
