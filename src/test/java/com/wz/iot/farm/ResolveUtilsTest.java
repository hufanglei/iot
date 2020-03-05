package com.wz.iot.farm;

import com.google.gson.Gson;
import com.wz.iot.utils.CommonUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

public class ResolveUtilsTest {

    byte[] bytes;

    @Before
    public void init() {
        String hexData = "232302FE4C413944484B4D543548424A584B3233360101E513071A0E02000101030100000008FEBC15C027735A010F06A30000020101045D4E124E206E16262710050006E84B2E01B59CCF0601010CF801020CF80101550103540700000000000000000008010115C0277300A80001A80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF809010100385555545454545454555454555555555554545555555554545454545454545454545555555454545454545454545454555455545455555555C8";
        bytes = CommonUtils.hexStringToByte(hexData);
    }

    public static void main(String[] args) throws IOException {
//        String hexData = "232302FE4C413944484B4D543548424A584B3233360101E513071A0E02000101030100000008FEBC15C027735A010F06A30000020101045D4E124E206E16262710050006E84B2E01B59CCF0601010CF801020CF80101550103540700000000000000000008010115C0277300A80001A80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF809010100385555545454545454555454555555555554545555555554545454545454545454545555555454545454545454545454555455545455555555C8";
//
//        System.out.println(hexData.substring(0, 44));

//        String s = new SimpleDateFormat("YYMMddHHmmss").format(new Date());
//        System.out.println(Long.);

    /*    String s = "200304100337" +//数据 时间
                "0001" +//数据 流水号
                "03" +//数据 信息类型标志 农机作业数据
                "00001111" +//数据 信息农机作业 作业面积 4个字节
                "200304060337" +//数据 信息农机作业  开始时间
                "200304100337";//数据 信息农机作业  结束时间
        System.out.println(s.length());
*/

       /* //http://www.ip33.com/bcc.html BCC校验
        String s = "232302FE4C413944484B4D543548424A584B32333601" +
                "0019" +  //单元长度
                "200304100337" +//数据 时间
                "0001" +//数据 流水号
                "03" +//数据 信息类型标志 农机作业数据
                "00001111" +//数据 信息农机作业 作业面积 4个字节
                "200304060337" +//数据 信息农机作业  开始时间
                "200304100337";
        System.out.println(s);
        byte[] bytes = CommonUtils.hexStringToByte("02FE4C413944484B4D543548424A584B32333601001920030410033700010300001111200304060337200304100337");
        //计算bcc
        String bcc = getBCC(bytes, 0, bytes.length - 1.json);
        System.out.println(bcc);*/


       //计算定位状态
        int i = 0b0000_0000;
    }

    //作业面积
    //232302FE4C413944484B4D543548424A584B3233360100191403040A0325000103000011111403040603251403040A0325C8
    String hex2 = "232302FE4C413944484B4D543548424A584B32333601" +
            "0019" +  //单元长度
            "1403040A0325" +//数据 时间
            "0001" +//数据 流水号
            "03" +//数据 信息类型标志 农机作业数据
            "00001111" +//数据 信息农机作业 作业面积 4个字节
            "140304060325" +//数据 信息农机作业  开始时间 6个字节
            "1403040A0325" +//数据 信息农机作业  结束时间 6个字节
            "C8";//校验


    //终端定位
    //232302FE4C413944484B4D543548424A584B3233360100191403040A0325000103000011111403040603251403040A0325C8
    String hex = "232302FE4C413944484B4D543548424A584B32333601" +
            "001D" +  //单元长度
            "1403040A0325" +//数据 时间6
            "0001" +//数据 流水号2
            "01" +//数据 信息类型标志 终端定位 1.json
            "00" +//数据 定位状态 1个字节
            "06F0ABB1" +//数据 经度 4个字节
            "0260FAB1" +//数据 纬度4个字节
            "044C" +//数据 GNSS速度 2个字节
            "0032" +//数据 水平精度因子 2个字节
            "000003E8" +//数据 海波高度 4个字节
            "02" +//数据 卫星数 1个字节
            "0078" +//数据 地面航向 2个字节
            "ED";//校验



    //农机工况信息体
    //232302FE4C413944484B4D543548424A584B32333601001D1403040A03250001010006F0ABB10260FAB1044C0032000003E8020078ED
    String hex3 = "232302FE4C413944484B4D543548424A584B32333601" +
            "001B" +  //单元长度
            "1403040A0325" +//数据 时间6
            "0001" +//数据 流水号2
            "02" +//数据 信息类型标志 农机工况信息体 1.json
            "1C20" +//数据 发动机转速 2个字节
            "64" +//数据 机油压力 1个字节
            "000007D0" +//数据 发动机工作时间 4个字节
            "00002710" +//数据 燃油消耗总量 4个字节
            "0096" +//数据 每小时油耗  2个字节
            "1E" +//数据 发动机实际扭矩百分比 1个字节
            "000003E8" +//数据 行驶总里程 4个字节
            "43";//校验

    //混合
    //232302FE4C413944484B4D543548424A584B3233360100411403040A03250001010006F0ABB10260FAB1044C0032000003E8020078021C2064000007D00000271000961E000003E803000011111403040603251403040A03255B
    String hex4 = "232302FE4C413944484B4D543548424A584B32333601" +
            "0041" +  //单元长度
            "1403040A0325" +//数据 时间6
            "0001" +//数据 流水号2
            "01" +//数据 信息类型标志 终端定位 1.json
            "00" +//数据 定位状态 1个字节
            "06F0ABB1" +//数据 经度 4个字节
            "0260FAB1" +//数据 纬度4个字节
            "044C" +//数据 GNSS速度 2个字节
            "0032" +//数据 水平精度因子 2个字节
            "000003E8" +//数据 海波高度 4个字节
            "02" +//数据 卫星数 1个字节
            "0078" +//数据 地面航向 2个字节
            "02" +//数据 信息类型标志 农机工况信息体 1.json
            "1C20" +//数据 发动机转速 2个字节
            "64" +//数据 机油压力 1个字节
            "000007D0" +//数据 发动机工作时间 4个字节
            "00002710" +//数据 燃油消耗总量 4个字节
            "0096" +//数据 每小时油耗  2个字节
            "1E" +//数据 发动机实际扭矩百分比 1个字节
            "000003E8" +//数据 行驶总里程 4个字节
            "03" +//数据 信息类型标志 农机作业数据
            "00001111" +//数据 信息农机作业 作业面积 4个字节
            "140304060325" +//数据 信息农机作业  开始时间 6个字节
            "1403040A0325" +//数据 信息农机作业  结束时间 6个字节
            "5B";//校验


    /**
     * 实时数据测试
     * @throws IOException
     */
    @Test
    public void resolve() throws IOException {
//      System.out.println(s.length());
        System.out.println(hex4);
        bytes = CommonUtils.hexStringToByte(hex4);
        Map<String, Object> resolve = ResolveUtils.resolveRealtime(bytes);
        System.out.println(new Gson().toJson(resolve));
    }

    /**
     * 测试车辆登入解析
     */
    @Test
    public void resolveVehicleLogin() {
        String hexData = "232301FE4C53364841313034314A4737383237323401001E13071A0E0216000338393836303242313033313543303530393432360100D5";
        byte[] bytes = ResolveUtils.hexStringToByte(hexData);
        Map<String, Object> stringObjectMap = ResolveUtils.resolveVehicleLogin(bytes);
        System.out.println(new Gson().toJson(stringObjectMap));
    }

    /**
     * 测试车辆登出解析
     */
    @Test
    public void resolveVehicleLogOut() {
        String hexData = "232304FE4C413942475A4C46304A5959434B30303601000813071A0E02020001D7";
        byte[] bytes = ResolveUtils.hexStringToByte(hexData);
        Map<String, Object> stringObjectMap = ResolveUtils.resolveVehicleLogOut(bytes);
        System.out.println(new Gson().toJson(stringObjectMap));
    }

}