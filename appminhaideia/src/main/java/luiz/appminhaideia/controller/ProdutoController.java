package luiz.appminhaideia.controller;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import luiz.appminhaideia.api.AppUtil;
import luiz.appminhaideia.datamodel.ProdutoDatamodel;
import luiz.appminhaideia.datasource.AppDataBase;
import luiz.appminhaideia.model.Produto;

public class ProdutoController extends AppDataBase implements ICrud<Produto> {

    ContentValues dadosDoProduto;

    public ProdutoController(Context context) {
        super(context);

        Log.i(AppUtil.TAG, "ProdutoController: Conectado");
    }

    @Override
    public boolean incluir(Produto obj) {

        dadosDoProduto = new ContentValues();
        dadosDoProduto.put(ProdutoDatamodel.NOME,obj.getNome());
        dadosDoProduto.put(ProdutoDatamodel.FORNECEDOR,obj.getFornecedor());

        return insert(ProdutoDatamodel.TABELA,dadosDoProduto);
    }

    @Override
    public boolean alterar(Produto obj) {

        dadosDoProduto = new ContentValues();
        dadosDoProduto.put(ProdutoDatamodel.ID,obj.getId());
        dadosDoProduto.put(ProdutoDatamodel.NOME,obj.getNome());
        dadosDoProduto.put(ProdutoDatamodel.FORNECEDOR,obj.getFornecedor());

        return update(ProdutoDatamodel.TABELA,dadosDoProduto);
    }

    @Override
    public boolean deletar(Produto obj) {

        dadosDoProduto = new ContentValues();
        dadosDoProduto.put(ProdutoDatamodel.ID,obj.getId());
        return delete(ProdutoDatamodel.TABELA,dadosDoProduto);
    }

    @Override
    public List<Produto> listar() {
        List<Produto> produtoList = new ArrayList<>();

        return produtoList;
    }
}
