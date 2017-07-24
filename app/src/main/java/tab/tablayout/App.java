package tab.tablayout;

import android.app.Application;
import android.content.Context;

/**
 * Created by tederen on 2017/7/20.
 */

public class App extends Application {
    public static App app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }

    public static Context getAppContext() {

        return app.getApplicationContext();
    }
}
