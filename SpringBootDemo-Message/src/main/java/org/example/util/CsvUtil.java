package org.example.util;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author:Lr
 * @createTime:2021/1/12 15:54
 * @see:org.example.util
 */
public class CsvUtil {

    public static List<String[]> getCsvData(InputStream in, String charsetName) {
        List<String[]> list = new ArrayList<String[]>();
        int i = 0;
        try (CSVReader csvReader = new CSVReaderBuilder(
                new BufferedReader(
                        new InputStreamReader(
                                in, charsetName))).build()) {
            for (String[] next : csvReader) {
                //去除第一行的表头，从第二行开始
                if (i >= 1) {
                    list.add(next);
                }
                i++;
            }
            return list;
        } catch (Exception e) {
            System.out.println("CSV文件读取异常");
            return list;
        }
    }
}
