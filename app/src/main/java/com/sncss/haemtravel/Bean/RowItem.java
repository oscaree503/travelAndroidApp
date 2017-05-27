package com.sncss.haemtravel.Bean;

/**
 * Created by developer1 on 25/2/16.
 * used by custome base adapter
 */
public class RowItem {
    private String circle_empty;
    private String text;
    private String arrow_circle;

    public RowItem(String circle_empty, String text, String arrow_circle) {
        this.circle_empty = circle_empty;
        this.text = text;
        this.arrow_circle = arrow_circle;
    }

    public String getcircle_empty() {
        return circle_empty;
    }

    public void setcircle_empty(String circle_empty) {
        this.circle_empty = circle_empty;
    }
    public String getarrow_circle() {
        return arrow_circle;
    }
    public void setarrow_circle(String arrow_circle) {
        this.arrow_circle = arrow_circle;
    }
    public String gettext() {
        return text;
    }
    public void settext(String text) {
        this.text = text;
    }
    @Override
    public String toString() {return circle_empty + "\n"+text + "\n" + arrow_circle;}
}
