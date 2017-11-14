package co.nayt.photos.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import co.nayt.photos.R;
import co.nayt.photos.data.remote.PhotoModel;
import co.nayt.photos.di.ActivityScoped;
import dagger.android.support.DaggerFragment;

/**
 * This class describes the Fragment which shows the
 * results from the webservice thanks to the Presenter.
 */
@ActivityScoped
public class PhotosListFragment extends DaggerFragment implements PhotosListContract.View {
    @Inject
    PhotosListContract.Presenter mPresenter;

    private PhotosListAdapter mAdapter;

    @Inject
    public PhotosListFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mAdapter = new PhotosListAdapter(getActivity().getApplicationContext(), new ArrayList<PhotoModel>(0));
        View parent = inflater.inflate(R.layout.photos_list_fragment, container, false);
        RecyclerView photosList = parent.findViewById(R.id.photos_list);
        photosList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        photosList.setLayoutManager(linearLayoutManager);
        photosList.setAdapter(mAdapter);
        return parent;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.takeView(this);
    }

    @Override
    public void onDestroy() {
        mPresenter.dropView();
        super.onDestroy();
    }

    @Override
    public void showPhotos(List<PhotoModel> photos) {
        mAdapter.setItems(photos);
    }

    @Override
    public void showLoadingFailure() {
        Snackbar.make(getView(), "Loading error", Snackbar.LENGTH_LONG).show();
    }
}
