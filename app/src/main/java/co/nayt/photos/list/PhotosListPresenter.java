package co.nayt.photos.list;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;

import javax.inject.Inject;

import co.nayt.photos.data.local.Photo;
import co.nayt.photos.data.local.PhotosDataSource;
import co.nayt.photos.data.remote.PhotoModel;
import co.nayt.photos.data.remote.PhotosService;
import co.nayt.photos.data.utils.DataUtils;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Function;
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
    private PhotosDataSource mLocalPhotosDataSource;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private boolean mLocalDataAvailable = false;

    @Inject
    PhotosListPresenter(PhotosService photosService, PhotosDataSource localPhotosDataSource) {
        this.mPhotosService = photosService;
        this.mLocalPhotosDataSource = localPhotosDataSource;
    }

    @Override
    public void takeView(PhotosListContract.View view) {
        this.mPhotosView = view;
        loadPhotos();
    }

    @Override
    public void loadPhotos() {
        // TODO: fix local data retrieval
        // Call loadFromLocalDatabase() here if local data is available
        // or the device is offline.
        Observable<List<PhotoModel>> call = mPhotosService.getPhotos();
        Disposable d = call
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<PhotoModel>>() {

                    @Override
                    public void onNext(@NonNull List<PhotoModel> photos) {
                        List<Photo> list = DataUtils.convertRemoteToLocalModel(photos);
                        mPhotosView.showPhotos(list);
                        // Persisting data into local storage
                        insertIntoLocalDatabase(list);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mPhotosView.showLoadingFailure();
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() { }
                });

        mCompositeDisposable.add(d);
    }

    private void loadFromLocalDatabase() {
        Flowable<List<Photo>> photos = mLocalPhotosDataSource.getAllPhotos();
        photos.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Subscriber<List<Photo>>() {
                @Override
                public void onSubscribe(Subscription s) { }

                @Override
                public void onNext(List<Photo> photos) {
                    mPhotosView.showPhotos(photos);
                }

                @Override
                public void onError(Throwable t) {
                    mPhotosView.showLoadingFailure();
                    t.printStackTrace();
                }

                @Override
                public void onComplete() { }
            });
    }

    private void insertIntoLocalDatabase(final List<Photo> photos) {
        Completable
            .fromAction(new Action() {
                @Override
                public void run() throws Exception {
                    mLocalPhotosDataSource.insertAllPhotos(photos.toArray(new Photo[photos.size()]));
                }
            })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new CompletableObserver() {
                @Override
                public void onSubscribe(@NonNull Disposable d) { }

                @Override
                public void onComplete() {
                    mLocalDataAvailable = true;
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    e.printStackTrace();
                }
            });
    }

    private boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager)
                        ((Fragment) mPhotosView).getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
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
