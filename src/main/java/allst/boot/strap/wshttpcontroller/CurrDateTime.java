package allst.boot.strap.wshttpcontroller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * 获取当前的星期、日期(年月日)、时间
 * @author June 2019/03/19 下午 03:39
 * @version 1.0
 */
public class CurrDateTime {
    public static void main(String[] args) {
        String res = getCurrentFullTime();
        System.out.println(res);
//        String[] str = res.split(" ");
//        for (String a : str) {
//            System.out.println(a);
//        }
        String date = getCurrentDate();
        System.out.println(date);

        String time = getCurrentTime();
        System.out.println(time);

        String week = getDayOfTheWeek();
        System.out.println(week);

        String wk = dateToWeek(new Date());
        System.out.println(wk);

        dateFor();
    }

    public static String getCurrentFullTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public static String getCurrentDate() {
        LocalDate currentDate = LocalDate.now();
        return String.valueOf(currentDate);
    }

    public static String getCurrentTime() {
        LocalTime currentTime = LocalTime.now();
        return String.valueOf(currentTime);
    }

    public static String getDayOfTheWeek() {
        String[][] strArray = {{"1", "一"}, {"2", "二"}, {"3", "三"}, {"4", "四"}, {"5", "五"}, {"6", "六"}, {"7", "日"}};

        LocalDate currentDate = LocalDate.now();
        String k = String.valueOf(currentDate.getDayOfWeek());
        //获取行数
        for (int i = 0; i < strArray.length; i++) {
            if (k.equals(strArray[i][0])) {
                k = strArray[i][1];
                break;
            }
        }
        return "星期" + k;
    }

    public static String dateToWeek(Date datet) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        // 获得一个日历
        Calendar cal = Calendar.getInstance();
        cal.setTime(datet);
        // 指示一个星期中的某天。
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    private static void dateFor() {
        SimpleDateFormat sdf = new SimpleDateFormat();
        Date date = new Date();
        System.out.println("日期格式化为 : " + sdf.format(date));
    }
}
