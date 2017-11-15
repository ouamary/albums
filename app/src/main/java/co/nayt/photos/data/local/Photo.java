package co.nayt.photos.data.local;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * This class defines our Photo entity.
 */
@Entity(tableName = "photos")
public class Photo {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "photoid")
    private String mId;

    @ColumnInfo(name = "phototitle")
    private String mTitle;

    @ColumnInfo(name = "photourl")
    private String mUrl;

    public Photo(@NonNull String id, String title, String url) {
        this.mId = id;
        this.mTitle = title;
        this.mUrl = url;
    }

    public String getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getUrl() {
        return mUrl;
    }
}
