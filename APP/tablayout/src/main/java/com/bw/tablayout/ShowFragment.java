package com.bw.tablayout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @name APP
 * @class name：com.bw.tablayout
 * @class describe
 * @anthor 24673
 * @time 2019/11/20 11:39
 * @change
 * @chang time
 * @class describe
 */
public class ShowFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.show_fragment, container, false);
        return view;
    }
}
