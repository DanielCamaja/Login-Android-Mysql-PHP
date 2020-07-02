package com.example.loginagenda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.loginagenda.Adaptadores.AdapterAgendaSil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AgendaSilver extends AppCompatActivity {

    private static final String URL_Agenda = "https://pruebashostdaniel.000webhostapp.com/agenda.php";
    List<DatosAgenda> mData;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda_silver);

        recyclerView =  findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mData = new ArrayList<>();

        loadAgenda();
    }

    private void loadAgenda() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_Agenda,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject agendasil = array.getJSONObject(i);

                                mData.add(new DatosAgenda(
                                        agendasil.getInt("id"),
                                        agendasil.getString("hora"),
                                        agendasil.getString("names"),
                                        agendasil.getString("info"),
                                        agendasil.getString("image")
                                ));
                                //AdapterAgendaSil adapterAgendaSil = new AdapterAgendaSil(AgendaSilver.this, mData);
                                //recyclerView.setAdapter(adapterAgendaSil);
                            }
                            AdapterAgendaSil adapterAgendaSil = new AdapterAgendaSil(AgendaSilver.this,mData);
                            recyclerView.setAdapter(adapterAgendaSil);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AgendaSilver.this,error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        Volley.newRequestQueue(this).add(stringRequest);

    }
}