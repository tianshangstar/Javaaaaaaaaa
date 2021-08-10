package com.evan.javaaaaaaaaa.advanced.io.bio;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FileBIO {

    /**
     * 这里只是bio的例子，如果同时操作两个file才是完整的例子
     */
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();

        File file = new File("D:\\develop\\workspace\\other\\Javaaaaaaaaa\\src\\com\\evan\\javaaaaaaaaa\\advanced\\io\\file_for_test");
        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
//            reader = new BufferedReader(new FileReader(file));
            writer = new BufferedWriter(new FileWriter(file));
//            executorService.execute(new ReadFileTask(reader, "firstTask"));
//            executorService.execute(new ReadFileTask(reader, "secondTask"));
            executorService.execute(new WriteFileTask(writer, "firstTask"));
            // 这里不会生效，因为都是写到同一个buffer里面了。每次flush的时候不一定刷的是当前task还是两个task写入的内容
//            executorService.execute(new WriteFileTask(writer, "secondTask"));

            executorService.shutdown();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 只是为了测试，reader没close哈
//        finally {
//            try {
//                if (reader != null) {
//                    reader.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

    }

    static class WriteFileTask implements Runnable {
        private BufferedWriter writer;

        private String taskName;

        WriteFileTask(BufferedWriter bufferedWriter, String taskName) {
            this.writer = bufferedWriter;
            this.taskName = taskName;
        }

        @Override
        public void run() {

            for (int i = 0; i < 10; i++) {
                try {
                    writer.newLine();
                    String content = taskName + " >>>> " + i;
                    System.err.println(taskName + " write content : " + content);
                    writer.write(taskName + " >>>> " + i);
                    // 写一行刷一行进去
                    Thread.sleep(200);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class ReadFileTask implements Runnable {

        private BufferedReader reader;

        private String taskName;

        ReadFileTask(BufferedReader reader, String taskName) {
            this.reader = reader;
            this.taskName = taskName;
        }

        @Override
        public void run() {
            while (true) {
                System.err.println("task : " + taskName + " is running");
                try {

                    String lineContent;
                    if (StringUtils.isEmpty(lineContent = reader.readLine())) {
                        break;
                    }
                    System.err.println(taskName + " >>>>> " + lineContent);
                    Thread.sleep(2000);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
