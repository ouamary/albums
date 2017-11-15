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
import co.nayt.photos.data.local.Photo;
import co.nayt.photos.data.remote.PhotoModel;
import co.nayt.photos.data.utils.DataUtils;

/**
 * This class will help the RecyclerView laying out our
 * list of photos. Its ViewHolder uses Butterknife to
 * inflate its elements.
 */
class PhotosListAdapter extends RecyclerView.Adapter<PhotosListAdapter.ViewHolder> {

    private List<Photo> mPhotosList;
    private Context mContext;

    PhotosListAdapter(Context context, List<Photo> items) {
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
        final Photo item = getItem(position);

        holder.mTopText.setText(item.getId());
        holder.mBottomText.setText(item.getTitle());

        Picasso.with(mContext)
                .load(item.getUrl())
                .placeholder(R.mipmap.ic_dl_placeholder)
                .error(R.mipmap.ic_err_placeholder)
                .resize(DataUtils.RESIZE_DIMEN, DataUtils.RESIZE_DIMEN)
                .into(holder.mPhotoView);
    }

    @Override
    public int getItemCount() {
        return mPhotosList.size();
    }

    void setItems(List<Photo> photosList) {
        this.mPhotosList = photosList;
        notifyDataSetChanged();
    }

    private Photo getItem(int position) {
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
