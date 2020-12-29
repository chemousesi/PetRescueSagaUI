package Movible;

import java.io.Serializable;

public abstract class Element implements Serializable, Cloneable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public boolean estMobile() {
        return true;
    }

}
