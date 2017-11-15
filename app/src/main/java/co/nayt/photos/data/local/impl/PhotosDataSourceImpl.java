package co.nayt.photos.data.local.impl;

import java.util.List;

import co.nayt.photos.data.local.Photo;
import co.nayt.photos.data.local.PhotosDao;
import co.nayt.photos.data.local.PhotosDataSource;
import io.reactivex.Flowable;

/**
 * This class allows interaction with the database.
 */
public class PhotosDataSourceImpl implements PhotosDataSource {
    private final PhotosDao mPhotosDao;

    public PhotosDataSourceImpl(PhotosDao photosDao) {
        this.mPhotosDao = photosDao;
    }

    @Override
    public Flowable<Photo> getPhoto(String id) {
        return mPhotosDao.getPhoto(id);
    }

    @Override
    public Flowable<List<Photo>> getAllPhotos() {
        return mPhotosDao.getAllPhotos();
    }

    @Override
    public void insertOrUpdatePhoto(Photo photo) {
        mPhotosDao.insertPhoto(photo);
    }

    @Override
    public void insertAllPhotos(Photo... photos) {
        mPhotosDao.insertAllPhotos(photos);
    }

    @Override
    public void deleteAllPhotos() {
        mPhotosDao.deleteAllPhotos();
    }
}
