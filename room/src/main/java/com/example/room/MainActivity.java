package com.example.room;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.room.db.User;
import com.example.room.db.UserDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button insert = findViewById(R.id.insert);
        Button query = findViewById(R.id.query);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new InsertAsycTask(MainActivity.this).execute();
            }
        });
        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        List<User> users = UserDatabase.getInstance(MainActivity.this).getUserDao().getAllUsers();
                        for(User user : users) {
                            Log.d("ROOM", "onClick: "+user.getAge());
                            Log.d("ROOM", "onClick: "+user.getName());
                        }
                    }
                }).start();
            }
        });
    }

    class InsertAsycTask extends AsyncTask<Void,Integer,Integer>{


        private final Context context;

        InsertAsycTask(Context context){
            this.context= context;
        }
        @Override
        protected Integer doInBackground(Void... voids) {
            List<User> users = new ArrayList<>();
            User userfeng = new User();
            User useryang = new User();
            userfeng.setAge(20);
            userfeng.setName("feng");
            useryang.setAge(10);
            useryang.setName("yang");



            users.add(userfeng);
            users.add(useryang);
            Log.d("ROOM", "doInBackground: insert");
            insert(users,context);
//            UserDatabase.getInstance(context).getUserDao().insert(users);
            return null;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    List<User> users = UserDatabase.getInstance(MainActivity.this).getUserDao().getAllUsers();
                    for(User user : users) {
                        Log.d("ROOM", "onClick: "+user.getAge());
                        Log.d("ROOM", "onClick: "+user.getName());
                    }
                }
            }).start();

        }
    }

    void insert (List<User> users, Context context){
        List<User> updates = new ArrayList<>();
        for (User user:users) {
            User get = UserDatabase.getInstance(context).getUserDao().getUser(user.getName());
            Log.d("ROOM", "insert: "+user.getName() +", get="+get+", age="+user.getAge() );
            if (get != null) {
                user.setId(get.getId());//update 是根据 id (primarykey)来的 ***坑***
                updates.add(user);
            }
        }
        Log.d("ROOM", "updates: "+updates.size());
        if (updates.size()>0) {
            UserDatabase.getInstance(context).getUserDao().update(updates);
            users.removeAll(updates);
        }
        Log.d("ROOM", "updates: "+users.size());
        if (users.size()>0) {
            UserDatabase.getInstance(context).getUserDao().insert(users);
        }
    }
}
