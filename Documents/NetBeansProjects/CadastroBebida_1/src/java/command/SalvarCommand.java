
package command;

import entidades.EntidadeDominio;
import vh.Resultado;

/**
 *
 * @author renato
 */
public class SalvarCommand extends AbstractCommand{

    @Override
    public Resultado execute(EntidadeDominio entidade) {
     return fachada.salvar(entidade);
    }
    
}
