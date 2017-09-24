/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vh;

import entidades.Autor;
import entidades.Bebida;
import entidades.Categoria;
import entidades.Editora;
import entidades.EntidadeDominio;
import entidades.GrupoPrecificacao;
import entidades.Livro;
import entidades.SubCategoria;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

/**
 *
 * @author renato
 */
public class LivroViewHelper implements IViewHelper {

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {

        String operacao = request.getParameter("acao").toUpperCase();
        int id = 0;
        int ano = 0;
        String titulo = null;
        Categoria categoria = null;
        SubCategoria subCategoria = null;
        Editora editora = null;
        int edicao = 0;
        int isbn = 0;
        int numeroPaginas = 0;
        String Sinopse = null;
        float altura = 0;
        float largura = 0;
        float peso = 0;
        float profundidade = 0;
        Autor autor = null;
        GrupoPrecificacao grupoPrecificacao = null;
        ArrayList<Categoria> categorias = null;
        ArrayList<SubCategoria> subCategorias = null;

        if (operacao.equals("CONSULTAR") || operacao.equals("EDITAR") || operacao.equals("EXCLUIR")) {
            if (operacao.equals("CONSULTAR")) {
                String consulta = request.getParameter("consulta").toUpperCase();
                String dadosConsulta = request.getParameter("dadosconsulta").toUpperCase();

                categoria = new Categoria(0, null, dadosConsulta);

            } else {
                id = Integer.parseInt(request.getParameter("codigo"));
            }
        }
        if (operacao.equals("SALVAR") || operacao.equals("ALTERAR")) {
            if (operacao.equals("ALTERAR")) {
                id = Integer.parseInt(request.getParameter("codigo"));
            } else {
                id = 0;
            }

            
            
            String categoriasSelect = request.getParameter("categoria");
            String subcategoriasSelect = request.getParameter("subcategoria");
            
            String[] categoriasArray = categoriasSelect.split(",");
            String[] subcategoriasArray = subcategoriasSelect.split(",");
            
            
            ano = Integer.parseInt(request.getParameter("ano"));
            titulo = request.getParameter("titulo");
            editora = new Editora(Integer.parseInt(request.getParameter("editora")), null, null);
            edicao = Integer.parseInt(request.getParameter("edicao"));
            isbn = Integer.parseInt(request.getParameter("isbn"));
            numeroPaginas = Integer.parseInt(request.getParameter("numeropaginas"));
            Sinopse = request.getParameter("sinopse");
            altura = Float.parseFloat(request.getParameter("altura"));
            largura = Float.parseFloat(request.getParameter("largura"));
            peso = Float.parseFloat(request.getParameter("peso"));
            profundidade = Float.parseFloat(request.getParameter("profundidade"));
            autor = new Autor(Integer.parseInt(request.getParameter("autor")), null, null);
            grupoPrecificacao = new GrupoPrecificacao(Integer.parseInt(request.getParameter("precificacao")), null, null, 0);

            categorias = new ArrayList<>();
            for (String s : categoriasArray) {
                categorias.add(new Categoria(Integer.parseInt(s), null, null));
            }
            //categorias.add(categoria);
            subCategorias = new ArrayList<>();
            for (String s : subcategoriasArray) {
                subCategorias.add(new SubCategoria(Integer.parseInt(s),null, null));
            }
            //subCategorias.add(subCategoria);
        }

        Livro livro = new Livro(id, null, ano, titulo, categorias, subCategorias, editora, edicao, isbn, numeroPaginas, Sinopse, altura, largura, peso, edicao, autor, grupoPrecificacao);
        return livro;

    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String operacao = request.getParameter("acao").toUpperCase();
        JSONObject json;
        json = new JSONObject();
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        Livro livro = null;
        if (resultado.getMsg() == null) {
            switch (operacao) {
                case "SALVAR":
                    resultado.setMsg("Bebida cadastrada com sucesso!");
                    json.put("mensagem", resultado.getMsg());

                    List<EntidadeDominio> bebidas = resultado.getEntidadeDominios();
                    livro = (Livro) resultado.getEntidadeDominios().get(0);

                    json.put("codigo", livro.getId());
                    out.print(json.toString());
                    return;
            }
        } else {
            json.put("mensagem", resultado.getMsg());
        }

        //create Json Object
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        json.put("mensagem", resultado.getMsg());
        out.print(json.toString());
    }

}
