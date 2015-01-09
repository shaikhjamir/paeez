package com.paeez.rest.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Shrikant on 1/9/15.
 */
public class Utilities {

    public static Date stringToDate(String strDate) {
        // Convert String to Date
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
            date = sdf.parse(strDate);

        } catch(ParseException e) {
            //TODO log this
        }

        return date;

    }

}
