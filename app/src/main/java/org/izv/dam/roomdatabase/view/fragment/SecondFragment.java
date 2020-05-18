package org.izv.dam.roomdatabase.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import org.izv.dam.roomdatabase.R;
import org.izv.dam.roomdatabase.util.LogUtil;
import org.izv.dam.roomdatabase.view.ViewModel;

import static org.izv.dam.roomdatabase.MainActivity.TAG;

public class SecondFragment extends Fragment {

    private ViewModel viewModel;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        Log.v(LogUtil.getTag(TAG), "onCreateView");
        viewModel = new ViewModelProvider(getActivity()).get(ViewModel.class);
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        Log.v(LogUtil.getTag(TAG), "onViewCreated");
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.button_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }
}
