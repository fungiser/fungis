package cn.dream.mapdivide.service;

import cn.dream.mapdivide.utils.DegreeTransformUtils;
import cn.dream.mapdivide.utils.DivideCalculateUtil;
import org.springframework.stereotype.Service;

@Service
public class DivideCalculateService {

    /**
     * @param belongs   该地所在的东、西经度(belongs)
     * @param longitude 该地的经度(longitude,latitude)
     * @param latitude  该地的纬度(longitude,latitude)
     * @param scale     要计算的比例尺精度(scale)1-8:100万到5000
     * @return
     */
    public String DivideCalculate(int belongs, double longitude, double latitude, int scale) {
        DivideCalculateUtil dcUtils = new DivideCalculateUtil();
        String calculateResult = dcUtils.DivideCalculateUtil(belongs, longitude, latitude, scale);
        return calculateResult;
    }

    public int[] transformByLocation01(double degress) {
        DegreeTransformUtils dtu = new DegreeTransformUtils();
        int[] transform = dtu.transformByLocation01(degress);
        return transform;
    }

    public double transformByLocation02(String[] degress) {
        DegreeTransformUtils dtu = new DegreeTransformUtils();
        double transform = dtu.transformByLocation02(degress);
        return transform;
    }


}
