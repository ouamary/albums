package co.nayt.photos.di;

import android.app.Application;

import javax.inject.Singleton;

import co.nayt.photos.PhotosApplication;
import co.nayt.photos.data.remote.RemoteModule;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * This interface describes our app component joining together
 * all of our dependencies. It declares the injection methods
 * as well as the Builder needed by the application.
 */
@Singleton
@Component(modules = {
        RemoteModule.class,
        ApplicationModule.class,
        ActivityBindingModule.class,
        AndroidSupportInjectionModule.class
})
public interface AppComponent extends AndroidInjector<DaggerApplication> {
    void inject(PhotosApplication application);

    @Override
    void inject(DaggerApplication instance);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        Builder remoteModule(RemoteModule remoteModule);

        AppComponent build();
    }
}
