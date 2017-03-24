package tk.saiprasad.talky;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class TalkyMessagingService extends FirebaseMessagingService {
    private static final String TAG="FCM Service";



    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.d(TAG,"From : "+remoteMessage.getFrom());
        Log.d(TAG,"Notification Message body: "+remoteMessage.getNotification());
        showNotification(remoteMessage.getData().get("message"));
    }
    private void showNotification(String message){
        Intent i=new Intent(this,MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,i,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this)
                .setAutoCancel(true)
                .setContentTitle("FCM Test")
                .setSmallIcon(android.R.drawable.ic_menu_more)
                .setContentIntent(pendingIntent);
        NotificationManager manager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        manager.notify(0,builder.build());
    }
}
