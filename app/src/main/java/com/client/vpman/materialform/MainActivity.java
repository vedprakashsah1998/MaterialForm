package com.client.vpman.materialform;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDatabaseRef;
    MaterialButton Register;
    TextInputEditText editName,editMobile,editDesignation,editEmail,editCard;
    MaterialTextView Reuired,Reuired1,Reuired2,Reuired3;
    String[] city = { "Jaipur","Delhi","Gurugramn","Banglore","Hyderabad","California","Muzaffarpur","Lucknow","Patna" };
    String[] state = { "Rajasthan","Uttap Pradesh","Punjab","Haryana","Chandigarh","Bihar","Odisha","Chennai","TamilNadu" };

    String city1,state1,editName1,editMobile1,editDesignation1,editEmail1,editCard1;
    Spinner spinner,spinner1;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editName=findViewById(R.id.editName);
        editMobile=findViewById(R.id.editNumber);
        editDesignation=findViewById(R.id.editDesiginition);
        editEmail=findViewById(R.id.editEmail);
        Reuired=findViewById(R.id.reqd);
        Reuired1=findViewById(R.id.reqd1);
        Reuired2=findViewById(R.id.reqd2);
        Reuired3=findViewById(R.id.reqd3);
        Register=findViewById(R.id.Register);
        editCard=findViewById(R.id.editCardNumber);
         spinner =  findViewById(R.id.city);
        spinner.setOnItemSelectedListener(this);
         spinner1 =  findViewById(R.id.state);
        spinner1.setOnItemSelectedListener(this);
        editName.getBackground().clearColorFilter();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,city);

        spinner.setAdapter(adapter);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,state);

        spinner1.setAdapter(adapter1);












        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseRef = mDatabase.getReference();

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

if (editName.getText().length()==0&&editCard.getText().length()==0&&editDesignation.getText().length()==0&&editEmail.getText().length()==0&&editMobile.getText().length()==0) {
    Reuired.setTextColor(Color.RED);
    Reuired1.setTextColor(Color.RED);
    Reuired2.setTextColor(Color.RED);
    Reuired3.setTextColor(Color.RED);
    Toast.makeText(MainActivity.this, "Please enter the data", Toast.LENGTH_SHORT).show();
}
    else
    {
        StoreData();
    }
}


        });



    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void StoreData()
    {
        editName1=editName.getText().toString().trim();
        editEmail1=editEmail.getText().toString().trim();
        editMobile1=editMobile.getText().toString().trim();
        editDesignation1=editDesignation.getText().toString().trim();
        editCard1=editCard.getText().toString().trim();
        city1=spinner.getSelectedItem().toString();
        state1=spinner1.getSelectedItem().toString();



            mDatabaseRef.child("Detail").push().child("editName").setValue(editName1);
            mDatabaseRef.child("Detail").push().child("city").setValue(city1);
            mDatabaseRef.child("Detail").push().child("state").setValue(state1);
            mDatabaseRef.child("Detail").push().child("Mobile No").setValue(editMobile1);
            mDatabaseRef.child("Detail").push().child("Designition").setValue(editDesignation1);
            mDatabaseRef.child("Detail").push().child("Email").setValue(editEmail1);
            mDatabaseRef.child("Detail").push().child("Card No").setValue(editCard1);

            Toast.makeText(this, "Data is  Stored Successfully", Toast.LENGTH_SHORT).show();



        
    }

}
