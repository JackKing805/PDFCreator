package jerry.build.pdfcreator.pdf.content.util;

import androidx.annotation.Size;


public class Color extends android.graphics.Color {

    public static int parseColorWithAlpha(@Size(min = 1) String color, float alpha) {
        if(alpha<0 || alpha>1){
            throw new IllegalStateException("can't set alpha Less than 0 or more than 1");
        }
        alpha = alpha*100;

        color = "#" + getAlpha(alpha) + color.substring(1);
        return parseColor(color);
    }

    private static String getAlpha(float value) {
        value = Double.valueOf( Math.ceil((value / 100) * 255)).floatValue();
        int n = Float.valueOf(value).intValue();
        if (n < 10) {
            String hex = Integer.toHexString(n);
            return "0" + hex;
        } else {
            return Integer.toHexString(n);
        }
    }
}
