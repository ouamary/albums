package co.nayt.photos;

import co.nayt.photos.data.local.LocalModule;
import co.nayt.photos.data.remote.RemoteModule;
import co.nayt.photos.data.utils.DataUtils;
import co.nayt.photos.di.AppComponent;
import co.nayt.photos.di.DaggerAppComponent;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

/**
 * This class builds our application with an injector which
 * uses the Builder pattern defined in the app component.
 */
public class PhotosApplication extends DaggerApplication {
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent component = DaggerAppComponent.builder()
                .application(this)
                .remoteModule(new RemoteModule(DataUtils.BASE_URL))
                .localModule(new LocalModule(this))
                .build();

        component.inject(this);
        return component;
    }
}
