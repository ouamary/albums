package co.nayt.photos.list;

import android.os.Bundle;

import javax.inject.Inject;

import co.nayt.photos.R;
import dagger.Lazy;
import dagger.android.support.DaggerAppCompatActivity;

/**
 * This is our main Activity which uses a Lazy handle for
 * adding the Fragment to its fragment manager.
 */
public class PhotosListActivity extends DaggerAppCompatActivity {
    @Inject
    PhotosListPresenter mPresenter;

    @Inject
    Lazy<PhotosListFragment> mFragmentLazy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photos_list_activity);

        PhotosListFragment fragment =
                (PhotosListFragment) getSupportFragmentManager().findFragmentById(R.id.content);

        // The fragment is not found, retrieving it from Dagger
        if (fragment == null) {
            fragment = mFragmentLazy.get();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.content, fragment)
                    .commit();
        }
    }

    @Override
    protected void onDestroy() {
        mPresenter.destroy();
        mPresenter = null;
        super.onDestroy();
    }
}
