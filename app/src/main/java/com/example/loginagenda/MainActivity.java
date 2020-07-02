package com.example.loginagenda;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {


    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView registrotexto = (TextView) findViewById(R.id.registrotextid);

        registrotexto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Registro.class);
                MainActivity.this.startActivity(intent);

            }
        });

        final EditText userlogin = (EditText) findViewById(R.id.textlogin);
        final EditText passlogin = (EditText) findViewById(R.id.passwordlogin);
        btn1 = (Button) findViewById(R.id.btnlogin);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String usuario = userlogin.getText().toString();
                final String clave = passlogin.getText().toString();

                Response.Listener<String> respuesta = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonrespuesta = new JSONObject(response);
                            boolean ok = jsonrespuesta.getBoolean("success");
                            if (ok==true){
                                String nombre = jsonrespuesta.getString("nombre");
                                int edad = jsonrespuesta.getInt("edad");

                                Intent bienvenido = new Intent(MainActivity.this, MainActivity2.class);
                                bienvenido.putExtra("nombre",nombre);
                                bienvenido.putExtra("edad",edad);

                                MainActivity.this.startActivity(bienvenido);
                                MainActivity.this.finish();

                            }else{
                                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                //AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
                                //alerta.setMessage("Usuario o Contraseña erronea").setNegativeButton("Reintentar", null.create().show();
                            }
                        }catch (JSONException e){
                            e.getMessage();
                        }
                    }
                };
                LoginRequest r = new LoginRequest(usuario,clave, respuesta);
                RequestQueue cola = Volley.newRequestQueue(MainActivity.this);
                cola.add(r);
            }
        });


    }
}