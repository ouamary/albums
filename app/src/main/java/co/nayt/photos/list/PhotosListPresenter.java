package co.nayt.photos.list;

import android.support.annotation.Nullable;

import java.util.List;

import javax.inject.Inject;

import co.nayt.photos.data.remote.PhotoModel;
import co.nayt.photos.data.remote.PhotosService;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * This class is our presenter. It manages the interactions
 * between the model and the view.
 */
public class PhotosListPresenter implements PhotosListContract.Presenter {
    @Nullable
    private PhotosListContract.View mPhotosView;
    private PhotosService mPhotosService;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    @Inject
    PhotosListPresenter(PhotosService photosService) {
        this.mPhotosService = photosService;
    }

    @Override
    public void takeView(PhotosListContract.View view) {
        this.mPhotosView = view;
        loadPhotos();
    }

    @Override
    public void loadPhotos() {
        Observable<List<PhotoModel>> call = mPhotosService.getPhotos();
        Disposable d = call
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<PhotoModel>>() {

                    @Override
                    public void onNext(@NonNull List<PhotoModel> photos) {
                        mPhotosView.showPhotos(photos);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {}

                    @Override
                    public void onComplete() {}
                });

        mCompositeDisposable.add(d);
    }

    @Override
    public void dropView() {
        mPhotosView = null;
    }

    @Override
    public void destroy() {
        mCompositeDisposable.clear();
        dropView();
    }
}
