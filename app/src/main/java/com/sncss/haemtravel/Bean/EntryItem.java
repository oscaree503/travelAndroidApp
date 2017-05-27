package com.sncss.haemtravel.Bean;

import com.sncss.haemtravel.interfaces.Item;

/**
 * Created by developer1 on 10/3/16.
 * Usese by country adapter to ge the country name and code
 */
public class EntryItem implements Item {

    public final String title;
    public final String images;
    public final String countryCode;
    /*public final String flag_ref;*/

    public EntryItem(String title, String images, String countryCode/*, String flag_ref*/) {
        this.title = title;
        this.images = images;
        this.countryCode = countryCode;
       /* this.flag_ref = flag_ref;*/
    }

    public String getTitle() {
        return title;
    }

    public String getImages() {
        return images;
    }

    @Override
    public boolean isSection() {
        return false;
    }

}
