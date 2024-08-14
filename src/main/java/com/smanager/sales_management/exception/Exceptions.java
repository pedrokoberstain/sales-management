package com.smanager.sales_management.exception;

public class Exceptions {

    private String msUser;
    private String msDev;

    public Exceptions(String msUser, String msDev) {
        this.msUser = msUser;
        this.msDev = msDev;
    }

    public String getMsUser() {
        return msUser;
    }

    public String getMsDev() {
        return msDev;
    }
}
