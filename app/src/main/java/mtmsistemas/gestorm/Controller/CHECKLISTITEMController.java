package mtmsistemas.gestorm.Controller;

import mtmsistemas.gestorm.Model.CHECKLISTITEM;

/**
 * Created by Giovanni on 05/03/2018.
 */

public class CHECKLISTITEMController {
    CHECKLISTITEM teste = new CHECKLISTITEM(null);

    CHECKLISTITEM[] itens = new CHECKLISTITEM[]{teste};

    public CHECKLISTITEM[] getItens(){return itens;}

    public CHECKLISTITEM getItem(int posicao){
        return itens[posicao];
    }
}
