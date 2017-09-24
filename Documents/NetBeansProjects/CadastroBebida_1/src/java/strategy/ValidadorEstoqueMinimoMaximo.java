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
public class ValidadorEstoqueMinimoMaximo implements IStrategy {

    @Override
    public String processar(EntidadeDominio entidade) {
        if (entidade instanceof Bebida) {
            Bebida bebida = (Bebida) entidade;

            int estoqueminimo = bebida.getEstoqueminimo();
            int estoquemaximo = bebida.getEstoquemaximo();
            
            if(estoqueminimo > estoquemaximo){
                return "O estoque Mínimo deve ser menor que o Máximo!";
            }

        } else {
            return "Deve ser registrado uma Bebida!";
        }

        return null;
    }

}
