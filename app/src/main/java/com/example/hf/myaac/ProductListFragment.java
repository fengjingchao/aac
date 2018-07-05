package com.example.hf.myaac;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ProductListFragment extends Fragment{

    public static final String TAG = "ProductListFragment";
    ProductAdapter adapter;
    TextView loadingTv;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list,container,false);
        RecyclerView recyclerView =view.findViewById(R.id.products_list);
        loadingTv =view.findViewById(R.id.loading_tv);
        adapter = new ProductAdapter();
        LinearLayoutManager layoutmanager = new LinearLayoutManager(getContext());
        //设置RecyclerView 布局
        recyclerView.setLayoutManager(layoutmanager);
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        List<ProductEntity> productList = DataGenerator.generateProducts();
        Log.d(TAG, "onCreateView: "+productList.size());
        adapter.setProductList(productList);
        loadingTv.setVisibility(View.GONE);

    }
}
