package com.example.tejashree.societymanagementsystem.gui.memberGUI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tejashree.societymanagementsystem.R;
import com.example.tejashree.societymanagementsystem.emailVerification.SendMail;

import java.util.Random;

public class CreateNewMemberActivity extends AppCompatActivity {

    Button b;

    public static EditText firstName;
    public static EditText lastName;
    public static EditText email;
    public static EditText pass,confirmPass;
    public static EditText aadharNo;
    public static EditText mobileNo;



    int randomNo=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_society_page2);
        b= (Button) findViewById(R.id.nextPage2Button);
        email= (EditText) findViewById(R.id.emailID);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                firstName= (EditText) findViewById(R.id.firstNameID);
                lastName= (EditText) findViewById(R.id.lastNameID);
                email= (EditText) findViewById(R.id.emailID);
                pass= (EditText) findViewById(R.id.passID);
                confirmPass= (EditText) findViewById(R.id.conPassID);
                aadharNo= (EditText) findViewById(R.id.aadharNoID);
                mobileNo= (EditText) findViewById(R.id.mobileNoID);

                if(aadharNo.length()!=12)
                {
                    Toast.makeText(CreateNewMemberActivity.this, "Aadhar length cannot be less than 12", Toast.LENGTH_SHORT).show();
                }

                else if(verifyEmail() && verifyPassword())
                {
                    //code for sending mail
                    sendMail();
                    Intent intent =new Intent(CreateNewMemberActivity.this,OTPMailVerifierUserActivity.class);
                    intent.putExtra("otp",Integer.toString(randomNo));//send otp
                    startActivity(intent);
                }


            }
        });

    }
    private boolean verifyPassword()
    {
        if(!pass.getText().toString().equals(confirmPass.getText().toString()))
        {
            Toast.makeText(CreateNewMemberActivity.this, "Password did not match", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    private boolean verifyEmail() {
        String mail=email.getText().toString();
        boolean flag = false;//if flag is false then mail is faulty else correct
        //1 length of mail should be at least 3 characters
        int len = mail.length();

            if (len < 3)
            {

                flag = false;
                Toast.makeText(this, "Length should be at least 3", Toast.LENGTH_SHORT).show();
                return  flag;

            }
            else
                flag = true;



//            //2 check whether @ character is present
//            //3 check whether only one @ character is present
               int count = 0;
//
//
            for (int i = 0; i < mail.length(); i++)
            {
                char ch = mail.charAt(i);
                if (ch == '@')
                    count++;
            }
            if (count == 1)//only one @ is present
            {
                flag = true;
            }
            else if (count>1)
            {

                flag = false;
                Toast.makeText(this, "Only one @should be present", Toast.LENGTH_SHORT).show();
                return flag;
            }
            else
            {
                flag=false;
                Toast.makeText(this, "@ should be present", Toast.LENGTH_SHORT).show();
                return flag;
            }


//
//
//            //4 first character should not be .
            if (mail.charAt(0) == '.')
            {
                flag = false;
                Toast.makeText(this, "First character should not be .", Toast.LENGTH_SHORT).show();
            }
            else
            {
                flag = true;
            }
//
//
//            //5 email should not contain .. character
//
            for (int i = 0; i < mail.length() - 1; i++)
            {
                if (mail.charAt(i) == '.' && mail.charAt(i + 1) == '.')
                {
                    flag = false;
                    Toast.makeText(this, "Email should not contain .. character", Toast.LENGTH_SHORT).show();
                    return false;
                }

            }
            flag = true;
//
//
//            //7 email should contain domain name
//            System.out.println(mail);
            if (mail.endsWith(".com"))
            {
                flag = true;
            }
            else
            {
                flag = false;
                Toast.makeText(this, "Domain Name not present", Toast.LENGTH_SHORT).show();
                return false;
            }
//
//
//            //6 email should not contain character like \ " , ( ) < > \  / : ; '
//
            String special = "()<>:;,/&?$%*{}[]";//array of special characters
//
            char element;
            for (int i = 0; i < mail.length(); i++)
            {
                element = mail.charAt(i);
                for (int j = 0; j < special.length(); j++)
                {
                    if (special.charAt(j) == element)//special condition found
                    {
                        flag = false;
                        Toast.makeText(this, "Special characters not allowed in emailId", Toast.LENGTH_SHORT).show();
                        return flag;
                    }
                }
            }
            flag = true;

        return flag;
        }

        public void sendMail()
        {
            String email1 = email.getText().toString().trim();
            String subject = "Verification of email ID";

            Random r = new Random();
            randomNo = r.nextInt(9999 - 1000) + 1000;
            String message = "Your OTP is "+Integer.toString(randomNo);
            Toast.makeText(this, Integer.toString(randomNo), Toast.LENGTH_SHORT).show();
            //Creating SendMail object
            SendMail sm = new SendMail(this, email1, subject, message);//UNCOMMENT THIS LINE AFTERWARDS

            //Executing sendmail to send email
            sm.execute();//UNCOMMENT THIS LINE AFTERWARDS
        }

}