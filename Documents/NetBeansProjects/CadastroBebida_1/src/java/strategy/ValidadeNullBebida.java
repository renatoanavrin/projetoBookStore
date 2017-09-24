/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package strategy;

import entidades.Bebida;
import entidades.EntidadeDominio;
import java.util.Date;

/**
 *
 * @author renato
 */
public class ValidadeNullBebida implements IStrategy {

    @Override
    public String processar(EntidadeDominio entidade) {
        if (entidade instanceof Bebida) {
            Bebida bebida = (Bebida) entidade;
            int fabricante = 0;

            String nome = bebida.getNome();
            fabricante = bebida.getFabricante().getId();
            Date dataFabricacao = bebida.getDataFabricacao();
            Date dataValidade = bebida.getDataValidade();
            double teor = bebida.getTeorAlcoolico();
            double preco = bebida.getPreco();
            int estoqueminimo = bebida.getEstoqueminimo();
            int estoquemaximo = bebida.getEstoquemaximo();

            if (nome == null || dataFabricacao == null || dataValidade == null  || preco == 0 || estoqueminimo == 0 || estoquemaximo == 0) {
                return "Nome, Data Fabricação, Data Validade, teor, preco,estoque mínimo e estoque máximo são de preenchimento obrigatório!";
            }

            if (nome.trim().equals("")) {
                return "Nome, Data Fabricação, Data Validade, teor, preco,estoque mínimo e estoque máximo são de preenchimento obrigatório!";
            }

        } else {
            return "Deve ser registrado uma Bebida!";
        }

        return null;
    }

}
