package jframe;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Objects;
import java.util.Random;

public class gameJFrame extends JFrame implements KeyListener, ActionListener {

    //创建二维数组
    //用于管理数据
    int[][] data = new int [4][4];

    //x，y记录空白数组的位置
    int x = 0;
    int y = 0;

    //定义一个变量来记录路径
    String path = "image/Nan/";

    //定义一个数组来储存正确的数据
    int[][] win ={
            {1,2,3,4},
            {5,6,7,8},
            {9,10,11,12},
            {13,14,15,0}
    };

    //记录步骤
    int step = 0;

    //选项下条目
    JMenuItem replayItem = new JMenuItem("重新游戏");
    JMenuItem reloginItem = new JMenuItem("重新登录");
    JMenuItem closeItem = new JMenuItem("关闭游戏");

    JMenuItem meItem = new JMenuItem("关于作者");

    public gameJFrame(){
        //初始化界面
        initJframe();

        //初始化菜单
        initJMenuBar();

        //初始化数据
        initData();

        //初始化图片
        initImage();

        //界面显示
        this.setVisible(true);
    }

    private void initData() {
        int[] tempArr = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        Random r = new Random();
        for(int i = 0; i < tempArr.length; i++){
            int index = r.nextInt(tempArr.length);
            int temp = tempArr[i];
            tempArr[i] = tempArr[index];
            tempArr[index] = temp;
        }


        for(int i = 0; i < tempArr.length; i++){
            if(tempArr[i] == 0) {
                x = i / 4;
                y = i % 4;
            }
            data[i / 4][i % 4] = tempArr[i];

        }
    }

    private void initImage() {
        //清空页面
        this.getContentPane().removeAll();

        if(victory()) {
            JLabel winJLabel = new JLabel(new ImageIcon("image/win/win.png"));
            winJLabel.setBounds(60,283,500,80);
            this.getContentPane().add(winJLabel);
        }

        JLabel stepCount = new JLabel("步骤：" + step);
        stepCount.setBounds(50,30,100,20);
        this.getContentPane().add(stepCount);

        for(int i = 0; i < 4; i++){
            for(int j = 0; j< 4; j++)
            {
                int num = data[i][j];
                //创建一个图片ImageIcon的对象
                ImageIcon icon = new ImageIcon(path + num +".png");
                //创建一个JLabel的对象（管理容器）
                JLabel jLabel = new JLabel(icon);
                //指定图片位置
                jLabel.setBounds(105 * j + 83,105 * i + 100,105,105);
                //给图片添加边框
                jLabel.setBorder(new BevelBorder(0));
                //把管理容器添加到界面中
                this.getContentPane().add(jLabel);

            }
        }

        //刷新界面
        this.getContentPane().repaint();
    }

    private void initJMenuBar() {
        //初始化菜单
        JMenuBar jMenuBar = new JMenuBar();

        //创建选项
        JMenu functionJmenu = new JMenu("功能");
        JMenu aboutJmenu = new JMenu("关于我们");




        //将每一个选项下的条目添加到选项中
        functionJmenu.add(replayItem);
        functionJmenu.add(reloginItem);
        functionJmenu.add(closeItem);

        aboutJmenu.add(meItem);

        //给条目绑定事件
        replayItem.addActionListener(this);
        reloginItem.addActionListener(this);
        closeItem.addActionListener(this);
        meItem.addActionListener(this);
        //将菜单中选型添加到菜单中
        jMenuBar.add(functionJmenu);
        jMenuBar.add(aboutJmenu);

        //给界面时设置菜单
        this.setJMenuBar(jMenuBar);
    }

    private void initJframe() {
        //设置宽高
        this.setSize(603, 680);
        //设置标题
        this.setTitle("拼图");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //关闭
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //取消默认的居中放置
        this.setLayout(null);
        //增加键盘监听事件
        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == 65){
            //清空页面
            this.getContentPane().removeAll();

            JLabel all = new JLabel(new ImageIcon("image/Nan/nan.png"));
            all.setBounds(83,100,420,420);
            this.getContentPane().add(all);

            //刷新界面
            this.getContentPane().repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        if (victory()) {
            return;
        }


        //对上下左右进行判断
        int code = e.getKeyCode();
        System.out.println(code);

        if(code == 37){
            System.out.println("向左移动");
            if (y == 3){
                return;
            }
            data[x][y] = data[x][y + 1];
            data[x][y + 1] = 0;
            y++;
            step++;
            initImage();
        } else if (code == 38){
            System.out.println("向上移动");
            if (x == 3){
                return;
            }
            data[x][y] = data[x + 1][y];
            data[x + 1][y] = 0;
            x++;
            step++;
            initImage();
        } else if (code == 39){
            System.out.println("向右移动");
            if (y == 0){
                return;
            }
            data[x][y] = data[x][y - 1];
            data[x][y - 1] = 0;
            y--;
            step++;
            initImage();
        } else if (code == 40){
            System.out.println("向下移动");
            if(x == 0){
                return;
            }
            data[x][y] = data[x - 1][y];
            data[x - 1][y] = 0;
            x--;
            step++;
            initImage();
        }else if(code == 65) {
            initImage();
        } else if (code ==87) {
            data = new int[][] {
                    {1,2,3,4},
                    {5,6,7,8},
                    {9,10,11,12},
                    {13,14,15,0}
            };
            initImage();
        }
    }

    //判断胜利
    public  boolean victory() {
        for(int i = 0; i < data.length; i++) {
            for(int j = 0; j < data[i].length; j++) {
                if (data[i][j] != win[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        if (obj == replayItem) {
            System.out.println("重新游戏");
            step = 0;
            initData();
            initImage();

        } else if (obj == reloginItem) {
            System.out.println("重新登录");
            this.setVisible(false);
            new  loginJframe();

        } else if (obj == closeItem) {
            System.out.println("关闭游戏");
            System.exit(0);
        } else if (obj == meItem) {
            System.out.println("关于作者");

            //创建一个弹窗
            JDialog jDialog = new JDialog();
            //创建一个图片管理
            JLabel jLabel = new  JLabel(new ImageIcon("image/me/me.png"));
            ///位置和宽高
            jLabel.setBounds(0,0,210,252);
            //图片添加到弹框中
            jDialog.getContentPane().add(jLabel);
            //设置弹框大小
            jDialog.setSize(300,300);
            ///弹框置顶
            jDialog.setAlwaysOnTop(true);
            //弹框居中
            jDialog.setLocationRelativeTo(null);
            //弹框不关闭其他界面无法使用
            jDialog.setModal(true);
            //显示弹框
            jDialog.setVisible(true);
        }
    }
}
