package com.wz.iot.newe;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;


/**
 * 解析工具类
 *
 * @author hfl on 2019/9/9
 */
public class ResolveUtils {
    private static final Logger logger = LogManager.getLogger(ResolveUtils.class);

    public static final String STARTE_SYMBOL = "##";
    public static final String STARTE_SYMBOL_HEX = "2323";
    public static final int FE = 0xfe;
    public static final int FF = 0xff;
    public static final int FF_FE = 0xfffe;
    public static final int FF_FF = 0xffff;
    public static final int FF_FF_FF_FE = 0xfffffffe;
    public static final int FF_FF_FF_FF = 0xffffffff;
    //车牌号
    public final static String VIN = "vin";
    //采集时间
    public final static String GATHER_TIME = "gathertime";
    //车辆状态
    public final static String CARSTS = "carsts";
    //充电状态
    public final static String CDZT = "cdzt";
    //运行模式
    public final static String RUNMODE = "runmode";
    //车速
    public final static String CAR_SPEED = "carspeed";
    //总里程
    public final static String TOTAL_MILES = "totalmiles";
    //总电压
    public final static String TOTALV = "totalv";
    //总电流
    public final static String TOTALC = "totalc";
    //SOC
    public final static String SOC = "soc";
    //DCDC状态
    public final static String DCDCSTS = "dcdcsts";
    //驱动
    public final static String QDZT = "qdzt";
    //制动
    public final static String ZDZT = "zdzt";
    //挡位
    public final static String DW = "dw";
    //绝缘电阻
    public final static String JYDZ = "jydz";
    //加速踏板开度
    public final static String JSTBKD = "jstbkd";
    //制动踏板开度
    public final static String ZDTBKD = "zdtbkd";


    //驱动电机个数
    public final static String MOTONUMS = "motonums";
    //驱动电机序号
    public final static String MOTOXH = "motoxh";
    //驱动电机状态
    public final static String MOTOSTS = "motosts";
    //驱动电机控制器温度
    public final static String MOTOCON_TEMP = "motocontemp";
    //驱动电机转速
    public final static String MOTORPM = "motorpm";
    //驱动电机转矩
    public final static String MOTOZJ = "motozj";
    //驱动电机温度
    public final static String MOTOTEMP = "mototemp";
    //电机控制器输入电压
    public final static String MOTOCONINV = "motoconinv";
    //电机控制器直流母线电流
    public final static String MOTODMC = "motodmc";


    //燃料电池电压
    public final static String RLBATV = "rlbatv";
    //燃料电池电流
    public final static String RLBATC = "rlbatc";
    //燃料消耗率
    public final static String RLXHL = "rlxhl";
    //燃料电池温度探针总数
    public final static String RLDCWDTZZS = "rldcwdtzzs";
    //探针温度值
    public final static String TZWDZ = "tzwdz";
    //氢系统中最高温度
    public final static String QXTMAX_TEMP = "qxtmaxtemp";
    //氢系统中最高温度探针代号
    public final static String QXTMAX_TEMP_NUM = "qxtmaxtempnum";
    //氢气最高浓度
    public final static String QQZGND = "qqzgnd";
    //氢气最高浓度传感器代号
    public final static String QQZGNDCGQDH = "qqzgndcgqdh";
    //氢气最高压力
    public final static String QQZGYL = "qqzgyl";
    //氢气最高压力传感器代号
    public final static String QQZGYLCGQDH = "qqzgylcgqdh";
    //高压DCDC状态
    public final static String GYDCDCZT = "gydcdczt";

    //发动机状态
    public final static String FDJZT = "fdjzt";
    //曲轴转速
    public final static String QZZS = "qzzs";
    //燃料消耗率
    public final static String FDJRLXHL = "fdjrlxhl";


    //定位状态
    public final static String DWFLAG = "dwflag";
    //精度
    public final static String LNG = "lng";
    //纬度
    public final static String LAT = "lat";


    //最高电压电池子系统号
    public final static String MAXVB_NUM = "maxvbnum";
    //最高电压电池单体代号
    public final static String MAXVS_NUM = "maxvsnum";
    //电池单体电压最高值
    public final static String MAXV = "maxv";
    //最低电压电池子系统号
    public final static String MINVB_NUM = "minvbnum";
    //最低电压电池单体代号
    public final static String MINVSNUM = "minvsnum";
    //电池单体电压最低值
    public final static String MINV = "minv";
    //最高温度子系统号
    public final static String MAXTEMPZCH = "maxtempzch";
    //最高温度探针单体号
    public final static String MAXTEMPTZH = "maxtemptzh";
    //最高温度值
    public final static String MAXTEMP = "maxtemp";
    //最低温度子系统号
    public final static String MINTEMPZCH = "mintempzch";
    //最低温度探针单体号
    public final static String MINTEMPTZH = "mintemptzh";
    //最低温度值
    public final static String MINTEMP = "mintemp";


    //最高报警等级
    public final static String MAXWLVL = "maxwlvl";
    //温度差异报警
    public final static String WDCYBJ = "wdcybj";
    //电池高温报警
    public final static String DCGWBJ = "dcgwbj";
    //车载储能装置类型过压报警
    public final static String DLXDCBGYBJ = "dlxdcbgybj";
    //车载储能装置类型欠压报警
    public final static String DLXDCBQYBJ = "dlxdcbqybj";
    //SOC低报警
    public final static String SOCDBJ = "socdbj";
    //单体电池过压报警
    public final static String DTXDCGYBJ = "dtxdcgybj";
    //单体电池欠压报警
    public final static String DTXDCQYBJ = "dtxdcqybj";
    //SOC过高报警
    public final static String SOCGGBJ = "socggbj";
    //SOC跳变报警
    public final static String SOCTBBJ = "soctbbj";
    //可充电储能系统不匹配报警
    public final static String DLXDCBBPPBJ = "dlxdcbbppbj";
    //电池单体一致性差报警
    public final static String DLXDCDTYZXCBJ = "dlxdcdtyzxcbj";
    //绝缘报警
    public final static String JYBJ = "jybj";
    //DC-DC温度报警
    public final static String DCDCWDBJ = "dcdcwdbj";
    //制动系统报警
    public final static String ZDXTBJ = "zdxtbj";
    //DC-DC状态报警
    public final static String DCDCZTBJ = "dcdcztbj";
    //驱动电机控制器温度报警
    public final static String QDDJKZQWDBJ = "qddjkzqwdbj";
    //高压互锁状态报警
    public final static String GYHSZTBJ = "gyhsztbj";
    //驱动电机温度报警
    public final static String QDDJWDBJ = "qddjwdbj";
    //车载储能装置类型过充
    public final static String DLXDCGC = "dlxdcgc";


    //可充电储能装置故障总数
    public final static String DCGZZS = "dcgzzs";
    //可充电储能装置故障码
    public final static String DCGZM = "dcgzm";
    //驱动电机故障总数
    public final static String QDDJGZZS = "qddjgzzs";
    //驱动电机故障码
    public final static String QDDJGZM = "qddjgzm";
    //发动机故障总数
    public final static String FDJGZZS = "fdjgzzs";
    //发动机故障码
    public final static String FDJGZM = "fdjgzm";
    //其他故障总数
    public final static String QTGZZS = "qtgzzs";
    //其他故障码
    public final static String QTGZM = "qtgzm";


    //可充电储能子系统个数
    public final static String XDCZXTS = "xdczxts";
    //可充电储能子系统号
    public final static String DYDCZXT = "dydczxt";
    //可充电储能装置电压
    public final static String BATV = "batv";
    //可充电储能装置电流
    public final static String BATC = "batc";
    //单体电池总数
    public final static String BATSVNUMS = "batsvnums";
    //单体电池电压
    public final static String SINGLEVS = "singlevs";


    //可充电储能子系统号
    public final static String WDDCZXT = "wddczxt";
    //可充电储能温度探针个数
    public final static String BATSTNUMS = "batstnums";
    //可充电储能温度探针检测的温度
    public final static String SINGLETS = "singlets";


    /**
     * 解析实时数据
     * 解析这个字符串并将其放入 map集合中
     */
    public static Map<String, Object> resolveRealtime(byte[] bytes) {
        String originHex = bytesToHexString(bytes);
        try {
            Map<String, Object> map = resolve(bytes, 0x02);
            logger.info("原始报文[上报实时信息] - [" + originHex + "]");
            return map;
        } catch (IOException e) {
            logger.error("[实时信息解析出错] -  [" + originHex + "]");
        }
        return null;
    }

    /**
     * 解析补发数据
     * 解析这个字符串并将其放入 map集合中
     */
    public static Map<String, Object> resolveReissue(byte[] bytes) {
        String originHex = bytesToHexString(bytes);
        try {
            Map<String, Object> map = resolve(bytes, 0x03);
            logger.info("原始报文[上报补发信息] - [" + originHex + "]");
            return map;
        } catch (IOException e) {
            logger.error("[补发信息解析出错] -  [" + originHex + "]");
        }
        return null;
    }

    /**
     * 解析车辆登入数据
     * <p>
     * 解析这个字符串并将其放入 map集合中
     */
    public static Map<String, Object> resolveVehicleLogin(byte[] bytes) {
        String originHex = bytesToHexString(bytes);
        try {
            Map<String, Object> map = resolve(bytes, 0x01);
            logger.info("原始报文[上报车辆登入] - [" + originHex + "]");
            return map;
        } catch (IOException e) {
            logger.error("[车辆登出数据解析出错] -  [" + originHex + "]");
        }
        return null;
    }

    /**
     * 解析车辆登出数据
     * 解析这个字符串并将其放入 map集合中
     */
    public static Map<String, Object> resolveVehicleLogOut(byte[] bytes) {
        String originHex = bytesToHexString(bytes);
        try {
            Map<String, Object> map = resolve(bytes, 0x04);
            logger.info("原始报文[上报车辆登出] - [" + originHex + "]");
            return map;
        } catch (IOException e) {
            logger.error("[车辆登出数据解析出错] -  [" + originHex + "]");
        }
        return null;
    }

    /**
     * 解析这个字符串并将其放入 map集合中
     * @param bytes
     */
    public static Map<String, Object> resolve(byte[] bytes, int infoFlag) throws IOException {
        Map<String, Object> map = new HashMap<>();
        //转成16进制字符串
        String hexStr = bytesToHexString(bytes);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        DataInputStream dis = new DataInputStream(byteArrayInputStream);

        //跳过前4个字节
        dis.skipBytes(4);
        //车辆识别代号字符串
        byte[] vinArr = new byte[17];
        dis.read(vinArr);
        map.put(VIN, new String(vinArr));
        //跳过加密方式1个字节
        dis.skipBytes(1);
        //数据单元长度
        int dataLen = dis.readUnsignedShort();
        //数据单元16进制
        String hexData = hexStr.substring(48, 48 + dataLen * 2);
        byte[] dataBytes = hexStringToByte(hexData);
        //解析实时数据
        //数据采集时间
        setGanterTime(map, dis);
        //解析剩下的数据
        byte[] otherBytes = subArray(dataBytes, 6, dataBytes.length - 6);
        switch (infoFlag) {
//            case 0x01:
//                vehicleLoginResolveDetail(otherBytes, map, dis);
//                break;
            case 0x02:
            case 0x03:
                resovleDetail(otherBytes, map, dis);
                break;
//            case 0x04:
//                vehicleLogOutResolveDetail(otherBytes, map, dis);
//                break;
        }

        return map;
    }

    public static void resovleDetail(byte[] bytes, Map<String, Object> map, DataInputStream dis) throws IOException {
        if (bytes.length > 0) {
            //信息类型标志
            int infoDataType = bytes[0];
            byte[] otherBytes = null;
            switch (infoDataType) {
                case 0x01:
                    //整车数据
                    otherBytes = integralVehicleResovleDetail(bytes, map, dis);
                    //获取剩余的数据
                    resovleDetail(otherBytes, map, dis);
                    break;
                case 0x02:
                    //驱动电机数据
                    otherBytes = motorResolveDetail(bytes, map, dis);
                    resovleDetail(otherBytes, map, dis);
                    break;
                case 0x03:
                    //燃料电池数据
                    otherBytes = fuelBatteryResolveDetail(bytes, map, dis);
                    resovleDetail(otherBytes, map, dis);
                    break;
                case 0x04:
                    //发动机数据
                    otherBytes = engineResovleDetail(bytes, map, dis);
                    resovleDetail(otherBytes, map, dis);
                    break;
                case 0x05:
                    //车辆位置数据
                    otherBytes = locationResolveDetail(bytes, map, dis);
                    resovleDetail(otherBytes, map, dis);
                    break;
                case 0x06:
                    //极值数据
                    otherBytes = extremeResolveDetail(bytes, map, dis);
                    resovleDetail(otherBytes, map, dis);
                    break;
                case 0x07:
                    //报警数据
                    otherBytes = alarmResolveDetail(bytes, map, dis);
                    resovleDetail(otherBytes, map, dis);
                    break;
                case 0x08:
                    //可充电储能装置电压数据
                    otherBytes = rechargevDetailResolveDetail(bytes, map, dis);
                    resovleDetail(otherBytes, map, dis);
                    break;
                case 0x09:
                    //可充电储能装置温度数据
                    otherBytes = rechargetDetailResolveDetail(bytes, map, dis);
                    resovleDetail(otherBytes, map, dis);
                    break;
                default:
                    throw new RuntimeException("解析出错");
            }
        }
    }



    /**
     * 驱动电机数据
     *
     * @author hfl on 2019/8/7
     */
    public static byte[] motorResolveDetail(byte[] bytes, Map<String, Object> map, DataInputStream dis) throws IOException {
        //信息类型标志
        dis.skipBytes(1);
        //驱动电机个数
        int n = dis.readUnsignedByte();
        map.put(MOTONUMS, n);
        byte[] listBytes = null;
        if (n > 0) {
            //每个驱动电机数据的字节数为12个
            listBytes = subArray(bytes, 2, 12 * n);
            for (int i = 0; i < n; i++) {
                //单个电机的数据
                //开始解析数据
                //驱动电机序号
                int motoxh = dis.readUnsignedByte();
                map.put(MOTOXH, motoxh);
                //驱动电机状态
                int motosts = dis.readUnsignedByte();
                map.put(MOTOSTS, motosts);
                //驱动电机控制器温度
                int motocontemp = dis.readUnsignedByte();
                map.put(MOTOCON_TEMP, motocontemp - 40);
                //驱动电机转速
                int motorpm = dis.readUnsignedShort();
                map.put(MOTORPM, motorpm - 20000);
                //驱动电机转矩
                int motozj = dis.readUnsignedShort();
                BigDecimal motozjBigDecimal = new BigDecimal(motozj).subtract(new BigDecimal(20000)).divide(new BigDecimal(10), 1, BigDecimal.ROUND_HALF_UP);
                map.put(MOTOZJ, motozjBigDecimal);
                //驱动电机温度
                int mototemp = dis.readUnsignedByte();
                map.put(MOTOTEMP, mototemp - 40);
                //电机控制器输入电压
                int motoconinv = dis.readUnsignedShort();
                BigDecimal motoconinvBigDecimal = new BigDecimal(motoconinv).divide(new BigDecimal(10), 1, BigDecimal.ROUND_HALF_UP);
                map.put(MOTOCONINV, motoconinvBigDecimal);
                //电机控制器直流母线电流
                int motodmc = dis.readUnsignedShort();
                BigDecimal motodmcBigDecimal = new BigDecimal(motodmc).divide(new BigDecimal(10), 1, BigDecimal.ROUND_HALF_UP).subtract(new BigDecimal(1000));
                map.put(MOTODMC, motodmcBigDecimal);
            }

        }
        byte[] otherBytes = null;
        if (listBytes == null) {
            otherBytes = subArray(bytes, 2, bytes.length - 2);
        } else {
            int len = 2 + 12 * n;
            otherBytes = subArray(bytes, len, bytes.length - len);
        }
        return otherBytes;
    }

    /**
     * 可充电储能装置电压数据 解析
     *
     * @author hfl on 2019/8/9
     */
    public static byte[] rechargevDetailResolveDetail(byte[] bytes, Map<String, Object> map, DataInputStream dis) throws IOException {
        //信息类型标志
        dis.skipBytes(1);
        //可充电储能子系统个数
        int n = dis.readUnsignedByte();
        map.put(XDCZXTS, n);
        //统计电压信息列表字节总长度
        int itemCount = 0;
        //可充电储能子系统电压信息列表
        //可充电储能子系统号
        int dydczxt = 0;
        //可充电储能装置电压
        BigDecimal batvBigdecimal = new BigDecimal(0);
        //可充电储能装置电流
        BigDecimal batcBigdecimal = new BigDecimal(0);
        //单体电池总数
        int batsvnums = 0;

        //单体电池电压
        List<BigDecimal> singlevsArr = new LinkedList<>();
        if (n > 0 && n != FE && n != FF) {
            for (int i = 0; i < n; i++) {
                //可充电储能子系统号
                dydczxt = dis.readUnsignedByte();
                //可充电储能装置电压
                int batv = dis.readUnsignedShort();
                batvBigdecimal = new BigDecimal(batv).divide(new BigDecimal(10), 1, BigDecimal.ROUND_HALF_UP);
                //可充电储能装置电流
                int batc = dis.readUnsignedShort();
                batcBigdecimal = new BigDecimal(batc).divide(new BigDecimal(10), 1, BigDecimal.ROUND_HALF_UP).subtract(new BigDecimal(1000));
                //单体电池总数
                batsvnums = dis.readUnsignedShort();
                //本帧起始电池序号
                int bzdcxh = dis.readUnsignedShort();
                //本帧单体电池总数
                int m = dis.readUnsignedByte();
                //单体电池电压
                if (m > 0) {
                    for (int j = 0; j < m; j++) {
                        int item = dis.readUnsignedShort();
                        BigDecimal itemBigdecimal = new BigDecimal(item).divide(new BigDecimal(1000), 3, BigDecimal.ROUND_HALF_UP);
                        singlevsArr.add(itemBigdecimal);
                    }
                }
                itemCount += itemCount + 10 + 2 * m;
            }

        }
        //可充电储能子系统号
        map.put(DYDCZXT, dydczxt);
        //可充电储能装置电压
        map.put(BATV, batvBigdecimal);
        //可充电储能装置电流
        map.put(BATC, batcBigdecimal);
        //单体电池总数
        map.put(BATSVNUMS, batsvnums);
        //单体电池电压
        map.put(SINGLEVS, singlevsArr);
        int len = 2 + itemCount;
        byte[] otherBytes = subArray(bytes, len, bytes.length - len);
        return otherBytes;
    }
    /**
     * 可充电储能装置温度信息 数据解析
     *
     * @author hfl on 2019/8/9
     */
    public static byte[] rechargetDetailResolveDetail(byte[] bytes, Map<String, Object> map, DataInputStream dis) throws IOException {
        //信息类型标志
        dis.skipBytes(1);
        //可充电储能子系统个数
        int n = dis.readUnsignedByte();
        map.put(XDCZXTS, n);
        //统计储能子系统温度信息列表字节总长度
        int itemCount = 0;
        //可充电储能子系统号
        int wddczxt = 0;
        //可充电储能温度探针个数
        int batstnums = 0;
        //可充电储能温度探针检测的温度
        List<Integer> singletsArr = new LinkedList<>();
        //可充电储能子系统温度信息列表
        if (n > 0 && n != FE && n != FF) {
            for (int i = 0; i < n; i++) {
                //可充电储能子系统号
                wddczxt = dis.readUnsignedByte();
                //可充电储能温度探针个数
                batstnums = dis.readUnsignedShort();
                if (batstnums > 0 && batstnums != FE && batstnums != FF) {
                    for (int j = 0; j < batstnums; j++) {
                        int item = dis.readUnsignedByte();
                        //可充电储能温度探针检测的温度
                        singletsArr.add(item - 40);
                    }
                }
                itemCount += itemCount + 3 + 1 * batstnums;
            }
        }
        map.put(WDDCZXT, wddczxt);
        map.put(BATSTNUMS, batstnums);
        map.put(SINGLETS, singletsArr);

        int len = 2 + itemCount;
        byte[] otherBytes = subArray(bytes, len, bytes.length - len);
        return otherBytes;
    }
    /**
     * 车辆位置数据
     *
     * @author hfl on 2019/8/7
     */
    public static byte[] locationResolveDetail(byte[] bytes, Map<String, Object> map, DataInputStream dis) throws IOException {
        //信息类型标志
        dis.skipBytes(1);
        //定位状态
        int dwflag = dis.readUnsignedByte();
        map.put(DWFLAG, dwflag);
        //经度
        byte[] lngBytes = new byte[4];
        dis.read(lngBytes);
        int lng = bytesToInt(lngBytes);
        BigDecimal lngBigdecimal = getDivideBigDecimal(lng, 1000_000,6);
        map.put(LNG, lngBigdecimal);
        //纬度
        byte[] latBytes = new byte[4];
        dis.read(latBytes);
        int lat = bytesToInt(latBytes);
        BigDecimal latBigdecimal = getDivideBigDecimal(lat, 1000_000,6);
        map.put(LAT, latBigdecimal);
        byte[] otherBytes = subArray(bytes, 10, bytes.length - 10);
        return otherBytes;
    }
    /**
     * 整车数据 解析类
     *
     * @author hfl on 2019/8/7
     */
    public static byte[] integralVehicleResovleDetail(byte[] bytes, Map<String, Object> map, DataInputStream dis) throws IOException {
        //信息标志
        dis.skipBytes(1);
        //车辆状态
        int carsts = dis.readUnsignedByte();
        map.put(CARSTS, carsts);
        //充电状态
        int cdzt = dis.readUnsignedByte();
        map.put(CDZT, cdzt);
        //运行模式
        int runmode = dis.readUnsignedByte();
        map.put(RUNMODE, runmode);
        //车速
        int carspeed = dis.readUnsignedShort();
        //如果是FE FF,设置为0
        BigDecimal carspeedBigDecimal = getDivideBigDecimal(carspeed, 10, 1);
        map.put(CAR_SPEED, carspeedBigDecimal);
        //累计里程
        byte[] totalmilesByteArr = new byte[4];
        dis.read(totalmilesByteArr);
        int totalmiles = bytesToInt(totalmilesByteArr);
        //如果是FFFE ,FFFF设置为0
        BigDecimal totalmilesBigDecimal = getDivideBigDecimal(totalmiles, 10, 1);
        map.put(TOTAL_MILES, totalmilesBigDecimal);
        //总电压
        int totalv = dis.readUnsignedShort();
        BigDecimal totalvBigDecimal = getDivideBigDecimal(totalv, 10, 1);
        map.put(TOTALV, totalvBigDecimal);
        //总电流
        int totalc = dis.readUnsignedShort();
        BigDecimal totalcBigDecimal = new BigDecimal(totalc).divide(new BigDecimal(10), 1, BigDecimal.ROUND_HALF_UP).subtract(new BigDecimal(1000));
        map.put(TOTALC, totalcBigDecimal);
        //SOC
        int soc = dis.readUnsignedByte();
        map.put(SOC, soc);
        //DC-DC 状态
        int dcdcsts = dis.readUnsignedByte();
        map.put(DCDCSTS, dcdcsts);
        //档位
        int dw = dis.readUnsignedByte();
        String dwStr = Integer.toBinaryString(dw);
        dwStr = coverFullPos(dwStr, 8);
        //倒数第5位 正数第3位是驱动力 			qdzt
        map.put(QDZT, dwStr.substring(2, 3));
        //有无制动力
        map.put(ZDZT, dwStr.substring(3, 4));
        //档位
        String substring = dwStr.substring(4);
        map.put(DW, Integer.valueOf(substring, 2));
        //绝艳电阻
        int jydz = dis.readUnsignedShort();
        map.put(JYDZ, jydz);
        //加速踏板开度
        int jstbkd = dis.readUnsignedByte();
        map.put(JSTBKD, jstbkd);
        //制动踏板开度
        int zdtbkd = dis.readUnsignedByte();
        map.put(ZDTBKD, zdtbkd);
        byte[] otherBytes = subArray(bytes, 21, bytes.length - 21);
        return otherBytes;
    }

    /**
     * 燃料电池 数据解析 工具类
     *
     * @author hfl on 2019/8/8
     */
    public static byte[]  fuelBatteryResolveDetail(byte[] bytes, Map<String, Object> map, DataInputStream dis) throws IOException {
        //信息类型标志
        dis.skipBytes(1);
        //燃料电池电压
        int rlbatv = dis.readUnsignedShort();
        BigDecimal rlbatvBigDecimal = getDivideBigDecimal(rlbatv, 10, 1);
        map.put(RLBATV, rlbatvBigDecimal);
        //燃料电池电流
        int rlbatc = dis.readUnsignedShort();
        BigDecimal rlbatcBigDecimal = getDivideBigDecimal(rlbatc, 10, 1);
        map.put(RLBATC, rlbatcBigDecimal);
        //燃料消耗率
        int rlxhl = dis.readUnsignedShort();
        BigDecimal rlxhlBigDecimal = getDivideBigDecimal(rlxhl, 100, 2);
        map.put(RLXHL, rlxhlBigDecimal);
        //燃料电池温度探针总数
        int n = dis.readUnsignedShort();
        map.put(RLDCWDTZZS, n);
        if (n > 0) {
            List<Integer> tzwdzArr = new LinkedList<>();
            //探针温度值
            for (int i = 0; i < n; i++) {
                int item = dis.readUnsignedByte();
                tzwdzArr.add(item - 40);
            }
            //探针温度值
            map.put(TZWDZ, tzwdzArr);
        }
        //氢系统中最高温度
        int qxtmaxtemp = dis.readUnsignedShort();
        BigDecimal qxtmaxtempBigDecimal = getDivideBigDecimal(qxtmaxtemp, 10, 1).subtract(new BigDecimal(40));
        map.put(QXTMAX_TEMP, qxtmaxtempBigDecimal);
        //氢系统中最高温度探针代号
        int qxtmaxtempnum = dis.readUnsignedByte();
        map.put(QXTMAX_TEMP_NUM, qxtmaxtempnum);
        //氢气最高浓度
        int qqzgnd = dis.readUnsignedShort();
        map.put(QQZGND, qqzgnd);
        //氢气最高浓度传感器代号
        int qqzgndcgqdh = dis.readUnsignedByte();
        map.put(QQZGNDCGQDH, qqzgndcgqdh);
        // 氢气最高压力
        int qqzgyl = dis.readUnsignedShort();
        BigDecimal qqzgylBigDecimal = getDivideBigDecimal(qqzgyl, 10, 1);
        map.put(QQZGYL, qqzgylBigDecimal);
        //氢气最高压力传感器代号
        int qqzgylcgqdh = dis.readUnsignedByte();
        map.put(QQZGYLCGQDH, qqzgylcgqdh);
        //高压DCDC状态
        int gydcdczt = dis.readUnsignedByte();
        map.put(GYDCDCZT, gydcdczt);
        int len = 18 + n;
        byte[] otherBytes = subArray(bytes, len, bytes.length - len);
        return otherBytes;
    }

    /**
     * 极值数据 解析
     */
    public static byte[] extremeResolveDetail(byte[] bytes, Map<String, Object> map, DataInputStream dis) throws IOException {
        //信息类型标志
        dis.skipBytes(1);
        //最高电压电池子系统号(1)
        int maxvbnum = dis.readUnsignedByte();
        map.put(MAXVB_NUM, maxvbnum);
        //最高电压电池单体代号
        int maxvsnum = dis.readUnsignedByte();
        map.put(MAXVS_NUM, maxvsnum);
        //电池单体电压最高值(2)
        int maxv = dis.readUnsignedShort();
        //最小单位为0.001v
        BigDecimal maxvBigDecimal = new BigDecimal(maxv).divide(new BigDecimal(1000), 3, BigDecimal.ROUND_HALF_UP);
        map.put(MAXV, maxvBigDecimal);
        //最低电压电池子系统号
        int minvbnum = dis.readUnsignedByte();
        map.put(MINVB_NUM, minvbnum);
        //最低电压电池单体代号
        int minvsnum = dis.readUnsignedByte();
        map.put(MINVSNUM, minvsnum);
        //电池单体电压最低值
        int minv = dis.readUnsignedShort();
        BigDecimal minvBigDecimal = new BigDecimal(minv).divide(new BigDecimal(1000), 3, BigDecimal.ROUND_HALF_UP);
        map.put(MINV, minvBigDecimal);
        //最高温度子系统号
        int maxtempzch = dis.readUnsignedByte();
        map.put(MAXTEMPZCH, maxtempzch);
        // 最高温度探针序号
        int maxtemptzh = dis.readUnsignedByte();
        map.put(MAXTEMPTZH, maxtemptzh);
        //最高温度值
        int maxtemp = dis.readUnsignedByte();
        map.put(MAXTEMP, maxtemp - 40);
        //最低温度子系统号
        int mintempzch = dis.readUnsignedByte();
        map.put(MINTEMPZCH, mintempzch);
        //最低温度探针序号
        int mintemptzh = dis.readUnsignedByte();
        map.put(MINTEMPTZH, mintemptzh);
        //最低温度值
        int mintemp = dis.readUnsignedByte();
        map.put(MINTEMP, mintemp - 40);
        byte[] otherBytes = subArray(bytes, 15, bytes.length - 15);
        return otherBytes;
    }

    /**
     * 发动机数据解析
     * @param bytes
     * @param map
     * @param dis
     * @return
     * @throws IOException
     */
    public static byte[] engineResovleDetail(byte[] bytes, Map<String, Object> map, DataInputStream dis) throws IOException {
        //信息类型标志
        dis.skipBytes(1);
        //发动机状态
        int fdjzt = dis.readUnsignedByte();
        map.put(FDJZT, fdjzt);
        //曲轴转速
        int qzzs = dis.readUnsignedShort();
        map.put(QZZS, qzzs);
        //燃料消耗率
        int fdjrlxhl = dis.readUnsignedShort();
        BigDecimal fdjrlxhlBigDecimal = new BigDecimal(fdjrlxhl).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
        map.put(FDJRLXHL, fdjrlxhlBigDecimal);
        byte[] otherBytes = subArray(bytes, 6, bytes.length - 6);
        return otherBytes;
    }
    /**
     * 报警数据解析
     * @param bytes
     * @param map
     * @param dis
     * @return
     * @throws IOException
     */
    public static byte[] alarmResolveDetail(byte[] bytes, Map<String, Object> map, DataInputStream dis) throws IOException {
        //信息类型标志
        dis.skipBytes(1);
        //最高报警等级
        int maxwlvl = dis.readUnsignedByte();
        map.put(MAXWLVL, maxwlvl);
        //通用报警标志
        byte[] commonFlagBytes = new byte[4];
        dis.read(commonFlagBytes);
        int commonFlag = byte2Int(commonFlagBytes);
        String commonBits = coverFullPos(String.valueOf(commonFlag), 32);
        //反过来顺序
        StringBuffer sb = new StringBuffer().append(commonBits).reverse();
        commonBits = sb.toString();
        // 温度差异报警
        String wdcybj = commonBits.substring(0, 1);
        map.put(WDCYBJ, Integer.valueOf(wdcybj));
        //电池高温报警
        String dcgwbj = commonBits.substring(1, 2);
        map.put(DCGWBJ, Integer.valueOf(dcgwbj));
        //车载储能装置类型过压报警
        String dlxdcbgybj = commonBits.substring(2, 3);
        map.put(DLXDCBGYBJ, Integer.valueOf(dlxdcbgybj));
        //车载储能装置类型欠压报警
        String dlxdcbqybj = commonBits.substring(3, 4);
        map.put(DLXDCBQYBJ, Integer.valueOf(dlxdcbqybj));
        //SOC低报警
        String socdbj = commonBits.substring(4, 5);
        map.put(SOCDBJ, Integer.valueOf(socdbj));
        //单体电池过压报警
        String dtxdcgybj = commonBits.substring(5, 6);
        map.put(DTXDCGYBJ, Integer.valueOf(dtxdcgybj));
        //单体电池欠压报警
        String dtxdcqybj = commonBits.substring(6, 7);
        map.put(DTXDCQYBJ, Integer.valueOf(dtxdcqybj));
        //SOC过高报警
        String socggbj = commonBits.substring(7, 8);
        map.put(SOCGGBJ, Integer.valueOf(socggbj));
        //SOC跳变报警
        String soctbbj = commonBits.substring(8, 9);
        map.put(SOCTBBJ, Integer.valueOf(soctbbj));
        //可充电储能系统不匹配报警
        String dlxdcbbppbj = commonBits.substring(9, 10);
        map.put(DLXDCBBPPBJ, Integer.valueOf(dlxdcbbppbj));
        //电池单体一致性差报警
        String dlxdcdtyzxcbj = commonBits.substring(10, 11);
        map.put(DLXDCDTYZXCBJ, Integer.valueOf(dlxdcdtyzxcbj));
        //绝缘报警
        String jybj = commonBits.substring(11, 12);
        map.put(JYBJ, Integer.valueOf(jybj));
        //DC - DC温度报警
        String dcdcwdbj = commonBits.substring(12, 13);
        map.put(DCDCWDBJ, Integer.valueOf(dcdcwdbj));
        //制动系统报警
        String zdxtbj = commonBits.substring(13, 14);
        map.put(ZDXTBJ, Integer.valueOf(zdxtbj));
        //DC-DC状态报警
        String dcdcztbj = commonBits.substring(14, 15);
        map.put(DCDCZTBJ, Integer.valueOf(dcdcztbj));
        //驱动电机控制器温度报警
        String qddjkzqwdbj = commonBits.substring(15, 16);
        map.put(QDDJKZQWDBJ, Integer.valueOf(qddjkzqwdbj));
        //高压互锁状态报警
        String gyhsztbj = commonBits.substring(16, 17);
        map.put(GYHSZTBJ, Integer.valueOf(gyhsztbj));
        //驱动电机温度报警
        String qddjwdbj = commonBits.substring(17, 18);
        map.put(QDDJWDBJ, Integer.valueOf(qddjwdbj));
        //车载储能装置类型过充
        String dlxdcgc = commonBits.substring(18, 19);
        map.put(DLXDCGC, Integer.valueOf(dlxdcgc));

        //可充电储能装置故障总数
        int n1 = dis.readUnsignedByte();
        map.put(DCGZZS, n1);
        //可充电储能装置故障码列表
        gzm(map, dis, n1, DCGZM);
        //驱动电机故障总数
        int n2 = dis.readUnsignedByte();
        map.put(QDDJGZZS, n2);
        gzm(map, dis, n2, QDDJGZM);
        //发动机故障总数
        int n3 = dis.readUnsignedByte();
        map.put(FDJGZZS, n3);
        gzm(map, dis, n3, FDJGZM);
        //其他故障总数
        int n4 = dis.readUnsignedByte();
        map.put(QTGZZS, n4);
        gzm(map, dis, n4, QTGZM);
        int len = 10 + 4 * n1 + 4 * n2 + 4 * n3 + 4 * n4;
        byte[] otherBytes = subArray(bytes, len, bytes.length - len);
        return otherBytes;
    }

    private static void gzm(Map<String, Object> map, DataInputStream dis, int n1, String key) throws IOException {
        if (n1 > 0 && n1 != FE && n1 != FF) {
            List<Integer> dcgzm = new ArrayList<>();
            for (int i = 0; i < n1; i++) {
                //每个故障的数据字节数组
                byte[] itemBytes = new byte[4];
                dis.read(itemBytes);
                int item = byte2Int(itemBytes);
                dcgzm.add(item);
            }
            //可充电储能装置故障码列表
            map.put(key, dcgzm);
        }
    }
    /**
     * bytes 转16进制字符串
     *
     * @param bArray
     * @return
     */
    public static String bytesToHexString(byte[] bArray) {
        StringBuffer sb = new StringBuffer(bArray.length);
        String sTemp;
        for (int i = 0; i < bArray.length; i++) {
            sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2) {
                sb.append(0);
            }
            sb.append(sTemp);
        }
        return sb.toString().toUpperCase();
    }


    /**
     * 16进制字符串转bytes
     *
     * @param hex
     * @return
     */
    public static byte[] hexStringToByte(String hex) {
        int len = 0;
        int num = 0;
        //判断字符串的长度是否是两位
        if (hex.length() >= 2) {
            //判断字符喜欢是否是偶数
            len = (hex.length() / 2);
            num = (hex.length() % 2);
            if (num == 1) {
                hex = "0" + hex;
                len = len + 1;
            }
        } else {
            hex = "0" + hex;
            len = 1;
        }
        byte[] result = new byte[len];
        char[] achar = hex.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
        }
        return result;
    }

    private static int toByte(char c) {
        if (c >= 'a') {
            return (c - 'a' + 10) & 0x0f;
        }
        if (c >= 'A') {
            return (c - 'A' + 10) & 0x0f;
        }
        return (c - '0') & 0x0f;
    }

    /**
     * 字节数组拆分
     *
     * @param paramArrayOfByte 原始数组
     * @param paramInt1        起始下标
     * @param paramInt2        要截取的长度
     * @return 处理后的数组
     */
    public static byte[] subArray(byte[] paramArrayOfByte, int paramInt1, int paramInt2) {
        byte[] arrayOfByte = new byte[paramInt2];
        int i = 0;
        while (true) {
            if (i >= paramInt2) {
                return arrayOfByte;
            }
            arrayOfByte[i] = paramArrayOfByte[(i + paramInt1)];
            i += 1;
        }
    }

    /**
     * byte数组转int类型的对象
     *
     * @param bytes
     * @return
     */
    public static int byte2Int(byte[] bytes) {
        return (bytes[0] & 0xff) << 24
                | (bytes[1] & 0xff) << 16
                | (bytes[2] & 0xff) << 8
                | (bytes[3] & 0xff);
    }
    /**
     * bytes 数组转成int
     */
    public static Integer bytesToInt(byte[] bArray) {
        String s = bytesToHexString(bArray);
        return Integer.valueOf(s, 16);
    }


    /**
     * 偏移缩放数据处理
     * @param divisor
     * @param scale
     * @param roundingMode
     * @return
     */
    public static BigDecimal getDivideBigDecimal(int divisor, int scale, int roundingMode) {
        return new BigDecimal(divisor).divide(new BigDecimal(scale), roundingMode, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 解析采集时间
     *
     * @param map
     * @param dis
     * @throws IOException
     */
    public static void setGanterTime(Map<String, Object> map, DataInputStream dis) throws IOException {
        int year = dis.readUnsignedByte();
        int month = dis.readUnsignedByte();
        int day = dis.readUnsignedByte();
        int hour = dis.readUnsignedByte();
        int min = dis.readUnsignedByte();
        int second = dis.readUnsignedByte();
        StringBuilder builder = new StringBuilder();
        builder.append("20" + year)
                .append("-")
                .append(formateTime(month))
                .append("-")
                .append(formateTime(day))
                .append(" ")
                .append(formateTime(hour))
                .append(":")
                .append(formateTime(min))
                .append(":")
                .append(formateTime(second));
        map.put("gathertime", builder.toString());
    }

        /**
     * 封装时间格式 不到10 前面加 0 补齐
     *
     * @param n
     */
    public static String formateTime(int n) {
        if (n < 10) {
            return "0" + n;
        }
        return n + "";
    }

    /**
     * 如果字符串位数不够，则前面补0，如果位数大于40，则扔出异常
     *
     * @param hex
     * @param num 需要的长度
     * @return
     */
    public static String coverFullPos(String hex, int num) {
        int length = hex.length();
        int otherLen = 0;
        StringBuffer sb = new StringBuffer();
        if (length < num) {
            otherLen = num - length;
            for (int i = 0; i < otherLen; i++) {
                sb.append("0");
            }
        }
        if (length > num) {
            throw new RuntimeException("数据异常，字符串超过长度限制");
        }
        return sb.toString() + hex;
    }
}
