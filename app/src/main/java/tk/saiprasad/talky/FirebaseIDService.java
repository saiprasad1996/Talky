package tk.saiprasad.talky;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by sai on 22/3/17.
 */

public class FirebaseIDService extends FirebaseInstanceIdService {

    private static final String TAG="FirebaseIDService";
    @Override
    public void onTokenRefresh() {
        String refreshedToken= FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG,"Refreshed token : "+refreshedToken);

        //sendRegistrationToServer(refreshedToken);
    }
    private void sendRegistrationToServer(String token){
        OkHttpClient  client=new OkHttpClient();
        RequestBody body=new FormBody.Builder()
                .add("Token",token)
                .build();
        Request request=new Request.Builder()
                .url("http://119.9.94.86:8000/rest/mobv01/fcmregister")
                .post(body)
                .build();
        try{
            client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
