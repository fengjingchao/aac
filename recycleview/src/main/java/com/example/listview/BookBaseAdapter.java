package com.example.listview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class BookBaseAdapter extends RecyclerView.Adapter<BookBaseAdapter.ViewHolder> {

    private List<Book> mBookList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView bookImage;
        TextView bookname;

        public ViewHolder(View view) {
            super(view);
            bookImage = (ImageView) view.findViewById(R.id.book_iamge);
            bookname = (TextView) view.findViewById(R.id.book_name);
        }
    }

    public BookBaseAdapter(List<Book> mBookList) {
        this.mBookList = mBookList;
    }

    //加载item 的布局  创建ViewHolder实例
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    //对RecyclerView子项数据进行赋值
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Book book = mBookList.get(position);
        holder.bookname.setText(book.getName());
        holder.bookImage.setImageResource(book.getImageId());
    }
    //返回子项个数
    @Override
    public int getItemCount() {
        return mBookList.size();
    }
}

