package Lab08;

import java.util.ArrayList;
import java.util.List;

public class PlataformaEduTech {
    public static void main(String[] args) {

        // ── 1. Criar objetos de todas as classes concretas
        VideoAula va = new VideoAula("Introdução à POO", 90, "Prof. Raimundo");
        Podcast pod = new Podcast("Papo de Dev", 60, "Ana Souza");
        ArtigoTexto art = new ArtigoTexto("Clean Code na Prática", 15, "Robert Martin");
        LiveStream live = new LiveStream("Maratona Java ao Vivo", 120, "Carlos Lima");
        Questionario quiz = new Questionario("Prova de POO - Módulo 1", 10);

        pod.adicionarConvidado("Bruno Costa");
        pod.adicionarConvidado("Marina Silva");

        // ── 2. Lista polimórfica de ConteudoDigital
        List<ConteudoDigital> conteudos = new ArrayList<>();
        conteudos.add(va);
        conteudos.add(pod);
        conteudos.add(art);
        conteudos.add(live);
        conteudos.add(quiz);

        // ── 3. Exibir detalhes (polimorfismo por classe abstrata)
        System.out.println("========================================");
        System.out.println("   CATÁLOGO DE CONTEÚDOS - EduTech");
        System.out.println("========================================");
        for (ConteudoDigital c : conteudos) {
            c.exibirDetalhes(); // cada subclasse executa seu próprio método
        }

        // ── 4. Listas específicas por interface
        List<Reproduzivel> reproduziveis = new ArrayList<>();
        List<Avaliavel> avaliaveis = new ArrayList<>();
        List<Imprimivel> imprimiveis = new ArrayList<>();

        for (ConteudoDigital c : conteudos) {
            if (c instanceof Reproduzivel) reproduziveis.add((Reproduzivel) c);
            if (c instanceof Avaliavel)    avaliaveis.add((Avaliavel) c);
            if (c instanceof Imprimivel)   imprimiveis.add((Imprimivel) c);
        }

        // ── 5.1. Testar reprodução
        System.out.println("\n========================================");
        System.out.println("   REPRODUÇÃO");
        System.out.println("========================================");
        for (Reproduzivel r : reproduziveis) {
            r.reproduzir();
        }

        // ── 5.2. Testar avaliações
        System.out.println("\n========================================");
        System.out.println("   AVALIAÇÕES");
        System.out.println("========================================");
        va.registrarAvaliacao(9);
        va.registrarAvaliacao(8);
        va.registrarAvaliacao(10);
        pod.registrarAvaliacao(7);
        pod.registrarAvaliacao(9);
        art.registrarAvaliacao(8);
        live.registrarAvaliacao(10);
        live.registrarAvaliacao(10);

        System.out.println("\n--- Médias finais ---");
        for (Avaliavel a : avaliaveis) {
            // Polimorfismo por interface: não sabemos o tipo concreto, apenas que é Avaliavel
            System.out.printf("Média: %.1f/10%n", a.getMediaAvaliacoes());
        }

        // ── 5.3. Testar impressão
        System.out.println("\n========================================");
        System.out.println("   IMPRESSÃO");
        System.out.println("========================================");
        for (Imprimivel i : imprimiveis) {
            i.imprimir();
        }

        // ── 5.4. Testar chat da live
        System.out.println("\n========================================");
        System.out.println("   CHAT AO VIVO");
        System.out.println("========================================");
        live.enviarMensagemChat("Ótima aula, professor!");
        live.enviarMensagemChat("Pode repetir o exemplo de herança?");
        live.enviarMensagemChat("Obrigado! Muito didático.");

        // ── Demonstração: mesmo objeto em duas listas
        // 'live' está em: conteudos, reproduziveis, avaliaveis — ao mesmo tempo.
        System.out.println("\n========================================");
        System.out.println("   DETALHES FINAIS APÓS INTERAÇÕES");
        System.out.println("========================================");
        live.exibirDetalhes();
        va.exibirDetalhes();
    }
}