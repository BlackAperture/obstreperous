package com.example.obstreperous.LocalAccountBypass;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.obstreperous.R;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

public class SecureAccountLogin extends LocalAccountBypassMain implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secure_account_login);

        Button button45 = (Button) findViewById(R.id.button45);
        button45.setOnClickListener(this);

    }


    public void onClick(View v) {



        try {

            String salt = getSalt();
            EditText pass = (EditText) findViewById(R.id.editText26);
            String password = pass.getEditableText().toString();
            String securePassword = getSecurePassword(password, salt);

        switch (v.getId()) {


            case R.id.button45:
                Intent button45 = new Intent(SecureAccountLogin.this, SecureAccount.class);

                if (password == securePassword)

            {

                startActivity(button45);
                break;
            }

            else {
                Toast.makeText(SecureAccountLogin.this, "Unable to log in. Incorrect credentials", Toast.LENGTH_LONG).show();
            }

            default:
                break;


        }



            Toast.makeText(SecureAccountLogin.this, "Unable to log in. Incorrect credentials", Toast.LENGTH_LONG).show();

        } catch (NoSuchAlgorithmException e) {
            Toast.makeText(SecureAccountLogin.this, "error in algorithm", Toast.LENGTH_LONG).show();

        } catch (NoSuchProviderException k) {
            Toast.makeText(SecureAccountLogin.this, "error in provider", Toast.LENGTH_LONG).show();

        } catch (NullPointerException n) {
            Toast.makeText(SecureAccountLogin.this, "Please enter a username and password.", Toast.LENGTH_LONG).show();
        }
    }


    private String getSecurePassword(String password, String salt) {
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(salt.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest(password.getBytes());
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    //Add salt
    private String getSalt() throws NoSuchAlgorithmException, NoSuchProviderException {
        //Always use a SecureRandom generator
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        //Create array for salt
        byte[] salt = new byte[16];
        //Get a random salt
        sr.nextBytes(salt);
        //return salt
        return salt.toString();
    }
}


/*
    This example shows how storing sensitive information locally behind an activity can be bypassed
    if the device is rooted or if the activity is exported.

    In adb shell, the service can still be started by sending a system command
    "am start -n com.example.guidepoint.obstreperous/.LocalAccountBypass.SecureAccount"

    This command will fail only if device is not rooted and the activity is not exported.

 */
