package com.example.loginagenda;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Registro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        final EditText nombret      = (EditText) findViewById(R.id.editname);
        final EditText usuariot     = (EditText) findViewById(R.id.editusuario);
        final EditText clavet       = (EditText) findViewById(R.id.editclave);
        final EditText edadt        = (EditText) findViewById(R.id.editedad);
        Button   btnRegistro = (Button) findViewById(R.id.btnregistro);

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = nombret.getText().toString();
                String usuario = usuariot.getText().toString();
                String clave = clavet.getText().toString();
                int edad = Integer.parseInt(edadt.getText().toString());

                Response.Listener<String> respuesta = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonrespuesta = new JSONObject(response);
                            boolean ok = jsonrespuesta.getBoolean("success");
                            if (ok == true){
                                Intent i = new Intent(Registro.this, MainActivity.class);
                                Registro.this.startActivity(i);
                                Registro.this.finish();
                            }else{
                                AlertDialog.Builder alerta = new AlertDialog.Builder(Registro.this);
                                alerta.setMessage("Fallo en el Registro")
                                        .setNegativeButton("Reintentar",null)
                                        .create()
                                        .show();
                            }
                        }catch (JSONException e){
                            e.getMessage();
                        }
                    }
                };

                RegistroRequest r = new RegistroRequest(nombre, usuario,clave,edad,respuesta);
                RequestQueue cola = Volley.newRequestQueue(Registro.this);
                cola.add(r);

            }
        });

    }
}