package com.people.survey.view.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.people.survey.R;
import com.people.survey.data.local.entity.MarvelEntity;
import com.people.survey.databinding.ListItemMainBinding;
import com.people.survey.viewmodel.MainViewModel;

import java.util.List;

public class VoterRecyclerViewAdapter extends RecyclerView.Adapter<VoterRecyclerViewAdapter.ViewHolder> implements View.OnClickListener {
    ListItemMainBinding binding;
    private List<MarvelEntity> dataModelList;
    private MainViewModel viewModel;

    public VoterRecyclerViewAdapter(List<MarvelEntity> dataModelList, MainViewModel vm) {
        this.dataModelList = dataModelList;
        viewModel = vm;
    }

    @NonNull
    @Override
    public VoterRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                                  int viewType) {
        /* binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.list_item_main, parent, false);*/
        binding = ListItemMainBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        binding.setViewmodel(viewModel);
        binding.setClickListener(this);
        return new ViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MarvelEntity dataModel = dataModelList.get(position);
        binding.setModel(dataModel);
    }


    @Override
    public int getItemCount() {
        return this.dataModelList.size();
    }

    public void setVotersData(List<MarvelEntity> marvelModels) {

        dataModelList = marvelModels;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.plant_item_title:
                Log.e("CLICK","CLICKKKK:"+v.getId());
                break;
        }

    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public ListItemMainBinding itemRowBinding;

        public ViewHolder(ListItemMainBinding itemRowBinding) {
            super(itemRowBinding.getRoot());
            this.itemRowBinding = itemRowBinding;
        }
    }


    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view, String url) {
        Glide.with(view.getContext()).load(url).into(view);
    }
}