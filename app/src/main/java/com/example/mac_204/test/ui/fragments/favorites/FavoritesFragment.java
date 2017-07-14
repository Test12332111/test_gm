package com.example.mac_204.test.ui.fragments.favorites;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.arellomobile.mvp.presenter.ProvidePresenterTag;
import com.example.mac_204.test.R;
import com.example.mac_204.test.data.ui.models.LocationUIModel;
import com.example.mac_204.test.ui.activities.detail.DetailActivity;
import com.example.mac_204.test.ui.fragments.BaseFragment;
import com.example.mac_204.test.ui.fragments.map.MapFragment;
import com.example.mac_204.test.ui.fragments.map.MapPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by mac-204 on 7/13/17.
 */

public class FavoritesFragment extends BaseFragment implements FavoritesView, FavoritesAdapter.OnItemClickListener {

    public static final String TAG_FRAGMENT = "TAG_FAVORITES_FRAGMENT";

    @Inject FavoritesAdapter favoritesAdapter;
    @InjectPresenter(type = PresenterType.GLOBAL) FavoritesPresenter favoritesPresenter;

    @ProvidePresenterTag(presenterClass = FavoritesPresenter.class, type = PresenterType.GLOBAL)
    String provideFavoritesPresenterTag() {
        return FavoritesPresenter.class.getName();
    }

    @ProvidePresenter(type = PresenterType.GLOBAL)
    FavoritesPresenter provideFavoritesPresenter() {
        return new FavoritesPresenter();
    }

    @BindView(R.id.recycler_list_favorites) RecyclerView recyclerListFavorites;

    public static FavoritesFragment getInstance() {
        FavoritesFragment fragment = new FavoritesFragment();
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
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerFavorites();
    }

    @Override
    public void onItemClick(LocationUIModel locationUIModel) {
        Intent intent = DetailActivity.getIntent(getContext(),locationUIModel);
        startActivity(intent);
    }

    //MVP methods

    @Override
    public void updFavoritesLocations(List<LocationUIModel> locationUIModels) {
        this.favoritesAdapter.updLocationUIModels(locationUIModels);
        this.favoritesAdapter.notifyDataSetChanged();
    }

    @Override
    public void addFavoritesLocations(List<LocationUIModel> locationUIModels) {
        int start = favoritesAdapter.getItemCount();
        this.favoritesAdapter.addLocationUIModels(locationUIModels);
        this.favoritesAdapter.notifyItemRangeInserted(start, locationUIModels.size());
    }

    @Override
    public void clearFavoritesLocations() {
        this.favoritesAdapter.clearLocationUIModels();
        this.favoritesAdapter.notifyDataSetChanged();
    }

    private void initRecyclerFavorites() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        RecyclerView.ItemAnimator scaleInAnimationAdapter = new DefaultItemAnimator();
        this.recyclerListFavorites.setItemAnimator(scaleInAnimationAdapter);
        this.recyclerListFavorites.setLayoutManager(linearLayoutManager);
        this.favoritesAdapter.setOnItemClickListener(this);
        this.recyclerListFavorites.setAdapter(favoritesAdapter);
    }



}
