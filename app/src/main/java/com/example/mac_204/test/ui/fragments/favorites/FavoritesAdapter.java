package com.example.mac_204.test.ui.fragments.favorites;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mac_204.test.R;
import com.example.mac_204.test.data.ui.models.LocationUIModel;
import com.example.mac_204.test.data.ui.models.SightUIModel;
import com.example.mac_204.test.inject.qualifier.ActivityContext;
import com.example.mac_204.test.inject.scope.FragmentScope;
import com.example.mac_204.test.ui.fragments.detail.DetailAdapter;
import com.example.mac_204.test.ui.fragments.list.ListAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mac-204 on 7/14/17.
 */
@FragmentScope
public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.BasicHolder> {



    private Context context;
    private LayoutInflater layoutInflater;
    private List<LocationUIModel> locationUIModels;
    private OnItemClickListener onItemClickListener;

    @Inject
    public FavoritesAdapter(@ActivityContext Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.locationUIModels = new ArrayList<>();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void updLocationUIModels(List<LocationUIModel> locationUIModels) {
        this.locationUIModels.clear();
        this.locationUIModels.addAll(locationUIModels);
    }

    public void addLocationUIModels(List<LocationUIModel> locationUIModels) {
        this.locationUIModels.addAll(locationUIModels);
    }

    public void clearLocationUIModels() {
        this.locationUIModels.clear();
    }

    @Override
    public BasicHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.adapter_favorites_basic, parent, false);
        return new BasicHolder(view);
    }

    @Override
    public void onBindViewHolder(BasicHolder holder, int position) {
        final LocationUIModel location = locationUIModels.get(position);
        holder.txtName.setText(location.getName());
        holder.txtName.setText(location.getName());

        Glide.with(context.getApplicationContext())
                .load(location.getBackgroundUrl())
                .crossFade()
                .into(holder.imgIcon);

        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
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
        return locationUIModels.size();
    }

    public interface OnItemClickListener{

        void onItemClick(LocationUIModel locationUIModel);
    }

    static class BasicHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.layout_item) LinearLayout layoutItem;
        @BindView(R.id.txt_name) TextView txtName;
        @BindView(R.id.img_icon) ImageView imgIcon;

        BasicHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
