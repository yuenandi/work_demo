package org.example.file.demo;

import org.example.utils.MakeData;
import org.example.utils.NameData;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class charStreamDemo {
    public static void main(String[] args) throws IOException {
        File file = new File("/Users/yuenandi/Project/work_demo/src/main/java/org/example/stream/demo/hello.txt");
        File peopleFile = new File("/Users/yuenandi/Project/work_demo/src/main/java/org/example/stream/demo/peopleData.txt");
        MakeData makeData = new MakeData();
        List<NameData> nameData = makeData.makeData(200);
//        normal(file);
//        writeStudents(nameData, peopleFile);
        String fileName = "/Users/yuenandi/Project/work_demo/src/main/java/org/example/stream/demo/peopleData.txt";
        List<String> data = new ArrayList<>();
        nameData.stream().forEach((nd) -> {
            data.add(String.format("%s\t\t\t%s", "姓名：" + nd.getName(), "身份证号：" + nd.getIdCardNumber()));
        });
        String strData = data.stream().collect(Collectors.joining("\n"));

        writeStringToFile(fileName, strData, StandardCharsets.UTF_8);

        System.out.println(readFileToString(fileName, StandardCharsets.UTF_8));
    }

    public static void normal(File file) {
        Writer writer = null;
        Reader reader = null;
        try {
            writer = new OutputStreamWriter(new FileOutputStream(file));
            String str = "Ynd test writer";
            writer.write(str);

            writer.close();
            reader = new InputStreamReader(new FileInputStream(file));
            char[] cbuf = new char[1024];
            reader.read(cbuf);
            System.out.println("-----" + new String(cbuf));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void writeStudents(List<NameData> nameData, File file) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            for (NameData n : nameData) {
                writer.write("姓名：" + n.getName() + ", 身份证号：" + n.getIdCardNumber());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void copy(final Reader input, final Writer output) throws IOException {
        char[] buf = new char[4096];
        int charsRead = 0;
        while ((charsRead = input.read(buf)) != -1) {
            output.write(buf, 0, charsRead);
        }
    }

    public static String readFileToString(final String fileName, final Charset encoding) throws IOException {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), encoding));
            StringWriter writer = new StringWriter();
            copy(reader, writer);
            return writer.toString();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    public static void writeStringToFile(final String fileName, final String data, final Charset encoding) throws IOException {
        Writer writer = null;
        try {
            writer = new OutputStreamWriter(new FileOutputStream(fileName), encoding);
            writer.write(data);
        } finally {
            if (writer != null) {
                writer.close();

            }
        }
    }
}
