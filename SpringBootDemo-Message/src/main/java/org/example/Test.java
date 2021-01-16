package org.example;

import com.opencsv.CSVReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * @description:
 * @author:Lr
 * @createTime:2021/1/12 10:45
 * @see:org.example
 */
public class Test {

    public static void main(String[] args) {
//        From from = new From();
//        from.setDate(new Date());
//        from.setStrFromA("AAAAAA");
//        from.setStrFromB("1");
//        CsvHandleImpl csvHandle = new CsvHandleImpl();
//        To to = csvHandle.csvHandleMappinga(from, To.class);
//        System.out.println(to.toString());

        CSVReader csvReader = null;
        try {
            String path = "D:\\code\\SpringBootDemo\\SpringBootDemo-Message\\src\\main\\resources\\file\\sec_permission.del";
            csvReader = new CSVReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8));
            String[] strs;
            while ((strs = csvReader.readNext()) != null) {
                //转为ArrayList<Model>
                System.out.println(Arrays.deepToString(strs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (csvReader != null) {
                    csvReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
