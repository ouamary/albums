package co.nayt.photos.di;

import android.app.Application;
import android.content.Context;

import dagger.Binds;
import dagger.Module;

/**
 * This class binds the Context to the Application.
 */
@Module
abstract class ApplicationModule {
    @Binds
    abstract Context bindContext(Application application);
}
