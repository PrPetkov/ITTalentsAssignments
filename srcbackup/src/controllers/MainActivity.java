package com.example.lifeorganiser.src.controllers;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lifeorganiser.R;
import com.example.lifeorganiser.src.Models.user.UserManager;

public class MainActivity extends AppCompatActivity {

    private UserManager userManager;

    private TextView todayTextView;
    private TextView todoTextView;
    private TextView calendarTextView;
    private TextView tasksTextView;
    private TextView accountsTextView;
    private TextView shoppingListTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today);

        this.userManager = UserManager.getInstance();

        this.todayTextView = (TextView) findViewById(R.id.todayNavigationTodayTextView);
        this.todoTextView = (TextView) findViewById(R.id.todayNavigationTODOTextView);
        this.calendarTextView = (TextView) findViewById(R.id.todayNavigationCalendarTextView);
        this.tasksTextView = (TextView) findViewById(R.id.todayNavigationTasksTextView);
        this.accountsTextView = (TextView) findViewById(R.id.todayNavigationAccountTextView);
        this.shoppingListTextView = (TextView) findViewById(R.id.todayNavigationShoppingTextView);

        final FragmentManager fragmentManager = getFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        TodayFragment todayFragment = new TodayFragment();

        fragmentTransaction.replace(android.R.id.content, todayFragment);



        this.todayTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TodayFragment todayFragment = new TodayFragment();

                fragmentTransaction.replace(android.R.id.content, todayFragment);
            }
        });

        this.tasksTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragmentTransaction.commit();
                TasksFragment taskFragment = new TasksFragment();

                fragmentTransaction.replace(android.R.id.content, taskFragment);
            }
        });



    }
}
