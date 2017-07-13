package com.example.mac_204.test.ui.fragments.detail;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.mac_204.test.R;
import com.example.mac_204.test.data.ui.models.LocationUIModel;
import com.example.mac_204.test.data.ui.models.SightUIModel;
import com.example.mac_204.test.ui.fragments.BaseFragment;
import com.example.mac_204.test.ui.fragments.list.ListFragment;
import com.example.mac_204.test.util.ScreenUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by mac-204 on 7/13/17.
 */

public class DetailFragment extends BaseFragment implements OnMapReadyCallback, DetailView {

    public static final String TAG_FRAGMENT = "TAG_DETAIL_FRAGMENT";


    public static final String BUNDLE_KEY_LOCATION_MODEL = "BUNDLE_KEY_LOCATION_MODEL";

    @Inject
    DetailAdapter detailAdapter;
    @InjectPresenter
    DetailPresenter detailPresenter;

    @BindView(R.id.recycler_list_sights)
    RecyclerView recyclerListSights;

    private GoogleMap mMap;


    @ProvidePresenter
    DetailPresenter provideDetailPresenter() {
        LocationUIModel locationUIModel = null;

        if (getArguments() != null) {
            if (getArguments().containsKey(BUNDLE_KEY_LOCATION_MODEL)) {
                locationUIModel = (LocationUIModel) getArguments().getSerializable(BUNDLE_KEY_LOCATION_MODEL);
            }
        }

        return new DetailPresenter(locationUIModel);
    }


    public static DetailFragment getInstance(LocationUIModel locationUIModel) {
        DetailFragment fragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(BUNDLE_KEY_LOCATION_MODEL, locationUIModel);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public String getFragmentTag() {
        return TAG_FRAGMENT;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerSights();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        detailPresenter.getLocation();
    }

    @Override
    public void updLocation(LocationUIModel locationUIModel) {
        addLocationToMap(locationUIModel);
        updSights(locationUIModel);
    }

    private void addLocationToMap(LocationUIModel location){
        LatLng locLatLng = new LatLng(location.getLat(), location.getLan());
        mMap.addMarker(new MarkerOptions().position(locLatLng).title(location.getName()));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(locLatLng));

        for (int i = 0; i < location.getSights().size(); i++) {
            SightUIModel sightUIModel = location.getSights().get(i);
            LatLng latLng = new LatLng(location.getLat(), location.getLan());
            final MarkerOptions markerOptions = new MarkerOptions().position(latLng)
                    .title(location.getName());
            final Marker myMarker = mMap.addMarker(markerOptions);
            mMap.addMarker(markerOptions);
            Glide.with(getContext().getApplicationContext())
                    .load(sightUIModel.getIconUrl())
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

    private void updSights(LocationUIModel location){
        detailAdapter.updSightUIModels(location.getSights());
        detailAdapter.notifyDataSetChanged();
    }

    private void initRecyclerSights() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        RecyclerView.ItemAnimator scaleInAnimationAdapter = new DefaultItemAnimator();
        this.recyclerListSights.setItemAnimator(scaleInAnimationAdapter);
        this.recyclerListSights.setLayoutManager(linearLayoutManager);
        this.recyclerListSights.setAdapter(detailAdapter);
    }

}



