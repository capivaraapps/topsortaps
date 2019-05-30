package app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Map.Entry;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        
        ArrayList<Aresta> arestas = new ArrayList<Aresta>();
        Scanner keyboard = new Scanner(System.in);

        while(arestas.size()<=2){
            System.out.println("Minimo de 'Largo' deve ser o grafo?");
            int MIN_PER_RANK = keyboard.nextInt();
            System.out.println("Maximo de 'Largo' deve ser o grafo?");
            int MAX_PER_RANK = keyboard.nextInt();
            System.out.println("Minimo de 'Alto' deve ser o grafo?");
            int MIN_RANKS = keyboard.nextInt();
            System.out.println("Maximo de 'Alto' deve ser o grafo?");
            int MAX_RANKS = keyboard.nextInt();
            System.out.println("% de provabilidade de fazer ligação com um novo vertice?");
            int PERCENT = keyboard.nextInt();

            System.out.println("Gerando Grafo...");
            arestas = gerarGrafo(MIN_PER_RANK, MAX_PER_RANK, MIN_RANKS, MAX_RANKS, PERCENT);
        }
        
        System.out.println("SIZE ----> " + arestas.size());
        
        // Cria um grafo e adicionamos as arestas
        Grafo g = new Grafo(arestas.size()); 
        for(Aresta a: arestas)
            g.addAresta(a.getX(), a.getY());
        
        System.out.println("Grafo ordenado topologicamente pelo algoritmo de Kahn:"); 
        g.topologicalSort(); 
    }

    public static ArrayList<Aresta> gerarGrafo(int MIN_PER_RANK, int MAX_PER_RANK, int MIN_RANKS, int MAX_RANKS, int PERCENT){
        // final int MIN_PER_RANK = 1; /* Nodes/Rank: How 'fat' the DAG should be.  */
        // final int MAX_PER_RANK = 5;
        // final int MIN_RANKS = 3;    /* Ranks: How 'tall' the DAG should be.  */
        // final int MAX_RANKS = 5;
        // final int PERCENT = 30;
        int i, j, k, vertices = 0;
        ArrayList<Aresta> arestasGeradas = new ArrayList<Aresta>();
        Random r = new Random();

        int ranks = MIN_RANKS + (r.nextInt() % (MAX_RANKS - MIN_RANKS + 1));

        for (i = 0; i < ranks; i++){
            // New nodes of 'higher' rank than all nodes generated till now.
            int new_nodes = MIN_PER_RANK
                            + (r.nextInt() % (MAX_PER_RANK - MIN_PER_RANK + 1));

            // Edges from old nodes ('nodes') to new ones ('new_nodes'). 
            for (j = 0; j < vertices; j++){
                for (k = 0; k < new_nodes; k++){
                    if ( (r.nextInt() % 100) < PERCENT){
                        arestasGeradas.add(new Aresta(j, k+vertices));
                        System.out.println("  "+j+" -> "+(k+vertices)); // An Edge.  
                    }
                }
            }
            vertices += new_nodes; // Accumulate into old node set. 
        }

        return arestasGeradas;
    }
}

class Aresta{
    private int x;
    private int y;

    Aresta(){ this.x=0; this.y=0; }
    Aresta(int x, int y){ this.x=x; this.y=y; }

    public int getX(){return this.x;}
    public int getY(){return this.y;}
}