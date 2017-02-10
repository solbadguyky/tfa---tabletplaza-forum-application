package app.tabletplaza.tfa.objects;

import com.orhanobut.logger.Logger;

/**
 * Created by SolbadguyKY on 09-Feb-17.
 */

public class PagingObject {
    public static final String TAG = "PagingObject";

    public enum STATUS {
        OnLoading, Fail, Free, Success, Reset
    }


    public PagingObject() {

    }

    public PagingObject(String url, int page) {
        this.currentUrl = url;
        this.currentPage = page;
    }

    public PagingObject(String website, String bypassHash, int page) {
        this.currentWebsite = website;
        this.currentPage = page;
        this.bypassHashString = bypassHash;
    }

    private int maxItemPerPage = 10;

    public int currentPage = 0;
    public STATUS onLoading = STATUS.Free;
    public String currentUrl, currentWebsite, bypassHashString, currentMethod;

    private OnLoadingListener onLoadingListener;

    public String getCurrentPageApi() {
        return getCurrentPageUrl() + "&limit=" + maxItemPerPage + "&order_by=post_date&order=d&offset=" + getCurrentPageOffset();
    }

    private String getCurrentPageUrl() {
        if (currentWebsite != null && bypassHashString != null && currentMethod != null && !currentWebsite.isEmpty()
                && !bypassHashString.isEmpty() && !currentMethod.isEmpty()) {
            return currentWebsite + "api.php?action=" + currentMethod + "&hash=" + bypassHashString;
        } else {
            return currentUrl;
        }
    }

    private int getCurrentPageOffset() {
        return maxItemPerPage * currentPage;
    }

    public void setOnLoading(STATUS onLoading) {
        Logger.d(onLoading);
        switch (onLoading) {
            case Free:
                if (this.onLoadingListener != null) {
                    this.onLoadingListener.freeing();
                }
                break;
            case OnLoading:

                break;
            case Fail:
                if (this.onLoadingListener != null) {
                    this.onLoadingListener.complete();
                }
                break;
            case Success:
                currentPage++;
                if (this.onLoadingListener != null) {
                    this.onLoadingListener.complete();
                }
                break;
            case Reset:
                if (this.onLoadingListener != null) {
                    this.onLoadingListener.onReset();
                }
                break;
        }

        this.onLoading = onLoading;
    }


    public STATUS getOnLoading() {
        return this.onLoading;
    }

    public void setOnLoadingListener(OnLoadingListener listener) {
        this.onLoadingListener = listener;
    }

    public interface OnLoadingListener {
        void onReset();

        void complete();

        void freeing();
    }
}
