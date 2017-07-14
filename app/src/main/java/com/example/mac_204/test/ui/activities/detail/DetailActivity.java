package com.example.mac_204.test.ui.activities.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.example.mac_204.test.R;
import com.example.mac_204.test.data.ui.models.LocationUIModel;
import com.example.mac_204.test.ui.activities.BaseActivity;
import com.example.mac_204.test.ui.fragments.BaseFragment;
import com.example.mac_204.test.ui.fragments.detail.DetailFragment;

import butterknife.BindView;

/**
 * Created by mac-204 on 7/14/17.
 */

public class DetailActivity extends BaseActivity {

    public static final String BUNDLE_KEY_LOCATION_MODEL = "BUNDLE_KEY_LOCATION_MODEL";

    private LocationUIModel bundleLocationUIModel;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.frame_fragment_container) FrameLayout frameFragmentContainer;


    public static Intent getIntent(Context context, LocationUIModel locationUIModel) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(BUNDLE_KEY_LOCATION_MODEL,locationUIModel);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getDataFromBundle();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setFirstFragment(DetailFragment.getInstance(bundleLocationUIModel));
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace);
        toolbar.setNavigationOnClickListener(view -> {
            getSupportFragmentManager().popBackStack();
        });

        getSupportFragmentManager().addOnBackStackChangedListener(() -> {
            int stackSize = getSupportFragmentManager().getBackStackEntryCount();
            if (stackSize == 0) {
                finish();
            }
        });
    }

    public void setFirstFragment(BaseFragment fragment) {
        if (getSupportFragmentManager().findFragmentByTag(fragment
                .getFragmentTag()) == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.frame_fragment_container, fragment, fragment.getFragmentTag())
                    .addToBackStack(fragment.getFragmentTag())
                    .commitAllowingStateLoss();
        }
    }

    private void getDataFromBundle(){
        if (getIntent().getExtras() != null){
            bundleLocationUIModel = (LocationUIModel) getIntent().getSerializableExtra(BUNDLE_KEY_LOCATION_MODEL);
        }
    }

}
