import model.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class App {
    public static void main(String[] args){
        // Inicializa o JPA usando o nome do Persistence Unit que você definiu
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("meu-pu");
        EntityManager em = emf.createEntityManager();

        Produto p = new Produto();
        p.setNome("Teclado Gamer");
        p.setPreco(200.0);
        p.setDescricao("legal");

        em.getTransaction().begin();
        em.persist(p); //Aqui o JPA faz o "Inset into produtos"
        em.getTransaction().commit();

        System.out.println("Salvo com sucesso!");

        em.close();
        emf.close();
    }

}
