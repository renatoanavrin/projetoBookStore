/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachada;

import dao.BebidaDao;
import dao.IDAO;
import dao.LivroDao;
import entidades.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import strategy.IStrategy;
import vh.Resultado;
import java.sql.SQLException;
import strategy.ValidadeNullBebida;
import strategy.ValidadorDataValidade;
import strategy.ValidadorEstoqueMinimoMaximo;

/**
 *
 * @author renato
 */
public class Fachada implements IFachada {

    private Map<String, IDAO> daos;
    private Map<String, Map<String, List<IStrategy>>> rns;
    private Resultado resultado;

    public Fachada() {
        /* Instânciando o Mapa de DAOS*/
        daos = new HashMap<String, IDAO>();
        /*Instanciando o mapa de regras de negórios*/
        rns = new HashMap<String, Map<String, List<IStrategy>>>();

        /*Criando instâncias dos DAOS a serem utilizados*/
        LivroDao livroDAO = new LivroDao();

        /*Adicionando cada dao no mapa indexando pelo nome da classe*/
        daos.put(Livro.class.getName(), livroDAO);

        /*Criando instâncias de regras de negócio a serem utilizadas*/
        ValidadeNullBebida validadeNullBebida = new ValidadeNullBebida();
        ValidadorEstoqueMinimoMaximo validadorEstoque = new ValidadorEstoqueMinimoMaximo();
        ValidadorDataValidade validadorDataValidade = new ValidadorDataValidade();
        /*Criando uma lista para conter as regras de negócio de fornecedor*/
        List<IStrategy> rnsSalvarBebida = new ArrayList<IStrategy>();
        /* Adicionando as regras a serem utilizadas na operação salvar da bebida*/
        rnsSalvarBebida.add(validadeNullBebida);
        rnsSalvarBebida.add(validadorEstoque);
        rnsSalvarBebida.add(validadorDataValidade);

        /*Cria o mapa que poderá conter todas as listas de regras de negócio
        específica por operacao da bebida*/
        Map<String, List<IStrategy>> rnsBebida = new HashMap<String, List<IStrategy>>();
        /*
            Adiciona a lista de regras da operação salvar
         */
        rnsBebida.put("SALVAR", rnsSalvarBebida);
        rnsBebida.put("ALTERAR", rnsSalvarBebida);

        /*
        Adiciona a regra de salvar nas regras gerais
         */
        rns.put(Bebida.class.getName(), rnsBebida);

    }

    @Override
    public Resultado salvar(EntidadeDominio entidade) {
        resultado = new Resultado();
        String nmClasse = entidade.getClass().getName();

        String msg = executarRegras(entidade, "SALVAR");

        if (msg == null) {
            IDAO dao = daos.get(nmClasse);
            try {
                dao.salvar(entidade);
                List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
                entidades.add(entidade);
                resultado.setEntidades(entidades);
            } catch (SQLException e) {
                String msgSql = e.getMessage();
                String sqlString = e.getSQLState();
                resultado.setMsg("Não foi possível realizar o registro!" + msgSql + sqlString);

            }
        } else {
            resultado.setMsg(msg);

        }

        return resultado;
    }

    @Override
    public Resultado alterar(EntidadeDominio entidade) {
        resultado = new Resultado();
        String nmClasse = entidade.getClass().getName();

        String msg = executarRegras(entidade, "ALTERAR");

        if (msg == null) {
            IDAO dao = daos.get(nmClasse);
            try {
                dao.alterar(entidade);
                List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
                entidades.add(entidade);
                resultado.setEntidades(entidades);
            } catch (SQLException e) {
                String msgSql = e.getMessage();
                String sqlString = e.getSQLState();
                resultado.setMsg("Não foi possível realizar o registro!" + msgSql + sqlString);

            }
        } else {
            resultado.setMsg(msg);

        }

        return resultado;
    }

    @Override
    public Resultado excluir(EntidadeDominio entidade) {
        resultado = new Resultado();
        String nmClasse = entidade.getClass().getName();

        String msg = executarRegras(entidade, "EXCLUIR");

        if (msg == null) {
            IDAO dao = daos.get(nmClasse);
            try {
                dao.excluir(entidade);
                List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
                entidades.add(entidade);
                resultado.setEntidades(entidades);
            } catch (SQLException e) {
                e.printStackTrace();
                resultado.setMsg("Não foi possível realizar o registro!");

            }
        } else {
            resultado.setMsg(msg);

        }

        return resultado;

    }

    @Override
    public Resultado consultar(EntidadeDominio entidade) {
        resultado = new Resultado();
        String nmClasse = entidade.getClass().getName();

        IDAO dao = daos.get(nmClasse);
        try {
            List<EntidadeDominio> entidades = dao.consultar(entidade, null, null);
            resultado.setEntidades(entidades);
        } catch (SQLException e) {
            String msgSql = e.getMessage();
            String sqlString = e.getSQLState();
            resultado.setMsg("Não foi possível realizar a consulta!" + msgSql + sqlString);

        }
        return resultado;
    }

    private String executarRegras(EntidadeDominio entidade, String operacao) {
        String nmClasse = entidade.getClass().getName();
        StringBuilder msg = new StringBuilder();

        Map<String, List<IStrategy>> regrasOperacao = rns.get(nmClasse);

        if (regrasOperacao != null) {
            List<IStrategy> regras = regrasOperacao.get(operacao);

            if (regras != null) {
                for (IStrategy s : regras) {
                    String m = s.processar(entidade);

                    if (m != null) {
                        msg.append(m);
                        msg.append("\n");
                    }
                }
            }

        }

        if (msg.length() > 0) {
            return msg.toString();
        } else {
            return null;
        }
    }

    @Override
    public Resultado visualizar(EntidadeDominio entidade) {
        resultado = new Resultado();
        resultado.setEntidades(new ArrayList<EntidadeDominio>(1));
        //resultado.getEntidades().add(entidade);		
        return resultado;
    }
}
