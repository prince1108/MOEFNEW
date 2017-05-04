package utility;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;


/**
 * Created by Ravi on 8/26/2016.
 */
public class Util {
    public static final String PREFS_NAME = "MOEFCC";

    public static void alertMessage(Context context, String message) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setMessage(message);
        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                arg0.dismiss();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }


    public static String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }

    public static long randomNumber() {
        Random rand = new Random();
        long accumulator = 1 + rand.nextInt(9); // ensures that the 16th digit isn't 0
        for (int i = 0; i < 15; i++) {
            accumulator *= 10L;
            accumulator += rand.nextInt(10);
        }
        return accumulator;
    }

    public static long getTimeIndays() {
        String stringDate1 = "2016-08-17 03:44:14";
        String stringDate2 = "2016-08-26 03:44:14";
        Date startDate = convertStringToDate(stringDate1);// Set start date
        Date endDate = convertStringToDate(stringDate2);
        ;// Set end date
        long duration = endDate.getTime() - startDate.getTime();
        long diffInDays = TimeUnit.MILLISECONDS.toDays(duration);
        System.out.println("Duration in DAYS==" + diffInDays);
        return diffInDays;
    }

    public static Date convertStringToDate(String dateString) {
        Date myDate = null;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd h:m:s");
        try {
            myDate = df.parse(dateString);
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        return myDate;
    }


    public static void clearUserDetails(Context context) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        settings.edit().clear().commit();
        ((Activity) context).finish();
    }

    public static int getSubCategoryPosition(Context context) {
        SharedPreferences settings;
        int pos;
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE); //1
        pos = settings.getInt("SubCategoryPosition",0); //2
        return pos;
    }

    public static String getUserName(Context context) {
        SharedPreferences settings;
        String text;
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE); //1
        text = settings.getString("name", ""); //2
        return text;
    }

    public static String getUid(Context context) {
        SharedPreferences settings;
        String text;
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE); //1
        text = settings.getString("uid", ""); //2
        return text;
    }

    public static String getProfileImage(Context context) {
        SharedPreferences settings;
        String text;
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE); //1
        text = settings.getString("ProImage", ""); //2
        return text;
    }
    public static int changeLanguage(Context context) {
        SharedPreferences settings;
        int value;
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE); //1
        value = settings.getInt("Language", 1); //2
        return value;
    }

    public static int getPosition(String[] sourceArray, String value) {
        int count = sourceArray.length;
        int pos = 0;
        for (int i = 0; i < count; i++) {
            if (value.equalsIgnoreCase(sourceArray[i])) {
                pos = i;
                return pos;
            }
        }
        return pos;
    }

    public static Bitmap getclip(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();
        final Rect rect = new Rect();//new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawRect(rect, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }
}
