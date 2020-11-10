package id.ac.id.telkomuniversity.tass.praktikactivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText namaAnda;
    Button buttonPindah;

    private String KEY_NAME = "NAMA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText namaAnda = findViewById(R.id.edt_nama);
        Button btnPindahHalaman = findViewById(R.id.btnPindahHalaman);

        btnPindahHalaman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(namaAnda.getText().toString().length() ==0){
                    Toast.makeText(MainActivity.this,
                            "Input tidak boleh kosong",Toast.LENGTH_LONG).show();
                }
                else {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                    builder.setTitle("Pindah Activity Yuk");
                    builder.setMessage("Yakin nih ingin pindah? :))");
                    builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            int NOTIFICATION_ID = 188;
                            String CHANNEL_ID = "Allysa's channel";
                            Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                            intent.putExtra("Text", namaAnda.getText().toString());
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | intent.FLAG_ACTIVITY_CLEAR_TASK);
                            PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this,0,intent,0);

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                                CharSequence name           = CHANNEL_ID;
                                String description          = CHANNEL_ID;
                                int importance              = NotificationManager.IMPORTANCE_DEFAULT;

                                NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
                                channel.setDescription(description);
                                NotificationManager notificationManager = getSystemService(NotificationManager.class);
                                notificationManager.createNotificationChannel(channel);
                            }

                            NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID);
                            builder.setSmallIcon(R.drawable.logo_star);
                            builder.setContentTitle("Pindah Activity Yuk");
                            builder.setContentText("Datamu berhasil terkirim");
                            builder.setStyle(new NotificationCompat.BigTextStyle()
                                    .bigText("Berhasil pindah ke SecoundActivity"));
                            builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
                            builder.setContentIntent(pendingIntent);

                            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(MainActivity.this);

                            notificationManager.notify(NOTIFICATION_ID, builder.build());
                            startActivity(intent);
                        }
                    });
                    builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MainActivity.this, "Tidak jadi pindah Ke halaman berikutnya :(", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.show();


                }
            }
        });

    }




}