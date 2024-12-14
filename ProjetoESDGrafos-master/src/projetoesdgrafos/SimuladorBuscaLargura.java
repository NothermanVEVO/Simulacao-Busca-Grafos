package projetoesdgrafos;

import projetoesdgrafos.utils.Utils;
import projetoesdgrafos.grafo.Grafo;

import javax.swing.JFrame;

import br.com.davidbuzatto.jsge.core.engine.EngineFrame;
import br.com.davidbuzatto.jsge.math.Vector2;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import projetoesdgrafos.grafo.Aresta;
import projetoesdgrafos.grafo.Vertice;

/**
 * Simulador de Busca em Largura.
 * 
 * @author Prof. Dr. David Buzatto
 */
public class SimuladorBuscaLargura extends EngineFrame {
    
    private Grafo grafo;
    int verticeFonte = -1;
    
    public SimuladorBuscaLargura() {
        super( 650, 500, "Busca em Largura", 60, true );
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
            String value = JOptionPane.showInputDialog(null, "Entre com o valor da busca do vertice fonte: ", "Valor da fonte", JOptionPane.QUESTION_MESSAGE);
            if (value != null) {
                if(value.matches("[+-]?[0-9]+")){
                    int num = Integer.parseInt(value);
                    if(num >= 0){
                        if(grafo.vertices.containsKey(num)){
                            verticeFonte = num;
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
        grafo.draw( this );
        if(verticeFonte >= 0){
            buscaLarguraInicial(grafo, verticeFonte);
        }
    }
    
    private boolean[] visitado;
    private int[] vizinhoDe;
    private int[] distanciaDe;
    
    public void buscaLarguraInicial(Grafo grafo, int verticeFonte){
        int quantidadeVertices = grafo.getQuantidadeVertices();
        
        visitado = new boolean[quantidadeVertices];  // Inicializa os arrays
        vizinhoDe = new int[quantidadeVertices];
        distanciaDe = new int[quantidadeVertices];
        
        for (int i = 0; i < quantidadeVertices; i++) {
            distanciaDe[i] = -1; 
        }
        
        buscaLargura(grafo, verticeFonte);
    }
    
    public void buscaLargura(Grafo grafo, int verticeFonte){
        ArrayList<Integer> fila = new ArrayList<Integer>();
        fila.add(verticeFonte);
        visitado[verticeFonte] = true;
        
        while(!fila.isEmpty()){
            int vertice = fila.remove(0);
           
            for(Aresta i : grafo.adjacentes(vertice)){
                
                Vector2 pontoOrigem = Utils.pontoNaCircunferencia(i.origem.pos, 
                                                                Vertice.VERTICE_SIZE, 
                                                                Math.atan2(i.destino.pos.y - i.origem.pos.y, i.destino.pos.x - i.origem.pos.x));
                
                Vector2 pontoDestino = Utils.pontoNaCircunferencia(i.destino.pos, 
                                                                Vertice.VERTICE_SIZE, 
                                                                Math.atan2(i.origem.pos.y - i.destino.pos.y, i.origem.pos.x - i.destino.pos.x));
                int destino = i.destino.id;
                    if (!visitado[destino]) {            
                    fila.add(destino);               
                    visitado[destino] = true;        
                    vizinhoDe[destino] = vertice;    
                    distanciaDe[destino] = distanciaDe[vertice] + 1; 
                    desenharSeta(pontoOrigem, pontoDestino, 7, 7);
                }

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
