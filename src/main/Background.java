package main;

import database.Entity.Dish;
import utils.Load;
import utils.Search;
import utils.Set;

import javax.swing.*;
import java.awt.*;
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
    public Image backgroundNull=Load.image("setup/"+4+".png");
    public Image backgroundHave=Load.image("setup/"+3+".png");
    public Image []searchResult=new Image[4];
    public ArrayList<Dish> tempDishes=new ArrayList<>();
    public Image randomImage=null;
    public int []tmpWidth=new int[3];
    public int []tmpHeight=new int[3];
    public ImageIcon searchButton=new ImageIcon("res/setup/search.png");
    public ImageIcon generateButton=new ImageIcon("res/setup/generate.png");
    public ImageIcon searchButtonPressed=new ImageIcon("res/setup/search_pressed.png");
    public ImageIcon generateButtonPressed=new ImageIcon("res/setup/generate_pressed.png");
    JButton search=new JButton();
    JButton generate=new JButton();
    JTextField text=new JTextField();
    public String randomString="";
    public boolean hasSearched=false;
    public boolean hasRandom=false;
    public boolean hasResult=false;
    public String resultString="";
    public int lastSearch=-1;
    public int lastRandom=-1;
    public Background() {
        setOpaque(false);
        setBounds(0, 0, Main.WIDTH, Main.HEIGHT);
        this.add(search);
        search.setBounds(180,185,100,35);
        search.setBorderPainted(false);
        generate.setBounds(45,185,100,35);
        generate.setBorderPainted(false);
        text.setBounds(20,95,286,50);
        search.setVisible(true);
//根据按钮大小改变图片大小
        searchButton.getImage();
        Image temp = searchButton.getImage().getScaledInstance(search.getWidth(), search.getHeight(), Image.SCALE_DEFAULT);
        searchButton = new ImageIcon(temp);
        search.setIcon(searchButton);

        generateButton.getImage();
        temp = generateButton.getImage().getScaledInstance(generate.getWidth(), generate.getHeight(), Image.SCALE_DEFAULT);
        generateButton= new ImageIcon(temp);
        generate.setIcon(generateButton);
        //为按钮添加点击事件监听器
        search.addActionListener(e -> {
            if(text.getText().length()>0&&!text.getText().startsWith(" ")){
                tempDishes= Search.SearchByName(Main.Dishes,text.getText());
                if(!tempDishes.isEmpty()){
                    if(tempDishes.get(0).conformity==1000){
                        searchResult[0]=Load.image("dishes/"+tempDishes.get(0).path);
                        resultString=tempDishes.get(0).name;
                    }
                    else{
                        Random randoms=new Random(new Date().getTime());
                        int random = (int) (randoms.nextDouble()*(tempDishes.size()));
                        if(tempDishes.get(0).conformity>100){
                            while(tempDishes.get(random).conformity<100||(random==lastSearch&&tempDishes.size()>1&&tempDishes.get(1).conformity>100)){
                                random = (int) (randoms.nextDouble()*(tempDishes.size()));
                            }
                        }
                        else {
                            while(random==lastSearch&&tempDishes.size()>1){
                                random = (int) (randoms.nextDouble()*(tempDishes.size()));
                            }
                        }
                        lastSearch=random;
                        searchResult[0] = Load.image("dishes/" + tempDishes.get(random).path);
                        resultString=tempDishes.get(random).name;
                    }
                }
                else{
                    resultString="";
                }
                hasSearched=true;
                this.repaint();
            }
        });

        generate.setVisible(true);
        //为按钮添加点击事件监听器
        generate.addActionListener(e -> {
            if(text.getText().length()>0&&!text.getText().startsWith(" ")){
                tempDishes= Search.SearchOutrageous(Main.Dishes,text.getText());
                if(!tempDishes.isEmpty()){
                    if(tempDishes.get(0).conformity==1000){
                        randomImage=Load.image("dishes/"+tempDishes.get(0).path);
                        randomString=tempDishes.get(0).name;
                    }
                    else{
                        Random randoms=new Random(new Date().getTime());
                        int random = (int) (randoms.nextDouble()*(tempDishes.size()));
                        if(tempDishes.get(0).conformity>100){
                            while(tempDishes.get(random).conformity<100||(random==lastRandom&&tempDishes.size()>1&&tempDishes.get(1).conformity>100)){
                                random = (int) (randoms.nextDouble()*(tempDishes.size()));
                            }
                        }
                        else {
                            while(random==lastRandom&&tempDishes.size()>1){
                                random = (int) (randoms.nextDouble()*(tempDishes.size()));
                            }
                        }
                        lastRandom=random;
                        randomImage = Load.image("dishes/" + tempDishes.get(random).path);
                        randomString=tempDishes.get(random).name;
                    }
                    hasResult=true;
                }
                else{
                    hasResult=false;
                }
                hasRandom=true;
                this.repaint();
            }
        });

        search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                searchButtonPressed.getImage();
                final Image temp2 = searchButtonPressed.getImage().getScaledInstance(search.getWidth(), search.getHeight(), Image.SCALE_DEFAULT);
                searchButtonPressed = new ImageIcon(temp2);
                search.setIcon(searchButtonPressed);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                searchButton.getImage();
                Image temp = searchButton.getImage().getScaledInstance(search.getWidth(), search.getHeight(), Image.SCALE_DEFAULT);
                searchButton = new ImageIcon(temp);
                search.setIcon(searchButton);
            }
        });

        generate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                generateButtonPressed.getImage();
                final Image temp2 = generateButtonPressed.getImage().getScaledInstance(search.getWidth(), search.getHeight(), Image.SCALE_DEFAULT);
                generateButtonPressed = new ImageIcon(temp2);
                generate.setIcon(generateButtonPressed);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                generateButton.getImage();
                final Image temp2 = generateButton.getImage().getScaledInstance(generate.getWidth(), generate.getHeight(), Image.SCALE_DEFAULT);
                generateButton= new ImageIcon(temp2);
                generate.setIcon(generateButton);
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


    }
    public void paint(Graphics g) {
        paintBasic(g);
        search.repaint();
        generate.repaint();
        text.repaint();

        if(hasRandom){
            Font f = new Font("思源宋体", Font.BOLD, Set.fontByHeight(25));
            int tempWidth=tmpWidth[2]-6;
            int tempHeight=tempWidth*3/4;
            int tempX=13;
            int tempY=325;
            int drawX=13;
            int drawY=585;
            if(hasResult){
                g.drawImage(randomImage, tempX,tempY,tempX+tempWidth,tempY+tempHeight,0, 0, randomImage.getWidth(null), randomImage.getHeight(null),null);
                Main.canvas.paintString("「"+randomString+"」",f,g,drawX,drawY,tmpWidth[1],Color.BLACK,Color.BLACK);
            }
            else{
                Main.canvas.paintString("「无结果」",f,g,drawX,drawY,tmpWidth[1],Color.BLACK,Color.BLACK);
            }
            g.translate(-drawX, -drawY);
        }

        Font f = new Font("思源宋体", Font.BOLD, Set.fontByHeight(30));
        if(hasSearched)
        {
            if (tempDishes.isEmpty() || tempDishes.get(0).conformity < 0.1) {
                backgroundImgs[1] = backgroundNull;
                x = 2 * interval + (int) (Main.WIDTH * (horizontalSize) - 1.5 * interval);
                y = interval;
                tmpWidth[1] = width = Main.WIDTH - 5 * interval - (int) (Main.WIDTH * (horizontalSize) - 1.5 * interval);
                tmpHeight[1] = height = Main.HEIGHT - 6 * interval;
                g.drawImage(backgroundImgs[1], x + 3, y + 3, x + 3 + width - 6, y + 3 + height - 6, 0, 0, backgroundImgs[1].getWidth(null), backgroundImgs[1].getHeight(null), null);
                //Main.canvas.drawCenteredStringByOutline(g,"「"+tempDishes.get(0).name+"」",tmpWidth[1],tmpWidth[0]+17,1,f,100+tmpWidth[1],Color.BLACK,Color.BLACK);
                Main.canvas.paintString("「无结果」", f, g, tmpWidth[0] + 20, 100 + tmpWidth[1], tmpWidth[1], Color.BLACK, Color.BLACK);
            } else {
                backgroundImgs[1] = backgroundHave;
                x = 2 * interval + (int) (Main.WIDTH * (horizontalSize) - 1.5 * interval);
                y = interval;
                tmpWidth[1] = width = Main.WIDTH - 5 * interval - (int) (Main.WIDTH * (horizontalSize) - 1.5 * interval);
                tmpHeight[1] = height = Main.HEIGHT - 6 * interval;
                g.drawImage(backgroundImgs[1], x + 3, y + 3, x + 3 + width - 6, y + 3 + height - 6, 0, 0, backgroundImgs[1].getWidth(null), backgroundImgs[1].getHeight(null), null);
                //Main.canvas.drawCenteredStringByOutline(g,"「"+tempDishes.get(0).name+"」",tmpWidth[1],tmpWidth[0]+17,1,f,100+tmpWidth[1],Color.BLACK,Color.BLACK);
                g.drawImage(searchResult[0], tmpWidth[0] + 23, 75, tmpWidth[0] + tmpWidth[1] + 17, tmpWidth[1] + 60, 0, 0, searchResult[0].getWidth(null), searchResult[0].getHeight(null), null);
                int drawX = tmpWidth[0] + 20;
                int drawY = 100 + tmpWidth[1];
                Main.canvas.paintString("「" + resultString + "」", f, g, drawX, drawY, tmpWidth[1], Color.BLACK, Color.BLACK);
                g.translate(-drawX, -drawY);
            }
        }
    }

}
