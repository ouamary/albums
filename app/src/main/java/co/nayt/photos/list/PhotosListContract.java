package co.nayt.photos.list;

import java.util.List;

import co.nayt.photos.BasePresenter;
import co.nayt.photos.BaseView;
import co.nayt.photos.data.local.Photo;
import co.nayt.photos.data.remote.PhotoModel;

/**
 * This interface describes the contract between the
 * view and the presenter.
 */
interface PhotosListContract {
    interface View extends BaseView<Presenter> {
        void showPhotos(List<Photo> photos);

        void showLoadingFailure();
    }

    interface Presenter extends BasePresenter<View> {
        void loadPhotos();
    }
}
