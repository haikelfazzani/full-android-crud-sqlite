package com.example.haikel.tpsqlite;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListContact extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemClickListener {

    private ListView listView;
    private ArrayAdapter<String> adapter;

    private DatabaseHandler dbHelper;
    private List<Contact> listContact;
    private ArrayList<String> list = new ArrayList<>();

    private Button btnBack;

    private Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_contact);

        listView = findViewById(R.id.listContact);
        btnBack = findViewById(R.id.btnBack);

        dbHelper = new DatabaseHandler(ListContact.this);
        listContact = dbHelper.getAllContact();

        for(Contact c : listContact) {
            String r  = "Name : "+c.getName() + " - Tel : " + c.getTel();
            list.add(r);
        }

        adapter = new ArrayAdapter<String>(ListContact.this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(ListContact.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

        if(position > -1) {
            Intent myIntent = new Intent(ListContact.this, MainActivity.class);
            myIntent.putExtra("contact", gson.toJson(listContact.get(position)));
            startActivity(myIntent);
        }

    }
}
