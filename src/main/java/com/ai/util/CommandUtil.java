package com.ai.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CommandUtil {
    // 保存进程的输入流信息
    private List<String> stdoutList = new ArrayList<String>();
    // 保存进程的错误流信息
    private List<String> erroroutList = new ArrayList<String>();


    public boolean wavToPcm(String wav, String pcm) {

        String command = "ffmpeg -i " + wav + "  -f s16be -ar 8000 -ac 1 -acodec pcm_s16be " + pcm;

        File f = new File(pcm);
        if (f.exists()) {
            f.delete();
        }

        CommandUtil util = new CommandUtil();
        util.executeCommand(command);
        for (String string : util.getErroroutList()) {
            System.out.println(string);
            if (string.contains(pcm)) {
                return true;
            }
        }

        return false;
    }

    public void executeCommand(String command) {
        // 先清空
        stdoutList.clear();
        erroroutList.clear();

        Process p = null;
        try {
            p = Runtime.getRuntime().exec(command);

            // 创建2个线程，分别读取输入流缓冲区和错误流缓冲区
            ThreadUtil stdoutUtil = new ThreadUtil(p.getInputStream(), stdoutList);
            ThreadUtil erroroutUtil = new ThreadUtil(p.getErrorStream(), erroroutList);
            //启动线程读取缓冲区数据
            stdoutUtil.start();
            erroroutUtil.start();

            p.waitFor();
            p.destroy();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<String> getStdoutList() {
        return stdoutList;
    }

    public List<String> getErroroutList() {
        return erroroutList;
    }



    class ThreadUtil implements Runnable {
        // 设置读取的字符编码
        private String character = "GB2312";
        private List<String> list;
        private InputStream inputStream;

        public ThreadUtil(InputStream inputStream, List<String> list) {
            this.inputStream = inputStream;
            this.list = list;
        }

        public void start() {
            Thread thread = new Thread(this);
            thread.setDaemon(true);//将其设置为守护线程
            thread.start();
        }

        public void run() {
            BufferedReader br = null;
            try {
                br = new BufferedReader(new InputStreamReader(inputStream, character));
                String line = null;
                while ((line = br.readLine()) != null) {
                    if (line != null) {
                        list.add(line);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    //释放资源
                    inputStream.close();
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
