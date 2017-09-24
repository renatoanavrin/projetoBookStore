/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package strategy;

import entidades.EntidadeDominio;

/**
 *
 * @author renato
 */
public interface IStrategy {
    public String processar(EntidadeDominio entidade);
}
