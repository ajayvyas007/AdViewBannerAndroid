package com.android.best.be.best.sharedprefrence;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ajay on 21/3/17.
 */

public class PrefManager {
    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "";

    // All Shared Preferences Keys
    private static final String IS_SUBMIT = "IsSubmit";


    // Constructor
    public PrefManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void submitSuccess(){
        // Storing login value as TRUE
        editor.putBoolean(IS_SUBMIT, true);
        // commit changes
        editor.commit();
    }

    public boolean checkSubmit(){
        // Check login status
        if(this.isSubmit()){
            // user is not logged in redirect him to Login Activity
            return false;
        }
        return true;
    }

    // Get Login State
    public boolean isSubmit(){
        return pref.getBoolean(IS_SUBMIT, false);
    }
}
