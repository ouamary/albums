package co.nayt.photos.list;

import co.nayt.photos.di.ActivityScoped;
import co.nayt.photos.di.FragmentScoped;
import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * This class describes the main module for the list
 * feature. It binds the Presenter to the Fragment.
 */
@Module
public abstract class PhotosListModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract PhotosListFragment photosListFragment();

    @ActivityScoped
    @Binds
    abstract PhotosListContract.Presenter photosListPresenter(PhotosListPresenter presenter);
}
