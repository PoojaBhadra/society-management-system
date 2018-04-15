package com.example.tejashree.societymanagementsystem.databasePhpMyadmin.societyRegistration;

/**
 * Created by Pratik on 30-Sep-17.
 */

public class ConstantsSociety {
    //https://hoofed-can.000webhostapp.com
    private static final String ROOT_URL = "https://hoofed-can.000webhostapp.com/sms/registerSociety/v1/";
    public static final String URL_REGISTER = ROOT_URL + "registerSociety.php";
    public static final String URL_GETSOCIETYNAMES=ROOT_URL+"dynamicSocietyNameRetrieval.php";
    public static final String URL_GETINCOMINGREQUESTS=ROOT_URL+"getIncomingRequests.php";
    public static final String URL_ADDMEMBERTOSOCIETY=ROOT_URL+"addMemberToSociety.php";
    public static final String URL_GETCOMPLAINTTABLENAME=ROOT_URL+"getComplaintTableName.php";
    public static final String URL_GETCOMPLAINTSENDERNAME=ROOT_URL+"getComplaintSender.php";
    public static final String URL_GETCOMPLAINTSUBJECTNAME=ROOT_URL+"getComplaintSubject.php";
    public static final String URL_GETCOMPLAINTBODYNAME=ROOT_URL+"getComplaintBody.php";
    public static final String URL_MARKCOMPLAINBODYNAME=ROOT_URL+"markComplaintAsRead.php";
    public static final String URL_SENDNOTICE=ROOT_URL+"sendNotice.php";
    public static final String URL_ADMINPROFILE=ROOT_URL+"getAdminProfile.php";
    public static final String URL_ADMINCHANGEPASSWORD=ROOT_URL+"updatePasswordAdmin.php";
    public static final String URL_POPULATEMEMBERS=ROOT_URL+"populateMembers.php";
    public static final String URL_REMOVEMEMBERS=ROOT_URL+"removeMember.php";
}