package controllers;

import beans.Aluno;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.AlunoModel;

/**
 *
 * @author souza
 */
public class AlunosController extends HttpServlet {
    
    //criar uma lista para receber alunos vindos do model
    List<Aluno> alunosDados;
     //cria um objeto aluno para classe toda
    Aluno aluno = new Aluno();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        try {
            // vamos instaciar o objeto model do aluno para solicitarmos os dados
            AlunoModel am = new AlunoModel();
            
            // vamos executar o metodo listar do objeto alunomodel(am)
            
            alunosDados = am.listar();
            
            //vamos enviar toda essa parada loca para a View
            request.setAttribute("listaAlunos", alunosDados);

             request.getRequestDispatcher("view_listar.jsp").forward(request, response);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AlunosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // pega o valor enviado na variável "operacao"
        String operacao = request.getParameter("operacao");

        // criar um menu de opções com a estrutura de seleção switch
        switch (operacao) {
            case "Inserir":
                try{
                    //vamos recuperar os dados do formulario
                    aluno.setRa(request.getParameter("ra"));
                    
                            
                    aluno.setNome(request.getParameter("nome"));
                    aluno.setCurso(request.getParameter("curso"));
                    
                    AlunoModel am = new AlunoModel();
                    
                    //passando os valores para o objeto "am"
                    am.inserir(aluno);
                    
                    //redireciona para a view messagem
                request.setAttribute("mensagem",am.toString());
                request.getRequestDispatcher("view_mensagem.jsp").forward(request, response);
                    
                }catch(SQLException sql){
                request.setAttribute("mensagem", sql.getMessage());
                request.getRequestDispatcher("view_mensagem.jsp").forward(request, response);
                
                }
                
                //request.setAttribute("mensagem", "Inserir novo registro ");
                //request.getRequestDispatcher("view_mensagem.jsp").forward(request, response);
               
                break;
            case "Pesquisar":
                String valorDigitado = request.getParameter ("valor");
                
                try{
                    AlunoModel am=new AlunoModel();
                    //vamos verificar que tipo foi solicitado
                    
                    switch(request.getParameter("tipo")){
                        case "ra":
                            aluno.setRa(valorDigitado);
                                    break;
                        case "nome":
                            aluno.setNome(valorDigitado);
                            break;
                        case "curso":
                            aluno.setCurso(valorDigitado);
                              break;
                    }
                    // aqui vai a chamada para a pesquisa 
                    alunosDados = am.pesquisar(aluno ,request.getParameter("tipo"));
                 
                    //vamos retornar os dados para a tela listar
                    request.setAttribute("listaAlunos", alunosDados);
                    request.getRequestDispatcher("view_listar.jsp").forward(request, response);
                
                }catch(SQLException sql){
                
                
                request.setAttribute("mensagem", sql.getMessage());
                request.getRequestDispatcher("view_mensagem.jsp").forward(request, response);
                }
                break;
            case "Editar":
                
                try{
                AlunoModel am = new AlunoModel();
                aluno.setRa(request.getParameter("ra"));
                alunosDados =am.pesquisar(aluno,"ra");
                
                
                request.setAttribute("alunoDados", alunosDados);
                request.getRequestDispatcher("view_mensagem.jsp").forward(request, response);
                }catch(SQLException e){
                    request.setAttribute("mensagem", e.getMessage());
                    request.getRequestDispatcher("view_mensagem.jsp").forward(request, response);
                }
                
           
                break;
                
                
                
            case "Atualizar": //salvar
                request.setAttribute("mensagem", "Atualizar");
                request.getRequestDispatcher("view_editar.jsp").forward(request, response);
                break;
            case "Excluir":
                request.setAttribute("mensagem", "Excluir");
                request.getRequestDispatcher("view_mensagem.jsp").forward(request, response);
                break;
            case "ConfirmarExclusao":
                request.setAttribute("mensagem", "Confirmar Exclusão");
                request.getRequestDispatcher("view_mensagem.jsp").forward(request, response);
                break;
        }
    }
}
