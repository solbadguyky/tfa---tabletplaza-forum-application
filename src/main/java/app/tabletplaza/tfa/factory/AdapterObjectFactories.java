package app.tabletplaza.tfa.factory;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import app.tabletplaza.tfa.adapter.adapterObjects.Post_ObjectAdapter_Xenforo;
import app.tabletplaza.tfa.objects.PostObject_Xenforo;

/**
 * Created by SolbadguyKY on 21-Jan-17.
 */

public class AdapterObjectFactories {

    public static ArrayList<Post_ObjectAdapter_Xenforo> convertObjectsToAdapter(ArrayList<PostObject_Xenforo> objects) {
        ArrayList<Post_ObjectAdapter_Xenforo> resultArr = new ArrayList<>();
        if (objects != null && objects.size() > 0) {
            for (PostObject_Xenforo object : objects) {
                Post_ObjectAdapter_Xenforo postObjectAdapter_xenforo = new Post_ObjectAdapter_Xenforo();
                postObjectAdapter_xenforo.setPostObject(object);

                Logger.d(object.getName());
                resultArr.add(postObjectAdapter_xenforo);
            }
        }

        return resultArr;
    }

}
