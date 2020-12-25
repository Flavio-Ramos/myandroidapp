package luiz.appminhaideia.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import luiz.appminhaideia.R;
import luiz.appminhaideia.model.Cliente;

public class SplashActivity extends AppCompatActivity {

    String TAG = "APP_MINHA_IDEIA";
    int TempoDeEspera = 1000 * 5;
    Cliente objCliente;

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
                objCliente = new Cliente(
                        "Juliana",
                        "juju@gmail.com",
                        "(011) 9 8745-6123",
                        25,false
                );

                Log.d(TAG, "trocarTela: Aguardando tempo");
                Intent TrocarDeTela = new Intent(SplashActivity.this, MainActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString("nome",objCliente.getNome());
                bundle.putString("email",objCliente.getEmail());

                TrocarDeTela.putExtras(bundle);

                startActivity(TrocarDeTela);
                finish();
            }
        },TempoDeEspera);
    }

}