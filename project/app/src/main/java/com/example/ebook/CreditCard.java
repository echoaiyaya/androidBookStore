package com.example.ebook;

public class CreditCard {
    private String creditNum;
    private String creditDate;
    private String creditCvv;

    public String getCreditNum() {
        return creditNum;
    }

    public void setCreditNum(String creditNum) {
        this.creditNum = creditNum;
    }

    public String getCreditDate() {
        return creditDate;
    }

    public void setCreditDate(String creditDate) {
        this.creditDate = creditDate;
    }

    public String getCreditCvv() {
        return creditCvv;
    }

    public void setCreditCvv(String creditCvv) {
        this.creditCvv = creditCvv;
    }

    public CreditCard(String creditNum, String creditDate, String creditCvv) {
        this.creditNum = creditNum;
        this.creditDate = creditDate;
        this.creditCvv = creditCvv;
    }
}
