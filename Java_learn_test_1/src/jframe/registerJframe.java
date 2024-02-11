package jframe;

import javax.swing.*;

public class registerJframe extends JFrame {
    public registerJframe(){
        //设置宽高
        this.setSize(488, 500);
        //设置标题
        this.setTitle("拼图 登录");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //关闭
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);




        this.setVisible(true);

    }
}
