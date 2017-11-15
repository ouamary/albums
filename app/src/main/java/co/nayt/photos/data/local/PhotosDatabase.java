package co.nayt.photos.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import co.nayt.photos.data.utils.DataUtils;

/**
 * This class represents the Room database containing
 * the Photos table.
 */
@Database(entities = {Photo.class}, version = 1)
abstract class PhotosDatabase extends RoomDatabase {

    private static volatile PhotosDatabase INSTANCE;

    public abstract PhotosDao photosDao();

    static PhotosDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (PhotosDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PhotosDatabase.class, DataUtils.DB_NAME)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

