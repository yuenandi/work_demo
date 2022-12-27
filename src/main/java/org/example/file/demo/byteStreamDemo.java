package org.example.file.demo;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class byteStreamDemo {
    public static void main(String[] args) {
        File file = new File("/Users/yuenandi/Project/work_demo/src/main/java/org/example/stream/demo/hello.txt");
//        Normal(file);
//        AllRead1(file);
        String data = "Ynd test write dsfasdf sd  sdfsd  dsfsdf\n";
        byte[] ob = data.getBytes(StandardCharsets.UTF_8);
        try {
            writeByteArrayToFile(file, ob);
            byte[] rb = readFileToByteArray(file);
            System.out.println(new String(rb));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void AllRead1(File file) {
        InputStream inputStream = null;
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            inputStream = new FileInputStream(file);
            byte[] buf = new byte[10];
            int byteRead = 0;
            int num = 0;
            while ((byteRead = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, byteRead);
                num++;
            }
            System.out.println("duqucishu:    " + num);
            System.out.println(outputStream.toString("UTF-8"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 需要确定字节数
    public static void AllRead0(File file) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            byte[] buf = new byte[1024];
            int off = 0;
            int byteRead = 0;
            int num = 0;
            while ((byteRead = inputStream.read(buf, off, 1024 - off)) != -1) {
                off += byteRead;
                num++;
            }
            System.out.println("读次数：" + num);
            System.out.println(new String(buf, 0, off));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void Normal(File file) {
        OutputStream outputStream = null;
        InputStream inputStream = null;
        try {
            outputStream = new FileOutputStream(file, true);
            String data = "Ynd test write\n";
            byte[] ob = data.getBytes(StandardCharsets.UTF_8);
            outputStream.write(ob);

            byte[] buf = new byte[3];
            inputStream = new FileInputStream(file);
            int bytesRead = inputStream.read(buf);
            System.out.println(new String(buf, 0, bytesRead));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void copy(InputStream input, OutputStream output) throws IOException {
        byte[] buf = new byte[4096];
        int bytesRead = 0;
        while ((bytesRead = input.read(buf)) != -1) {
            output.write(buf, 0, bytesRead);
        }
    }

    public static byte[] readFileToByteArray(File file) throws IOException {
        InputStream input = new BufferedInputStream(new FileInputStream(file));
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            copy(input, output);
            return output.toByteArray();
        } finally {
            input.close();
        }
    }

    public static void writeByteArrayToFile(File file, byte[] data) throws IOException {
        OutputStream output = new FileOutputStream(file);
        try{
            output.write(data);
        }finally {
            output.close();
        }
    }
}
