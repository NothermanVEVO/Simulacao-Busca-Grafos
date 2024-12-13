package projetoesdgrafos;

import projetoesdgrafos.utils.Utils;
import projetoesdgrafos.grafo.Aresta;
import projetoesdgrafos.grafo.Grafo;
import projetoesdgrafos.grafo.Vertice;

import java.awt.BasicStroke;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import br.com.davidbuzatto.jsge.core.engine.EngineFrame;
import br.com.davidbuzatto.jsge.math.Vector2;

/**
 * Simulador de Busca em Profundidade.
 * 
 * @author Prof. Dr. David Buzatto
 */
public class SimuladorBuscaProfundidade extends EngineFrame {
    
    private Grafo grafo;

    private static int verticeInicial = -1;
    
    public SimuladorBuscaProfundidade() {
        super( 650, 500, "Busca em Profundidade", 60, true );
        setAlwaysOnTop(true);
        setAlwaysOnTop(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    @Override
    public void create() {
        grafo = Utils.criarGrafoTeste();
        setDefaultFontSize( 20 );
        setDefaultStrokeLineWidth( 2 );
        setDefaultStrokeEndCap( STROKE_CAP_ROUND );
    }
    
    @Override
    public void update( double delta ) {
        if (isMouseButtonPressed(1)) {
            String value = JOptionPane.showInputDialog(null, "Entre com o valor da fonte: ", "Valor da fonte", JOptionPane.QUESTION_MESSAGE);
            if (value != null) {
                if(value.matches("[+-]?[0-9]+")){
                    int num = Integer.parseInt(value);
                    if(num >= 0){
                        if(grafo.vertices.containsKey(num)){
                            verticeInicial = num;
                        } else{
                            JOptionPane.showMessageDialog(null, "Você deve entrar com uma fonte valida", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    } else{
                        JOptionPane.showMessageDialog(null, "Você deve entrar com um valor POSITIVO!", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } else{
                    JOptionPane.showMessageDialog(null, "Você deve entrar com um valor do tipo INTEIRO!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    @Override
    public void draw() {
        clearBackground( WHITE );
        drawText( "Clique para escolher a fonte e executar o algoritmo.", 10, 10, BLACK );
        setDefaultFontSize( 20 );
        setDefaultStrokeLineWidth( 2 );
        setDefaultStrokeEndCap( STROKE_CAP_ROUND );
        grafo.draw( this );

        setStroke(new BasicStroke(5));
        buscaEmProfundidade(grafo, verticeInicial);
    }

    public void buscaEmProfundidade(Grafo grafo, int verticeInicial) {
        if (verticeInicial < 0) {
            return;
        }
        Set<Vertice> visitados = new HashSet<>();
        Vertice inicial = grafo.vertices.get(verticeInicial);
        profundidadeRecursivo(grafo, inicial, visitados);
    }

    private void profundidadeRecursivo(Grafo grafo, Vertice vertice, Set<Vertice> visitados) {
        visitados.add(vertice);

        for (Aresta aresta : grafo.adjacentes(vertice.id)) {
            Vector2 pontoOrigem = Utils.pontoNaCircunferencia(vertice.pos, 
                                                                Vertice.VERTICE_SIZE, 
                                                                Math.atan2(aresta.destino.pos.y - vertice.pos.y, aresta.destino.pos.x - vertice.pos.x));
            Vector2 pontoDestino = Utils.pontoNaCircunferencia(aresta.destino.pos, 
                                                                Vertice.VERTICE_SIZE, 
                                                                Math.atan2(vertice.pos.y - aresta.destino.pos.y, vertice.pos.x - aresta.destino.pos.x));
            if (!visitados.contains(aresta.destino)) {
                // vertice.id + " -> " + aresta.destino.id
                desenharSeta(pontoOrigem, pontoDestino, 7, 7);
                profundidadeRecursivo(grafo, aresta.destino, visitados);
            }
        }
    }

    public void desenharSeta(Vector2 origem, Vector2 destino, int larguraPonta, int alturaPonta) {
        drawLine(new Vector2(origem.x, origem.y), new Vector2(destino.x, destino.y), RED);

        double angulo = Math.atan2(destino.y - origem.y, destino.x - origem.x);

        int xPonta1 = (int) (destino.x - larguraPonta * Math.cos(angulo - Math.PI / 6));
        int yPonta1 = (int) (destino.y - larguraPonta * Math.sin(angulo - Math.PI / 6));
        int xPonta2 = (int) (destino.x - larguraPonta * Math.cos(angulo + Math.PI / 6));
        int yPonta2 = (int) (destino.y - larguraPonta * Math.sin(angulo + Math.PI / 6));

        drawLine(destino, new Vector2(xPonta1, yPonta1), RED);
        drawLine(destino, new Vector2(xPonta2, yPonta2), RED);
    }
    
    
}
