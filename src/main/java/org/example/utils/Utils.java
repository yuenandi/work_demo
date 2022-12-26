package org.example.utils;

import java.io.UnsupportedEncodingException;
import org.apache.commons.lang3.StringUtils;

public class Utils {
    public static void MySystemOutPf(String msg, int s) {

        String split = "+----------------------------------------------------------------------------------------------------+";

        switch (s){
            case 0:
                System.out.printf("%s\n", split);
                break;
            case 1:
                System.out.printf("%s\n", split);
                System.out.print(StringUtils.rightPad("|"+ msg, split.length() - 1, ' '));
                System.out.println("|");
                break;
            case 2:
                System.out.print(StringUtils.rightPad("|"+ msg, split.length() - 1, ' '));
                System.out.println("|");
                break;
            case 3:
                System.out.print(StringUtils.rightPad("|"+ msg, split.length() - 1, ' '));
                System.out.println("|");
                System.out.printf("%s\n", split);
                break;
        }

    }
}
