package com.example.mac_204.test.ui.activities.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.mac_204.test.R;
import com.example.mac_204.test.data.remote.models.LocationRestModel;
import com.example.mac_204.test.ui.activities.BaseActivity;
import com.example.mac_204.test.ui.fragments.BaseFragment;

public class MainActivity extends BaseActivity implements MainView {

    @InjectPresenter MainPresenter mainPresenter;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            mainPresenter.setFragment(item.getItemId());
            item.setChecked(true);
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        getSupportFragmentManager().addOnBackStackChangedListener(() -> {
            int stackSize = getSupportFragmentManager().getBackStackEntryCount();
            if (stackSize == 0) {
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        int stackSize = getSupportFragmentManager().getBackStackEntryCount();
        if (stackSize == 1) {
            moveTaskToBack(true);
            return;
        }

        super.onBackPressed();
    }

    //MVP methods

    @Override
    public void setTapFragment(BaseFragment fragment) {
        setFragment(fragment);
    }

    @Override
    public void setFirstFragment(BaseFragment fragment) {
        if (getSupportFragmentManager().findFragmentByTag(fragment
                .getFragmentTag()) == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.content, fragment, fragment.getFragmentTag())
                    .addToBackStack(fragment.getFragmentTag())
                    .commitAllowingStateLoss();
        }
    }

    private void setFragment(BaseFragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, fragment, fragment.getFragmentTag())
                .commitAllowingStateLoss();
    }


}
