package br.com.giulia.webservicetcc.webservices;

import android.app.DownloadManager;
import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


import br.com.giulia.webservicetcc.webservices.content.AppSquidexInfo;
import br.com.giulia.webservicetcc.webservices.content.Token;


public class WebServiceControle {
    private static RequestQueue requestQueue;
    private static Token token;

    public RequestQueue getRequestQueueInstance(Context context) {
        if (requestQueue == null)
            requestQueue = Volley.newRequestQueue(context);
        return requestQueue;
    }

    private void geraToken(Context context, final GeraTokenListener geraTokenListener) {
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                "https://cloud.squidex.io/identity-server/connect/token",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        token = new Gson().fromJson(response, Token.class);
                        if (geraTokenListener != null)
                            geraTokenListener.onTokenOk();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (geraTokenListener != null)
                            geraTokenListener.onErro();
                    }
                }
        ) {
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded";
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("grant_type", "client_credentials");
                params.put("client_id", "tccviolenciamulheres:androidclient");
                params.put("client_secret", "8cnM/1Vhq6jobjoRZ0ONtz/a1RTtr5ijxwk7dylRWMI=");
                params.put("scope", "squidex-api");
                return params;
            }
        };
        //
        getRequestQueueInstance(context).add(stringRequest);
    }


    public interface GeraTokenListener {
        public abstract void onTokenOk();

        public abstract void onErro();
    }

    public void carregaListaServicos(final Context context, final CarregaListaServicosListener carregaListaServicosListener) {
        if (token == null) {
            geraToken(context, new GeraTokenListener() {
                @Override
                public void onTokenOk() {
                    carregaListaServicos(context, carregaListaServicosListener);
                }

                @Override
                public void onErro() {
                    if (carregaListaServicosListener != null)
                        carregaListaServicosListener.onErro();
                }
            });
        } else {
            StringRequest stringRequest =
                    new StringRequest(Request.Method.GET,
                            "https://cloud.squidex.io/api/content/tccviolenciamulheres/servicos"
                            , new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            carregaListaServicosListener.onSucesso(new Gson().fromJson(response, AppSquidexInfo.class));
                        }
                    }
                            , new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }) {
                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            HashMap<String, String> headers = new HashMap<>();
                            headers.put("Authorization", token.getToken_type() + " " + token.getAccess_token());
                            return headers;
                        }
                    };
            //
            getRequestQueueInstance(context).add(stringRequest);
        }

    }

}
