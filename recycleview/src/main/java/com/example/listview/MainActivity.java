package com.example.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Book> mlsit = new ArrayList<Book>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化List数据
        initBook();
        //初始化RecyclerView
        RecyclerView recyslerview = (RecyclerView) findViewById(R.id.recycler_view);
        //创建LinearLayoutManager 对象 这里使用 LinearLayoutManager 是线性布局的意思
        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        recyslerview.setLayoutManager(layoutmanager);
        //设置Adapter
        BookBaseAdapter adapter = new BookBaseAdapter(mlsit);
        recyslerview.setAdapter(adapter);
    }

    private void initBook(){
        for (int i = 0; i < 10; i++) {
            Book book01 = new Book("Book"+i,R.mipmap.ic_launcher);
            mlsit.add(book01);
            Book book02 = new Book("Book"+i,R.mipmap.ic_launcher);
            mlsit.add(book02);
            Book book03 = new Book("Book"+i,R.mipmap.ic_launcher);
            mlsit.add(book03);
        }
    }

}
