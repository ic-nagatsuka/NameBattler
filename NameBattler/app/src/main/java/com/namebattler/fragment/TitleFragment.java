package com.namebattler.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.namebattler.R;

public class TitleFragment extends Fragment {

    static String titleKey = "title";

    public static Fragment newInstans(String title, boolean visi){
        TitleFragment fragment = new TitleFragment();

        Bundle bundle = new Bundle();
        bundle.putString(titleKey, title);
        bundle.putBoolean("visi", visi);

        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_title, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bnd = getArguments();
        String title = bnd.getString(titleKey);

        TextView textview = view.findViewById(R.id.fragment_title);
        textview.setText(title);

        Button btn = view.findViewById(R.id.fragment_title_back);

        if(getArguments().getBoolean("visi")){
            btn.setVisibility(View.VISIBLE);

        }else{
            btn.setVisibility(View.INVISIBLE);

        }


    }
}
