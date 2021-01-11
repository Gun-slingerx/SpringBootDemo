package org.example.util;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class AnalysisCsvUtil {


    /**
     * 解析文件
     * @param readpath
     * @param list
     */
    public static void readCSV(String readpath, ArrayList list) {
        File inFile = new File(readpath);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inFile));
            //用来跳过第一行的名称
            boolean sign = false;
            while (reader.ready()) {
                String line = reader.readLine();
                StringTokenizer st = new StringTokenizer(line, ",");
                int date, time, num_transaction, response_time;
                double sucRate;

                if (st.hasMoreTokens() && sign) {
                    date = Integer.parseInt(st.nextToken().trim());
                    time = Integer.parseInt(st.nextToken().trim());
                    num_transaction = Integer.parseInt(st.nextToken().trim());
                    sucRate = Double.parseDouble(st.nextToken().trim());
                    response_time = Integer.parseInt(st.nextToken().trim());

//                    Sample sample = new Sample(date, time, num_transaction, sucRate, response_time);
//                    list.add(sample);
                } else {
                    sign = true;
                }
            }
            reader.close();

        } catch (IOException e) {

            e.printStackTrace();
        }
    }



}



