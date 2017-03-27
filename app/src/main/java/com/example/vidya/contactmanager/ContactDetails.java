package com.example.vidya.contactmanager;

/**
 * Created by Vidya on 3/25/2017.
 */
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by shrutibidada on 3/25/17.
 */
// This is a model for add contact screen
public class ContactDetails implements Comparable<ContactDetails> {
    private String fname;
    private String lname;
    private String phone;
    private String email;
    private String dob;
    // static list will be used to keep the data updated with every action
    // File contains setter getter for all the members.
    static List<ContactDetails> contactList=new ArrayList<>();
    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    @Override
    public int compareTo(@NonNull ContactDetails o) {
        return this.getFname().compareTo(o.getFname());
    }
}