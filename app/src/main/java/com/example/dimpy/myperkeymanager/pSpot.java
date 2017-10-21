package com.example.dimpy.myperkeymanager;

/**
 * Created by dimpy on 22/10/17.
 */

public class pSpot {
    private String id;
    private String status;
    private String time;

    pSpot(String one, String two, String three) {
        id = one;
        status = two;
        time = three;
    }

    pSpot(String one, String two) {
        id = one;
        status = two;
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
}
