package com.namebattler.fragment;

import android.content.Intent;
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
import com.namebattler.activity.CharacterList;
import com.namebattler.activity.TopScreen;

public class TitleFragment extends Fragment {

    static String titleKey = "title";
    static String showButtonKey = "showButton";
    static String clsKey = "class";


    public static Fragment newInstance(String title, boolean showButton, Class cls){
        TitleFragment fragment = new TitleFragment();

        Bundle bundle = new Bundle();
        bundle.putString(titleKey, title);
        bundle.putBoolean(showButtonKey, showButton);
        bundle.putSerializable(clsKey, cls);

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

        if(getArguments().getBoolean(showButtonKey)){
            btn.setVisibility(View.VISIBLE);
        }else{
            btn.setVisibility(View.INVISIBLE);
        }

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Class cls = (Class)getArguments().getSerializable(clsKey);
                Intent intent = new Intent(getActivity(), cls);
                startActivity(intent);
            }
        });

    }
}
