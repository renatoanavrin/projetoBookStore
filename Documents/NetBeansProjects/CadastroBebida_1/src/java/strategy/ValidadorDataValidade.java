/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package strategy;

import entidades.Bebida;
import entidades.EntidadeDominio;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author renato
 */
public class ValidadorDataValidade implements IStrategy {

    @Override
    public String processar(EntidadeDominio entidade) {
        if (entidade instanceof Bebida) {
            Bebida bebida = (Bebida) entidade;
            int meses = 0;
double numeroDeMeses =0;
            boolean erro = false;
            int categoriaId = bebida.getCategoria().getId();

            switch (categoriaId) {
                case 1:
                    meses = 2;
                    break;
                case 2:
                    meses = 12;
                    break;
                default:
                    meses = 1;

            }

            java.util.Date date = new Date();
            /*Calendar cal = Calendar.getInstance();
            cal.setTime(bebida.getDataValidade());
            int mesValidade = cal.get(Calendar.MONTH);
            int anoValidade = cal.get(Calendar.YEAR);

            int mesAtual = cal.get(Calendar.MONTH);
            int anoAtual = cal.get(Calendar.YEAR);

            if (anoAtual >= anoValidade) {
                erro = true;
            }
            
            if(!erro){
                
                if(mesAtual > mesValidade){
                    erro = true;
                }
            }*/
            Date dataAtual = new Date();
            
                
                final double MES_EM_MILISEGUNDOS = 30.0 * 24.0 * 60.0 * 60.0 * 1000.0;
		//final double MES_EM_MILISEGUNDOS = 2592000000.0;
		 numeroDeMeses = (double)((bebida.getDataValidade().getTime() - dataAtual.getTime())/MES_EM_MILISEGUNDOS);
            
            if(numeroDeMeses < meses){
                erro = true;
            }
            

            if (erro) {
                return "Data de validade deve ter " + meses + " meses de validade pelo menos";
            }

        } else {
            return "Deve ser registrado uma Bebida!";
        }

        return null;
    }

}
