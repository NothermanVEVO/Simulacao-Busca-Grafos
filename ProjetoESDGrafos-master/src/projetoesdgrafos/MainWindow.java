package projetoesdgrafos;

import br.com.davidbuzatto.jsge.collision.CollisionUtils;
import br.com.davidbuzatto.jsge.core.engine.EngineFrame;
import br.com.davidbuzatto.jsge.geom.Rectangle;
import br.com.davidbuzatto.jsge.image.Image;
import br.com.davidbuzatto.jsge.math.Vector2;

import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class MainWindow extends EngineFrame {

    BufferedImage matrixBImage;
    Image matrixImage;

    Rectangle largura = new Rectangle(65, 325, 150, 75);
    double larguraMult = 0.0;

    Rectangle profundidade = new Rectangle(410, 325, 215, 75);
    double profundidadeMult = 0.0;

    public MainWindow(){
        super( 650, 500, "Janela Principal", 60, true );
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @Override
    public void create() {
        try {
            matrixBImage = ImageIO.read(new File("src\\projetoesdgrafos\\img\\matrix.jpg"));
            matrixImage = new Image(toBufferedImage(matrixBImage.getScaledInstance(650, 500, BufferedImage.SCALE_DEFAULT)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw() {
        drawImage(matrixImage, 0, 0);

        setFontSize(100);
        drawText("Take", new Vector2(0, 10), WHITE);
        drawText("your", new Vector2(400, 10), WHITE);
        drawText("choice.", new Vector2(135, 170), WHITE);

        Vector2 largSize = new Vector2(largura.width, largura.height).scale(Math.abs(1.0 + larguraMult));
        Vector2 largPos = new Vector2(largura.x + ((largura.width - largSize.x) / 2), largura.y + ((largura.height - largSize.y) / 2));
        fillRoundRectangle(new Vector2(largPos.x, largPos.y), largSize, 10, WHITE);
        setFontSize(35);
        drawText("Largura", new Vector2(largura.x, largura.y + 25), BLACK);

        Vector2 profundSize = new Vector2(profundidade.width, profundidade.height).scale(1.0 + profundidadeMult);
        Vector2 profundPos = new Vector2(profundidade.x + ((profundidade.width - profundSize.x) / 2), profundidade.y + ((profundidade.height - profundSize.y) / 2));
        fillRoundRectangle(new Vector2(profundPos.x, profundPos.y), profundSize, 10, WHITE);
        setFontSize(30);
        drawText("Profundidade", new Vector2(profundidade.x, profundidade.y + 30), BLACK);
    }

    @Override
    public void update(double delta) {
        if(CollisionUtils.checkCollisionPointRectangle(getMousePositionPoint(), largura)){
            setMouseCursor(12);
            larguraMult = 0.1;
            if(isMouseButtonPressed(1)){
                this.setState(Frame.ICONIFIED);
                try {
                    Thread.sleep(500); // PRA FICAR CHIQUE (DESNECESSARIO kk)
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Open largura");
                new SimuladorBuscaLargura();
            }
        } else if(CollisionUtils.checkCollisionPointRectangle(getMousePositionPoint(), profundidade)){
            setMouseCursor(12);
            profundidadeMult = 0.1;
            if(isMouseButtonPressed(1)){
                this.setState(Frame.ICONIFIED);
                try {
                    Thread.sleep(500); // PRA FICAR CHIQUE (DESNECESSARIO kk)
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Open profundidade");
                new SimuladorBuscaProfundidade();
            }
        } else{
            setMouseCursor(0);
            larguraMult = 0.0;
            profundidadeMult = 0.0;
        }
    }

    public static void main(String[] args){
        new MainWindow();
    }
    
    public static BufferedImage toBufferedImage(java.awt.Image img) {
        if (img instanceof BufferedImage)
        {
            return (BufferedImage) img;
        }

        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        return bimage;
    }

}
