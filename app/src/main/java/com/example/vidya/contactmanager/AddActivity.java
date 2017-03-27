package com.example.vidya.contactmanager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import java.io.File;

import static android.os.Environment.*;

/**
 * Created by Vidya on 3/25/2017.
 */
public class AddActivity {
    public String path= getExternalStorageDirectory().getAbsolutePath()+"/ContactManager";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
    }

    public void onButtonClick(View v){
        if(v.getId()==R.id.savebutton)
        {
            EditText fname=(EditText)findViewById(R.id.fname);
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
            ContactDetails.contactList.add(contactDetail);
            String saveString=fname.getText().toString()+"\t"+lname.getText().toString()+"\t";
            saveString+=phone.getText().toString()+"\t"+email.getText().toString()+"\t"+dob.getText().toString();
            File file= new File(path+"/contactManager.txt");
            save(file,saveString);
            Intent launchActivity1= new Intent(AddActivity.this,MainActivity.class);
            startActivity(launchActivity1);

        }
    }
    public void save(File file, String str)
    {
        FileOutputStream fos=null;
        try{
            fos=new FileOutputStream(file, true);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        try{
            fos.write(str.getBytes());
            fos.write("\n".getBytes());
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

    // @Override
//public boolean onOptionsItemSelected(MenuItem item) {
//// Handle action bar item clicks here. The action bar will
//// automatically handle clicks on the Home/Up button, so long
//// as you specify a parent activity in AndroidManifest.xml.
//int id = item.getItemId();
////noinspection SimplifiableIfStatement
//if (id == R.id.action_settings) {
//return true;
//}
//return super.onOptionsItemSelected(item);
//}
}
