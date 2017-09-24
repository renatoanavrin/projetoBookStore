/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vh;

import entidades.Bebida;
import entidades.Categoria;
import entidades.EntidadeDominio;
import entidades.Fabricante;
import entidades.Fornecedor;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import util.ConverteDate;

/**
 *
 * @author renato
 */
public class bebidaViewHelper implements IViewHelper {

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        String operacao = request.getParameter("acao").toUpperCase();

        int id = 0;
        Categoria categoria = null;
        Fornecedor fornecedor = null;
        Fabricante fabricante = null;
        Date dataValidade = null;
        Date dataFabricacao = null;
        Date dataAtual = ConverteDate.dataAtual();
        String nome = null;
        double teorAlcoolico = 0;
        double preco = 0;
        double precoInicial = 0;
        double precoFinal = 0;
        int estoqueminimo = 0;
        int estoquemaximo = 0;

        if (operacao.equals("CONSULTAR") || operacao.equals("EDITAR")) {

            if (operacao.equals("CONSULTAR")) {
                String consulta = request.getParameter("consulta").toUpperCase();
                String dadosConsulta = request.getParameter("dadosconsulta").toUpperCase();

                if (consulta.equals("CATEGORIA")) {
                    categoria = new Categoria(0, null, dadosConsulta);
                    fornecedor = new Fornecedor(0, null, null);
                } else {
                    categoria = new Categoria(0, null, null);
                    fornecedor = new Fornecedor(0, null, dadosConsulta);
                }

                if (request.getParameter("precoinicial").length() == 0) {
                    precoInicial = 0;
                } else {
                    precoInicial = Double.parseDouble(request.getParameter("precoinicial"));
                }

                if (request.getParameter("precofinal").length() == 0) {
                    precoFinal = 0;
                } else {
                    precoFinal = Double.parseDouble(request.getParameter("precofinal"));
                }

                id = 0;
            } else {
                id = Integer.parseInt(request.getParameter("codigo"));
                categoria = null;
                fornecedor = null;
                teorAlcoolico = 0;
            }

            nome = null;
            fabricante = null;
            dataValidade = null;
            dataFabricacao = null;
        }

        if (operacao.equals("EXCLUIR")) {
            id = Integer.parseInt(request.getParameter("codigo"));
            categoria = null;
            fornecedor = null;
            nome = null;
            fabricante = null;
            dataValidade = null;
            teorAlcoolico = 0;
            dataFabricacao = null;
        }
        if (operacao.equals("SALVAR") || operacao.equals("ALTERAR")) {

//            ConverteDate.converteStringDate(dtCadastro);
            nome = request.getParameter("nome");
            dataFabricacao = ConverteDate.converteStringDate(request.getParameter("datafabricacao"));
            fabricante = new Fabricante(Integer.parseInt(request.getParameter("fabricante")), dataAtual, null);
            dataValidade = ConverteDate.converteStringDate(request.getParameter("datavalidade"));
            if (request.getParameter("teor").trim().length() > 0) {
                teorAlcoolico = Float.parseFloat(request.getParameter("teor").replace(',', '.'));
                teorAlcoolico = Math.round(teorAlcoolico * 100.0) / 100.0;
            }else{
                teorAlcoolico = 0;
            }

            id = Integer.parseInt(request.getParameter("codigo"));
            categoria = new Categoria(Integer.parseInt(request.getParameter("categoria")), null, null);
            fornecedor = new Fornecedor(Integer.parseInt(request.getParameter("fornecedor")), null, null);
            preco = Float.parseFloat(request.getParameter("preco").replace(',', '.'));
            preco = Math.round(preco * 100.0) / 100.0;
            estoqueminimo = Integer.parseInt(request.getParameter("estoqueminimo"));
            estoquemaximo = Integer.parseInt(request.getParameter("estoquemaximo"));

        }

        Bebida bebida = new Bebida(nome, fabricante, dataFabricacao, dataValidade, teorAlcoolico, categoria, fornecedor, id, null, preco, precoInicial, precoFinal, estoqueminimo, estoquemaximo);
        return bebida;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String operacao = request.getParameter("acao").toUpperCase();
        JSONObject json;
        json = new JSONObject();
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        Bebida bebida = null;
        if (resultado.getMsg() == null) {
            switch (operacao) {
                case "SALVAR":
                    resultado.setMsg("Bebida cadastrada com sucesso!");
                    json.put("mensagem", resultado.getMsg());

                    List<EntidadeDominio> bebidas = resultado.getEntidadeDominios();
                    bebida = (Bebida) resultado.getEntidadeDominios().get(0);

                    json.put("codigo", bebida.getId());
                    out.print(json.toString());
                    return;

                case "ALTERAR":
                    resultado.setMsg("Bebida alterada com sucesso!");
                    break;

                case "EXCLUIR":
                    resultado.setMsg("Bebida excluída com sucesso!");
                    break;

                case "CONSULTAR":
                    GridBebidas grid = new GridBebidas();
                    resultado.setMsg(grid.getGridBebidas(resultado.getEntidadeDominios()));

                    /* response.setContentType("application/json");
                    response.setCharacterEncoding("utf-8");*/
                    //json.put("mensagem", resultado.getMsg());
                    // json.put("mensagem", "teste");
                    break;

                case "EDITAR":
                    bebida = (Bebida) resultado.getEntidadeDominios().get(0);

                    //create Json Object
                    json = new JSONObject();
                    response.setContentType("application/json");
                    response.setCharacterEncoding("utf-8");

                    // put some value pairs into the JSON object .
                    json.put("nome", bebida.getNome());
                    json.put("fabricante", bebida.getFabricante().getId());
                    /*json.put("dataFabricacao", bebida.getDataFabricacao());
                    json.put("dataValidade", bebida.getDataValidade());*/
                    json.put("fornecedor", bebida.getFornecedor().getId());
                    json.put("categoria", bebida.getCategoria().getId());
                    json.put("teoralcoolico", bebida.getTeorAlcoolico());

                    // finally output the json string       
                    //resultado.setMsg(json.toString());
                    DecimalFormat df = new DecimalFormat("0.##");
                    json = new JSONObject();
                    json.put("nome", bebida.getNome());
                    json.put("fabricante", bebida.getFabricante().getId());
                    json.put("fornecedor", bebida.getFornecedor().getId());
                    json.put("categoria", bebida.getCategoria().getId());
                    String teorAlcoolico;
                    teorAlcoolico = df.format(bebida.getTeorAlcoolico());
                    json.put("teoralcoolico", teorAlcoolico);
                    json.put("mensagem", resultado.getMsg());
                    json.put("codigo", bebida.getId());
                    json.put("dataFabricacao", ConverteDate.converteDateString(bebida.getDataFabricacao()));
                    json.put("dataValidade", ConverteDate.converteDateString(bebida.getDataValidade()));
                    String preco = df.format(bebida.getPreco());
                    json.put("preco", preco);
                    json.put("estoqueminimo", bebida.getEstoqueminimo());
                    json.put("estoquemaximo", bebida.getEstoquemaximo());

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
