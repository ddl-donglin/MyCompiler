package Util;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 文件操作
 */
public class FileUtil {
    /**
     * 文件读取到缓冲区
     * @param buffer 缓冲区
     * @param fileSrc 文件路径
     * @return true : success
     * 		   false : filed
     */
    public static boolean readFile(StringBuffer buffer, String fileSrc) {
        try {
            //FileReader fileReader = new FileReader(fileSrc);
            //BufferedReader br = new BufferedReader(fileReader);
            InputStreamReader inputStream = new InputStreamReader(new FileInputStream(fileSrc),"gbk");
            BufferedReader br = new BufferedReader(inputStream);
            String temp = null;
            while ((temp = br.readLine()) != null) {
                buffer.append(temp);
            }
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String readFile(String file) throws IOException {
        StringBuilder result = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String s;
        while((s = br.readLine())!=null)
            result.append(System.lineSeparator() + s);
        br.close();
        return result.toString();

    }

    /**
     * 去掉所有空格，换行，tab等
     * @param str
     * @return
     */
    public static String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\s+");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    /**
     * 去掉所有空行,默认连续空行不超过10行
     * @param str
     * @return
     */
    public static String replaceBlankLine(String str){
        String result = str.replaceAll("(?m)^\\s*$(\\n|\\r\\n)", "");;
        for(int i = 0; i < 10; i++){
            result = result.replaceAll("(?m)^\\s*$(\\n|\\r\\n)", "");
        }
        return result;
    }

    /**
     * 追加方式写文件
     * @param args	需要写入字符串
     * @return	true : success
     * 		   false : filed
     */
    public static boolean writeFile(String args) {
        try {
            File file = new File("./output.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(args);
            bw.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return true;
        }
    }

    public static boolean writeFile(String args, String path) {
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(args);
            bw.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return true;
        }
    }
    /**
     * 清空文件
     */
    public static boolean clearFile() {
        try {
            File file = new File("./output.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("");
            bw.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return true;
        }
    }

    public static boolean clearFile(String filepath) {
        try {
            File file = new File(filepath);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("");
            bw.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return true;
        }
    }


    public static String format(String result) {
        StringBuffer reStringBuffer = new StringBuffer();
        char[] resultCharArray = result.toCharArray();
        for(int i = 0; i < resultCharArray.length; i++){

            if(resultCharArray[i] == '-' && resultCharArray[i+1] == '>'){
                reStringBuffer.append('→');
                i+=2;
            }

            if(resultCharArray[i] == '=' && resultCharArray[i+1] == '>'){
                reStringBuffer.append('→');
                i+=2;
            }

            if(resultCharArray[i] == '/' && resultCharArray[i+1] == '*') {
                i += 2;
                while (true) {
                    if (resultCharArray[i] == '*' && resultCharArray[i+1] == '/'){
                        i +=2;
                        break;
                    }
                    i++;
                }
            }
            reStringBuffer.append(resultCharArray[i]);
        }
        return FileUtil.replaceBlankLine(reStringBuffer.toString());
    }
}
