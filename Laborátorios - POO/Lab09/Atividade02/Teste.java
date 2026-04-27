package Lab09.Atividade02;

public class Teste {
    
    public static void main(String[] args) {
 
        CatalogoProdutos catalogo = new CatalogoProdutos();
 
        catalogo.adicionarProduto("Teclado Mecânico", 349.90);
        catalogo.adicionarProduto("Mouse Gamer", 199.90);
        catalogo.adicionarProduto("Monitor 24\"", 1199.00);
        catalogo.adicionarProduto("Headset USB", 249.90);
        catalogo.adicionarProduto("Webcam HD", 199.90); // mesmo preço que Mouse — desempate por ID
        catalogo.adicionarProduto("SSD 480GB", 399.00);
        catalogo.adicionarProduto("Mousepad XL", 89.90);
 
        System.out.println("=== LISTAGEM POR PREÇO ===");
        catalogo.listarProdutos();
 
        //Ordenação por nome
        System.out.println("\n=== LISTAGEM POR NOME ===");
        catalogo.listarPorNome();
 
        //Faixa de preços
        System.out.println("\n=== FAIXA R$150,00 a R$350,00 ===");
        catalogo.listarPorFaixaDePreco(150.00, 350.00);
 
        //Remoção por nome
        System.out.println("\n=== REMOVENDO 'Headset USB' ===");
        catalogo.removerPorNome("Headset USB");
        catalogo.removerPorNome("Impressora"); // inexistente
 
        System.out.println("\n=== CATÁLOGO APÓS REMOÇÃO ===");
        catalogo.listarProdutos();
 
        //Dois produtos com mesmo preço — desempate por ID
        System.out.println("\n=== DESEMPATE POR ID (Mouse e Webcam, ambos R$199,90) ===");
        System.out.println("Ordem esperada: Mouse (ID menor) antes de Webcam (ID maior)");
    }
}
