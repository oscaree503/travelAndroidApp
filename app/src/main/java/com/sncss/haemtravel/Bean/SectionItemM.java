package com.sncss.haemtravel.Bean;

import com.sncss.haemtravel.interfaces.Item;

/**
 * Created by developer1 on 10/3/16.
 * use to hold the section Item on meinFavorites activity
 */
public class SectionItemM extends MeinFavorites implements Item {

    private  String title=null;



    public SectionItemM(String title) {
        super();
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    @Override
    public boolean isSection() {
        return true;
    }

}