package co.nayt.photos.data.remote;

import android.database.Observable;

import java.util.List;

import retrofit2.http.GET;

public interface PhotosService {
    @GET("photos")
    Observable<List<PhotoModel>> getPhotos();
}
