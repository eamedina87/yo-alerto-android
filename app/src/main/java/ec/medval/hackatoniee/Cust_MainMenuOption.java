package ec.medval.hackatoniee;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Supertel on 11/3/15.
 */
public class Cust_MainMenuOption extends LinearLayout {

    private ImageView image;
    private TextView title;

    public Cust_MainMenuOption(Context context) {
        super(context);
        setOrientation(HORIZONTAL);
        LayoutInflater.from(context).inflate(R.layout.mainmenu_option, this, true);
        this.image = (ImageView)findViewById(R.id.image);
        try{
            this.title = (TextView)findViewById(R.id.title);
        }
        catch (NullPointerException e)
        {

        }

    }

    public Cust_MainMenuOption(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.mainmenu_option, this, true);
        this.image = (ImageView)findViewById(R.id.image);
        try{
            this.title = (TextView)findViewById(R.id.title);
        }
        catch (NullPointerException e)
        {

        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public Cust_MainMenuOption(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.mainmenu_option, this, true);
        this.image = (ImageView)findViewById(R.id.image);
        try{
            this.title = (TextView)findViewById(R.id.title);
        }
        catch (NullPointerException e)
        {

        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public Cust_MainMenuOption(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        LayoutInflater.from(context).inflate(R.layout.mainmenu_option, this, true);
        this.image = (ImageView)findViewById(R.id.image);
        try{
            this.title = (TextView)findViewById(R.id.title);
        }
        catch (NullPointerException e)
        {

        }
    }

    public Drawable getImageDrawable() {
        return image.getDrawable();
    }

    public void setImageDrawable(Drawable image) {
        this.image.setImageDrawable(image);
    }

    public String getTitleString() {
        return title.getText().toString();
    }

    public void setTitleString(String title) {
        this.title.setText(title);
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public void setTitle(TextView title) {
        this.title = title;
    }
}
