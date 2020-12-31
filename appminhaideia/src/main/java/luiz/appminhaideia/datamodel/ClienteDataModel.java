package luiz.appminhaideia.datamodel;

public class ClienteDataModel {
    //Toda Classe Data Model tem esta Estrutura
    //1 - Atributo nome da tabela
    public static final String TABELA = "Cliente";

    //2 -  Atributo um para um com os nomes dos campos
    public static final String ID = "id";
    public static final String NOME = "nome";
    public static final String EMAIL = "email";

    //3 - Query para criar a tabela no banco de dados
    public static String queryCriarTabela = "";

    //4 - Metodo para gerar o Script para criar a tabela
    public static String criarTabela(){

        queryCriarTabela += "CREATE TABLE " + TABELA + " ( ";
        queryCriarTabela += ID + " integer primary key autoincrement, ";
        queryCriarTabela += NOME + " text, ";
        queryCriarTabela += EMAIL + " text ";
        queryCriarTabela += " )";

        return queryCriarTabela;
    }

    //5 - Query de consulta gerais


}
