package com.example.mtoto_app;

public class ModeClass {
    int today_uzito,today_urefu;

    public ModeClass(int today_uzito, int today_urefu) {
        this.today_uzito = today_uzito;
        this.today_urefu = today_urefu;
    }

    @Override
    public String toString() {
        return "ModeClass{" +
                "today_uzito=" + today_uzito +
                ", today_urefu=" + today_urefu +
                '}';
    }

    public int getToday_uzito() {
        return today_uzito;
    }

    public void setToday_uzito(int today_uzito) {
        this.today_uzito = today_uzito;
    }

    public int getToday_urefu() {
        return today_urefu;
    }

    public void setToday_urefu(int today_urefu) {
        this.today_urefu = today_urefu;
    }
}
