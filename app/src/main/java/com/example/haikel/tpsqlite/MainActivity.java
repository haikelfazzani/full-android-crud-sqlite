package com.example.haikel.tpsqlite;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText nom, tel;
    Button btnAdd;

    private DatabaseHandler dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHandler(MainActivity.this);

        nom = findViewById(R.id.nom);
        tel = findViewById(R.id.tel);
        btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String txtNom = nom.getText().toString();
        String txtTel = tel.getText().toString();

        Contact contact = new Contact();
        contact.setNom(txtNom);
        contact.setTel(txtTel);

        dbHelper.openDB();

        long l = dbHelper.addContact(contact);

        Intent intent = new Intent(MainActivity.this, ListContact.class);
        startActivity(intent);
    }
}
