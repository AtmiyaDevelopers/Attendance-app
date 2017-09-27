package com.example.jigar.excels.networking;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Map;

/**
 * Created by JIGAR on 9/26/2017.
 */

public class VolleyNetwork {
    Context context;
    RequestQueue requestQueue;
    ProgressDialog progressDialog;
    public VolleyNetwork(Context context) {
        this.context = context;
        requestQueue=Volley.newRequestQueue(context);
        progressDialog=new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Plz Wait....");
    }
    public void sendtoServer(String url, final Map<String,String> map){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(context, "Check Your Internet Connection", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                if(map==null) {
                    return super.getParams();
                }
                return map;
            }
        };
        requestQueue.add(stringRequest);
        return ;
    }
}
