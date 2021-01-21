package jerry.build.pdfcreator.pdf.content.util;

import android.util.Log;

import androidx.annotation.Size;


public class Color extends android.graphics.Color {

    public static int parseColorWithAlpha(@Size(min=1) String color, @Size(min=0,max=100) double alpha) {
        color = "#"+getAlpha(alpha)+color.substring(1);
        Log.e("parseColorWithAlpha", "parseColorWithAlpha: "+color );
        return parseColor(color);
    }

    private static String getAlpha(double value) {
        value = Math.ceil((value/100)*255);
        int n = Double.valueOf(value).intValue();
        if(n<10){
            String hex= Integer.toHexString(n);
            return "0"+hex;
        }else{
            return Integer.toHexString(n);
        }
    }
}
