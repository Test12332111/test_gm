package com.example.mac_204.test.ui.fragments.list;

import android.content.Context;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mac_204.test.R;
import com.example.mac_204.test.data.ui.models.LocationUIModel;
import com.example.mac_204.test.inject.qualifier.ActivityContext;
import com.example.mac_204.test.inject.scope.FragmentScope;
import com.example.mac_204.test.util.ScreenUtil;

import java.util.ArrayList;
import java.util.List;


import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mac-204 on 7/13/17.
 */
@FragmentScope
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.BasicHolder> {

    private Context context;
    private int spanCount;
    private int itemHeight;
    private LayoutInflater layoutInflater;
    private List<LocationUIModel> locationUIModelList;
    private OnItemClickListener onItemClickListener;

    @Inject
    public ListAdapter(@ActivityContext Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.locationUIModelList = new ArrayList<>();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setSpanCount(int spanCount) {
        this.spanCount = spanCount;
        this.itemHeight = ScreenUtil.getDisplayWidth(context) / spanCount;
    }


    public void updLocationUIModelList(List<LocationUIModel> locationUIModelList) {
        this.locationUIModelList.clear();
        this.locationUIModelList.addAll(locationUIModelList);
    }

    @Override
    public BasicHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.adapter_list_basic, parent, false);
        return new BasicHolder(view);
    }

    @Override
    public void onBindViewHolder(BasicHolder holder, int position) {
        final LocationUIModel location = locationUIModelList.get(position);
        holder.relativeContainer.getLayoutParams().height = (ScreenUtil.getDisplayWidth(context) / spanCount);
        holder.txtName.setText(location.getName());
        holder.imgFavorite.setVisibility(location.isFavorite() ? View.VISIBLE : View.GONE);
        Glide.with(context.getApplicationContext())
                .load(location.getBackgroundUrl())
                .override(itemHeight, itemHeight)
                .crossFade()
                .into(holder.imageImage);
        holder.relativeContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(location);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return locationUIModelList.size();
    }



    public interface OnItemClickListener {

        void onItemClick(LocationUIModel locationUIModel);

    }

    static class BasicHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.relative_container) RelativeLayout relativeContainer;
        @BindView(R.id.img_favorite) ImageView imgFavorite;
        @BindView(R.id.image_image) ImageView imageImage;
        @BindView(R.id.txt_name) TextView txtName;


        BasicHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
