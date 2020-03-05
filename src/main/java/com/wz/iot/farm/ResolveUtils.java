package com.wz.iot.farm;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static java.math.BigDecimal.ROUND_HALF_UP;

public class ResolveUtils {

    private static final Logger logger = LogManager.getLogger(ResolveUtils.class);

    //车牌号
    public final static String VIN = "vin";
    //采集时间
    public final static String GATHER_TIME = "gathertime";
    /******************** 终端定位信息  **********************************************/
    //定位状态
    public final static String GNNS_DWFLAG = "gunsdwflag";
    //经度
    public final static String GNNS_LNG = "gunslng";
    //纬度
    public final static String GNNS_LAT = "gunslat";
    //GNSS速度
    public final static String GNNS_SPEED = "gunsspeed";
    //水平精度因子
    public final static String GNNS_HPF = "gunshpf";
    //拔海高度
    public final static String GNNS_ALT = "gunsaltitude";
    //卫星数
    public final static String GNNS_SATE_COUNT = "gunssatecount";
    //地面航向
    public final static String GNNS_COURSE = "gunscourse";
    /******************** 农机工况  **********************************************/
    //发动机转速
    public final static String SITUATION_ENGINE_SPEED = "situationenginespeed";
    //机油压力
    public final static String SITUATION_H_P = "situationhydraulicspressure";
    //发动机工作时间
    public final static String SITUATION_TIME = "situationtime";
    //燃油消耗总量
    public final static String SITUATION_FUEL_TOTAL = "situationfueltotal";
    //每小时油耗
    public final static String SITUATION_FUEL_PER_HOUR = "situationfuelhour";
    //发动机实际扭矩百分比
    public final static String SITUATION_ENGINE_TORQUE_PERCENT = "situationenginetorquepercent";
    //行驶总里程
    public final static String SITUATION_TOTAL_MILEAGE = "situationtotalmileage";
    //额外的
    //工况采集时间
    public final static String GATHER_TIME_SITUATION = "gathertimesituation";

    /******************** 农机作业数据  **********************************************/
    //作业面积
    public final static String WORKING_AREA = "workingarea";
    //作业开始时间
    public final static String WORKING_START_TIME = "workingstarttime";
    //作业结束时间
    public final static String WORKING_END_TIME = "workingendtime";
    //额外的
    //作业采集时间
    public final static String GATHER_TIME_WORKING = "gathertimeworking";

    /******************** 车辆登入、登出  **********************************************/
    //流水号
    public final static String SERIAL_NUMBER = "serialnumber";
    //iccid，登出的不需要
    public final static String ICCID = "iccid";





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

    public static Map<String, Object> resolve(byte[] bytes, int infoFlag) throws IOException {
        Map<String, Object> map = new HashMap<>();
        //转成16进制字符串
        String hexStr = bytesToHexString(bytes);
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
             DataInputStream dis = new DataInputStream(byteArrayInputStream)) {
            //跳过前4个字节
            dis.skipBytes(4);
            //车辆识别代号字符串
            byte[] vinArr = new byte[17];
            dis.read(vinArr);
            map.put(VIN, "'"+new String(vinArr)+"'");
            //跳过加密方式1个字节
            dis.skipBytes(1);
            //数据单元长度
            int dataLen = dis.readUnsignedShort();
            //数据单元16进制
            String hexData = hexStr.substring(48, 48 + dataLen * 2);
            byte[] dataBytes = hexStringToByte(hexData);
            //解析实时数据
            //数据采集时间
            setGanterTime(map, dis, GATHER_TIME);
            int n =6;
            //流水号
            if(infoFlag == 0x02 || infoFlag == 0x03){
                int serialNum = dis.readUnsignedShort();
                map.put(SERIAL_NUMBER, "'"+serialNum+"'");
                n = 6 + 2;
            }
            //解析实时数据
            byte[] otherBytes = subArray(dataBytes, n, dataBytes.length - n);
            switch (infoFlag) {
                case 0x01:
                    vehicleLoginResolveDetail(otherBytes, map, dis);
                    break;
                case 0x02:
                case 0x03:
                    realtimeResolveDetail(otherBytes, map, dis);
                    break;
                case 0x04:
                    vehicleLogOutResolveDetail(otherBytes, map, dis);
                    break;
            }
        }
        return map;
    }

    /**
     * 车辆登入数据解析
     */
    private static void vehicleLogOutResolveDetail(byte[] otherBytes, Map<String, Object> map, DataInputStream dis) throws IOException {
        //车辆流水号
        int serial_number = dis.readUnsignedShort();
        map.put(SERIAL_NUMBER, "'"+serial_number+ "'");

    }


    /**
     * 车辆登入数据解析
     */
    private static void vehicleLoginResolveDetail(byte[] bytes, Map<String, Object> map, DataInputStream dis) throws IOException {
        if (bytes.length > 0) {
            //车辆流水号
            int serial_number = dis.readUnsignedShort();
            map.put(SERIAL_NUMBER, "'"+ serial_number+"'");
            //iccid
            byte[] iccidArr = new byte[20];
            dis.read(iccidArr);
            map.put(ICCID,  "'"+new String(iccidArr)+"'");
        }

    }

    /**
     * 解析工况信息
     *
     * @param bytes
     * @param map
     * @param dis
     * @return
     * @throws IOException
     */
    private static byte[] situationResolve(byte[] bytes, Map<String, Object> map, DataInputStream dis) throws IOException {
        //信息类型标志
        dis.skipBytes(1);
        //发动机转速
        int engine_speed = dis.readUnsignedShort();
        BigDecimal speedBigdecimal = new BigDecimal(engine_speed).multiply(new BigDecimal(0.125)).setScale(3, BigDecimal.ROUND_HALF_UP);
        map.put(SITUATION_ENGINE_SPEED, "'"+speedBigdecimal+"'");
        //机油压力
        int hydraulics_pressure = dis.readUnsignedByte();
        BigDecimal hpBigdecimal = new BigDecimal(hydraulics_pressure).multiply(new BigDecimal(0.5)).setScale(1, BigDecimal.ROUND_HALF_UP);
        map.put(SITUATION_H_P, "'"+hpBigdecimal+"'");
        //发动机工作时间
        byte[] situation_time_Bytes = new byte[4];
        dis.read(situation_time_Bytes);
        int time = bytesToInt(situation_time_Bytes);
        //精度:0.05h
        BigDecimal situation_time = new BigDecimal(time).multiply(new BigDecimal(0.05)).setScale(2, BigDecimal.ROUND_HALF_UP);
        map.put(SITUATION_TIME, "'"+situation_time+"'");
        //燃油消耗总量
        byte[] total_fuel_Bytes = new byte[4];
        dis.read(total_fuel_Bytes);
        int total_fuel = bytesToInt(total_fuel_Bytes);
        //精度0.05h
        BigDecimal total_fuelBigDecimal = new BigDecimal(total_fuel).multiply(new BigDecimal(0.5)).setScale(1, BigDecimal.ROUND_HALF_UP);
        map.put(SITUATION_FUEL_TOTAL, "'"+total_fuelBigDecimal+"'");
        //每小时油耗
        int fuel_per_hour = dis.readUnsignedShort();
        //精度0.05L
        BigDecimal fuel_per_hourBigDecimal = new BigDecimal(fuel_per_hour).multiply(new BigDecimal(0.05)).setScale(2, BigDecimal.ROUND_HALF_UP);
        map.put(SITUATION_FUEL_PER_HOUR, "'"+fuel_per_hourBigDecimal+"'");
        //发动机实际扭矩百分比
        int torque_percentage = dis.readUnsignedByte();
        //偏移量-125
        torque_percentage = torque_percentage - 125;
        map.put(SITUATION_ENGINE_TORQUE_PERCENT, "'"+torque_percentage+"'");
        //行驶总里程
        byte[] total_mileageBytes = new byte[4];
        dis.read(total_mileageBytes);
        int total_mileage = bytesToInt(total_mileageBytes);
        //精度0.1km
        BigDecimal total_mileageBigDecimal = new BigDecimal(total_mileage).multiply(new BigDecimal(0.1)).setScale(1, BigDecimal.ROUND_HALF_UP);
        map.put(SITUATION_TOTAL_MILEAGE, "'"+total_mileageBigDecimal+"'");
        //工况采集时间
        map.put(GATHER_TIME_SITUATION, "'"+(String) map.get(GATHER_TIME)+"'");
        //已经读取了19个字节的数据，获取剩余的字节数据，让后面的程序继续解析
        byte[] otherBytes = subArray(bytes, 19, bytes.length - 19);
        return otherBytes;
    }


    private static void realtimeResolveDetail(byte[] bytes, Map<String, Object> map, DataInputStream dis) throws IOException {
        if (bytes.length > 0) {
            //信息类型标志
            int infoDataType = bytes[0];
            byte[] otherBytes = null;
            switch (infoDataType) {
                case 0x01:
                    //终端定位信息体
                    otherBytes = locationResove(bytes, map, dis);
                    //获取剩余的数据
                    realtimeResolveDetail(otherBytes, map, dis);
                    break;
                case 0x02:
                    //农机工况信息体
                    otherBytes = situationResolve(bytes, map, dis);
                    realtimeResolveDetail(otherBytes, map, dis);
                    break;
                case 0x03:
                    //农机作业信息体
                    otherBytes = WorkingResolve(bytes, map, dis);
                    realtimeResolveDetail(otherBytes, map, dis);
                    break;
                default:
                    throw new RuntimeException("解析错误");
            }
        }



    }

    private static byte[] WorkingResolve(byte[] bytes, Map<String, Object> map, DataInputStream dis) throws IOException {
        //信息类型标志
        dis.skipBytes(1);
        //作业面积
        byte[] working_area_Bytes = new byte[4];
        dis.read(working_area_Bytes);
        int working_area = bytesToInt(working_area_Bytes);
        BigDecimal bigDecimal = new BigDecimal(working_area).multiply(new BigDecimal(0.1)).setScale(1, ROUND_HALF_UP);
        map.put(WORKING_AREA, "'"+bigDecimal+"'");
        //作业开始时间
        setGanterTime(map, dis, WORKING_START_TIME);
        //作业结束时间
        setGanterTime(map, dis, WORKING_END_TIME);
        //工况采集时间
        map.put(GATHER_TIME_WORKING, "'"+(String) map.get(GATHER_TIME)+"'");
        //已经读取了16个字节的数据，获取剩余的字节数据，让后面的程序继续解析
        byte[] otherBytes = subArray(bytes, 17, bytes.length - 17);
        return otherBytes;
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
    private static byte[] subArray(byte[] paramArrayOfByte, int paramInt1, int paramInt2) {
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
     * 解析采集时间
     *
     * @param map
     * @param dis
     * @throws IOException
     */
    private static void setGanterTime(Map<String, Object> map, DataInputStream dis, String key) throws IOException {
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
        map.put(key, "'"+ builder.toString()+"'");
    }

    /**
     * 封装时间格式 不到10 前面加 0 补齐
     *
     * @param n
     */
    private static String formateTime(int n) {
        if (n < 10) {
            return "0" + n;
        }
        return n + "";
    }

    /**
     * bytes 数组转成int
     */
    private static Integer bytesToInt(byte[] bArray) {
        String s = bytesToHexString(bArray);
        return Integer.valueOf(s, 16);
    }

    /**
     * 偏移缩放数据处理
     *
     * @param divisor      原始数值
     * @param scale        缩小倍数
     * @param roundingMode 四舍五入取4位小数
     * @return
     */
    private static BigDecimal getDivideBigDecimal(int divisor, int scale, int roundingMode) {
        return new BigDecimal(divisor).divide(new BigDecimal(scale), roundingMode, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 解析定位
     *
     * @param bytes
     * @param map
     * @param dis
     * @return
     * @throws IOException
     */
    private static byte[] locationResove(byte[] bytes, Map<String, Object> map, DataInputStream dis) throws IOException {
        //信息类型标志
        dis.skipBytes(1);
        //定位状态
        int dwflag = dis.readUnsignedByte();
        map.put(GNNS_DWFLAG, "'"+dwflag+ "'");
        //经度
        byte[] lngBytes = new byte[4];
        dis.read(lngBytes);
        int lng = bytesToInt(lngBytes);
        //缩小10的6次方倍
        BigDecimal lngBigdecimal = getDivideBigDecimal(lng, 1000_000, 6);
        map.put(GNNS_LNG, "'"+lngBigdecimal+ "'");
        //纬度
        byte[] latBytes = new byte[4];
        dis.read(latBytes);
        int lat = bytesToInt(latBytes);
        //缩小10的6次方倍
        BigDecimal latBigdecimal = getDivideBigDecimal(lat, 1000_000, 6);
        map.put(GNNS_LAT,  "'"+latBigdecimal +"'");
        //GNSS速度
        int speed = dis.readUnsignedShort();
        //精度0.1km，所以获取的值应缩小10倍
        BigDecimal speedBigdecimal = getDivideBigDecimal(speed, 10, 1);
        map.put(GNNS_SPEED, "'"+speedBigdecimal+"'");
        //水平精度因子
        int gnns_hpf = dis.readUnsignedShort();
        //精度0.1km，所以获取的值应缩小10倍
        BigDecimal hpfBigdecimal = getDivideBigDecimal(gnns_hpf, 10, 1);
        map.put(GNNS_HPF, "'"+hpfBigdecimal+"'");
        //拔海高度
        byte[] altBytes = new byte[4];
        dis.read(altBytes);
        int alt = bytesToInt(altBytes);
        //精度0.1km，所以获取的值应缩小10倍
        BigDecimal altBigdecimal = getDivideBigDecimal(alt, 10, 1);
        map.put(GNNS_ALT, "'"+altBigdecimal+"'");
        //卫星数
        int satecount = dis.readUnsignedByte();
        map.put(GNNS_SATE_COUNT, "'"+satecount+"'");
        //地面航向
        int course = dis.readUnsignedShort();
        map.put(GNNS_COURSE, "'"+course+"'");
        //已经读取了20个字节的数据，获取剩余的字节数据，让后面的程序继续解析
        byte[] otherBytes = subArray(bytes, 21, bytes.length - 21);
        return otherBytes;
    }

}

