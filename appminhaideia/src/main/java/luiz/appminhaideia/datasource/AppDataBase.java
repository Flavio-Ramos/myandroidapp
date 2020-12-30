package luiz.appminhaideia.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import luiz.appminhaideia.api.AppUtil;
import luiz.appminhaideia.datamodel.ClienteDataModel;
import luiz.appminhaideia.datamodel.ProdutoDatamodel;
import luiz.appminhaideia.model.Cliente;

public class AppDataBase extends SQLiteOpenHelper {

    private static final String DB_NAME = "App.db";
    public static final int DB_VERSION =1;

    SQLiteDatabase db;

    public AppDataBase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

        Log.i(AppUtil.TAG, "AppDataBase: Criando banco de dados");

        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ClienteDataModel.criarTabela());
        Log.d(AppUtil.TAG, "onCreate: tabela cliente criada" + ClienteDataModel.criarTabela());

        db.execSQL(ProdutoDatamodel.criarTabela());
        Log.d(AppUtil.TAG, "onCreate: tabela produto criada " + ProdutoDatamodel.criarTabela());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insert(String Tabela, ContentValues obj){
        db = getWritableDatabase();
        boolean retorno = false;
        try{
            retorno = db.insert(Tabela,null,obj) > 0;
        }catch(Exception e){
            Log.i(AppUtil.TAG, "insert: " + e.getMessage());
        }
        return retorno;
    }

    public boolean update(String Tabela, ContentValues dados){
        db = getWritableDatabase();
        boolean retorno = false;
        try {
            retorno = db.update(Tabela,dados,"id=?",new String[] {String.valueOf(dados.get("id"))}) > 0;
        }catch(Exception e){
            Log.d(AppUtil.TAG, "update: "+e.getMessage());
        }
        return retorno;
    }

    public boolean delete(String Tabela,ContentValues dados){
        db = getWritableDatabase();
        boolean retorno = false;
        try{
            retorno = db.delete(Tabela,"id=?",new String[] {String.valueOf(dados.get("id"))}) > 0;
        }catch(Exception e){
            Log.i(AppUtil.TAG, "delete: "+e.getMessage());
        }
        return retorno;
    }

    public List<Cliente> getAllClientes(String Tabela){
        db = getReadableDatabase();
        Cliente objCliente;
        List<Cliente> clienteList = new ArrayList<>();
        String sql = "Select * from " + Tabela;
        Cursor cursor;
        cursor = db.rawQuery(sql,null);

        if(cursor.moveToFirst()){
            do {
                objCliente = new Cliente();
                objCliente.setId(cursor.getInt(cursor.getColumnIndex(ClienteDataModel.ID)));
                objCliente.setNome(cursor.getString(cursor.getColumnIndex(ClienteDataModel.NOME)));
                objCliente.setEmail(cursor.getString(cursor.getColumnIndex(ClienteDataModel.EMAIL)));

                clienteList.add(objCliente);
            }while(cursor.moveToNext());
        }
        return clienteList;
    }
}
