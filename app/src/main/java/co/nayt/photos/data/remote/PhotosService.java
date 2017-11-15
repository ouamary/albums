package co.nayt.photos.data.remote;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * This interface describes the Rx-enabled photos service.
 */
public interface PhotosService {
    @GET("photos")
    Observable<List<PhotoModel>> getPhotos();
}
