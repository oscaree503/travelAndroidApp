package com.sncss.haemtravel.Bean;

import com.sncss.haemtravel.interfaces.Item;

/**
 * Created by developer1 on 10/3/16.
 * use to hold the sectionItem
 */
public class SectionItem extends Country implements Item {

    private  String title=null;



    public SectionItem(String title) {
        super(  );
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