package university.courier;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class SignUp  extends Activity {
    EditText name;
    EditText login;
    EditText password;
    EditText phone;
    EditText role;
    Button signUpBtn;
    Switch switchRole;
    public boolean checkSwitch;

    DataBaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name = (EditText) findViewById(R.id.nameField);
        login = (EditText) findViewById(R.id.loginField);
        password = (EditText) findViewById(R.id.passField);
        phone = (EditText) findViewById(R.id.phoneField);
        // role = (EditText) findViewById(R.id.roleField);
        signUpBtn = (Button) findViewById(R.id.signUpBtn);
        switchRole = (Switch) findViewById(R.id.switchRole);

        db = new DataBaseHelper(this);
        onSignUpBtnClick();

    }

    public boolean setRole() {

        switchRole.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkSwitch = true;
                } else {
                    checkSwitch = false;
                }
            }
        });

        return checkSwitch;
    }

    public void onSignUpBtnClick() {

        signUpBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String namestr = name.getText().toString();
                        String loginstr = login.getText().toString();
                        String passwordstr = password.getText().toString();
                        String phonestr = phone.getText().toString();
                        // String rolestr = role.getText().toString();

                        String rolestr = setRole() ? "Клиент" : "Курьер";

                        User chelovek = new User(namestr, loginstr, passwordstr, phonestr, rolestr);

                        /*user.setName(namestr);
                        user.setLogin(loginstr);
                        user.setPassword(passwordstr);
                        user.setPhone(phonestr);
                        user.setRole(rolestr);
                        */

                        if (!(namestr.trim().length() == 0) && !(loginstr.trim().length() == 0) && !(passwordstr.trim().length() == 0) && !(phonestr.equals(" ")) && !(rolestr.trim().length() == 0)) {

                            boolean isInserted = db.insertUser(chelovek);

                            /*boolean isInserted = db.insertDataToUserTable(user.getName(),
                                    user.getLogin(),
                                    user.getPassword(),
                                    user.getPhone(),
                                    user.getRole());
                            */

                            if (isInserted) {
                                name.setText(" ");
                                login.setText(" ");
                                password.setText(" ");
                                phone.setText(" ");
                               //  role.setText(" ");

                                Toast.makeText(SignUp.this, "Data inserted", Toast.LENGTH_LONG).show();

                            } else {
                                Toast.makeText(SignUp.this, "123", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(SignUp.this, "Пожалуйста, заполните все поля", Toast.LENGTH_LONG).show();
                        }
                    }

                }
        );

    }
}
