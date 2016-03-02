package com.example.lifeorganiser.src.controllers;


import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import com.example.lifeorganiser.R;
import com.example.lifeorganiser.src.Models.events.DatedEvent;
import com.example.lifeorganiser.src.Models.user.UserManager;

import java.util.ArrayList;
import java.util.Calendar;


public class TasksFragment extends android.app.Fragment {

    private static final String ADD_TEXT = "add +";

    private UserManager userManager;

    private ListView tasksListView;

    public TasksFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.userManager = UserManager.getInstance();

        View v = inflater.inflate(R.layout.fragment_today, container, false);

        this.tasksListView = (ListView) v.findViewById(R.id.todayActivityListView);

        ArrayList<DatedEvent> events = new ArrayList<>();
        ArrayList<String> stringEvents = new ArrayList<>();
        for (DatedEvent event: this.userManager.getTasks()){

            events.add(event);
            stringEvents.add(event.getDateTime().get(Calendar.HOUR) + " " +
                    event.getDateTime().get(Calendar.MINUTE) + " " +
                    event.getTitle());

        }

        stringEvents.add(TasksFragment.ADD_TEXT);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                R.layout.simple_listview_item,
                stringEvents);
        this.tasksListView.setAdapter(adapter);

        this.tasksListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (((TextView) view).getText().equals(TasksFragment.ADD_TEXT)) {
                    FragmentManager fm = getFragmentManager();
                    AddTaskDialog dialogFragment = new AddTaskDialog ();
                    dialogFragment.show(fm, "Sample Fragment");

                    return;
                }

                //TODO start task view
            }
        });

        return v;
    }

}