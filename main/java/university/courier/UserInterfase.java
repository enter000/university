package university.courier;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class UserInterfase extends AppCompatActivity {
    TextView wichRole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_interfase);

        wichRole = (TextView) findViewById(R.id.whichRole);
    }
}
