package co.nayt.photos.data.utils;

import java.util.ArrayList;
import java.util.List;

import co.nayt.photos.data.local.Photo;
import co.nayt.photos.data.remote.PhotoModel;

/**
 * A helper class for handling data-related operations.
 */
public class DataUtils {
    public static final String BASE_URL = "http://jsonplaceholder.typicode.com";
    public static final int RESIZE_DIMEN = 60;
    public static final String DB_NAME = "Photos.db";

    public static List<Photo> convertRemoteToLocalModel(List<PhotoModel> photoModelList) {
        List<Photo> result = new ArrayList<>();
        for (PhotoModel p: photoModelList) {
            result.add(new Photo(String.valueOf(p.getId()), p.getTitle(), p.getThumbnailUrl()));
        }
        return result;
    }

    public static List<PhotoModel> convertLocalToRemote(List<Photo> photoList) {
        List<PhotoModel> result = new ArrayList<>();
        for (Photo p: photoList) {
            result.add(new PhotoModel(Integer.parseInt(p.getId()), p.getTitle(), p.getUrl()));
        }
        return result;
    }
}
