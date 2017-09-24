/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import vh.IViewHelper;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.*;
import entidades.*;
import java.util.HashMap;
import java.util.Map;
import vh.Resultado;
import vh.bebidaViewHelper;

/**
 *
 * @author renato
 */
@WebServlet(urlPatterns = {"/Servlet"})
public class Servlet extends HttpServlet {

    private static Map<String, ICommand> commands;
    private static Map<String, IViewHelper> vhs;

    public Servlet() {
        /**
         * Utilizando a Command para chaar a Fachada e indexando cada command
         * pela operação garantimos que esta servlet atenderá qualquer operação
         */
        
        commands = new HashMap<String, ICommand>();
        commands.put("SALVAR", new SalvarCommand());
        commands.put("EXCLUIR", new ExcluirCommand());
        commands.put("CONSULTAR", new ConsultarCommand());
        commands.put("VISUALIZAR", new VisualizarCommand());
        commands.put("ALTERAR", new AlterarCommand());

        /**
         * Utilizando o ViewHelper para tratar especificações de qualquer tela e
         * indexando cada viewhelper pela url me que esta servlet é chamada no
         * form garantimos que esta servlet atenderá qualquer entidade
         */
        vhs = new HashMap<String, IViewHelper>();
        /**
         * A chave do mapa é o mapeamento da Servlet para cada form que esta
         * configurado no web.xml e sendo utilizada na chamada do AJAX
         */
        vhs.put("/cadastroLivro", new bebidaViewHelper());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doProcessRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doProcessRequest(request, response);
    }

    protected void doProcessRequest(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        //Obtêm a uri que invocou esta servlet (O que foi definido no methdo do form html)
        String uri = request.getRequestURI();

        //Obtêm a operação executada
        String operacao = request.getParameter("acao");

        //Obtêm um viewhelper indexado pela uri que invocou esta servlet
        IViewHelper vh = vhs.get(uri);

        //O viewhelper retorna a entidade especifica para a tela que chamou esta servlet
        EntidadeDominio entidade = vh.getEntidade(request);

        //Obtêm o command para executar a respectiva operação
        ICommand command = commands.get(operacao);

        /*Executa o command que chamará a fachada para executar a operação requisitada
		 * o retorno é uma instância da classe resultado que pode conter mensagens derro 
		 * ou entidades de retorno
         */
        Resultado resultado = command.execute(entidade);

        /*
		 * Executa o método setView do view helper específico para definir como deverá ser apresentado 
		 * o resultado para o usuário
         */
        vh.setView(resultado, request, response);

    }
}
