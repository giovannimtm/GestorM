package mtmsistemas.gestorm.Util;

/**
 * Created by jean on 21/02/2018.
 */

public class ClsItensEntrada {
    public String[] getItens() {
        return itens;
    }

    public String getItem(int posicao){
        return itens[posicao];
    }

    public void setItens(String[] itens) {
        this.itens = itens;
    }

    public void setItem(String item, int posicao){
        this.itens[posicao] = item;
    }

    public String[] getCaminhoFoto() {
        return caminhoFoto;
    }

    public String getCaminhoFotoItem(int posicao){
        return caminhoFoto[posicao];
    }

    public void setCaminhoFoto(String[] caminhoFoto) {
        this.caminhoFoto = caminhoFoto;
    }

    public void setCaminhoFotoItem(String caminho, int posicao){
        this.caminhoFoto[posicao] = caminho;
    }

    public String[] getStatusEquip() {
        return statusEquip;
    }

    public String getStatusEquipItem(int posicao){
        return statusEquip[posicao];
    }

    public void setStatusEquip(String[] statusEquip) {
        this.statusEquip = statusEquip;
    }

    public void setSatatusEquipItem(String status, int posicao){
        this.statusEquip[posicao] = status;
    }

    String[] itens = new String[] { "Cupcake", "Donut", "Eclair", "Froyo", "Gingerbread",
            "Honeycomb", "Ice Cream Sandwich", "Jelly Bean",
            "KitKat", "Lollipop", "Marshmallow", "Nougat" };
    String[] caminhoFoto = new String[itens.length];
    String[] statusEquip = new String[itens.length];
}
