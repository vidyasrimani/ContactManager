package com.example.vidya.contactmanager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * Created by shrutibidada on 3/21/17.
 */

public class AddActivity extends AppCompatActivity {

    public String path= Environment.getExternalStorageDirectory().getAbsolutePath()+"/ContactManager";
    File file= new File(path+"/contactManager.txt");
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        FloatingActionButton fab2 = (FloatingActionButton)findViewById(R.id.delIcon);
        fab2.setEnabled(false);
        String val1=getIntent().getStringExtra("indexOfList");
        if(val1!=null && !val1.isEmpty())
        {
            setTitle("Edit Contact");
            int id=Integer.parseInt(val1);
            ContactDetails contactDetail=ContactDetails.contactList.get(id);
            ((EditText)findViewById(R.id.fname)).setText(contactDetail.getFname());
            ((EditText)findViewById(R.id.lname)).setText(contactDetail.getLname());
            ((EditText)findViewById(R.id.phone)).setText(contactDetail.getPhone());
            ((EditText)findViewById(R.id.email)).setText(contactDetail.getEmail());
            ((EditText)findViewById(R.id.dob)).setText(contactDetail.getDob());
            fab2.setEnabled(true);
        }
    }

    public void onButtonClick(View v){
        String val1=getIntent().getStringExtra("indexOfList");
        // if(v.getId()==R.id.savebutton ||v.getId()==R.id.saveIcon )
        if(v.getId()==R.id.saveIcon )
        {
            EditText fname=(EditText)findViewById(R.id.fname) ;
            EditText lname=(EditText)findViewById(R.id.lname);
            EditText phone=(EditText)findViewById(R.id.phone);
            EditText email=(EditText)findViewById(R.id.email);
            EditText dob=(EditText)findViewById(R.id.dob);
            ContactDetails contactDetail=new ContactDetails();
            contactDetail.setFname(fname.getText().toString());
            contactDetail.setLname(lname.getText().toString());
            contactDetail.setPhone(phone.getText().toString());
            contactDetail.setEmail(email.getText().toString());
            contactDetail.setDob(dob.getText().toString());
            if(fname.getText().toString().isEmpty()){
                Toast.makeText(AddActivity.this, "First Name Empty", Toast.LENGTH_LONG).show();
            }
            else{
                if(val1!=null && !val1.isEmpty())//for update
                {
                    ContactDetails.contactList.set(Integer.parseInt(val1),contactDetail);
                    Toast.makeText(AddActivity.this, "Changes updated", Toast.LENGTH_LONG).show();
                }
                else//for add
                {
                    ContactDetails.contactList.add(contactDetail);
                    Toast.makeText(AddActivity.this, "Contact Added", Toast.LENGTH_LONG).show();
                }

                Intent launchActivity1= new Intent(AddActivity.this,MainActivity.class);
                startActivity(launchActivity1);
            }

        }
        else if(v.getId()==R.id.delIcon)
        {
            if(val1!=null && !val1.isEmpty())
            {
                ContactDetails.contactList.remove(Integer.parseInt(val1));
                Toast.makeText(AddActivity.this, "Deleted Successfully ", Toast.LENGTH_LONG).show();
                Intent launchActivity1= new Intent(AddActivity.this,MainActivity.class);
                startActivity(launchActivity1);
            }
        }
        save();
    }
    // writes the data into the text file on every action that is performed
    public void save()
    {
        FileOutputStream fos=null;
        try{
            fos=new FileOutputStream(file,false);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        try{
            for(int i=0;i<ContactDetails.contactList.size();i++)
            {
                ContactDetails contact=ContactDetails.contactList.get(i);
                String data= contact.getFname()+"\t"+contact.getLname()+"\t"+contact.getPhone()+"\t"+contact.getEmail()+"\t"+contact.getDob();
                fos.write(data.getBytes());
                fos.write("\n".getBytes());
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
  /*  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        File dir=new File(path);
        dir.mkdirs();
        *//*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*//*
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        File file=new File(path+"/contactManager.txt");
      *//*  if((ContactDetails.contactList==null||ContactDetails.contactList.isEmpty()) &&  file.exists())
        {
            try{
                // Open the file that is the first
                // command line parameter
                FileInputStream fstream = new FileInputStream(file);
                // Get the object of DataInputStream
                DataInputStream in = new DataInputStream(fstream);
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String strLine;
                //Read File Line By Line
                while ((strLine = br.readLine()) != null)   {
                    // Print the content on the console
                   String [] str=strLine.split("    ");
                   ContactDetails contactDetail=new ContactDetails();
                    if(str.length>0)
                    {
                        contactDetail.setFname(str[0]);
                        if(str.length>1)
                        contactDetail.setLname(str[1]);
                        if(str.length>2)
                        contactDetail.setPhone(str[2]);
                        if(str.length>3)
                        contactDetail.setEmail(str[3]);
                        if(str.length>4)
                        contactDetail.setDob(str[4]);
                    }
                }
                //Close the input stream
                in.close();
            }catch (Exception e){//Catch exception if any
                System.err.println("Error: " + e.getMessage());
            }
        }*//*
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }*/

   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/

}

