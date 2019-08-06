package cn.dream.mapdivide.utils;

public class DivideCalculateUtil {

    public DivideCalculateUtil() {

    }

    /**
     * @param belongs   该地所在的东、西经度(belongs)
     * @param longitude 该地的经度(longitude,latitude)
     * @param latitude  该地的纬度(longitude,latitude)
     * @param scale     要计算的比例尺精度(scale)1-8:100万到5000
     * @return
     */
    public String DivideCalculateUtil(int belongs, double longitude, double latitude, int scale) {
        String divideNumber = "";

        //该地所在经度为东经
        if (belongs == 0) {
            //计算1：100w图幅编号
            int longitude100 = (int) Math.floor(longitude / 6) + 31;//东经
            int latitude100 = (int) Math.floor(latitude / 4) + 1;//纬度
            String referrence100 = latitudeReferrence(latitude100);
            divideNumber = locationCalculateByScale(referrence100, longitude100, longitude, latitude, scale);
        } else {
            //计算1：100w图幅编号
            int longitude100 = 30 - (int) Math.floor(longitude / 6);//西经
            int latitude100 = (int) Math.floor(latitude / 4) + 1;//纬度
            String referrence100 = latitudeReferrence(latitude100);
            divideNumber = locationCalculateByScale(referrence100, longitude100, longitude, latitude, scale);
        }
        return divideNumber;
    }


    public String latitudeReferrence(int latitude100) {
        String str = (char) (64 + latitude100) + "";
        return str;
    }

    public String locationCalculateByScale(String referrence100, int longitude100, double longitude, double latitude, int scale) {
        String divideNumber = "";
        double a = 0, b = 0;
        int c = 0, d = 0;
        switch (scale) {
            //1:50万--字母代码：B
            case 2:
                a = 3;//经差
                b = 2;//纬差
                c = ((int) (4 / b - Math.floor((latitude % 4) / b)));
                break;
            //1:25万--字母代码：C
            case 3:
                a = 1.5;
                b = 1;
                c = ((int) (4 / b - Math.floor((latitude % 4) / b)));
                break;
            //1:10万--字母代码：D
            case 4:
                a = 0.5;
                b = 1 / 3;
                c = ((int) (4 * 3 - Math.floor((latitude % 4) * 3)));
                break;
            //1:5万--字母代码：E
            case 5:
                a = 0.25;
                b = 1 / 6;
                c = ((int) (4 * 6 - Math.floor((latitude % 4) * 6)));
                break;
            //1:2.5万--字母代码：F
            case 6:
                a = 0.125;
                b = 1 / 12;
                c = ((int) (4 * 12 - Math.floor((latitude % 4) * 12)));
                break;
            //1:1万--字母代码：G
            case 7:
                a = 0.0625;
                b = 1 / 24;
                c = ((int) (4 * 24 - Math.floor((latitude % 4) * 24)));
                break;
            //1:1万--字母代码：H
            case 8:
                a = 0.03125;
                b = 1 / 48;
                c = ((int) (4 * 48 - Math.floor((latitude % 4) * 48)));
                break;
        }
        if (scale == 1) {
            //100万比例尺下 字母代码：A
            divideNumber = referrence100 + formatBy2position(longitude100);
        } else {
            d = (int) (Math.floor((longitude % 6) / a) + 1);
            divideNumber = referrence100 + formatBy2position(longitude100) + latitudeReferrence(scale) + formatBy3position(c) + formatBy3position(d);
        }
        return divideNumber;
    }

    public String formatBy2position(int number) {
        String str = number < 10 ? "0" + number : number + "";
        return str;
    }

    public String formatBy3position(int number) {
        String str = "";
        if (number < 10) {
            str = "00" + number;
        } else if (number == 10 || (number > 10 && number < 100)) {
            str = "0" + number;
        } else {
            str = number + "";
        }
        return str;
    }


}
