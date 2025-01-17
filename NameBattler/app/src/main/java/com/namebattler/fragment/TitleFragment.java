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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.namebattler.R;

public class TitleFragment extends Fragment {

    static String titleKey = "title";
    static String clsKey = "class";


    public static Fragment newInstance(String title, Class cls) {
        TitleFragment fragment = new TitleFragment();

        Bundle bundle = new Bundle();
        bundle.putString(titleKey, title);
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
        String title = bnd.getString(titleKey);                 //タイトル
        final Class cls = (Class) bnd.getSerializable(clsKey);   //遷移先のアクティビティ

        TextView textview = view.findViewById(R.id.fragment_title);
        textview.setText(title);

        Button btn = view.findViewById(R.id.fragment_title_back);

        if (cls != null) {
            //ボタンの表示をする
            btn.setVisibility(View.VISIBLE);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), cls);
                    startActivity(intent);
                }
            });
        } else {
            //ボタンを隠す
            btn.setVisibility(View.INVISIBLE);
        }


    }

    //タイトル表示
    public static void displayTitleFragment(FragmentManager fragmentManager, String text, Class moveClass) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.titleFragment,
                TitleFragment.newInstance(
                        text, moveClass));
        fragmentTransaction.commit();
    }

}
