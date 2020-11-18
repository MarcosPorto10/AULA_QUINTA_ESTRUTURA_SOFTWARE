package models;

//Essa classe sera resposável pelo CRUD
import beans.Aluno;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBConnection;

public class AlunoModel implements Serializable {
    // declarando os atributos principais

    private Connection conexao = null;
    private String status;
    
    //metodo construtor
    //toda vez que a classe AlunoModel for instanciada,
    //o construtor fará automaticamente a conexãoo com o banco
    public AlunoModel() throws SQLException {

        this.conexao = DBConnection.getInstance().getConnection();

    }

//Implementar os metodos do CRUD
    //metodo inserir (create) insert
    public void inserir(Aluno aluno) {
        try{
            String sql="INSERT INTO alunos (ra,nome,curso)VALUES (?,?,?)";    
        try(PreparedStatement ps =conexao.prepareStatement(sql)){
        //atribuir os valores do objetos as posicões (as interrogações)
        ps.setString(1, aluno.getRa());
        ps.setString(2, aluno.getNome());
        ps.setString(3,aluno.getCurso());
        
        //executa o SQL no banco de dados
        
        ps.execute();
        
        //fecha a conexão com o banco 
        
        ps.close();
        }
        conexao.close();//fecha a conexao com banco
       //mensagem para usuario 
        this.status="Aluno[" + aluno.getNome()+ "] inserindo com sucesso !";
        
        }catch(SQLException ex){
        //se houver erro,vamos avisa o usuario
        
        this.status= "Erro ao inserir o aluno [" + ex.getMessage() + "]";
        }
    }
    //metodo   de listar e pesquisar read-select

    public List<Aluno> listar() {
        //variavel para receber a lista de alunos originadas do banco
        List<Aluno> alunos = new ArrayList();
        //tratamento de erros 
        try {
            String sql = "SELECT * FROM alunos ORDER BY nome ASC;";
            try (
                    //preparar os dados (consulta ou query)
                    //e envia para o banco de dados
                    PreparedStatement ps = conexao.prepareStatement(sql);
                    //declarar uma variavel para RECEBER os dados (vetor)
                    ResultSet rs = ps.executeQuery()) {//cursor do Android
                //vamos percorrer (laço)os registros retornados (se houver)
                //para transformar os dados TABULARES (relacional)em objetos
                while(rs.next()){//enquanto houver proximo
                    Aluno aluno =new Aluno();
                    aluno.setId(rs.getInt("id"));
                    aluno.setRa(rs.getString("ra"));
                    aluno.setNome(rs.getString("nome"));
                    aluno.setCurso(rs.getString("curso"));
                    
                    //colocar o objeto que foi "populado ou alimentado"
                    //na nossa list de alunos(list <alunos>)
                    
                    alunos.add(aluno);
                
                }
                //fechamento das instancias rs e ps
                rs.close();
                ps.close();
                
            }
            //vamos retornar a lista de objetos
            return alunos;
            
        } catch (SQLException ex) {
            throw new RuntimeException("falha ao listar.", ex);
        }

    }

    public List<Aluno> pesquisar(Aluno aluno) {

        return null;
    }
//metodo para atualizar um registro update

    public void atualizar(Aluno aluno) {

    }

//metodo para excluir um registro delete
    public void excluir(Aluno aluno) {

    }
  //metodo que retorna um texto quando chamamos o modelo.toString
    @Override
   
   public String toString(){
       
       return status;
   }
}
