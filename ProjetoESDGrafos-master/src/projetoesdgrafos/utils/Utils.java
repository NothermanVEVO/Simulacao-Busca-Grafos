package projetoesdgrafos.utils;

import br.com.davidbuzatto.jsge.math.Vector2;
import projetoesdgrafos.grafo.Vertice;
import projetoesdgrafos.grafo.Grafo;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Prof. Dr. David Buzatto
 */
public interface Utils {
    
    public static Grafo criarGrafoTeste() {
        
        Grafo grafo = new Grafo();
        Map<Integer, Vertice> st = new TreeMap<>();
        
        grafo.addVertice( 100, 100 );
        grafo.addVertice( 150, 200 );
        grafo.addVertice( 250, 200 );
        grafo.addVertice( 200, 300 );
        grafo.addVertice( 300, 300 );
        grafo.addVertice( 100, 400 );
        grafo.addVertice( 350, 200 );
        grafo.addVertice( 450, 150 );
        grafo.addVertice( 550, 150 );
        grafo.addVertice( 450, 250 );
        grafo.addVertice( 550, 250 );
        grafo.addVertice( 450, 350 );
        grafo.addVertice( 550, 350 );
        
        grafo.addAresta( 0, 5 );
        grafo.addAresta( 4, 3 );
        grafo.addAresta( 0, 1 );
        grafo.addAresta( 9, 12 );
        grafo.addAresta( 6, 4 );
        grafo.addAresta( 5, 4 );
        grafo.addAresta( 0, 2 );
        grafo.addAresta( 11, 12 );
        grafo.addAresta( 9, 10 );
        grafo.addAresta( 0, 6 );
        grafo.addAresta( 7, 8 );
        grafo.addAresta( 9, 11 );
        grafo.addAresta( 5, 3 );
        
        return grafo;
        
    }
    
}
