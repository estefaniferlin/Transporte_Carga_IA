package algoritmogenetico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author estef
 */
public class AlgoritmoGenetico {
    private int tamPopulacao;
    private List<Individuo> populacao = new ArrayList();
    private Individuo geracao;
    private Individuo melhorSolucao;

    public AlgoritmoGenetico(int tamPopulacao) {
        this.tamPopulacao = tamPopulacao;
    }

    public void inicializaPopulacao(List peso, List valor, Double capMaxima) {
        for(int i=0; i<tamPopulacao; i++){
            this.populacao.add(new Individuo(peso, valor, capMaxima));
        }
        
        this.melhorSolucao = this.populacao.get(0);
    }

    public void melhorIndividuo(Individuo individuo) {
        if(individuo.getNotaAvaliacao() > this.melhorSolucao.getNotaAvaliacao()){
            this.melhorSolucao = individuo;
        }
    }

    public void ordenaPopulacao() {
        Collections.sort(this.populacao);
    }

    public Double somaAvaliacoes() {
        Double soma = 0.0;
        for(Individuo i : this.populacao){
            soma += i.getNotaAvaliacao();
        }
        
        return soma;
    }

    public int selecionaPai(Double somaAvaliacao) {
        int pai = -1;
        Double valorSorteado = Math.random() * somaAvaliacao;
        Double soma = 0.0;

        int i = 0;
        while(i < this.populacao.size() && soma < valorSorteado) {
            soma += this.populacao.get(i).getNotaAvaliacao();
            pai += 1;
            i += 1;
        }

        return pai;
    }

    public void visualizaGeracao() {
        Individuo melhor = this.populacao.get(0);

        System.out.println("\n\n**** MELHOR SOLUÇÃO: ****");
        System.out.println(" Geração: " + this.melhorSolucao.getGeracao()
                         + "\n Valor total: R$" + this.melhorSolucao.getNotaAvaliacao()
                         + "\n Capacidade usada: " + this.melhorSolucao.getCargaUsada() + "KG (de " + this.melhorSolucao.getCargaMax() + "KG)"
                         + "\n Cromossomo: " + this.melhorSolucao.getCromossomo());

    }

    public List resolverProblema(Double taxaMutacao, int numeroGeracoes, List pesos, List valores, Double capMaxima) {
        this.inicializaPopulacao(pesos, valores, capMaxima);

        for(Individuo i : this.populacao){
            i.avaliacao();
        }
            
        this.ordenaPopulacao();
        this.visualizaGeracao();

        for(int gen=0; gen<numeroGeracoes; gen++){

            Double somaAvaliacao = this.somaAvaliacoes();
            List<Individuo> novaPopulacao = new ArrayList();

            for(int j=0; j<this.populacao.size()/2; j++) {

                int pai1 = this.selecionaPai(somaAvaliacao);
                int pai2 = this.selecionaPai(somaAvaliacao);

                List<Individuo> filhos = this.getPopulacao().get(pai1).crossover(this.populacao.get(pai2));
                novaPopulacao.add(filhos.get(0).mutacao(taxaMutacao));
                novaPopulacao.add(filhos.get(1).mutacao(taxaMutacao));
            }

            this.setPopulacao(novaPopulacao);

            for(Individuo i : this.populacao){
                i.avaliacao();
            }

            this.ordenaPopulacao();
            this.visualizaGeracao();
            Individuo melhor = this.populacao.get(0);
            this.melhorIndividuo(melhor);

        }

        System.out.println("\n\n MELHOR SOLUÇÃO DO PROBLEMA: ");
        System.out.println(" Geração: " + this.melhorSolucao.getGeracao()
                         + "\n Valor total: R$" + this.melhorSolucao.getNotaAvaliacao()
                         + "\n Capacidade usada: " + this.melhorSolucao.getCargaUsada() + "kg de " + this.melhorSolucao.getCargaMax() + "kg)"
                         + "\n Cromossomo: " + this.melhorSolucao.getCromossomo()
        );

        return this.melhorSolucao.getCromossomo();

    }

    public int getTamPopulacao() {
        return tamPopulacao;
    }

    public void setTamPopulacao(int tamPopulacao) {
        this.tamPopulacao = tamPopulacao;
    }

    public List<Individuo> getPopulacao() {
        return populacao;
    }

    public void setPopulacao(List<Individuo> populacao) {
        this.populacao = populacao;
    }

    public Individuo getGeracao() {
        return geracao;
    }

    public void setGeracao(Individuo geracao) {
        this.geracao = geracao;
    }

    public Individuo getMelhorSolucao() {
        return melhorSolucao;
    }

    public void setMelhorSolucao(Individuo melhorSolucao) {
        this.melhorSolucao = melhorSolucao;
    }
    
}
