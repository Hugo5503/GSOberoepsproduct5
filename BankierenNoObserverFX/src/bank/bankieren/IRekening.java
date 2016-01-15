package bank.bankieren;

import java.io.Serializable;

/**
 *
 * @author Ruud
 */
public interface IRekening extends Serializable {

    /**
     *
     * @return
     */
    int getNr();

    /**
     *
     * @return
     */
    Money getSaldo();

    /**
     *
     * @return
     */
    IKlant getEigenaar();

    /**
     *
     * @return
     */
    int getKredietLimietInCenten();
}

