package com.example.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ViewDataBinding mBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        DataBindingBean bean = new DataBindingBean();
        mBinding.setVariable(BR.bean,bean);//为布局设置数据源
        bean.setText("feng");
        TextClickCallback callback = new TextClickCallback() {
            @Override
            public void onClick(DataBindingBean bean) {
                Toast.makeText(MainActivity.this,"text clicked="+bean.getText(),Toast.LENGTH_LONG).show();
                bean.setText("yang");//更新数据刷新UI
            }
        };
        mBinding.setVariable(BR.callback, callback);//给布局binding一个点击监听
    }
}
