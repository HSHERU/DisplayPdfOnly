package displaypdfonly.abdulrahmanaldehan.com.displaypdfonly;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.util.Date.parse;

/**
 * Created by Abdulrahman on 04-12-15.
 */
public class comment_object
{
    public String comment;
    //comment object id
    public String obj_id;
    public String date;
    public String username;
    public String Ayah_number;
    public comment_object(String comment,String obj_id,String comment_date,String username , String Ayah_number) {
        this.comment = comment;
        this.obj_id = obj_id;
        /**
        SimpleDateFormat format = new SimpleDateFormat("MMM dd, yyyy, hh:mm");
        Date newDate = null;
        try {
            newDate = format.parse(comment_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        format = new SimpleDateFormat("MMM dd,yyyy");

         this.date = format.format(newDate);
         */
        this.Ayah_number = Ayah_number;
        this.date = comment_date;
        this.username = username;
    }

}
