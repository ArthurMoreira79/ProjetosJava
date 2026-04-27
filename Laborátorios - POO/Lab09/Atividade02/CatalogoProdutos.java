package Lab09.Atividade02;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class CatalogoProdutos {//TreeSet ordenado por preço(e ID em empate) via Comparable
    
    private Set<Produto> produtos;

    public CatalogoProdutos(){
        this.produtos = new TreeSet<>();
    }

    public void adicionarProduto(String nome, double preco){
        produtos.add(new Produto(nome, preco));
    }

    //Listagem padrão: por preço crescente
    public void listarProdutos(){
        if(produtos.isEmpty()){
            System.out.println("Catálogo vazio.");
            return;
        }
        System.out.println("--- Catálogo (por preço) ---");
        for(Produto p : produtos){
            System.out.println(p);
        }
    }

    //Listagem por nome em ordem alfabética via comparator
    public void listarPorNome(){
        Comparator<Produto> porNome = Comparator.comparing(Produto :: getNome);
        Set<Produto> ordenaPorNome = new TreeSet<>(porNome);
        ordenaPorNome.addAll(produtos);
        System.out.println("--- Catálogo (por nome) ---");
        for(Produto p : ordenaPorNome){
            System.out.println(p);
        }
    }

    //Produtos dentro de uma faixa de preços
    public void listarPorFaixaDePreco(double min, double max){
        System.out.printf("--- Produtos entre R$ %.2f e R$ %.2f ---%n", min, max);
        boolean achou = false;
        for(Produto p : produtos){
            if(p.getPreco() >= min && p.getPreco() <= max){
                System.out.println(p);
                achou = true;
            }
        }
        if (!achou) System.out.println("Nenhum produto nessa faixa.");
    }

    //Remover produto pelo nome
    public void removerPorNome(String nome){
        Iterator<Produto> it = produtos.iterator();
        while(it.hasNext()){
            if(it.next().getNome().equalsIgnoreCase(nome)){
                it.remove();
                System.out.println("Produto \"" + nome + "\" removido.");
                return;
            }
        }
        System.out.println("Produto \"" + nome + "\" não encontrado.");
    }
}
