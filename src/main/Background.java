package main;

import database.Entity.Dish;
import utils.Load;
import utils.Search;
import utils.Set;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Background extends JPanel {

    public Image backgroundImg;
    public Integer x;
    public Integer y;
    public Integer width;
    public int interval=10;
    public Integer height;
    public double horizontalSize=0.4;
    public double verticalSize=0.4;
    public Image []backgroundImgs=new Image[3];
    public Image []searchResult=new Image[4];
    public ArrayList<Dish> tempDishes=new ArrayList<>();
    public Image randomImage=null;
    public int []tmpWidth=new int[3];
    public int []tmpHeight=new int[3];
    JButton search=new JButton("搜索");
    JButton generate=new JButton("随机生成");
    JTextField text=new JTextField();
    public String randomString="";
    public String searchString="";
    public Background() {
        setOpaque(false);
        setBounds(0, 0, Main.WIDTH, Main.HEIGHT);
        this.add(search);
        search.setBounds(110,200,100,50);
        generate.setBounds(110,330,100,50);
        text.setBounds(20,100,283,50);
        search.setVisible(true);
        search.addActionListener(new ActionListener() {        //为按钮添加点击事件监听器
             @Override
             public void actionPerformed(ActionEvent e) {
                 if(text.getText().length()>0&&!text.getText().startsWith(" ")){
                     tempDishes= Search.SearchByName(Main.Dishes,text.getText());
                     searchResult[0]=Load.image("dishes/"+tempDishes.get(0).path);
                     repaint();
                 }
             }
         });
        generate.setVisible(true);
        generate.addActionListener(new ActionListener() {        //为按钮添加点击事件监听器
            @Override
            public void actionPerformed(ActionEvent e) {
                Random randoms=new Random(System.currentTimeMillis());
                int random = (int) (randoms.nextDouble()*(Main.Dishes.size()-1)+1);
                while(!Main.Dishes.get(random).isOutrageous){
                    random = (int) (randoms.nextDouble()*(Main.Dishes.size()-1)+1);
                }
                randomImage=Load.image("dishes/"+Main.Dishes.get(random).path);
                randomString="「"+Main.Dishes.get(random).name+"」";
                repaint();
            }
        });
        text.setVisible(true);
        this.add(generate);
        this.add(text);
        setVisible(true);
        setLayout(null);
        backgroundImg = Load.image("setup/背景.png");
    }
    public void paintBasic(Graphics g){
        setBounds(0, 0, Main.WIDTH, Main.HEIGHT);
        setVisible(true);
        setLayout(null);

        g.drawImage(backgroundImg, 0,0,Main.WIDTH,Main.HEIGHT,0, 0, backgroundImg.getWidth(null), backgroundImg.getHeight(null),null);
        for(int id=0;id<3;id++){
            switch(id){
                case 0:
                    x=interval;
                    y=interval;
                    tmpWidth[0]=width=(int)(Main.WIDTH*(horizontalSize)-1.5*interval);
                    tmpHeight[0]=height=(int)(Main.HEIGHT*(verticalSize)-1.5*interval);
                    break;
                case 2:
                    x=interval;
                    y=(int)(Main.HEIGHT*(verticalSize)-1.5*interval)+interval+(int)(0.5*interval);
                    tmpWidth[2]=width=(int)(Main.WIDTH*(horizontalSize)-1.5*interval);
                    tmpHeight[2]=height=(Main.HEIGHT)-(int)(Main.HEIGHT*(verticalSize)-1.5*interval)-6*interval;
                    break;
                case 1:
                    x=2*interval+(int)(Main.WIDTH*(horizontalSize)-1.5*interval);
                    y=interval;
                    tmpWidth[1]=width=Main.WIDTH-5*interval-(int)(Main.WIDTH*(horizontalSize)-1.5*interval);
                    tmpHeight[1]=height=Main.HEIGHT-6*interval;
                    break;
            }
            backgroundImgs[id]= Load.image("setup/"+id+".png");
            Graphics2D g_2d = (Graphics2D) g;
            g_2d.setColor(new Color(0, 0, 0,5));
            Rectangle2D rect = new Rectangle2D.Double(x,y,width,height);
            Rectangle2D rect2 = new Rectangle2D.Double(x+1,y+1,width-2,height-2);
            Rectangle2D rect3 = new Rectangle2D.Double(x+2,y+2,width-4,height-4);
            g_2d.fill(rect);
            g_2d.fill(rect2);
            g_2d.fill(rect3);
            g.drawImage(backgroundImgs[id], x+3,y+3,x+3+width-6,y+3+height-6,0, 0, backgroundImgs[id].getWidth(null), backgroundImgs[id].getHeight(null),null);
        }

        Font f = new Font("思源宋体", Font.BOLD, Set.fontByWidth(tmpWidth[0]/6,"搜索"));
        FontMetrics fm = sun.font.FontDesignMetrics.getMetrics(f);
        Main.canvas.drawCenteredStringByOutline(g,"搜索",tmpWidth[0],interval,1,f,interval+fm.getHeight(),Color.BLACK,Color.white);
        f = new Font("思源宋体", Font.BOLD, Set.fontByWidth(tmpWidth[2]/3,"手气不错"));
        fm = sun.font.FontDesignMetrics.getMetrics(f);
        Main.canvas.drawCenteredStringByOutline(g,"手气不错",tmpWidth[0],interval,1,f,(int)(Main.HEIGHT*(verticalSize)-1.5*interval)+interval+(int)(0.5*interval)+fm.getHeight(),Color.BLACK,Color.white);
        f = new Font("思源宋体", Font.BOLD, Set.fontByWidth(tmpWidth[1]/3,"搜索结果"));
        fm = sun.font.FontDesignMetrics.getMetrics(f);
        Main.canvas.drawCenteredStringByOutline(g,"搜索结果",tmpWidth[1],2*interval+(int)(Main.WIDTH*(horizontalSize)-1.5*interval),1,f,interval+fm.getHeight(),Color.BLACK,Color.white);

    }
    public void paint(Graphics g) {
        paintBasic(g);
        search.repaint();
        generate.repaint();
        text.repaint();

        if(randomImage!=null){
            Font f = new Font("思源宋体", Font.BOLD, Set.fontByHeight(30));
            Main.canvas.drawCenteredStringByOutline(g,randomString,tmpWidth[0],9,1,f,430,Color.BLACK,Color.BLACK);
            g.drawImage(randomImage, 30,450,45+250,450+200,0, 0, randomImage.getWidth(null), randomImage.getHeight(null),null);
        }
        if(!tempDishes.isEmpty()&&!text.getText().isEmpty()){
            if(tempDishes.get(0).conformity<0.01){
                Font f = new Font("思源宋体", Font.BOLD, Set.fontByHeight(40));
                Main.canvas.drawCenteredStringByOutline(g,"无结果",tmpWidth[1],tmpWidth[0]+17,1,f,100,Color.BLACK,Color.BLACK);

            }
            else{
                Font f = new Font("思源宋体", Font.BOLD, Set.fontByHeight(40));
                Main.canvas.drawCenteredStringByOutline(g,"「"+tempDishes.get(0).name+"」",tmpWidth[1],tmpWidth[0]+17,1,f,100,Color.BLACK,Color.BLACK);
                g.drawImage(searchResult[0], tmpWidth[0]+30,120,tmpWidth[0]+tmpWidth[1]+10,tmpWidth[1]+100,0, 0, searchResult[0].getWidth(null), searchResult[0].getHeight(null),null);

            }
           }
    }

}
