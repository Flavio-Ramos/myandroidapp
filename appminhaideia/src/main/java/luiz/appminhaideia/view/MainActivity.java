package luiz.appminhaideia.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import javax.security.auth.login.LoginException;

import luiz.appminhaideia.R;
import luiz.appminhaideia.core.AppUtil;
import luiz.appminhaideia.model.Cliente;

public class MainActivity extends AppCompatActivity {

    private static final String TAG2 = "App_AulaSP";
    private static final String PREF_NOME = "App_AulaSP_prefe";

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor dados;

    String nomeProduto;
    int codProduto;
    float precoProduto;
    boolean estoqueProduto;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(TAG2, "onCreate: Rodando");
        sharedPreferences = getSharedPreferences(PREF_NOME, Context.MODE_PRIVATE);
        Log.i(TAG2, "onCreate: Pasta Criada");

        dados = sharedPreferences.edit();

        nomeProduto = "Notebook";
        codProduto = 9808245;
        precoProduto = 5000.28f;
        estoqueProduto = true;

        dados.putString("nomeProduto", nomeProduto);
        dados.putInt("codProduto", codProduto);
        dados.putFloat("precoProduto", precoProduto);
        dados.putBoolean("estoqueProduto", estoqueProduto);
        //salva os dados do prduto 1 no App_AulaSP_prefe.xml
        dados.apply();

        //Limpa o arquivo App_Aula_prefe.xml inteiro
        //dados.clear();
        //dados.apply();

        //deletar um campo App_AulaSP_prefe.xml no exemplo
        //foi o campo estoque
        //dados.remove("estoqueProduto");
        //dados.apply();

        Log.i(TAG2,"Dados gravados..");
        Log.i(TAG2,"Produto: " + nomeProduto);
        Log.i(TAG2,"Código: " + codProduto);
        Log.i(TAG2,"Preço: " + precoProduto);
        Log.i(TAG2,"Tem no estoque: " + estoqueProduto);

        Log.i(TAG2,"Dados recuperados..");
        Log.i(TAG2,"Produto: " + sharedPreferences.getString("nomeProduto","Fora de Estoque"));
        Log.i(TAG2,"Código: " + sharedPreferences.getInt("codProduto",-1));
        Log.i(TAG2,"Preço: " + sharedPreferences.getFloat("precoProduto", -1.0f));
        Log.i(TAG2,"Tem no estoque: " + sharedPreferences.getBoolean("estoqueProduto",false));

        //rodarMinhaideia();
        //rodarPrimeiroNivelamento();
        //rodarBrawserFake();
        //transfereDadosDeUmaViewParaOutra();
    }


    private void transfereDadosDeUmaViewParaOutra(){
        TextView textNome;
        Bundle bundle = getIntent().getExtras();
        Log.d(AppUtil.TAG,"Nome: " + bundle.getString("nome"));
        Log.d(AppUtil.TAG,"Email: " + bundle.getString("email"));

        textNome = findViewById(R.id.textNome);
        textNome.setText("Seja bem vindo(a) " + bundle.getString("nome"));
    }

    private void rodarBrawserFake(){

        WebView webView = findViewById(R.id.webview);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setVisibility(View.VISIBLE);

        webView.loadUrl("https://www.marcomaddo.com.br");

    }

    private void rodarMinhaideia(){
        Log.d(AppUtil.TAG, "onCreate: Tela Main Carregada...");
    }

    private void rodarPrimeiroNivelamento(){
        Cliente objCliente;
        objCliente = new Cliente("Maria","maria@maria","(011) 9876-5432",30,false);

        Log.i(AppUtil.TAG,exibeCliente(objCliente));

        objCliente = atualizaCliente(objCliente.getNome(),"maria@gmail.com",objCliente.getTelefone(),35,objCliente.isSexo(),objCliente);;

        Log.i(AppUtil.TAG,"Atualiando dados......");
        Log.i(AppUtil.TAG,exibeCliente(objCliente));
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