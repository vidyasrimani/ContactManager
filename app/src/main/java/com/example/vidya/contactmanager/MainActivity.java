package com.example.vidya.contactmanager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    List<Map<String, String>> contactList = new ArrayList<Map<String,String>>();
    public String path= Environment.getExternalStorageDirectory().getAbsolutePath()+"/ContactManager";
    File file = new File(path+"/contactManager.txt");

    /*
    Function : OnCreate
    Initializes contents when app is launched, sets an adapter to view the list
    Author : Vidya Mani (vxm163230)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(file.exists())
            read();
        else
            Toast.makeText(getBaseContext(),"No Contacts",Toast.LENGTH_SHORT).show();
        ListView lv = (ListView) findViewById(R.id.listView);
        SimpleAdapter simpleAapt = new SimpleAdapter(this, contactList, android.R.layout.simple_list_item_1, new String[] {"person"}, new int[] {android.R.id.text1});
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedFromList = parent.getAdapter().getItem(position).toString();
                String[] recordValue = selectedFromList.split("\t");
                Intent activity2 = new Intent (MainActivity.this, AddActivity.class);
                activity2.putExtra("indexOfList",Integer.toString(position));
                startActivity(activity2);

            }
        });
        lv.setAdapter(simpleAapt);
    }

    /*
    Function : read()
    Reads the data from the file when the application launches
    Author : Shruti Bidada (sgb160130)

     */
    public void read()
    {
        try {
            FileInputStream fin = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fin);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String contents;
            if((ContactDetails.contactList==null||ContactDetails.contactList.isEmpty()) &&  file.exists())
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
                    while ((strLine = br.readLine()) != null)
                    {
                        // Print the content on the console
                        String [] str=strLine.split("\t");
                        ContactDetails contactDetail=new ContactDetails();
                        if(str.length>0)
                        {
                            contactDetail.setFname(str[0]);
                            if(str.length>1)
                                contactDetail.setLname(str[1]);
                            else
                                contactDetail.setLname("");
                            if(str.length>2)
                                contactDetail.setPhone(str[2]);
                            else
                                contactDetail.setPhone("");
                            if(str.length>3)
                                contactDetail.setEmail(str[3]);
                            else
                                contactDetail.setEmail("");
                            if(str.length>4)
                                contactDetail.setDob(str[4]);
                            else
                                contactDetail.setDob("");
                        }
                        ContactDetails.contactList.add(contactDetail);
                    }
                    //Close the input stream
                    in.close();
                }
                catch (Exception e)
                {
                    //Catch exception if any
                    System.err.println("Error: " + e.getMessage());
                }
            }

            Collections.sort(ContactDetails.contactList);
            for (int i = 0; i < ContactDetails.contactList.size(); i++) {
                contactList.add(createContact("person", ContactDetails.contactList.get(i).getFname()+"\t"+ContactDetails.contactList.get(i).getLname()+"\n"+ContactDetails.contactList.get(i).getPhone()));

            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    /*
    Function : createContact
    Adapter picks the content from the specified hashmap, it is updated in this function
    Author : Vidya Sri Mani(vxm163230)

     */
    private HashMap<String, String> createContact(String key, String name)
    {
        HashMap<String, String> contact = new HashMap<String, String>();
        contact.put(key, name);

        return contact;
    }
    /*
    Function : onCreateOptionsMenu
    Inflate the menu; this adds items to the action bar if it is present.
    Author : Vidya Sri Mani(vxm163230)
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //Autogenerated
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        return super.onOptionsItemSelected(item);
    }

    /*
    Function : onBackPressed()
    Controls back option in the app from the main screen
    Author : Shruti Bidada(sgb160130)
     */
    @Override
    public void onBackPressed()
    {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
    }
    /*
    Function : goToAddContact
    Creates the intent, redirects to add activity page
    Author : Shruti Bidada(sgb160130)
    */
    public void goToAddContact(View v)
    {
        Intent addContact=new Intent(MainActivity.this,AddActivity.class);
        startActivity(addContact);
    }


}