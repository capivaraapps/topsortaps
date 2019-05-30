
package app;

import java.util.*; 

class Grafo{ 
    int numVertices; // Numero de vertices 
    List <Integer> adj[]; //Um Array de Listas que contem referencias a lista de "filhos" de cada vertice
    
    public Grafo(int numVertices){ 
        this.numVertices = numVertices; 
        adj = new ArrayList[numVertices]; 
        for(int i = 0; i < numVertices; i++) 
            adj[i]=new ArrayList<Integer>(); 
    } 
      
    // Funçaõ para adicionar uma aresta ao grafo
    public void addAresta(int u,int v){ 
        adj[u].add(v);
    }
    
    // Mostra por console o ordenamento topologico do grafo
    public void topologicalSort(){ 
        // Cria um array para armazenar o grau de todos os vertices. Todos são inicializados a zero
        int grau[] = new int[numVertices]; 
          
        // Percorre as listas de "filhos" para encher os graus de cada vertice (O(V+E))
        for(int i = 0; i < numVertices; i++){ 
            ArrayList<Integer> temp = (ArrayList<Integer>) adj[i]; 
            for(int vertice : temp){
                grau[vertice]++; 
            }
        }
          
        // Cria uma fila e adiciona todos os vertices com grau 0
        Queue<Integer> q = new LinkedList<Integer>(); 
        for(int i = 0;i < numVertices; i++){ 
            if(grau[i]==0) 
                q.add(i); 
        }
          
        // Inicializa o contador dos vertices visitados
        int cont = 0; 
          
        // Cria o vetor para armazenar o resultado
        Vector <Integer> topSort=new Vector<Integer>(); 
        while(!q.isEmpty()){ 
            // Extraimos o frente da fila e adicionamos ele no vetor resultado
            int u=q.poll(); 
            topSort.add(u); 
              
            // Percorremos todos seus vertices "filhos" do vertice e decrementamos seus graus em 1
            for(int vertice : adj[u]){ 
                // Se o grau é 0 adicionamos o vertice a fila
                grau[vertice]--;
                if(grau[vertice] == 0) 
                    q.add(vertice); 
            } 
            cont++; 
        } 
          
        // Verificamos se existe algum ciclo
        if(cont != numVertices){ 
            System.out.println("O grafo não é DAG!"); 
            return ; 
        } 
          
        // Mostramos por consola em ordem topologico            
        for(int i : topSort){ 
            System.out.print(i+" "); 
        } 
    } 
} 