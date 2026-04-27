package EstudoDeCaso02.gestaodeleilao;

import java.util.ArrayList;

import EstudoDeCaso02.gestaodelotes.Lote;
import EstudoDeCaso02.gestaodelotes.Pessoa;

public class Leilao {
    
    private BDLotes lotes;
    private Interface terminal;
    private boolean ativo;
 
    public Leilao() {
        this.lotes = new BDLotes();
        this.terminal = new Interface(this);
    }
 
    public boolean isAtivo() {
        return this.ativo;
    }
 
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
 
    public void adicionaLote(String descricao, double valorMinimo) {
        this.lotes.adicionaLote(descricao, valorMinimo);
    }
 
    // Atividade 4: removeLote por delegação
    public Lote removeLote(int idLote) {
        return this.lotes.removeLote(idLote);
    }
 
    public int quantidadeDeLotes() {
        return this.lotes.quantidadeDeLotes();
    }
 
    public Lote getLote(int numero) {
        return this.lotes.buscaLote(numero);
    }
 
    public String ofertaLance(int numeroLote, String nomePessoa, double valor) {
        Lote lote = this.getLote(numeroLote);
        if (lote == null) {
            return "Lote inexistente.";
        }
        if (lote.lancePara(new Pessoa(nomePessoa), valor)) {
            return "Lance bem sucedido!";
        }
        String msg = "Lance invalido. ";
        if (lote.getMaiorLance() != null) {
            msg += "Lance atual: R$ " + String.format("%.2f", lote.getMaiorLance().getValor())
                 + " por " + lote.getMaiorLance().getLicitante().getNome();
        } else {
            msg += "Valor minimo e: R$ " + String.format("%.2f", lote.getValorMinimo());
        }
        return msg;
    }
 
    public String obtemTodosOsLotes() {
        ArrayList<Lote> todos = this.lotes.getTodosOsLotes();
        if (todos.isEmpty()) {
            return "Nenhum lote cadastrado.\n";
        }
        StringBuilder sb = new StringBuilder();
        for (Lote lote : todos) {
            sb.append(lote.toString());
        }
        return sb.toString();
    }
 
    // Atividade 1: encerraLeilao
    public String encerraLeilao() {
        this.ativo = false;
        ArrayList<Lote> todos = this.lotes.getTodosOsLotes();
        StringBuilder resultado = new StringBuilder();
        resultado.append("=== LEILAO ENCERRADO ===\n\n");
 
        for (Lote lote : todos) {
            resultado.append("Lote ").append(lote.getNumero())
                     .append(": ").append(lote.getDescricao()).append("\n");
            if (lote.getMaiorLance() != null) {
                resultado.append("  VENDIDO para: ")
                         .append(lote.getMaiorLance().getLicitante().getNome())
                         .append(" | Valor: R$ ")
                         .append(String.format("%.2f", lote.getMaiorLance().getValor()))
                         .append("\n");
            } else {
                resultado.append("  NAO VENDIDO (nenhum lance recebido)\n");
            }
        }
        return resultado.toString();
    }
 
    // Atividade 2: getNaoVendidos
    public ArrayList<Lote> getNaoVendidos() {
        ArrayList<Lote> todos = this.lotes.getTodosOsLotes();
        ArrayList<Lote> naoVendidos = new ArrayList<>();
        for (Lote lote : todos) {
            if (lote.getMaiorLance() == null) {
                naoVendidos.add(lote);
            }
        }
        return naoVendidos;
    }
 
    public void iniciaOperacao() {
        this.terminal.iniciaOperacao();
    }
}
