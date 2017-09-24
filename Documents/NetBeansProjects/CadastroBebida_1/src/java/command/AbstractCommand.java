
package command;

import fachada.Fachada;
import fachada.IFachada;

/**
 *
 * @author renato
 */
public abstract class AbstractCommand implements ICommand{
    protected IFachada fachada = new Fachada();
}
