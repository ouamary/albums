package co.nayt.photos.di;

import co.nayt.photos.list.PhotosListActivity;
import co.nayt.photos.list.PhotosListModule;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * This class describes the module for binding our activity
 * with its main sub component PhotosListModule.
 */
@Module
abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = PhotosListModule.class)
    abstract PhotosListActivity photosListActivity();
}
