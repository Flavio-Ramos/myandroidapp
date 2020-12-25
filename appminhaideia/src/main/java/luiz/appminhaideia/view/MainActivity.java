package luiz.appminhaideia.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import luiz.appminhaideia.R;
import luiz.appminhaideia.model.Cliente;

public class MainActivity extends AppCompatActivity {

    String TAG = "APP_MINHA_IDEIA";
    TextView textNome;
    //@SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bundle = getIntent().getExtras();
        Log.d(TAG,"Nome: " + bundle.getString("nome"));
        Log.d(TAG,"Email: " + bundle.getString("email"));

        textNome = findViewById(R.id.textNome);
        textNome.setText("Seja bem vindo(a) " + bundle.getString("nome"));
        //rodarMinhaideia();
        //rodarPrimeiroNivelamento();
        //rodarBrawserFake();
    }

    private void rodarBrawserFake(){
        /*
        WebView webView = findViewById(R.id.webview);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.loadUrl("https://www.marcomaddo.com.br");
         */
    }

    private void rodarMinhaideia(){
        Log.d(TAG, "onCreate: Tela Main Carregada...");
    }

    private void rodarPrimeiroNivelamento(){
        Cliente objCliente;
        objCliente = new Cliente("Maria","maria@maria","(011) 9876-5432",30,false);

        Log.i(TAG,exibeCliente(objCliente));

        objCliente = atualizaCliente(objCliente.getNome(),"maria@gmail.com",objCliente.getTelefone(),35,objCliente.isSexo(),objCliente);;

        Log.i(TAG,"Atualiando dados......");
        Log.i(TAG,exibeCliente(objCliente));
    }


    private String exibeCliente(Cliente cliente){
        String dadosCliente = "\n";
        dadosCliente += "Nome: " + cliente.getNome() + "\n";
        dadosCliente += "Email: " + cliente.getEmail() + "\n";
        dadosCliente += "Telefone: " + cliente.getTelefone() + "\n";
        dadosCliente += "Idade: " + cliente.getIdade() + "\n";
        dadosCliente += "Sexo: " + parseBooleanString(cliente.isSexo());
        return  dadosCliente;
    }

    private String parseBooleanString(boolean sexo){
        String tipoSexo;
        tipoSexo = sexo == true ? "Masculino":"Feminino";
        return tipoSexo;
    }

    private Cliente atualizaCliente(String nome, String email,String telefone, int idade,boolean sexo,Cliente cliente){
        cliente.setNome(nome);
        cliente.setEmail(email);
        cliente.setTelefone(telefone);
        cliente.setIdade(idade);
        cliente.setSexo(sexo);
        return cliente;
    }
}