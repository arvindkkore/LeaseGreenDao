package com.devteam83.tutorials.leasegreendao;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.devteam83.tutorials.leasegreendao.adapter.MainActivityListAdapter;
import com.devteam83.tutorials.leasegreendao.model.DaoMaster;
import com.devteam83.tutorials.leasegreendao.model.DaoSession;
import com.devteam83.tutorials.leasegreendao.model.Lease;
import com.devteam83.tutorials.leasegreendao.model.LeaseDao;
import com.devteam83.tutorials.leasegreendao.model.Person;
import com.devteam83.tutorials.leasegreendao.model.PersonDao;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "lease-db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();

        LeaseDao leaseDao = daoSession.getLeaseDao();
        List leaseList = leaseDao.loadAll();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.main_activity_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MainActivityListAdapter adapter = new MainActivityListAdapter(leaseList);
        recyclerView.setAdapter(adapter);


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    public void insertSampleData(DaoSession daoSession) {
        Person person = new Person();
        person.setName("John Doe");
        PersonDao personDao = daoSession.getPersonDao();
        personDao.insertOrReplace(person);

        Lease lease = new Lease();
        lease.setItem("My Nexus 6");
        lease.setPerson(person);
        LeaseDao leaseDao = daoSession.getLeaseDao();
        leaseDao.insertOrReplace(lease);
    }
}