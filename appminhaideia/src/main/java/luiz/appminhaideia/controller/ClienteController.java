package luiz.appminhaideia.controller;

import android.util.Log;

import luiz.appminhaideia.core.AppUtil;

public class ClienteController {
    
    String versaoDoApp;
    public ClienteController(){
        this.versaoDoApp = AppUtil.versaoDoAplicativo();
        Log.i(AppUtil.TAG,"Minha versão: " + this.versaoDoApp);
    }

}
