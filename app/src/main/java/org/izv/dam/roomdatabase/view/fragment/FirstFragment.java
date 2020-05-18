package org.izv.dam.roomdatabase.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.izv.dam.roomdatabase.R;
import org.izv.dam.roomdatabase.model.data.Category;
import org.izv.dam.roomdatabase.util.LogUtil;
import org.izv.dam.roomdatabase.view.ViewModel;
import org.izv.dam.roomdatabase.view.adapter.CategoryAdapter;

import java.util.List;

import static org.izv.dam.roomdatabase.MainActivity.TAG;

public class FirstFragment extends Fragment {

    private ViewModel viewModel;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        Log.v(LogUtil.getTag(TAG), "onCreateView");
        viewModel = new ViewModelProvider(getActivity()).get(ViewModel.class);
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        Log.v(LogUtil.getTag(TAG), "onViewCreated");
        super.onViewCreated(view, savedInstanceState);

        RecyclerView rvList = view.findViewById(R.id.rvList);
        rvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        final CategoryAdapter adapter = new CategoryAdapter(getActivity());
        rvList.setAdapter(adapter);

        viewModel.getLiveCategoriesList().observe(getActivity(), new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                Log.v(LogUtil.getTag(TAG), "onChanged Live Get All");
                Log.v(LogUtil.getTag(TAG), categories.toString());
                adapter.setCategories(categories);
            }
        });

        viewModel.getLiveDelete().observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Log.v(LogUtil.getTag(TAG), "onChanged Live Delete: " + integer);

            }
        });

        viewModel.getLiveGet().observe(getActivity(), new Observer<Category>() {
            @Override
            public void onChanged(Category category) {
                Log.v(LogUtil.getTag(TAG), "onChanged Live Get: " + category.toString());

            }
        });

        viewModel.getLiveInsert().observe(getActivity(), new Observer<Long>() {
            @Override
            public void onChanged(Long aLong) {
                Log.v(LogUtil.getTag(TAG), "onChanged Live Insert: " + aLong);

            }
        });

        viewModel.getLiveEdit().observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Log.v(LogUtil.getTag(TAG), "onChanged Live Update: " + integer);

            }
        });

        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }
}
