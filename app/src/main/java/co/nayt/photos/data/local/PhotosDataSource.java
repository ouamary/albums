package co.nayt.photos.data.local;

import java.util.List;

import io.reactivex.Flowable;

/**
 * This interface will help us manage the local database.
 */
public interface PhotosDataSource {
    Flowable<Photo> getPhoto(String id);

    Flowable<List<Photo>> getAllPhotos();

    void insertOrUpdatePhoto(Photo photo);

    void insertAllPhotos(Photo... photos);

    void deleteAllPhotos();
}
