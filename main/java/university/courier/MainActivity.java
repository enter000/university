package university.courier;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.Button;
import android.widget.EditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // create instance
    DataBaseHelper myDb;
    EditText nameField;
    EditText loginField;
    EditText passField;
    EditText phoneField;
    EditText idField;
    Button addDataBtn;
    Button viewAllBtn;
    Button updateBtn;
    Button deleteBtn;
    Button login;
    Button signUpBtn;

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DataBaseHelper(this);

        // nameField = (EditText) findViewById(R.id.nameField);
        loginField = (EditText) findViewById(R.id.loginField);
        passField = (EditText) findViewById(R.id.passField);
        // phoneField = (EditText) findViewById(R.id.phoneField);
        addDataBtn = (Button) findViewById(R.id.signInBtn);
        // viewAllBtn = (Button) findViewById(R.id.viewBtn);
        // updateBtn = (Button) findViewById(R.id.updateBtn);
        // idField = (EditText) findViewById(R.id.idField);
        // deleteBtn = (Button) findViewById(R.id.deleteBtn);

        // test

        login = (Button) findViewById(R.id.signInBtn);
        signUpBtn = (Button) findViewById(R.id.signUpBtn);

        //

        //addData();
        // viewAllData();
        // updateData();
        // deleteData();
        switchToSignUpView();
        switchToUserInterface();

    }

    public void switchToUserInterface() {
        login.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String loginstr = loginField.getText().toString();
                        String passwordstr = passField.getText().toString();

                        String password = myDb.searchData(loginstr);
                        if (passwordstr.equals(password)) {
                            String user_role = myDb.searchRole(loginstr, password);

                            if (user_role.equals("Клиент")) {

                                Intent intent = new Intent(v.getContext(), UserInterfase.class);
                                startActivity(intent);
                                Toast.makeText(MainActivity.this, "Успешно", Toast.LENGTH_LONG).show();
                            } else if (user_role.equals("Курьер")) {

                                Intent intent = new Intent(v.getContext(), courierInterfase.class);
                                startActivity(intent);
                            }

                        } else {
                            Toast.makeText(MainActivity.this, "Данные введены неправильно", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }

    public void switchToSignUpView() {
        signUpBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(view.getContext(), SignUp.class);
                        startActivity(intent);
                    }
                }
        );
    }

    public void deleteData() {
        deleteBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int deletedRows = myDb.deleteDAta(idField.getText().toString());

                        if (deletedRows > 0) {
                            Toast.makeText(MainActivity.this, "Data deleted", Toast.LENGTH_LONG).show();
                        } else {

                        }   Toast.makeText(MainActivity.this, "there is no such id", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void updateData() {
        updateBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isUpdated = myDb.updateDataAboutUsers(idField.getText().toString(),
                                nameField.getText().toString(),
                                loginField.getText().toString(),
                                passField.getText().toString(),
                                phoneField.getText().toString());

                        if (isUpdated) {
                            Toast.makeText(MainActivity.this, "Data updated", Toast.LENGTH_LONG).show();
                        } else {

                        }   Toast.makeText(MainActivity.this, "Data is not updated", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    /*public void addData() {
        addDataBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        boolean isInserted = myDb.insertDataToUserTable(nameField.getText().toString(),
                               loginField.getText().toString(),
                               passField.getText().toString(),
                               phoneField.getText().toString());

                        nameField.setText(" ");
                        loginField.setText(" ");
                        passField.setText(" ");
                        phoneField.setText(" ");

                        if (isInserted) {
                            Toast.makeText(MainActivity.this, "Data inserted", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Data is not inserted", Toast.LENGTH_LONG).show();

                        }
                    }
                }
        );
    }
    */

    /*public void viewAllData() {
        viewAllBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor res = myDb.getAllDAtaAboutUsers();

                        if (res.getCount() == 0) {
                            showMessage("Error", "Nothing found");
                            return;
                        } else {
                            StringBuffer buffer = new StringBuffer();

                            while (res.moveToNext()) {

                                buffer.append("id :" + res.getString(0) + "\n");
                                buffer.append("name :" + res.getString(1) + "\n");
                                buffer.append("login :" + res.getString(2) + "\n");
                                buffer.append("pass :" + res.getString(3) + "\n");
                                buffer.append("phone :" + res.getString(4) + "\n\n");
                            }

                            showMessage("Data: ", buffer.toString());
                        }
                    }
                }
        );
    }
    */

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    */

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    */

}
