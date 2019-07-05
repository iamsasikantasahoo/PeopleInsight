package com.people.survey.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.work.Data;
import androidx.work.WorkInfo;

import com.people.survey.data.local.entity.MarvelEntity;
import com.people.survey.databinding.MainFragmentBinding;
import com.people.survey.view.adapter.VoterRecyclerViewAdapter;
import com.people.survey.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private MainFragmentBinding binding;
    VoterRecyclerViewAdapter myRecyclerViewAdapter;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        //binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false);
        binding = MainFragmentBinding.inflate(getLayoutInflater(), container, false);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        // binding.setMainViewModel(mViewModel);
        observeViewModel(mViewModel);
        myRecyclerViewAdapter = new VoterRecyclerViewAdapter(new ArrayList<MarvelEntity>(), mViewModel);
        binding.setMyAdapter(myRecyclerViewAdapter);

    }


    private void observeViewModel(MainViewModel viewModel) {
        viewModel.getVoterListLiveData().observe(this, new Observer<List<MarvelEntity>>() {
            @Override
            public void onChanged(@Nullable List<MarvelEntity> projects) {
                if (projects != null) {
                    myRecyclerViewAdapter.setVotersData(projects);
                    myRecyclerViewAdapter.notifyDataSetChanged();
                 }
            }
        });

        viewModel.getOutputWorkInfo().observe(this, new Observer<List<WorkInfo>>() {
            @Override
            public void onChanged(List<WorkInfo> listOfWorkInfos) {

                // If there are no matching work info, do nothing
                if (listOfWorkInfos == null || listOfWorkInfos.isEmpty()) {
                    return;
                }

                // We only care about the first output status.
                // Every continuation has only one worker tagged TAG_OUTPUT
                WorkInfo workInfo = listOfWorkInfos.get(0);
                boolean finished = workInfo.getState().isFinished();
                Data outputdata = workInfo.getOutputData();

                Log.e("WORKMANAGER:", outputdata.getString("KEY_IMAGE_URI")+"WORKMANAGER:"+workInfo.getState()+"::"+workInfo.getState().isFinished());

                if (!finished) {
                   // Log.e("WORKMANAGER:", "WORKMANAGER:"+workInfo.getState());
                } else {
                   // Log.e("WORKMANAGER:", "WORKMANAGER:"+workInfo.getState());
                }
            }
        });
    }
}
