package com.example.mac_204.test.ui.fragments.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.arellomobile.mvp.presenter.ProvidePresenterTag;
import com.example.mac_204.test.R;
import com.example.mac_204.test.data.remote.models.LocationRestModel;
import com.example.mac_204.test.data.ui.models.LocationUIModel;
import com.example.mac_204.test.ui.activities.detail.DetailActivity;
import com.example.mac_204.test.ui.fragments.BaseFragment;
import com.example.mac_204.test.ui.fragments.detail.DetailFragment;
import com.example.mac_204.test.util.ScreenUtil;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by mac-204 on 7/13/17.
 */

public class ListFragment extends BaseFragment implements ListAdapter.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener, ListView {

    public static final String TAG_FRAGMENT = "TAG_LIST_FRAGMENT";

    @Inject ListAdapter listAdapter;
    @InjectPresenter(type = PresenterType.GLOBAL) ListPresenter listPresenter;

    @ProvidePresenterTag(presenterClass = ListPresenter.class, type = PresenterType.GLOBAL)
    String provideListPresenterTag() {
        return ListPresenter.class.getName();
    }

    @ProvidePresenter(type = PresenterType.GLOBAL)
    ListPresenter provideListPresenter() {
        return new ListPresenter();
    }

    @BindView(R.id.recycler_list_points) RecyclerView recyclerListPoints;
    @BindView(R.id.swipe_refresh_upd) SwipeRefreshLayout swipeRefreshUpd;

    public static ListFragment getInstance() {
        ListFragment fragment = new ListFragment();
        Bundle bundle = new Bundle();
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
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.swipeRefreshUpd.setOnRefreshListener(this);
        initRecyclerListPoints();
    }

    @Override
    public void onItemClick(LocationUIModel locationUIModel) {
        Intent intent = DetailActivity.getIntent(getContext(),locationUIModel);
        startActivity(intent);
    }

    @Override
    public void onRefresh() {
        listPresenter.getLocations();
    }

    //MVP methods

    @Override
    public void onError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showRefresh(boolean show) {
        swipeRefreshUpd.setRefreshing(show);
    }

    @Override
    public void updLocations(List<LocationUIModel> locationUIModels) {
        listAdapter.updLocationUIModelList(locationUIModels);
        listAdapter.notifyDataSetChanged();
    }

    private void initRecyclerListPoints() {
        final int spanCount = ScreenUtil.getDisplayWidth(getActivity()) / ScreenUtil.convertDIPToPixels(getActivity(), 150);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), spanCount);
        RecyclerView.ItemAnimator scaleInAnimationAdapter = new DefaultItemAnimator();
        gridLayoutManager.setSpanCount(spanCount);
        this.recyclerListPoints.setItemAnimator(scaleInAnimationAdapter);
        this.recyclerListPoints.setLayoutManager(gridLayoutManager);
        this.listAdapter.setSpanCount(spanCount);
        this.listAdapter.setOnItemClickListener(this);
        this.recyclerListPoints.setAdapter(listAdapter);
    }

}
