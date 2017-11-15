package co.nayt.photos.data.local;

import android.content.Context;

import javax.inject.Singleton;

import co.nayt.photos.data.local.impl.PhotosDataSourceImpl;
import dagger.Module;
import dagger.Provides;

/**
 * The class defines a module providing a local database
 * instance.
 */
@Module
public class LocalModule {
    private Context mContext;

    public LocalModule(Context ctx) {
        this.mContext = ctx;
    }

    @Provides
    @Singleton
    PhotosDataSource provideLocalDatabase() {
        PhotosDatabase database = PhotosDatabase.getInstance(mContext);
        return new PhotosDataSourceImpl(database.photosDao());
    }
}
