import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;

class Surface extends JPanel implements ActionListener
{
    private final int FPS = 5;
    private final int DELAY = 1000 / FPS;
    private Timer timer;

    public Surface()
    {
        initTimer();
    }

    private void initTimer()
    {
        timer = new Timer(DELAY, this);
        // timer.start();
    }

    public Timer getTimer()
    {

        return timer;
    }

    private void doDrawing(Graphics g) throws IOException
    {
        Graphics2D g2d = (Graphics2D) g;

        Random r = new Random();
        /*
            int width = getWidth();
            int height = getHeight();

            for (int i = 0; i < width*height; i++) {
            g2d.setPaint(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
            int x = Math.abs(r.nextInt()) % width;
            int y = Math.abs(r.nextInt()) % height;
            g2d.drawLine(x, y, x, y);
        }
         */


        BMP bmp = new BMP("1.bmp");
        bmp.showInfo();
        drawBMP(g2d, bmp, 0, 0);
        // bmp.changeBrightness(-50);
        // bmp.negative();

        BMP bmp2 = new BMP("1.bmp");
        bmp2.crypt();
        drawBMP(g2d, bmp2, bmp2.width + 1, 0);
    }

    private void drawBMP(Graphics2D g2d, BMP bmp, int left, int top)
    {
        int k = bmp.dataOffset;
        for (int y = top; y < bmp.height; y++)
        {
            for (int x = left; x < bmp.width + left; x++)
            {
                drawPoint(g2d, x, bmp.height - y, new Color(bmp.data.get(k + 2), bmp.data.get(k + 1), bmp.data.get(k)));
                k += 3;
            }
        }
    }

    private void drawPoint(Graphics2D g2d, int x, int y, Color color)
    {
        g2d.setPaint(color);
        g2d.drawLine(x, y, x, y);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        try
        {
            doDrawing(g);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        repaint();
    }
}