package co.nayt.photos.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.nayt.photos.R;
import co.nayt.photos.data.remote.PhotoModel;

/**
 * This class will help the RecyclerView laying out our
 * list of photos. Its ViewHolder uses Butterknife to
 * inflate its elements.
 */
public class PhotosListAdapter extends RecyclerView.Adapter<PhotosListAdapter.ViewHolder> {

    private List<PhotoModel> mPhotosList;
    private Context mContext;

    PhotosListAdapter(Context context, List<PhotoModel> items) {
        this.mContext = context;
        this.mPhotosList = items;
    }

    @Override
    public PhotosListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View v = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final PhotoModel item = getItem(position);

        holder.mTopText.setText(String.valueOf(item.getId()));
        holder.mBottomText.setText(item.getTitle());

        Picasso.with(mContext).load(item.getThumbnailUrl()).resize(80, 80).into(holder.mPhotoView);
    }

    @Override
    public int getItemCount() {
        return mPhotosList.size();
    }

    public void setItems(List<PhotoModel> photosList) {
        this.mPhotosList = photosList;
        notifyDataSetChanged();
    }

    private PhotoModel getItem(int position) {
        return mPhotosList.get(position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_photo)
        ImageView mPhotoView;
        @BindView(R.id.tv_top_text)
        TextView mTopText;
        @BindView(R.id.tv_bottom_text)
        TextView mBottomText;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
