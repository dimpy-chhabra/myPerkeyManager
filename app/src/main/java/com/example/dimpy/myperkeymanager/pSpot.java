package com.example.dimpy.myperkeymanager;

/**
 * Created by dimpy on 22/10/17.
 */

public class pSpot {
    private String id;
    private String status;  //SPRY_BIT
    private String time;
    private String trans_id;
    private String usr_num;
    private String usr_name;
    private String usr_email;
    private String usr_adress;


    pSpot(String one, String two, String three) {
        id = one;
        status = two;
        time = three;
    }

    pSpot(String one, String two) {
        id = one;
        status = two;
    }

    pSpot(String park_id, String transid, String spry, String num, String name, String email, String adress) {
        id = park_id;
        trans_id = transid;
        if (transid.equals("") || transid.equalsIgnoreCase("") || transid.isEmpty()
                || trans_id.equals("") || trans_id.equalsIgnoreCase("") || trans_id.isEmpty()
                || transid.equals("NULL") || transid.equalsIgnoreCase("Null")
                || transid.equals(null) || trans_id.equalsIgnoreCase(null)) {
            status = "0";
            usr_num = "0";
            usr_name = "0";
            usr_adress = "0";
            usr_email = "0";
            time = "12:09";
        } else {
            status = spry;
            usr_num = num;
            usr_name = name;
            usr_adress = adress;
            usr_email = email;
            time = "12:09";
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUsr_num() {
        return usr_num;
    }

    public void setUsr_num(String usr_num) {
        this.usr_num = usr_num;
    }

    public String getUsr_name() {
        return usr_name;
    }

    public void setUsr_name(String usr_name) {
        this.usr_name = usr_name;
    }

    public String getUsr_email() {
        return usr_email;
    }

    public void setUsr_email(String usr_email) {
        this.usr_email = usr_email;
    }

    public String getUsr_adress() {
        return usr_adress;
    }

    public void setUsr_adress(String usr_adress) {
        this.usr_adress = usr_adress;
    }
}
