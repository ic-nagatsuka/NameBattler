package com.namebattler.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
        String title = bnd.getString(titleKey);                 //タイトル
        boolean showButton = bnd.getBoolean(showButtonKey);     //ボタンの表示
        final Class cls = (Class)bnd.getSerializable(clsKey);   //遷移先のアクティビティ

        TextView textview = view.findViewById(R.id.fragment_title);
        textview.setText(title);

        Button btn = view.findViewById(R.id.fragment_title_back);

        if(showButton){
            //ボタンの表示をする
            btn.setVisibility(View.VISIBLE);
            if(cls != null) {
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), cls);
                        startActivity(intent);
                    }
                });
            }else{
                Log.e(getClass().toString(), "ボタンの機能がありません");
                btn.setVisibility(View.INVISIBLE);
            }
        }else{
            //ボタンを隠す
            btn.setVisibility(View.INVISIBLE);
        }


    }

    //タイトル表示
    public static void displayTitleFragment(FragmentManager fragmentManager, String text, boolean showButton, Class moveClass){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment2,
                TitleFragment.newInstance(
                        text, showButton , moveClass));
        fragmentTransaction.commit();
    }

}
