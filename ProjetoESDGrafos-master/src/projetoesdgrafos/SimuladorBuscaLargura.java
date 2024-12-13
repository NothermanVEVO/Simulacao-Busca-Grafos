package projetoesdgrafos;

import projetoesdgrafos.utils.Utils;
import projetoesdgrafos.grafo.Grafo;

import javax.swing.JFrame;

import br.com.davidbuzatto.jsge.core.engine.EngineFrame;

/**
 * Simulador de Busca em Largura.
 * 
 * @author Prof. Dr. David Buzatto
 */
public class SimuladorBuscaLargura extends EngineFrame {
    
    private Grafo grafo;
    
    public SimuladorBuscaLargura() {
        super( 650, 500, "Busca em Largura", 60, true );
        setAlwaysOnTop(true);
        setAlwaysOnTop(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    @Override
    public void create() {
        grafo = Utils.criarGrafoTeste();
        // System.out.println( grafo );
        Grafo.buscaEmLargura(grafo, 0);
        setDefaultFontSize( 20 );
        setDefaultStrokeLineWidth( 2 );
        setDefaultStrokeEndCap( STROKE_CAP_ROUND );
    }
    
    @Override
    public void update( double delta ) {
    }
    
    @Override
    public void draw() {
        clearBackground( WHITE );
        drawText( "Clique para escolher a fonte e executar o algoritmo.", 10, 10, BLACK );
        grafo.draw( this );
    }
    
}
