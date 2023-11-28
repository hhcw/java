import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        File f = new File("C:\\Users\\胡\\IdeaProjects\\MyTxtReader\\班级名单.txt");
        if (!f.exists()) {
            System.out.println("文件不存在！");
            return;
        }

        BufferedReader bi = new BufferedReader(new FileReader(f));
        List<String> lines = new ArrayList<>();
        String line;
        while ((line = bi.readLine()) != null) {
            lines.add(line);
        }
        bi.close();

        Collections.sort(lines, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String[] s1 = o1.split("\\s+");
                String[] s2 = o2.split("\\s+");
                return s1[0].compareTo(s2[0]);
            }
        });

        BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\胡\\IdeaProjects\\MyTxtReader\\sorted.txt"));
        for (String l : lines)
            writer.write(l + "\n");

        writer.close();

        System.out.println("班级名单已按学号排序输出到sorted.txt文件中。");

        System.out.println("请输入学号：");
        String id = sc.nextLine();
        for (String l : lines) {
            String[] s = l.split("\\s+");
            if (s[0].equals(id)) {
                System.out.println("学生姓名：" + s[1]);
                break;
            }
        }
    }
}
