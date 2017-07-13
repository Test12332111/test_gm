package com.example.mac_204.test.ui.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.mac_204.test.R;
import com.example.mac_204.test.ui.fragments.BaseFragment;
import com.example.mac_204.test.ui.fragments.list.ListFragment;
import com.example.mac_204.test.ui.fragments.map.MapFragment;

public class MainActivity extends BaseActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    setFragment(ListFragment.getInstance());
                    return true;
                case R.id.navigation_dashboard:
                    setFragment(MapFragment.getInstance());
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }

    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        setFirstFragment(ListFragment.getInstance());
    }


    public void setFirstFragment(BaseFragment fragment) {
        if (getFragmentManager().findFragmentByTag(fragment
                .getFragmentTag()) == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content, fragment, fragment.getFragmentTag())
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
