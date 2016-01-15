package bank.bankieren;

import java.io.Serializable;

/**
 *
 * @author Ruud
 */
public interface IKlant extends Serializable,Comparable<IKlant> {

    /**
     *
     * @return
     */
    String getNaam();

    /**
     *
     * @return
     */
    String getPlaats();
}

