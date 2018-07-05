package com.example.databinding;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

public class DataBindingBean extends BaseObservable{
    private String text;

    @Bindable
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        notifyPropertyChanged(com.example.databinding.BR.text);
    }
}
