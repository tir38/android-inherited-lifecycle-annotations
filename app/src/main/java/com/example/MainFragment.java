package com.example;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_welcome, container, false);
//        WithAnnotations testClass = new WithAnnotations();
        WithInheritedAnnotations testClass = new WithInheritedAnnotations();
        getLifecycle().addObserver(testClass);

        return view;
    }
}
