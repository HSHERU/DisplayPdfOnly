package displaypdfonly.abdulrahmanaldehan.com.displaypdfonly;

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

    public comment_object(String comment,String obj_id,String date,String username)
    {
        this.comment = comment;
        this.obj_id = obj_id;
        this.date = date;
        this.username = username;
    }

}
