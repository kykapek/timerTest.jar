package ru.prog.timertest;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.TimeUnit;


import javax.swing.*;
import javax.swing.plaf.basic.BasicSplitPaneUI;




class VerticalLayout implements LayoutManager
{
    private Dimension size = new Dimension();

    // Следующие два метода не используются
    public void addLayoutComponent   (String name, Component comp) {}
    public void removeLayoutComponent(Component comp) {}

    // Метод определения минимального размера для контейнера
    public Dimension minimumLayoutSize(Container c) {
        return calculateBestSize(c);
    }
    // Метод определения предпочтительного размера для контейнера
    public Dimension preferredLayoutSize(Container c) {
        return calculateBestSize(c);
    }
    // Метод расположения компонентов в контейнере
    public void layoutContainer(Container container)
    {
        // Список компонентов
        Component list[] = container.getComponents();
        container.setBackground(Color.BLACK);
        int bo1=9;
        int bo2=8;
        int currentY = 0;
        for (int i = 0; i < 1; i++) {

            // Определение предпочтительного размера компонента
            Dimension pref = list[i].getPreferredSize();
            // Размещение компонента на экране

            list[i].setBounds(bo1, currentY, pref.width, pref.height);
            // Учитываем промежуток в 2 пикселя
            currentY += 2; //было 5
            // Смещаем вертикальную позицию компонента
            currentY += pref.height;
        }
        for (int i = 1; i < 2; i++) {

            // Определение предпочтительного размера компонента
            Dimension pref = list[i].getPreferredSize();
            // Размещение компонента на экране

            list[i].setBounds(bo2, currentY, pref.width, pref.height);
            // Учитываем промежуток в 2 пикселя
            currentY += 2; //было 5
            // Смещаем вертикальную позицию компонента
            currentY += pref.height;
        }


    }
    // Метод вычисления оптимального размера контейнера
    private Dimension calculateBestSize(Container c)
    {
        // Вычисление длины контейнера
        Component[] list = c.getComponents();
        int maxWidth = 0;
        for (int i = 0; i < list.length; i++) {
            int width = list[i].getWidth();
            // Поиск компонента с максимальной длиной
            if ( width > maxWidth )
                maxWidth = width;
        }
        // Размер контейнера в длину с учетом левого отступа
        size.width = maxWidth + 5;//было5
        // Вычисление высоты контейнера
        int height = 0;
        for (int i = 0; i < list.length; i++) {
            height += 5;
            height += list[i].getHeight();
        }
        size.height = height;
        return size;
    }
}




public class TimerDemo extends JFrame {

    private JLabel timeSeconds, timeSeconds1, daysLabel;
    private JLabel timeMinutes;
    private Timer timer;
    private JButton start;
    private JTextField stage1, stage2, dopH1,dopH2, dopMin1, dopMin2;
    int ww;
    public String days1,days2,s1="",s2="",dh1,dm1,dh2,dm2, sh1="", sm1="", sh2="", sm2="";
    public int intDays1,intDays2, intMin1, intHours1, intHours2, intMin2;

    public TimerDemo() {
        setTitle("TimerDemo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        stage1 = new JTextField("Количество дней в первом этапе:",55);
        stage2 = new JTextField("Количество дней во втором этапе:",55);
        dopH1 = new JTextField("Количество доп часов в 1 этапе:",55);
        dopMin1 = new JTextField("Количество доп минут в 1 этапе:",55);
        dopH2 = new JTextField("Количество доп часов во 2 этапе:",55);
        dopMin2 = new JTextField("Количество доп минут во 2 этапе:",55);
        setLayout(new FlowLayout(FlowLayout.LEFT));
        timeSeconds = new JLabel();
        timeSeconds.setFont(new Font("Monospaced", Font.BOLD, 20));
        //timeMinutes = new JLabel();
        timer = new Timer(1, new TimerTick());//Запускаю таймер
        start = new JButton("Start");

        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                days1 = stage1.getText();
                days2 = stage2.getText();
                dh1 = dopH1.getText();
                dm1 = dopMin1.getText();
                dh2 = dopH2.getText();
                dm2 = dopMin2.getText();
                char[] days1Array = days1.toCharArray();
                char[] days2Array = days2.toCharArray();
                char[] dh1Array = dh1.toCharArray();
                char[] dm1Array = dm1.toCharArray();
                char[] dh2Array = dh2.toCharArray();
                char[] dm2Array = dm2.toCharArray();
                int d1 = days1.indexOf(":");
                int d2 = days2.indexOf(":");
                int d3 = dh1.indexOf(":");
                int d4 = dm1.indexOf(":");
                int d5 = dh2.indexOf(":");
                int d6 = dm2.indexOf(":");
                int i = d1+1, j = d2+1, ih1 = d3+1, im1 = d4+1, ih2 = d5+1, im2 = d6+1;


                while(i != days1Array.length){
                    s1+=Character.toString(days1Array[i]);
                    i++;
                }
                System.out.println(s1);


                while(j != days2Array.length){
                    s2+=Character.toString(days2Array[j]);
                    j++;
                }
                System.out.println(s2);

                while(ih1 != dh1Array.length){
                    sh1 += Character.toString(dh1Array[ih1]);
                    ih1++;
                }
                System.out.println(sh1);
                while(im1 != dm1Array.length){
                    sm1 += Character.toString(dm1Array[im1]);
                    im1++;
                }
                System.out.println(sm1);
                while(ih2 != dh2Array.length){
                    sh2 += Character.toString(dh2Array[ih2]);
                    ih2++;
                }
                System.out.println(sh2);
                while(im2 != dm2Array.length){
                    sm2 += Character.toString(dm2Array[im2]);
                    im2++;
                }
                System.out.println(sm2);
                try{

                    intDays1 = Integer.parseInt(s1.trim());
                    System.out.println("days1 = " + intDays1);
                    intDays2 = Integer.parseInt(s2.trim());
                    System.out.println("days2 = " + intDays2);
                    intHours1 = Integer.parseInt(sh1.trim());
                    System.out.println("доп часы 1 = " + intHours1);
                    intMin1 = Integer.parseInt(sm1.trim());
                    System.out.println("доп мин 1 = " + intMin1);
                    intHours2 = Integer.parseInt(sh2.trim());
                    System.out.println("доп часы 2 = " + intHours2);
                    intMin2 = Integer.parseInt(sm2.trim());
                    System.out.println("доп мин 2 = " + intMin2);
                } catch (NumberFormatException nfe){
                    System.out.println("NumberFormatException" + nfe.getMessage());
                }

                remove(start);

                remove(stage1);
                remove(stage2);
                remove(dopH1);
                remove(dopMin1);
                remove(dopH2);
                remove(dopMin2);
                //timeSeconds.setBounds(160,100,200,30);
                add(timeSeconds);
                validate();
                repaint();
                timer.start();
            }
        });
        //start.setBounds(160,96,100,20);
        add(start);
        add(stage1);
        add(stage2);
        add(dopH1);
        add(dopMin1);
        add(dopH2);
        add(dopMin2);
        setPreferredSize(new Dimension(390, 252));
        this.setResizable(false);
        this.getContentPane().setBackground(Color.CYAN);
        setLocationRelativeTo(null);
        pack();

        // Создаем окно
        JFrame frame = new JFrame("VerticalLayoutTest");
        frame.setUndecorated(true);

        // Определяем размеры
        int fwidth = 162;
        int fheight = 98;
        frame.setPreferredSize(new Dimension(fwidth, fheight));
        frame.pack();
        int realWidth = frame.getContentPane().getWidth();
        int realHeight = frame.getContentPane().getHeight();
        int xAdd = fwidth -realWidth;
        int yAdd = fheight - realHeight;
        //fwidth +=xAdd;
        //fheight+=yAdd;
        frame.setPreferredSize(new Dimension(fwidth,fheight));
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Создаем панель с менеджером вертикального расположения компонентов
        JPanel contents = new JPanel(new VerticalLayout());
        // Добавим кнопки и текстовое поле в панель
        timeSeconds1 = new JLabel();

        timeSeconds1.setFont(new Font("a_LCDNova", Font.PLAIN, 30/3*4));
        timeSeconds1.setForeground(Color.WHITE);
        //timeSeconds1.setText(String.valueOf(realWidth) + " " + String.valueOf(realHeight) + " " +String.valueOf(fwidth) + " " + String.valueOf(fheight));
        daysLabel = new JLabel();
        daysLabel.setFont(new Font("a_LCDNova", Font.PLAIN, 83));
        daysLabel.setForeground(Color.WHITE);
        daysLabel.setText("");
        contents.add(daysLabel);
        contents.add(timeSeconds1);
        frame.getRootPane().setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.RED));//рамка
        //frame.setBackground(Color.BLUE);
        // Размещаем панель в контейнере
        frame.setContentPane(contents);
        // Открываем окно
        frame.setLocation(0,0);//где появится
        frame.setVisible(true);
        frame.setResizable(false);
        ww=fwidth;

        frame.pack();

    }

    public static void main(String[] args) {
        new TimerDemo().setVisible(true);
    }

  public class TimerTick implements ActionListener {

        String stringData, stringHours="", stringMinutes="",stringSeconds="", stringDopHours="", stringDopMinutes="";

        String stringData1, stringHours1="", stringMinutes1="",stringSeconds1="", stringDopHours1="", stringDopMinutes1="";
        String stringData2, stringHours2="", stringMinutes2="";
        int day, month, year;
        int intHours=0, intMinutes=0, intSeconds=0, stage = 1;

        Date secCheck11 = new Date();
        Date secCheck12 = new Date();
        Date secCheck21 = new Date();
        Date secCheck31 = new Date();

        void data_to_text(Date data){
            stringData=data.toString();
        }
        void hours_toString(){
            String data = stringData;
            String zero = "0";
            int p1 = 0, k=0, i=0;
            char[] dataArray = data.toCharArray();
            while(k!=3){
                if(dataArray[i] == ' ')
                    k+=1;
                i+=1;
            }
            p1=i;
            char fh = dataArray[p1];
            char sh = dataArray[p1+1];
            String s1 = Character.toString(fh);
            String s2 = Character.toString(sh);
            System.out.println(s1);
            System.out.println(s2);
            if(s1.compareTo(zero) != 0)
                stringHours+=s1+s2;
            if(s1.compareTo(zero)==0)
                stringHours+=s2;
            System.out.println("---"+stringHours+"---");
        }
        void hours_toInt(){
            try{
                intHours = Integer.parseInt(stringHours.trim());
                System.out.println("hours = " + intHours);
            } catch (NumberFormatException nfe){
                System.out.println("NumberFormatException" + nfe.getMessage());
            }
        }
        void min_toString(){
            String data = stringData;
            String zero = "0";
            int d1=0;
            d1 = data.indexOf(":");
            char[] dataArray = data.toCharArray();
            char fm = dataArray[d1+1];
            char sm = dataArray[d1+2];
            String s1 = Character.toString(fm);
            String s2 = Character.toString(sm);
            System.out.println(s1);
            System.out.println(s2);
            if(s1.compareTo(zero) != 0)
                stringMinutes+=s1+s2;
            if(s1.compareTo(zero) == 0)
                stringMinutes+=s2;
            System.out.println("---"+stringMinutes+"---");
        }
        void min_toInt(){
            try{
                intMinutes = Integer.parseInt(stringMinutes.trim());
                System.out.println("minutes = " + intMinutes);
            } catch (NumberFormatException nfe){
                System.out.println("NumberFormatException" + nfe.getMessage());
            }
        }
        void sec_toString(){
            String data = stringData;
            String zero = "0";
            int d1 = 0, k=0, i=0;
            char[] dataArray = data.toCharArray();
            while(k!=2){
                if(dataArray[i] == ':')
                    k+=1;
                i+=1;
            }
            d1=i;
            char fs = dataArray[d1];
            char ss = dataArray[d1+1];
            String s1 = Character.toString(fs);
            String s2 = Character.toString(ss);
            System.out.println(s1);
            System.out.println(s2);
            if(s1.compareTo(zero) != 0)
                stringSeconds+=s1+s2;
            if(s1.compareTo(zero)==0)
                stringSeconds+=s2;
            System.out.println("---"+stringSeconds+"---");
        }
        void sec_toInt(){
            try{
                intSeconds = Integer.parseInt(stringSeconds.trim());
                System.out.println("seconds = " + intSeconds);
            } catch (NumberFormatException nfe){
                System.out.println("NumberFormatException" + nfe.getMessage());
            }
        }



        void data_to_text1(Date data){
          stringData1=data.toString();
      }
        void hours_toString1(){
          String data = stringData1;
          String zero = "0";
          int p1 = 0, k=0, i=0;
          char[] dataArray = data.toCharArray();
          while(k!=3){
              if(dataArray[i] == ' ')
                  k+=1;
              i+=1;
          }
          p1=i;
          char fh = dataArray[p1];
          char sh = dataArray[p1+1];
          String s1 = Character.toString(fh);
          String s2 = Character.toString(sh);
          System.out.println(s1);
          System.out.println(s2);
          if(s1.compareTo(zero) != 0)
              stringHours1+=s1+s2;
          if(s1.compareTo(zero)==0)
              stringHours1+=s2;
          System.out.println("---"+stringHours1+"---");
      }
        void hours_toInt1(){
          try{
              intHours = Integer.parseInt(stringHours1.trim());
              System.out.println("hours = " + intHours);
          } catch (NumberFormatException nfe){
              System.out.println("NumberFormatException" + nfe.getMessage());
          }
      }
        void min_toString1(){
          String data = stringData1;
          String zero = "0";
          int d1=0;
          d1 = data.indexOf(":");
          char[] dataArray = data.toCharArray();
          char fm = dataArray[d1+1];
          char sm = dataArray[d1+2];
          String s1 = Character.toString(fm);
          String s2 = Character.toString(sm);
          System.out.println(s1);
          System.out.println(s2);
          if(s1.compareTo(zero) != 0)
              stringMinutes1+=s1+s2;
          if(s1.compareTo(zero) == 0)
              stringMinutes1+=s2;
          System.out.println("---"+stringMinutes1+"---");
      }
        void min_toInt1(){
          try{
              intMinutes = Integer.parseInt(stringMinutes1.trim());
              System.out.println("minutes = " + intMinutes);
          } catch (NumberFormatException nfe){
              System.out.println("NumberFormatException" + nfe.getMessage());
          }
      }
        void sec_toString1(){
          String data = stringData1;
          String zero = "0";
          int d1 = 0, k=0, i=0;
          char[] dataArray = data.toCharArray();
          while(k!=2){
              if(dataArray[i] == ':')
                  k+=1;
              i+=1;
          }
          d1=i;
          char fs = dataArray[d1];
          char ss = dataArray[d1+1];
          String s1 = Character.toString(fs);
          String s2 = Character.toString(ss);
          System.out.println(s1);
          System.out.println(s2);
          if(s1.compareTo(zero) != 0)
              stringSeconds1+=s1+s2;
          if(s1.compareTo(zero)==0)
              stringSeconds1+=s2;
          System.out.println("---"+stringSeconds1+"---");
      }
        void sec_toInt1(){
          try{
              intSeconds = Integer.parseInt(stringSeconds1.trim());
              System.out.println("seconds = " + intSeconds);
          } catch (NumberFormatException nfe){
              System.out.println("NumberFormatException" + nfe.getMessage());
          }
      }

      void data_to_text2(Date data){
          stringData2=data.toString();
      }
      void hours_toString2(){
          String data = stringData2;
          String zero = "0";
          int p1 = 0, k=0, i=0;
          char[] dataArray = data.toCharArray();
          while(k!=3){
              if(dataArray[i] == ' ')
                  k+=1;
              i+=1;
          }
          p1=i;
          char fh = dataArray[p1];
          char sh = dataArray[p1+1];
          String s1 = Character.toString(fh);
          String s2 = Character.toString(sh);
          System.out.println(s1);
          System.out.println(s2);
          if(s1.compareTo(zero) != 0)
              stringHours2+=s1+s2;
          if(s1.compareTo(zero)==0)
              stringHours2+=s2;
          System.out.println("---"+stringHours2+"---");
      }
      void hours_toInt2(){
          try{
              intHours = Integer.parseInt(stringHours2.trim());
              System.out.println("hours = " + intHours);
          } catch (NumberFormatException nfe){
              System.out.println("NumberFormatException" + nfe.getMessage());
          }
      }
      void min_toString2(){
          String data = stringData2;
          String zero = "0";
          int d1=0;
          d1 = data.indexOf(":");
          char[] dataArray = data.toCharArray();
          char fm = dataArray[d1+1];
          char sm = dataArray[d1+2];
          String s1 = Character.toString(fm);
          String s2 = Character.toString(sm);
          System.out.println(s1);
          System.out.println(s2);
          if(s1.compareTo(zero) != 0)
              stringMinutes2+=s1+s2;
          if(s1.compareTo(zero) == 0)
              stringMinutes2+=s2;
          System.out.println("---"+stringMinutes2+"---");
      }
      void min_toInt2(){
          try{
              intMinutes = Integer.parseInt(stringMinutes2.trim());
              System.out.println("minutes = " + intMinutes);
          } catch (NumberFormatException nfe){
              System.out.println("NumberFormatException" + nfe.getMessage());
          }
      }


        boolean flag=false;
        int m=0,g=0,breakout=0, mig =0,k=0;
        int seconds = 10, seconds1;
        int minutes = 35, minutes1;
        int hours = 6, hours1;
        int days = 6, days1;
        long sum = days*(hours * 2 * 10 + minutes * 10 + seconds), auf=0,dem;
        int drop1=200, drop2=200, drop_1=200, drop_2=200;

        public void hms(){
            Date myDate = new Date();
            data_to_text(myDate);
            hours_toString();
            hours_toInt();
            min_toString();
            min_toInt();
            sec_toString();
            sec_toInt();
            seconds = intSeconds;
            minutes = intMinutes;
            hours = intHours;
        }
        public void hms1(){
          Date myDate = new Date();
          data_to_text1(myDate);
          hours_toString1();
          hours_toInt1();
          min_toString1();
          min_toInt1();
          sec_toString1();
          sec_toInt1();
          seconds1 = intSeconds;
          minutes1 = intMinutes;
          hours1 = intHours;
      }

        @Override
        public void actionPerformed(ActionEvent e) {

            if(stage == 1) {

                if (m == 0) {
                    hms();
                    days = intDays1;
                    if(hours+intHours1>=24){
                        days++;
                        hours = hours + intHours1 -24;
                    } else {
                        hours+=intHours1;
                    }
                    if(minutes+intMin1>=60){
                        hours++;
                        if (hours>=24){
                            days++;
                            hours-=24;
                        }
                        minutes = (minutes + intMin1) - 60;
                        //System.out.println("яяяяя "+minutes);
                    } else {
                        minutes+=intMin1;
                    }
                    m++;
                }
                /*
                две переменные а и б
                если переменная б изменилась, то переменной а присваиваем б
                и дальше спрашиваем секунды и кладем их в переменную б
                 */
                if (seconds != 0) {
                    //drop2 = drop1;
                    while(drop2 == drop1){
                        Date drop = new Date();
                        drop2 = drop.getSeconds();
                        //System.out.println("drop2 = " + drop2);
                    }

                    if (drop1 != drop2){
                        drop1=drop2;
                        seconds--; //вычитание секунд
                    }
                    //System.out.println("на приколе2 "+intSecCheck12);
                }
                sum--;
                timeSeconds.setText(String.valueOf(hours) + ":" + String.valueOf(minutes) + ":" + String.valueOf(seconds));
                daysLabel.setText(String.valueOf(days));
                if (days >= 100)
                    daysLabel.setText(String.valueOf(days));
                if (days < 100 && days >= 10)
                    daysLabel.setText("0" + String.valueOf(days));
                if (days < 10)
                    daysLabel.setText("00" + String.valueOf(days));
                if (hours >= 10 && minutes >= 10 && seconds >= 10) {//111
                    //daysLabel.setText("  "+String.valueOf(days));
                    timeSeconds1.setText(String.valueOf(hours) + ":" + String.valueOf(minutes) + ":" + String.valueOf(seconds));
                }
                if (hours < 10 && minutes < 10 && seconds < 10) { //000
                    //daysLabel.setText("  "+String.valueOf(days));
                    timeSeconds1.setText("0" + String.valueOf(hours) + ":" + "0" + String.valueOf(minutes) + ":" + "0" + String.valueOf(seconds));
                }
                if (hours < 10 && minutes >= 10 && seconds >= 10) { //011
                    //daysLabel.setText("  "+String.valueOf(days));
                    timeSeconds1.setText("0" + String.valueOf(hours) + ":" + String.valueOf(minutes) + ":" + String.valueOf(seconds));
                }
                if (hours >= 10 && minutes >= 10 && seconds < 10) { //110
                    //daysLabel.setText("  "+String.valueOf(days));
                    timeSeconds1.setText(String.valueOf(hours) + ":" + String.valueOf(minutes) + ":" + "0" + String.valueOf(seconds));
                }
                if (hours >= 10 && minutes < 10 && seconds >= 10) { //101
                    //daysLabel.setText("  "+String.valueOf(days));
                    timeSeconds1.setText(String.valueOf(hours) + ":" + "0" + String.valueOf(minutes) + ":" + String.valueOf(seconds));
                }
                if (hours >= 10 && minutes < 10 && seconds < 10) { //100
                    //daysLabel.setText("  "+String.valueOf(days));
                    timeSeconds1.setText(String.valueOf(hours) + ":" + "0" + String.valueOf(minutes) + ":" + "0" + String.valueOf(seconds));
                }
                if (hours < 10 && minutes >= 10 && seconds < 10) { //010
                    //daysLabel.setText("  "+String.valueOf(days));
                    timeSeconds1.setText("0" + String.valueOf(hours) + ":" + String.valueOf(minutes) + ":" + "0" + String.valueOf(seconds));
                }
                if (hours < 10 && minutes < 10 && seconds >= 10) { //001
                    //daysLabel.setText("  "+String.valueOf(days));
                    timeSeconds1.setText("0" + String.valueOf(hours) + ":" + "0" + String.valueOf(minutes) + ":" + String.valueOf(seconds));
                }
                if (seconds == 0 && minutes != 0) { //обновление секунд
                    seconds = 60;
                    minutes--;
                    System.out.println(hours + ":" + minutes + ":" + seconds + " sum= " + sum + " days = " + days);
                    //timer.stop();
                    //timeLabel.setText("START!");
                }
                if (minutes == 0 && seconds == 0 && hours != 0) { //обновление минут
                    minutes = 59;
                    seconds = 60;
                    hours--;
                    System.out.println(hours + ":" + minutes + ":" + seconds + " sum= " + sum + " days = " + days);
                }
                if (days != 0 && hours == 0 && minutes == 0 && seconds == 0) { //обновление часов
                    hours = 23;
                    minutes = 59;
                    seconds = 60;
                    days--;
                    System.out.println(hours + ":" + minutes + ":" + seconds + " sum= " + sum + " days = " + days + " godfire");
                }
                if (seconds ==0 && minutes == 0 && hours == 0 && days == 0) { //дни
                    //days--;
                    stage++;

                    //timer.stop();
                    //timeSeconds.setText("Первый этап завершен");
                }
            }
            if(stage == 2 || (intDays1 ==-1 && intMin1 ==0 && intHours1==0)){
                stage=2;
                //System.out.println("z nen");
                if(breakout ==0 && intDays1!=-1){

                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                    breakout++;
                }

                if (g == 0) {
                    hms1();
                    System.out.println("я виноват");
                    days1 = intDays2;
                    if(hours1+intHours2>=24){
                        days1++;
                        hours1 = hours1 + intHours2 -24;
                    } else {
                        hours1+=intHours2;
                    }
                    if(minutes1+intMin2>=60){
                        hours1++;
                        if (hours1>=24){
                            days1++;
                            hours1-=24;
                        }
                        minutes1 = (minutes1 + intMin2) - 60;
                        //System.out.println("яяяяя "+minutes1);
                    } else {
                        minutes1+=intMin2;
                    }
                    g++;
                }

                if (seconds1 != 0) {
                    //drop_2 = drop_1;
                    while(drop_2 == drop_1){
                        Date drop_t = new Date();
                        drop_2 = drop_t.getSeconds();
                        //System.out.println("drop2 = " + drop_2);
                    }

                    if (drop_1 != drop_2){
                        drop_1=drop_2;
                        seconds1--; //вычитание секунд
                    }
                }
                auf++;
                sum--;
                timeSeconds.setText(String.valueOf(hours1) + ":" + String.valueOf(minutes1) + ":" + String.valueOf(seconds1));
                daysLabel.setText(String.valueOf(days1));
                if (days1 >= 100)
                    daysLabel.setText(String.valueOf(days1));
                if (days1 < 100 && days1 >= 10)
                    daysLabel.setText("0" + String.valueOf(days1));
                if (days1 < 10)
                    daysLabel.setText("00" + String.valueOf(days1));
                if (hours1 >= 10 && minutes1 >= 10 && seconds1 >= 10) {//111
                    //daysLabel.setText("  "+String.valueOf(days));
                    timeSeconds1.setText(String.valueOf(hours1) + ":" + String.valueOf(minutes1) + ":" + String.valueOf(seconds1));
                }
                if (hours1 < 10 && minutes1 < 10 && seconds1 < 10) { //000
                    //daysLabel.setText("  "+String.valueOf(days));
                    timeSeconds1.setText("0" + String.valueOf(hours1) + ":" + "0" + String.valueOf(minutes1) + ":" + "0" + String.valueOf(seconds1));
                }
                if (hours1 < 10 && minutes1 >= 10 && seconds1 >= 10) { //011
                    //daysLabel.setText("  "+String.valueOf(days));
                    timeSeconds1.setText("0" + String.valueOf(hours1) + ":" + String.valueOf(minutes1) + ":" + String.valueOf(seconds1));
                }
                if (hours1 >= 10 && minutes1 >= 10 && seconds1 < 10) { //110
                    //daysLabel.setText("  "+String.valueOf(days));
                    timeSeconds1.setText(String.valueOf(hours1) + ":" + String.valueOf(minutes1) + ":" + "0" + String.valueOf(seconds1));
                }
                if (hours1 >= 10 && minutes1 < 10 && seconds1 >= 10) { //101
                    //daysLabel.setText("  "+String.valueOf(days));
                    timeSeconds1.setText(String.valueOf(hours1) + ":" + "0" + String.valueOf(minutes1) + ":" + String.valueOf(seconds1));
                }
                if (hours1 >= 10 && minutes1 < 10 && seconds1 < 10) { //100
                    //daysLabel.setText("  "+String.valueOf(days));
                    timeSeconds1.setText(String.valueOf(hours1) + ":" + "0" + String.valueOf(minutes1) + ":" + "0" + String.valueOf(seconds1));
                }
                if (hours1 < 10 && minutes1 >= 10 && seconds1 < 10) { //010
                    //daysLabel.setText("  "+String.valueOf(days));
                    timeSeconds1.setText("0" + String.valueOf(hours1) + ":" + String.valueOf(minutes1) + ":" + "0" + String.valueOf(seconds1));
                }
                if (hours1 < 10 && minutes1 < 10 && seconds1 >= 10) { //001
                    //daysLabel.setText("  "+String.valueOf(days));
                    timeSeconds1.setText("0" + String.valueOf(hours1) + ":" + "0" + String.valueOf(minutes1) + ":" + String.valueOf(seconds1));
                }
                if (seconds1 == 0 && minutes1 != 0) { //обновление секунд
                    seconds1 = 60;
                    minutes1--;
                    System.out.println(hours1 + ":" + minutes1 + ":" + seconds1 + " sum= " + sum + " days = " + days1);
                    //timer.stop();
                    //timeLabel.setText("START!");
                }
                if (minutes1 == 0 && seconds1 == 0 && hours1 != 0) { //обновление минут
                    minutes1 = 59;
                    seconds1 = 60;
                    hours1--;
                    System.out.println(hours1 + ":" + minutes1 + ":" + seconds1 + " sum= " + sum + " days = " + days1);
                }
                if (days1 != 0 && hours1 == 0 && minutes1 == 0 && seconds1 == 0) { //обновление часов
                    hours1 = 23;
                    minutes1 = 59;
                    seconds1 = 60;
                    days1--;
                    System.out.println(hours1 + ":" + minutes1 + ":" + seconds1 + " sum= " + sum + " days = " + days1 + " godfire");
                }
                if (seconds1 == 0 && minutes1 == 0 && hours1 == 0 && days1 == 0 && mig <10 && flag == false) { //дни
                    //days--;
                    dem = auf+10;
                    flag = true;
                }
                if (seconds1 == 0 && minutes1 == 0 && hours1 == 0 && days1 == 0 && mig <10 && flag == true) { //дни
                    //days--;
                    if(mig % 2 == 0) {
                        timeSeconds1.setText("");
                        daysLabel.setText("");
                    }else{
                        timeSeconds1.setText("00:00:00");
                        daysLabel.setText("000");
                    }
                    mig++;
                }
                if (seconds1 == 0 && minutes1 == 0 && hours1 == 0 && days1 == 0 && mig ==10) { //дни
                    //days--;
                    stage=3;
                    System.out.println("1 и 2 завершены");
                    //timer.stop();
                }
        }
            if(stage == 3 || intDays1==-2) {

                    stage = 3;
                    Date date = new Date();
                    LocalDateTime now = LocalDateTime.now();
                    intHours = now.getHour();
                    intMinutes = now.getMinute();
                    LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    year = localDate.getYear() % 2000;
                    month = localDate.getMonthValue();
                    day = localDate.getDayOfMonth();
                    daysLabel.setFont(new Font("a_LCDNova", Font.PLAIN, 63));
                    timeSeconds1.setFont(new Font("a_LCDNova", Font.PLAIN, 42));
                    //System.out.println(year + " " + month + " " + day);
                    if (day >= 10 && month >= 10 && year >= 10)
                        timeSeconds1.setText(day + "." + month + "." + year);
                    if (day >= 10 && month >= 10 && year < 10)
                        timeSeconds1.setText(day + "." + month + ".0" + year);
                    if (day >= 10 && month < 10 && year >= 10)
                        timeSeconds1.setText(day + ".0" + month + "." + year);
                    if (day >= 10 && month < 10 && year < 10)
                        timeSeconds1.setText(day + ".0" + month + ".0" + year);
                    if (day < 10 && month >= 10 && year >= 10)
                        timeSeconds1.setText("0" + day + "." + month + "." + year);
                    if (day < 10 && month >= 10 && year < 10)
                        timeSeconds1.setText("0" + day + "." + month + ".0" + year);
                    if (day < 10 && month < 10 && year >= 10)
                        timeSeconds1.setText("0" + day + ".0" + month + "." + year);
                    if (day < 10 && month < 10 && year < 10)
                        timeSeconds1.setText("0" + day + ".0" + month + ".0" + year);
                    if(intHours>=10 && intMinutes>=10)
                        daysLabel.setText(intHours + ":" + intMinutes);
                    if(intHours>=10 && intMinutes<10)
                        daysLabel.setText(intHours + ":0" + intMinutes);
                    if(intHours<10 && intMinutes>=10)
                        daysLabel.setText("0"+intHours + ":" + intMinutes);
                    if(intHours<10 && intMinutes<10)
                        daysLabel.setText("0"+intHours + ":0" + intMinutes);

            }

    }
}}