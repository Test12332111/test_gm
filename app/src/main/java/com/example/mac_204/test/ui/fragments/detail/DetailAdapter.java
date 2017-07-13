package com.example.mac_204.test.ui.fragments.detail;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.mac_204.test.R;
import com.example.mac_204.test.data.ui.models.LocationUIModel;
import com.example.mac_204.test.data.ui.models.SightUIModel;
import com.example.mac_204.test.inject.qualifier.ActivityContext;
import com.example.mac_204.test.inject.scope.FragmentScope;
import com.example.mac_204.test.ui.fragments.list.ListAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mac-204 on 7/13/17.
 */

@FragmentScope
public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.BasicHolder>{

    private Context context;
    private LayoutInflater layoutInflater;
    private List<SightUIModel> sightUIModels;

    @Inject
    public DetailAdapter(@ActivityContext Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.sightUIModels = new ArrayList<>();
    }

    public void updSightUIModels(List<SightUIModel> sightUIModels) {
        this.sightUIModels.clear();
        this.sightUIModels.addAll(sightUIModels);
    }

    @Override
    public BasicHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.adapter_detail_basic, parent, false);
        return new BasicHolder(view);
    }

    @Override
    public void onBindViewHolder(BasicHolder holder, int position) {
        final SightUIModel sightUIModel = sightUIModels.get(position);
        holder.txtName.setText(sightUIModel.getName());
        holder.txtDescription.setText(sightUIModel.getDescription());
        holder.txtType.setText(sightUIModel.getType());

        Glide.with(context.getApplicationContext())
                .load(sightUIModel.getBackgroundUrl())
                .crossFade()
                .into(holder.imgIcon);

        Glide.with(context.getApplicationContext())
                .load(sightUIModel.getIconUrl())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .crossFade()
                .into(holder.imgAnchor);
    }

    @Override
    public int getItemCount() {
        return sightUIModels.size();
    }

    static class BasicHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_icon) ImageView imgIcon;
        @BindView(R.id.img_anchor) ImageView imgAnchor;
        @BindView(R.id.txt_name) TextView txtName;
        @BindView(R.id.txt_description) TextView txtDescription;
        @BindView(R.id.txt_type) TextView txtType;

        BasicHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
