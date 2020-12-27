package luiz.appminhaideia.view;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import luiz.appminhaideia.R;
import luiz.appminhaideia.core.AppUtil;
import luiz.appminhaideia.model.Cliente;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //rodarMinhaideia();
        //rodarPrimeiroNivelamento();
        //rodarBrawserFake();
        //transfereDadosDeUmaViewParaOutra();
        //rodaSharedPreferences();
        rodaImplementeGPS();
    }

    private void rodaImplementeGPS(){
        String TAG = "App_GPS";
        String PREF_NOME = "App_GPS_pref";//nome do arquivo

        SharedPreferences sharedPreferences;
        SharedPreferences.Editor dados;

        int REQUEST_LOCATION = 2020;
        //vai buscar a cordenada GPRS, pode ser 1- triangulada das antenas de celular, 2 - modem ADSL, 3- do gps do celular
        //pode ser a ultima comunicação registrada
        LocationManager locationManager;

        float latitude;
        float longitude;

        Log.i(TAG,"Rodando gprs main");

        sharedPreferences = getSharedPreferences(PREF_NOME, Context.MODE_PRIVATE);

        Log.i(TAG,"Sharede criada");
        dados = sharedPreferences.edit();

        latitude = 0.00f;
        longitude = 0.00f;

        locationManager = (LocationManager) getApplication().getSystemService(Context.LOCATION_SERVICE);

        if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            onGPS();
        }else{
            getLocation(dados,locationManager,latitude,longitude,REQUEST_LOCATION,TAG,sharedPreferences);
        }
    }
    //vai testar as permissões de localização definidas no arquivo manifeste, se estão liberadas
    private void getLocation(SharedPreferences.Editor tmpDados,LocationManager tmpLocationManager, float tmpLatitude, float tmpLongitude, int tmpREQUEST_LOCATION, String tmpTAG, SharedPreferences tmpSharedPreferences ) {
         if(ActivityCompat.checkSelfPermission(
                 getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                         getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
         {
             ActivityCompat.requestPermissions(getParent(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, tmpREQUEST_LOCATION);
         } else{
             //vai pegar a ultima locatização valida do celular
             Location locationGPS = tmpLocationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
             if(locationGPS != null){
                 double lat = locationGPS.getLatitude();
                 double longi = locationGPS.getLongitude();
                 tmpLatitude = (float) lat;
                 tmpLongitude = (float) longi;

                 Log.d(tmpTAG, "getLocation if: " + tmpLatitude + ", " +  tmpLongitude);
             }else{
                 tmpLatitude = 37.3316926f;
                 tmpLongitude = 122.029792f;

                 Log.d(tmpTAG, "getLocation else: " + tmpLatitude + ", " + tmpLongitude);
             }
         }
         //salvar os dados
        tmpDados.putFloat("latitude", tmpLatitude);//adiciona valor latitude a dados
        tmpDados.putFloat("longitude",tmpLongitude);//adiciona valor longitude a dados
        tmpDados.apply();

        Log.i(tmpTAG, "onCreate: Dados recuperados");

        Log.d(tmpTAG, "onCreate: Latitude " + tmpSharedPreferences.getFloat("latitude", 0.00f) );
        Log.d(tmpTAG, "onCreate: Longitude " + tmpSharedPreferences.getFloat("longitude",0.00f));
    }

    //exibe a tela para o usuário comfirmar o acessor a localização
    private void onGPS() {
        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
    }

    private void rodaSharedPreferences(){
        String TAG2 = "App_AulaSP";
        String PREF_NOME = "App_AulaSP_prefe";

        SharedPreferences sharedPreferences;
        SharedPreferences.Editor dados;

        String nomeProduto;
        int codProduto;
        float precoProduto;
        boolean estoqueProduto;

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
        dados.remove("estoqueProduto");
        dados.apply();

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