/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vh;

import entidades.EntidadeDominio;
import java.util.List;

/**
 *
 * @author renato
 */
public class Resultado {
    private String msg;
    private List<EntidadeDominio> entidades;
    
    /**
     * Método para recuperação do campo msg
     * @return  valor do campo msg
     */
    public String getMsg(){
        return msg;
    }
    
    /**
     * Atribui valor a variável msg
     * @return msg Atributo da Classe
     */
   public void setMsg(String msg) {
       this.msg = msg;
   }
   
   /**
    * Método de recuperação do campo entidades
    * 
    * @return valor do campo entidades
    */
   public List<EntidadeDominio> getEntidadeDominios(){
       return entidades;
   }
   
   /**
    * Valor de entidades atribuído a entidades
    * @param entidades Atribuido da Classe
    */
   public void setEntidades(List<EntidadeDominio> entidades){
       this.entidades = entidades;
   }
   
}
