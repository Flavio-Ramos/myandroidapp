package luiz.appminhaideia.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import luiz.appminhaideia.R;
import luiz.appminhaideia.controller.ClienteController;
import luiz.appminhaideia.core.AppUtil;
import luiz.appminhaideia.model.Cliente;

public class SplashActivity extends AppCompatActivity {

    int TempoDeEspera = 1000 * 3;
    Cliente objCliente;
    ClienteController clienteController;
    TextView textAppVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        clienteController = new ClienteController();
        textAppVersion = findViewById(R.id.textAppVersion);
        textAppVersion.setText(AppUtil.versaoDoAplicativo());
        Log.d(AppUtil.TAG, "onCreate: Tela Splash Carregada.....");
        trocarTela();

    }

    private void trocarTela() {

        Log.d(AppUtil.TAG, "trocarTela: Mudando de tela");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                objCliente = new Cliente(
                        "Juliana",
                        "juju@gmail.com",
                        "(011) 9 8745-6123",
                        25,false
                );

                Log.d(AppUtil.TAG, "trocarTela: Aguardando tempo");
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