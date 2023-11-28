import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        // 生成5千万个随机的double值
        int s= 0;
        Scanner sd = new Scanner(System.in);
        System.out.println("请输入要写入的double数目");
        s= sd.nextInt();
        double[] data = new double[s];
        Random random = new Random();
        for (int i = 0; i < s; i++) {
            data[i] = random.nextDouble();
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter("文件读写性能分析.txt"));//写入txt便转成pdf
        // 使用DataOutputStream写入文件
        long t1 = System.currentTimeMillis();
        DataOutputStream dos = new DataOutputStream(new FileOutputStream("data.dat"));
        for (int i = 0; i < s; i++) {
            dos.writeDouble(data[i]);
        }
        dos.close();
        long T1 = System.currentTimeMillis();
        System.out.println("使用DataOutputStream写入文件耗时：" + (T1 - t1) + "毫秒");
        writer.write( "使用DataOutputStream写入文件耗时：" + (T1 - t1) + "毫秒"+ "\n");
        // 使用OutputStreamWriter写入文件
        long t2 = System.currentTimeMillis();
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("data.txt"), "UTF-8");
        for (int i = 0; i < s; i++) {
            osw.write(String.valueOf(data[i]) + "\n");
        }
        osw.close();
        long T2 = System.currentTimeMillis();
        System.out.println("使用OutputStreamWriter写入文件耗时：" + (T2 - t2) + "毫秒");
        writer.write( "使用OutputStreamWriter写入文件耗时：" + (T2 - t2) + "毫秒"+ "\n");

        // 使用DataInputStream读取文件
        long t3 = System.currentTimeMillis();
        DataInputStream dis = new DataInputStream(new FileInputStream("data.dat"));
        for (int i = 0; i < s; i++) {
            double d = dis.readDouble();
        }
        dis.close();
        long T3 = System.currentTimeMillis();
        System.out.println("使用DataInputStream读取文件耗时：" + (T3 - t3) + "毫秒");
        writer.write( "使用DataInputStream读取文件耗时：" + (T3 - t3) + "毫秒"+ "\n");

        // 使用InputStreamReader读取文件
        long t4 = System.currentTimeMillis();
        InputStreamReader isr = new InputStreamReader(new FileInputStream("data.txt"), "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String line = null;
        while ((line = br.readLine()) != null) {
            double d = Double.parseDouble(line);
        }
        br.close();
        isr.close();
        long T4 = System.currentTimeMillis();
        System.out.println("使用InputStreamReader读取文件耗时：" + (T4 - t4) + "毫秒");
        writer.write( "使用InputStreamReader读取文件耗时：" + (T4 - t4) + "毫秒"+ "\n");
        writer.close();
    }
}
