package algoritmogenetico;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author estef
 */
public class Main {

    public static void main(String args[]) {

        List<Produto> produtos = new ArrayList();
        produtos.add(new Produto("Produto01", 50.0, 100.0));
        produtos.add(new Produto("Produto02", 40.0, 20.0));
        produtos.add(new Produto("Produto03", 40.0, 140.0));
        produtos.add(new Produto("Produto04", 3.0, 112.0));
        produtos.add(new Produto("Produto05", 2.0, 115.0));
        produtos.add(new Produto("Produto06", 60.0,200.0));
        produtos.add(new Produto("Produto07", 40.0, 5.0));
        produtos.add(new Produto("Produto08", 20.0, 10.0));

        List nomes = new ArrayList();
        List pesos = new ArrayList();
        List valores = new ArrayList();
        
        for (Produto p : produtos) {
            nomes.add(p.getNome());
            pesos.add(p.getPeso());
            valores.add(p.getValor());

        }

        Double capMaxima = 120.0;
        int tamPopulacao = 15;
        Double taxaMutacao = 0.01;
        int nroGeracoes = 100;

        AlgoritmoGenetico ag = new AlgoritmoGenetico(tamPopulacao);
        List resultado = ag.resolverProblema(taxaMutacao, nroGeracoes, pesos, valores, capMaxima);

        System.out.println(" Produtos selecionados na solução: ");
        for (int i=0; i<produtos.size(); i++) {
            if(resultado.get(i).equals("1")){
                System.out.println(" | Nome: " + produtos.get(i).getNome()
                                 + "\n | Valor: R$" + produtos.get(i).getValor()
                                 + "\n | Peso: " + produtos.get(i).getPeso() + "kg");
            }

        }

    }
    
}
