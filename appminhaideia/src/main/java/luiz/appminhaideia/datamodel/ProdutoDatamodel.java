package luiz.appminhaideia.datamodel;

public class ProdutoDatamodel {
    public static final String TABELA = "Produto";

    public static final String ID = "id";
    public static final String NOME = "nome";
    public static final String FORNECEDOR = "fornecedor";

    public static String queryCriarTabela = "";

    public static String criarTabela(){

        queryCriarTabela += "CREATE TABLE " + TABELA + " ( ";
        queryCriarTabela += ID + " integer primary key autoincrement, ";
        queryCriarTabela += NOME + " text, ";
        queryCriarTabela += FORNECEDOR + " text ";
        queryCriarTabela += " ) ";

        return queryCriarTabela;
    }
}
