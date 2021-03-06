package com.vinh.dictionary_1.data.source.local;

import android.arch.persistence.room.TypeConverter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * Created by VinhTL on 27/10/2017.
 */

public class DictTypeConverter {
    private static final String META = "<META http-equiv=\"Content-Type\" content=\"text/html;"
            + " charset=UTF-8\">\n<link rel=\"Stylesheet\" type=\"text/css\" href=\"dic.css\">";

    @TypeConverter
    public static String fromBlob(byte[] blob) {
        if (blob == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append(META).append(new String(blob, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    @TypeConverter
    public static byte[] fromString(String string) {
        if (string != null) {
            return string.getBytes(Charset.forName("UTF-8"));
        }
        return null;
    }
}
