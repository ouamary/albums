package co.nayt.photos.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;

/**
 * The DAO for the photos table.
 */
@Dao
public interface PhotosDao {
    @Query("SELECT * FROM photos WHERE photoid =:id")
    Flowable<Photo> getPhoto(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPhoto(Photo photo);

    @Query("DELETE FROM photos")
    void deleteAllPhotos();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllPhotos(Photo... photos);

    @Query("SELECT * FROM photos")
    Flowable<List<Photo>> getAllPhotos();
}
