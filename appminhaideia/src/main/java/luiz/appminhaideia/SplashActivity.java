package luiz.appminhaideia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class SplashActivity extends AppCompatActivity {

    String TAG = "APP_MINHA_IDEIA";
    int TempoDeEspera = 1000 * 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Log.d(TAG, "onCreate: Tela Splash Carregada.....");
        trocarTela();
    }

    private void trocarTela() {

        Log.d(TAG, "trocarTela: Mudando de tela");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "trocarTela: Aguardando tempo");
                Intent TrocarDeTela = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(TrocarDeTela);
                finish();
            }
        },TempoDeEspera);
    }

}