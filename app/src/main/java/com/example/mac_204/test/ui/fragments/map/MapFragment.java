package com.example.mac_204.test.ui.fragments.map;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.mac_204.test.R;
import com.example.mac_204.test.data.ui.models.LocationUIModel;
import com.example.mac_204.test.ui.fragments.BaseFragment;
import com.example.mac_204.test.ui.fragments.list.ListFragment;
import com.example.mac_204.test.ui.fragments.list.ListPresenter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

/**
 * Created by mac-204 on 7/13/17.
 */

public class MapFragment extends BaseFragment implements OnMapReadyCallback, MapView {

    public static final String TAG_FRAGMENT = "TAG_MAP_FRAGMENT";

    @InjectPresenter MapPresenter mapPresenter;

    private GoogleMap mMap;

    public static MapFragment getInstance() {
        MapFragment fragment = new MapFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public String getFragmentTag() {
        return TAG_FRAGMENT;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mapPresenter.getLocations();
    }

    //MVP methods

    @Override
    public void onError(String error) {

    }

    @Override
    public void showRefresh(boolean show) {

    }

    @Override
    public void updLocations(List<LocationUIModel> locationUIModels) {
        for (int i = 0; i < locationUIModels.size(); i++) {
            LocationUIModel location = locationUIModels.get(i);
            LatLng sydney = new LatLng(location.getLat(), location.getLan());
            final MarkerOptions markerOptions = new MarkerOptions().position(sydney)
                    .title(location.getName());
            final Marker myMarker = mMap.addMarker(markerOptions);
            mMap.addMarker(markerOptions);
            Glide.with(getContext().getApplicationContext())
                    .load(location.getIconUrl())
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            if (getContext() != null) {
                                myMarker.remove();
                                BitmapDescriptor icon = BitmapDescriptorFactory.fromBitmap(resource);
                                markerOptions.icon(icon);
                                mMap.addMarker(markerOptions);
                            }
                        }
                    });

        }
    }
}
