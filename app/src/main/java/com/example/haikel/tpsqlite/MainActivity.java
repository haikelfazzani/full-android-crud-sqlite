package com.example.haikel.tpsqlite;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.haikel.tpsqlite.dao.DatabaseHandler;
import com.example.haikel.tpsqlite.model.Contact;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText name, tel;
    private Button btnAdd, btnUpdate, btnDelete, btnList, btnListRecy;
    private Intent intent;

    private DatabaseHandler dbHelper;
    private Gson gson = new Gson();

    private Contact myContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHandler(MainActivity.this);

        name = findViewById(R.id.name);
        tel = findViewById(R.id.tel);

        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnList = findViewById(R.id.btnList);
        btnListRecy = findViewById(R.id.btnListRecy);

        btnAdd.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnList.setOnClickListener(this);
        btnListRecy.setOnClickListener(this);

        //myContact = gson.fromJson(getIntent().getStringExtra("contact"),Contact.class);
        myContact = (Contact) getIntent().getSerializableExtra("contact");

        if(myContact != null) {
            name.setText(myContact.getName());
            tel.setText(myContact.getTel());
        }

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnAdd:
                String txtNom = name.getText().toString();
                String txtTel = tel.getText().toString();

                Contact contact = new Contact();
                contact.setName(txtNom);
                contact.setTel(txtTel);

                dbHelper.openDB();

                long l = dbHelper.addContact(contact);

                if(l > -1) {
                    intent = new Intent(MainActivity.this, ListContactActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(MainActivity.this, "An Error occurred during process",
                            Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btnUpdate:
                myContact.setTel(tel.getText().toString());
                //myContact.setTel(name.getText().toString());
                dbHelper.updateContact(myContact);
                Toast.makeText(MainActivity.this, "A contact is updated ",
                        Toast.LENGTH_SHORT).show();
                break;

            case R.id.btnDelete:

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Delete process");
                builder.setMessage("Are you sure to delete : ");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dbHelper.deleteContact(name.getText().toString());
                        name.setText("");
                        tel.setText("");
                        Toast.makeText(MainActivity.this, "A contact is deleted ",
                                Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                builder.create().show();
                break;

            case R.id.btnListRecy:
                intent = new Intent(MainActivity.this, MyRecycleActivity.class);
                startActivity(intent);
                break;

            case R.id.btnList:
                intent = new Intent(MainActivity.this, ListContactActivity.class);
                startActivity(intent);
                break;
        }


    }
}
