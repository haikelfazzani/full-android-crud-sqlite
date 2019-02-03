package com.example.haikel.tpsqlite;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListContact extends AppCompatActivity implements View.OnClickListener {

    private ListView listView;
    private DatabaseHandler dbHelper;
    List<Contact> listContact;
    ArrayList<String> list = new ArrayList<>();

    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_contact);

        listView = findViewById(R.id.listContact);
        btnBack = findViewById(R.id.btnBack);

        dbHelper = new DatabaseHandler(ListContact.this);
        listContact = dbHelper.getAllContact();

        for(Contact c : listContact) {
            String r  = c.getNom() + " " + c.getTel();
            list.add(r);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ListContact.this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(ListContact.this, MainActivity.class);
        startActivity(intent);
    }
}
