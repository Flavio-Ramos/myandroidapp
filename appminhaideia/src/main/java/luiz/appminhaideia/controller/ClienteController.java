package luiz.appminhaideia.controller;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import java.util.List;

import luiz.appminhaideia.api.AppUtil;
import luiz.appminhaideia.datamodel.ClienteDataModel;
import luiz.appminhaideia.datasource.AppDataBase;
import luiz.appminhaideia.model.Cliente;

public class ClienteController extends AppDataBase implements ICrud<Cliente> {

    ContentValues dadoDoObjeto;

    public ClienteController(Context context) {
        super(context);

        Log.d(AppUtil.TAG, "ClienteController: Conectado");
    }

    @Override
    public boolean incluir(Cliente obj) {

        dadoDoObjeto = new ContentValues();
        dadoDoObjeto.put(ClienteDataModel.NOME,obj.getNome());
        dadoDoObjeto.put(ClienteDataModel.EMAIL,obj.getEmail());

        return insert(ClienteDataModel.TABELA, dadoDoObjeto);
    }

    @Override
    public boolean alterar(Cliente obj) {
        dadoDoObjeto = new ContentValues();
        dadoDoObjeto.put(ClienteDataModel.ID,obj.getId());
        dadoDoObjeto.put(ClienteDataModel.NOME,obj.getNome());
        dadoDoObjeto.put(ClienteDataModel.EMAIL,obj.getEmail());

        return update(ClienteDataModel.TABELA,dadoDoObjeto);
    }

    @Override
    public boolean deletar(Cliente obj) {
        dadoDoObjeto = new ContentValues();
        dadoDoObjeto.put(ClienteDataModel.ID,obj.getId());

        return delete(ClienteDataModel.TABELA,dadoDoObjeto);
    }

    @Override
    public List<Cliente> listar() {
        return getAllClientes(ClienteDataModel.TABELA);
    }
}
