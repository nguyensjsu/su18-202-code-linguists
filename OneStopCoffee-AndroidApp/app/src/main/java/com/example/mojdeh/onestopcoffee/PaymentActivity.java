package com.example.mojdeh.onestopcoffee;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PaymentActivity extends AppCompatActivity {
    Button postPay ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        postPay=(Button) findViewById(R.id.postPay);
        postPay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                volleyCall();

            }
        });

    }
    private void volleyCall (){
       final TextView payTxtView = (TextView) findViewById(R.id.textView);
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://10.0.2.2:8080/payment";
        Map<String , String> jsonObj = new HashMap<>();
        jsonObj.put( "cardNo" , "123456789");
        jsonObj.put( "cvv" , "123");
        jsonObj.put( "amount" , "10");
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST , url , new JSONObject(jsonObj),new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.i("PaymentActivity", response.toString());
                payTxtView.setText(response.toString());
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error:",""+error.getMessage());

                    }
                });
        queue.add(request);
    }
}
