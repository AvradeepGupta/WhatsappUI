package com.example.avrad.whatsapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class Chatsjava extends Fragment implements SearchView.OnQueryTextListener {
    private RecyclerAdapter adapter;
    private RecyclerView recyclerView;
    /*private MenuInflater menuInflater;
    private Menu menu;*/
    int icons[] = {R.drawable.a, R.drawable.a, R.drawable.a, R.drawable.a, R.drawable.a, R.drawable.a, R.drawable.a, R.drawable.a, R.drawable.a,R.drawable.a};
    String[] names = {"Siddharth", "Gaurav", "Apurv", "Ritvik", "Devarsh", "Allan", "Nick", "Mohit", "Raunak","Meau"};
    String[] status = {"Hello", "Hi there", "Busy", "No calls", "WhatsApp only", "bleh", "What's up?", "Sleeping", "At the movies","Kamn acho manu ?"};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chatsfrag, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.chatlist);
        adapter = new RecyclerAdapter(getActivity(), getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    private List<Item> getData() {
        List<Item> data = new ArrayList<>();
        for(int i = 0; i < names.length; i++) {
            Item current = new Item();
            current.iconid = icons[i];
            current.title = names[i];
            current.descript = status[i];
            data.add(current);
        }
        return data;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        // Inflate the menu; this adds items to the action bar if it is present.

        menuInflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, menuInflater);

        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        return true;
    }
    void updatesearch(ArrayList<String> newsearchList){
        adapter.searchChatList(newsearchList);
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        ArrayList<String> searchItemList = new ArrayList<String>(Arrays.asList(names));
        ArrayList<String> newsearchList = new ArrayList<String>();
        int ctr=0;
        for(int i = 0 ; i<searchItemList.size() ; i++)
        {
            if(searchItemList.get(i).toLowerCase().contains(newText))
            {
                ctr++;
                newsearchList.add(searchItemList.get(i));
            }
        }
        updatesearch(newsearchList);
        return true;
    }
}