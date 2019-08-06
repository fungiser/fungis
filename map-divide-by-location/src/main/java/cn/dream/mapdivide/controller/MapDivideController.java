package cn.dream.mapdivide.controller;

import cn.dream.mapdivide.service.DivideCalculateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MapDivideController {

    @Autowired
    private DivideCalculateService divideCalculateService;

    @RequestMapping("/mapdivide")
    public String enterHello() {
        return "mapdivide";
    }

    @RequestMapping("/mapdivide02")
    public String enterHello02() {
        return "mapdivide02";
    }

    @RequestMapping("/transform")
    public String enterHello03() {
        return "transform01";
    }


    @RequestMapping(value = "/calculate/mapdivide")
    public String list(@RequestParam("belongs") int belongs,
                       @RequestParam("longitude") double longitude,
                       @RequestParam("latitude") double latitude, @RequestParam("scale") int scale, Model model) {
        if (!StringUtils.isEmpty(belongs) && !StringUtils.isEmpty(longitude) && !StringUtils.isEmpty(latitude) && !StringUtils.isEmpty(scale)) {
            String calculateResult = divideCalculateService.DivideCalculate(belongs, longitude, latitude, scale);
           // System.out.println(belongs + "||" + longitude + "||" + latitude + "||" + scale);
            model.addAttribute("calculateResult", calculateResult);
        }
        model.addAttribute("belongs", belongs);
        model.addAttribute("longitude", longitude);
        model.addAttribute("latitude", latitude);
        model.addAttribute("scale", scale);
        return "mapdivide";
    }

    /*
    度分秒分幅计算器
     */
    @RequestMapping(value = "/calculate/mapdivide02")
    public String degreeCalculate(@RequestParam("belongs") int belongs, @RequestParam("scale") int scale, Model model,
                                  @RequestParam("longdegree") int longdegree, @RequestParam("latidegree") int latidegree,
                                  @RequestParam("longminute") int longminute, @RequestParam("latiminute") int latiminute,
                                  @RequestParam("longsecond") double longsecond, @RequestParam("latisecond") double latisecond) {

        if (!StringUtils.isEmpty(belongs) && !StringUtils.isEmpty(scale) && !StringUtils.isEmpty(longdegree) && !StringUtils.isEmpty(latidegree)) {
            String[] strings01 = new String[3];
            String[] strings02 = new String[3];
            strings01[0] = longdegree + "";
            strings01[1] = longminute + "";
            strings01[2] = longsecond + "";
            strings02[0] = latidegree + "";
            strings02[1] = latiminute + "";
            strings02[2] = latisecond + "";
            double transformLong = divideCalculateService.transformByLocation02(strings01);
            double transformLati = divideCalculateService.transformByLocation02(strings02);
            String calculateResult = divideCalculateService.DivideCalculate(belongs, transformLong, transformLati, scale);
            System.out.println(belongs + "||" + transformLong + "||" + transformLati + "||" + scale);
            model.addAttribute("calculateResult", calculateResult);
            model.addAttribute("longdegree", longdegree);
            model.addAttribute("longminute", longminute);
            model.addAttribute("longsecond", longsecond);
            model.addAttribute("latidegree", latidegree);
            model.addAttribute("latiminute", latiminute);
            model.addAttribute("latisecond", latisecond);

        }
        return "mapdivide02";
    }

    /*
    度数间相互转换      度分秒转十进制
     */
    @RequestMapping(value = "/calculate/transform01")
    public String transformCalculate01(Model model,
                                       @RequestParam("degree") int degree,
                                       @RequestParam("minute") int minute,
                                       @RequestParam("second") double second) {
        if (!StringUtils.isEmpty(degree) && !StringUtils.isEmpty(minute) && !StringUtils.isEmpty(second)) {
            String[] strings01 = new String[3];
            strings01[0] = degree + "";
            strings01[1] = minute + "";
            strings01[2] = second + "";
            double degrees02 = divideCalculateService.transformByLocation02(strings01);
            model.addAttribute("degrees02", degrees02);
        }
        model.addAttribute("degree", degree);
        model.addAttribute("minute", minute);
        model.addAttribute("second", second);
        return "transform01";

    }

    @RequestMapping(value = "/calculate/transform02")
    public String transformCalculate01(Model model, @RequestParam("degrees") double degrees) {
        if (!StringUtils.isEmpty(degrees)) {
            int[] location01 = divideCalculateService.transformByLocation01(degrees);
            int degree02 = location01[0];
            int minute02 = location01[1];
            double second02 = location01[2];
            model.addAttribute("degrees", degrees);
            model.addAttribute("degree02", degree02+"°");
            model.addAttribute("minute02", minute02+"′");
            model.addAttribute("second02", second02+"″");
        }
        return "transform01";
    }


}
