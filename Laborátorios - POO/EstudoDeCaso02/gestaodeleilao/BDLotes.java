package EstudoDeCaso02.gestaodeleilao;

import java.util.ArrayList;

import EstudoDeCaso02.gestaodelotes.Lote;

public class BDLotes {
    
    private ArrayList<Lote> lotes;
    private int numeroProxLote;
 
    public BDLotes() {
        this.lotes = new ArrayList<>();
        this.numeroProxLote = 1;
    }
 
    public void adicionaLote(String descricao, double valorMinimo) {
        this.lotes.add(new Lote(this.numeroProxLote, descricao, valorMinimo));
        this.numeroProxLote++;
    }
 
    public Lote buscaLote(int numero) {
        for (Lote lote : lotes) {
            if (lote.getNumero() == numero) {
                return lote;
            }
        }
        return null;
    }
 
    // Atividade 3: removeLote
    public Lote removeLote(int idLote) {
        Lote loteParaRemover = buscaLote(idLote);
        if (loteParaRemover != null) {
            lotes.remove(loteParaRemover);
            return loteParaRemover;
        }
        return null;
    }
 
    public ArrayList<Lote> getTodosOsLotes() {
        return (ArrayList<Lote>) this.lotes.clone();
    }
 
    public int quantidadeDeLotes() {
        return this.lotes.size();
    }
}
