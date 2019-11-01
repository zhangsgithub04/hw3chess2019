/**
 * Created by zhangs on 10/3/2019.
 */
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.Rectangle;
import java.awt.Color;


import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import javax.imageio.ImageIO;

import java.awt.event.MouseListener;

import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;






/**
 * This program demonstrates how to use Graphics2D to draw various graphic primitives.
 * @author Dr. S. Z.
 * images may be displayed slower than you expect.
 * If you download those images locally and display them, should be faster.
 * https://commons.wikimedia.org/wiki/Category:PNG_chess_pieces/Standard_transparent
 * GUI-Graphics, in the minimum you want to know how to respond to mouse click.
 * https://docs.oracle.com/javase/tutorial/uiswing/events/mouselistener.html
 */


public class ChessExample extends JFrame implements MouseListener, MouseMotionListener {


    int gamewidth; // so they can be visible to all instance functions
    int gameheight;
    int margin;

    chess co;

    BufferedImage imgbl=null; //light bishop
    BufferedImage imgkl=null; // light king
    BufferedImage imgkd=null; // dark king
    BufferedImage imgqd=null; // dark queen
    //!!! Expand this part


    boolean waitforsource;

    public ChessExample() {
        super("Chess Drawing Demo Fall 2019 ");

        waitforsource=true;

        co=new chess();

        gamewidth=700;
        gameheight=700;
        margin=50;

        //understand this loop !!!!
        // now draw game
        segheight=(gameheight-2*margin)/8; //here is how to calculate segment height

        //segwidth=60; //you need to calculate this like segheight;
        segwidth=(gamewidth-2*margin)/8;

        setSize(gamewidth, gameheight);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        loadallimages();

        //setVisible( true );
        setResizable(false);
        addMouseListener(this);
        addMouseMotionListener(this);


    }

    public void loadallimages()
    {
        try{

            imgbl=ImageIO.read(new URL("https://upload.wikimedia.org/wikipedia/commons/9/9b/Chess_blt60.png"));
            imgkl=ImageIO.read(new URL("https://upload.wikimedia.org/wikipedia/commons/3/3b/Chess_klt60.png"));
            imgkd=ImageIO.read(new URL("https://upload.wikimedia.org/wikipedia/commons/e/e3/Chess_kdt60.png"));
            imgqd=ImageIO.read(new URL("https://upload.wikimedia.org/wikipedia/commons/a/af/Chess_qdt60.png"));
            //!!! Expand this part

            // alternatively you can also download all images to local computer, and read them from there. faster
            //Image imgbl=ImageIO.read(new File("c:\\courses\\csci268\\Chess_blt60.png"));

        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }



    }
    int pxtocol(int px)
    {
        return (px-margin)/segwidth;
    }
    int pytorow(int py)
    {
        return (py-margin)/segheight;
    }

    int scol;
    int srow;
    int dcol;
    int drow;

    public void mouseClicked(MouseEvent e) {
        //System.out.println("Mouse clicked"+e.toString());
        //System.out.println(e.getX()+","+e.getY());

        int px=e.getX();
        int py=e.getY();

        int whichcol=pxtocol(px);
        int whichrow=pytorow(py); // you need to apply the above logic to this statement.

        System.out.println(whichrow+","+whichcol);

        if (waitforsource)
        {
            srow=whichrow;
            scol=whichcol;
            waitforsource=false;
        }
        else
        {
            drow=whichrow;
            dcol=whichcol;
            co.makeamove(srow, scol, drow, dcol);
            waitforsource=true;  //after second click, waiting for first click

            //revalidate();
            //invalidate(); //
            repaint(); // invoke paint function.

        }

    }


    public void mousePressed(MouseEvent e) {
        //System.out.println("Mouse pressed"+e.toString());
        //System.out.println(e.getX()+","+e.getY());
    }

    public void mouseReleased(MouseEvent e) {
        //System.out.println("Mouse released" + e.toString());
        //System.out.println(e.getX()+","+e.getY());
    }

    public void mouseEntered(MouseEvent e) {
        //System.out.println("Mouse entered"+ e.toString());
        //System.out.println(e.getX()+","+e.getY());
    }

    public void mouseExited(MouseEvent e) {
        //System.out.println("Mouse exited"+ e.toString());
        //System.out.println(e.getX()+","+e.getY());
    }


    public void mouseMoved(MouseEvent e) {
        //System.out.println("Mouse moved" + e.toString());
        //System.out.println(e.getX()+","+e.getY());
    }


    public void mouseDragged(MouseEvent e) {
        //System.out.println("Mouse dragged" + e.toString());
        //System.out.println(e.getX()+","+e.getY());
    }


    int segheight;
    int segwidth;

void sampledrawings(Graphics2D g2d)
{
    g2d.setColor(Color.yellow);

    g2d.fill(new Rectangle(0, 0, 480, 200));
    g2d.setColor(Color.black);

    g2d.drawLine(120, 50, 360, 50);

    g2d.draw(new Line2D.Double(59.2d, 99.8d, 419.1d, 99.8d));

    g2d.draw(new Line2D.Float(21.50f, 132.50f, 459.50f, 132.50f));

    g2d.draw(new Line2D.Float(21.50f, 132.50f, 459.50f, 132.50f));

    // Construct a square
    Rectangle s = new Rectangle(0, 0, 120, 60);
    //Graphics2D g2d = flag;
    g2d.setColor(Color.RED);
    g2d.fillRect(0,0,120,60);
    // draw the rectangle
    g2d.draw(s);



    BufferedImage image;

    try{

        image = ImageIO.read(new URL("https://upload.wikimedia.org/wikipedia/commons/9/9b/Chess_blt60.png"));
        int x = 20;
        int y = 20;

        g2d.drawImage(image, x, y, this);

        x=60;
        y=60;
        image = ImageIO.read(new URL("https://upload.wikimedia.org/wikipedia/commons/3/3b/Chess_klt60.png"));
        g2d.drawImage(image, x, y, this);
    }

    catch (Exception e)
    {
        System.out.println(e.toString());
    }

}
    void drawboard(Graphics2D g2d)
    {

        int startx, starty, endx, endy; // readability

        g2d.setColor(Color.YELLOW);
        g2d.fillRect(0,0,gamewidth,gameheight);
        int row, col;

        //drawsquares
        for(row=0; row<8; row++) // 8 segments, defined by night lines
        {
            for (col = 0; col < 8; col++) // 8 segments, defined by night lines
            {
                Color c = Color.GREEN;
                //!!! Do something here
                /*
                if ((col + row) % 2 == 0)
                    c=Color.WHITE;
                */
                g2d.setColor(c);
                g2d.fillRect(col * segwidth + margin + 1, row * segheight + margin + 1, segwidth - 2, segheight - 2);
            }
        }
            //drawlines
            for(row=0; row<9; row++) // 8 segments, defined by night lines
            {
                startx=margin;
                starty=row*segheight+margin;
                endx= gamewidth-margin;
                endy=starty;
                g2d.setColor(Color.RED);
                g2d.draw(new Line2D.Float(startx, starty, endx, endy));
            }

    }

    void drawgame(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        //sampledrawings(g2d); // showing some examples, need to be commented out in your final version.
        drawboard(g2d);
        updategamevisualization(g2d);
    }


    void updategamevisualization(Graphics2D g2d)
    {
        co.display();

        int x;
        int y;
        BufferedImage imgcurrent=null;

        for(int row=0;  row<8;  row++)
            for(int  col=0; col<8; col++)
            {
                if (co.game[row][col]==co.EMPTY)
                        continue;

                x= margin+col*segwidth;
                y = margin+row*segheight;

                    if (co.game[row][col]==co.LIGHTBISHOP)
                        imgcurrent = imgbl;
                    else if (co.game[row][col]==co.LIGHTKING)
                        imgcurrent = imgkl;
                    else if (co.game[row][col]==co.DARKKING)
                        imgcurrent = imgkd;
                    else if (co.game[row][col]==co.DARKQUEEN)
                        imgcurrent = imgqd;
                        //!!! Expand this part

                    else
                        imgcurrent=null;

                    if (imgcurrent!=null)
                        g2d.drawImage(imgcurrent, x, y, this);
                    else
                        g2d.drawString(String.valueOf(co.game[row][col]), x+segwidth/2,y+segheight/2);
            }

    }


/*
    public void paintComponent(Graphics g){

        //--- draw background
        g.setColor(Color.white);
        g.fillRect( 0, 0, 500, 500);
        //--- draw image
        if (imgbl!=null) g.drawImage(imgbl, 40, 100, this);
    }
*/

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        System.out.println("redraw");
        drawgame(g);
        co.display();


    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ChessExample().setVisible(true);
            }
        });
    }
}



// data model
class chess {
    char[][] game;
    int step;
    boolean gameover = false;
    static final char EMPTY = '-';
    static final char LIGHTPAWN = 'p';
    public static final char DARKPAWN = 'P';
    public static final char LIGHTROOK = 'r';
    public static final char DARKROOK = 'R';
    char LIGHTKNIGHT = 'n';
    char DARKKNIGHT = 'N';
    char LIGHTBISHOP = 'b';
    char DARKBISHOP = 'B';
    char LIGHTQUEEN = 'q';
    char DARKQUEEN = 'Q';
    char LIGHTKING = 'k';
    char DARKKING = 'K';

    public chess() {
        step=0;
        game = new char[8][8];

        game[7][0] = game[7][7] = LIGHTROOK;  //'r';
        game[0][0] = game[0][7] = DARKROOK; //'R'

        game[7][2] = game[7][5] = LIGHTBISHOP;  //'r';
        game[7][4] = LIGHTKING;  //'r';
        //!!! Expand this part
        // you need to do fill in  blank

        game[7][3] = LIGHTQUEEN;
        game[0][3] = DARKQUEEN;
        game[0][4] = DARKKING;

        for (int c = 0; c < 8; c++) {
            game[6][c] = LIGHTPAWN;
            // you need to figure out how to initialize dark PAWN
        }

        for (int r = 2; r <= 5; r++)
            for (int c = 0; c < 8; c++)
                game[r][c] = EMPTY;

    }


    void display()
    {
        for (int r = 0; r <8; r++)
        {
            for (int c = 0; c < 8; c++)
                System.out.print(game[r][c]);
            System.out.println();
        }


    }

    int whichside(char p)
    {
        if (Character.isUpperCase(p)) return 1; // dark side
        if (Character.isLowerCase(p)) return 0; // light side
        else return -1; // empty
    }
    /*
    check if a piece is lower case or not
    */

    boolean islightside(int row, int col)
    {

        boolean checkBool = Character.isLowerCase(game[row][col]);
        return checkBool;
    }

    //!!! you might want to use this signature, correspondingly change the way the function is called!
    //boolean makeamove(int srow,int scol, int drow, int  dcol)
    void makeamove(int srow,int scol, int drow, int  dcol)
    {
        //!!!  Do something here!

        // you should return immediately if index are out of boundary!
        //  also the character at the source position should not be EMPTY, should be consistent with step
        //  try to use step%2 and the above whichside function to check whether it is moving a valid side.
        /*
            More hints: We use a step counter, which is initialized to be zero and keeps track number of moves.
            Encoded all dark pieces using upper case letters , and light lower.
            Therefore, before making a move, if the step counter is odd, and the source piece is light, then return. If it is even, and .... then return.
            Basically, by checking the current step counter and upper case or lower case of the source piece, you can reject a move or continue to make the move.
        */
        if (step%2==1 && islightside(srow, scol)) return;

        System.out.println(srow+","+scol+","+drow+","+dcol);
        game[drow][dcol] = game[srow][scol];
        game[srow][scol] = EMPTY;
        step++; // counting on how many steps.
        // notice that legal move validation needs to be added later for a future course.
    }
}
