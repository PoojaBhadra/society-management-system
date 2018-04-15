package com.example.tejashree.societymanagementsystem.databasePhpMyadmin.userRegistration;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Pratik on 30-Sep-17.
 */

public class ConstantsUser
{
    //String address= InetAddress.getLocalHost().toString();
    //https://hoofed-can.000webhostapp.com
    private static final String ROOT_URL="https://hoofed-can.000webhostapp.com/sms/userRegistration/v1/";
    public static final String URL_REGISTER=ROOT_URL+"registerUser.php";

    public static final String  URL_LOGIN=ROOT_URL+"userLogin.php";//changed code
    public static final String URL_REGISTERCOMPLAINT=ROOT_URL+"registerComplaint.php";
    public static final String URL_GETNOTICETABLE=ROOT_URL+"getNoticeTable.php";
    public static final String URL_GETNOTICES=ROOT_URL+"getNotices.php";
    public static final String URL_SETMEMBERPROFILE=ROOT_URL+"setMemberProfile.php";
    public static final String URL_UPDATEPASSWORD=ROOT_URL+"updatePassword.php";
    public static final String URL_LEAVESOCIETY=ROOT_URL+"leaveSociety.php";



}
