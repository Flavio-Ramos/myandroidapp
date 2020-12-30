package luiz.appminhaideia.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import luiz.appminhaideia.R;
import luiz.appminhaideia.api.AppUtil;
import luiz.appminhaideia.controller.ClienteController;
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

        clienteController = new ClienteController(getBaseContext());
        textAppVersion = findViewById(R.id.textAppVersion);
        //textAppVersion.setText(AppUtil.versaoDoAplicativo());corrigir depois
        textAppVersion.setText("Corrigir versão");
        Log.d(AppUtil.TAG, "onCreate: Tela Splash Carregada.....");
        trocarTela();

    }

    private void trocarTela() {

        Log.d(AppUtil.TAG, "trocarTela: Mudando de tela");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                objCliente = new Cliente();
                objCliente.setNome("Juliana");
                objCliente.setEmail("juliana@juliana");

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