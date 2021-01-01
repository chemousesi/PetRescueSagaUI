package Model.Movible;

import java.io.Serializable;

public abstract class Element implements Serializable, Cloneable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public boolean estMobile() {
        return true;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        return super.clone();
    }

}
