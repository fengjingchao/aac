package com.example.database;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button ok= null;
    Button query= null;
    TextView book= null;
    EditText price = null;
    Dao dao = null;
    //数据库文件名称
    private static final String DATABASE_NAME = "BookStore.db";
    private static final int DATABASE_VERSION = 1;
    //数据库版本号private static final int DATABASE_VERSION = 3;
    private MyDatabaseHelper mMyDatabaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ok = findViewById(R.id.ok);
        query = findViewById(R.id.query);
        book = findViewById(R.id.book);
        price = findViewById(R.id.price);
        dao = Dao.getInstance(this,DATABASE_NAME,DATABASE_VERSION);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dao.insert();
                dao.update();

            }
        });

        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dao.transaction();
                dao.query();
                dao.delete();
                dao.query();
            }
        });

    }
}
