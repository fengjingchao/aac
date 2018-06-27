package com.example.livedata;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.arch.lifecycle.ViewModelProviders;

import java.util.ArrayList;
import java.util.List;

public class LiveDataFragment extends Fragment implements View.OnClickListener{
    private static final String TAG = "LiveDataFragment";
    private NameViewModel mNameViewModel;
    TextView mTvName;

    public static LiveDataFragment getInstance(){
        return new LiveDataFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mNameViewModel = ViewModelProviders.of(this).get(NameViewModel.class);
        mNameViewModel.getCurrentName().observe(this,(String name) -> {
            mTvName.setText(name);
            Log.d(TAG, "currentName: " + name);
        }); // 订阅LiveData中当前Name数据变化，以lambda形式定义Observer
        mNameViewModel.getNameList().observe(this, (List<String> nameList) -> {
            for (String item : nameList) {
                Log.d(TAG, "name: " + item);
            }
        }); // 订阅LiveData中Name列表数据变化，以lambda形式定义Observer
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_livedata, container, false);
        Button changeName = view.findViewById(R.id.btn_change_name);
        Button updateList = view.findViewById(R.id.btn_update_list);
        updateList.setOnClickListener(this);
        changeName.setOnClickListener(this);
        mTvName = view.findViewById(R.id.tv_name);
        return view;
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_change_name:
                mNameViewModel.getCurrentName().setValue("Jane");
                break;
            case R.id.btn_update_list:
                List<String> nameList = new ArrayList<>();
                for (int i = 0; i < 10; i++){
                    nameList.add("Jane<" + i + ">");
                }
                mNameViewModel.getNameList().setValue(nameList);
                break;
        }
    }

}
