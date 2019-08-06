package cn.dream.mapdivide.utils;

public class DegreeTransformUtils {

    /**
     * 经纬度由十进制转换为度分秒
     * @param degress
     * @return
     */
    public int[] transformByLocation01(double degress){
        int degree = (int) Math.floor(degress);
        int minute = (int) Math.floor((degress - degree)*60);
        int second =(int) Math.rint( (((degress - degree)*60) - minute)*60);
        int [] numberGroup = new int[3];
        numberGroup[0] = degree;
        numberGroup[1] = minute;
        numberGroup[2] = second;
        return numberGroup;
    }

    /**
     * 经纬度由度分秒转换为十进制
     * @param degress
     * @return
     */
    public double transformByLocation02(String [] degress){
        double degree = Double.parseDouble(degress[0]);
        double minute = Double.parseDouble(degress[1]);
        double second = Double.parseDouble(degress[2]);
        double sum=degree+(minute/60)+(second/3600);
        return sum;

    }
}
