package app.tabletplaza.tfa;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * Created by SolbadguyKY on 06-Feb-17.
 */

public class AndroidApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
    }
}
