package co.nayt.photos.data.remote;

import io.reactivex.Observable;

import java.util.List;

import retrofit2.http.GET;

/**
 * This interface describes the Rx-enabled photos service.
 */
public interface PhotosService {
    @GET("photos")
    Observable<List<PhotoModel>> getPhotos();
}
