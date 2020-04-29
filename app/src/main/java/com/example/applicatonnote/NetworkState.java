package com.example.applicatonnote;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;


public class NetworkState {
    public static boolean estConnecte(Context context)
    {
// récupération du manager :
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
// récupération de l'état de la connexion :
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null)
        {
            return networkInfo.isConnected();
        }
        return false;
    }

    public static void callRestApi(String textMemo, final Context context){
        if(estConnecte(context)){
            AsyncHttpClient client = new AsyncHttpClient();
            RequestParams requestParams = new RequestParams();
            requestParams.put("parametre", textMemo);
            client.post("http://httpbin.org/post", requestParams, new AsyncHttpResponseHandler()
            {
                @Override
                public void onSuccess(int statusCode, Header[] headers,
                                      byte[] response)
                {
                    // retour du webservice :
                    String retour = new String(response);
                    Toast.makeText(context,retour,Toast.LENGTH_LONG).show();

                }
                @Override
                public void onFailure(int statusCode, Header[] headers,
                                      byte[] errorResponse, Throwable e)
                {
                    Log.e("NetworkCall", e.toString());
                }
            });
        }else{

        }

    }
}
