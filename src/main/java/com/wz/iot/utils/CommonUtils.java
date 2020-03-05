package com.wz.iot.utils;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;

public class CommonUtils {


    /**
     * long类型转成byte数组
     *
     * @param number
     * @return
     */
    public static byte[] longToBytes(long number) {
        long temp = number;
        byte[] b = new byte[8];
        for (int i = 0; i < b.length; i++) {
            //将最低位保存在最低位
            b[i] = new Long(temp & 0xff).byteValue();
            // 向右移8位
            temp = temp >> 8;
        }
        return b;
    }


    /**
     * byte数组转成long
     *
     * @param b
     * @return
     */
    public static long byteToLong(byte[] b) {
        long s = 0;
        // 最低位
        long s0 = b[0] & 0xff;
        long s1 = b[1] & 0xff;
        long s2 = b[2] & 0xff;
        long s3 = b[3] & 0xff;
        // 最低位
        long s4 = b[4] & 0xff;
        long s5 = b[5] & 0xff;
        long s6 = b[6] & 0xff;
        long s7 = b[7] & 0xff;

        // s0不变
        s1 <<= 8;
        s2 <<= 16;
        s3 <<= 24;
        s4 <<= 8 * 4;
        s5 <<= 8 * 5;
        s6 <<= 8 * 6;
        s7 <<= 8 * 7;
        s = s0 | s1 | s2 | s3 | s4 | s5 | s6 | s7;
        return s;
    }

    /**
     * 注释：int到字节数组的转换！
     *
     * @param number
     * @return
     */
    public static byte[] intToBytes(int number) {
        int temp = number;
        byte[] b = new byte[4];
        for (int i = 0; i < b.length; i++) {
            //将最低位保存在最低位
            b[i] = new Integer(temp & 0xff).byteValue();
            // 向右移8位
            temp = temp >> 8;
        }
        return b;
    }


    /**
     * 注释：short到字节数组的转换！
     *
     * @param number
     * @return
     */
    public static byte[] shortToBytes(short number) {
        int temp = number;
        byte[] b = new byte[2];
        for (int i = 0; i < b.length; i++) {
            b[i] = new Integer(temp & 0xff).byteValue();//
            //将最低位保存在最低位
            temp = temp >> 8;// 向右移8位
        }
        return b;
    }

    /**
     * 注释：字节数组到short的转换！
     *
     * @param b
     * @return
     */
    public static short byteToShort(byte[] b) {
        short s = 0;
        short s0 = (short) (b[0] & 0xff);// 最低位
        short s1 = (short) (b[1] & 0xff);
        s1 <<= 8;
        s = (short) (s0 | s1);
        return s;
    }

    /**
     * byte数组转int类型的对象
     *
     * @param bytes
     * @return
     */
    public int byte2Int(Byte[] bytes) {
        return (bytes[0] & 0xff) << 24
                | (bytes[1] & 0xff) << 16
                | (bytes[2] & 0xff) << 8
                | (bytes[3] & 0xff);
    }

    /**
     * byte数组转int类型的对象
     *
     * @param bytes
     * @return
     */
    public int byte2Short(Byte[] bytes) {
        return (bytes[0] & 0xff) << 8
                | (bytes[1] & 0xff);

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
     * 将一个单字节的Byte转换成十六进制的数
     *
     * @param b byte
     * @return convert result
     */
    public static String byteToHex(byte b) {
        int i = b & 0xFF;
        return Integer.toHexString(i);
    }

    /**
     * 将16位的short转换成byte数组
     *
     * @param s short
     * @return byte[] 长度为2
     */
    public static byte[] shortToByteArray(short s) {
        byte[] targets = new byte[2];
        for (int i = 0; i < 2; i++) {
            int offset = (targets.length - 1 - i) * 8;
            targets[i] = (byte) ((s >>> offset) & 0xff);
        }
        return targets;
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
     * bytes 数组转成int
     */
    public static Integer bytesToInt(byte[] bArray) {
        String s = bytesToHexString(bArray);
        return Integer.valueOf(s, 16);
    }


    /**
     * 十进制int转16进制字符串
     *
     * @param num
     * @return
     */
    public static String intToHexString(int num) {
        String hexString = Integer.toHexString(num);
        return hexString;
    }

    /**
     * 字节数组拆分
     *
     * @param bytes     字节数组
     * @param paramInt1 起始下标
     * @param paramInt2 要截取的长度
     * @return 处理后的数组
     */
    public static String getStringByByte(byte[] bytes, int paramInt1, int paramInt2) {
        byte[] starteSymboBytes = subArray(bytes, paramInt1, paramInt2);
        return new String(starteSymboBytes).trim();
    }

    /**
     * ASCII转换为16进制
     *
     * @param str
     * @return
     */
    public static String convertStringToHex(String str) {

        char[] chars = str.toCharArray();

        StringBuffer hex = new StringBuffer();
        for (int i = 0; i < chars.length; i++) {
            hex.append(Integer.toHexString((int) chars[i]));
        }

        return hex.toString();
    }

    /**
     * 16进制转换为ASCII
     *
     * @param hex
     * @return
     */
    public static String convertHexToString(String hex) {

        StringBuilder sb = new StringBuilder();
        StringBuilder temp = new StringBuilder();

        //49204c6f7665204a617661 split into two characters 49, 20, 4c...
        for (int i = 0; i < hex.length() - 1; i += 2) {

            //grab the hex in pairs
            String output = hex.substring(i, (i + 2));
            //convert hex to decimal
            int decimal = Integer.parseInt(output, 16);
            //convert the decimal to character
            sb.append((char) decimal);

            temp.append(decimal);
        }

        return sb.toString();
    }

    /**
     * 获取BCC校验码
     *
     * @param data
     * @param start 开始位置0
     * @param end   字节数组长度
     * @return
     */
    public static String getBCC(byte[] data, int start, int end) {
        String ret = "";
        byte BCC[] = new byte[1];
        for (int i = start; i < data.length; i++) {
            if (i == end) {
                break;
            }
            BCC[0] = (byte) (BCC[0] ^ data[i]);
        }
        String hex = Integer.toHexString(BCC[0] & 0xFF);
        if (hex.length() == 1) {
            hex = '0' + hex;
        }
        ret += hex.toUpperCase();
        return ret;
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

    /**
     * 4位字节数组转换为整型
     *
     * @param b
     * @return
     */
    public static int byte2Int(byte[] b) {
        int intValue = 0;
        for (int i = 0; i < b.length; i++) {
            intValue += (b[i] & 0xFF) << (8 * (3 - i));
        }
        return intValue;
    }
//    public static int byte2Int(byte[] bytes) {
//        int value = 0;
//        // 由高位到低位
//        for (int i = 0; i < 4; i++) {
//            int shift = (4 - 1.json - i) * 8;
//            value += (bytes[i] & 0x000000FF) << shift;// 往高位游
//        }
//        return value;
//    }

    /**
     * 将byte[]转为各种进制的字符串
     *
     * @param bytes byte[]
     * @param radix 基数可以转换进制的范围，从Character.MIN_RADIX到Character.MAX_RADIX，超出范围后变为10进制
     * @return 转换后的字符串
     */
    public static String binary(byte[] bytes, int radix) {
        return new BigInteger(1, bytes).toString(radix);// 这里的1代表正数
    }


    //16进制数转二进制
    public static String toHex(int num) {
        //& 两两为1即为1
        //>>>无符号右移
        /**
         * eg.60
         *       0000-0000 0000-0000 0000-0000 0011-1100   60的二进制表示
         * &     0000-0000 0000-0000 0000-0000 0000-1111   15的二进制表示
         * &后的值   0000-0000 0000-0000 0000-0000 0000-1100          值为12即16进制的C
         */
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 8; i++) {
            int temp = num & 15;
            if (temp > 9) {
                sb.append((char) (temp - 10 + 'A'));//强转成16进制

            } else {
                sb.append(temp);
            }
            num = num >>> 4;
        }
        System.out.println(sb.reverse());//0000003C

        return null;
    }

    /**
     * 16进制字符串转十进制int
     *
     * @param HexString
     * @return
     */
    public static int HexStringToInt(String HexString) {

        int inJTFingerLockAddress = Integer.valueOf(HexString, 16);

        return inJTFingerLockAddress;
    }



    /****************************应答 start ***********************************************************/
    /**
     * 计算校验码
     *
     * @param bytes
     * @throws IOException
     */
    public static void completeBcc(byte[] bytes) throws IOException {
        String bccHexStr = CommonUtils.getBCC(bytes, 2, bytes.length - 1);
        int bcc = Integer.parseInt(bccHexStr, 16);
        bytes[bytes.length - 1] = setByteToValue(bcc);
    }


    /**
     * 当前时间生成6位字节数组
     */
    public static byte[] returnTime() {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             DataOutputStream dos = new DataOutputStream(byteArrayOutputStream)) {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DATE);
            int hour = calendar.get(Calendar.HOUR);
            int min = calendar.get(Calendar.MINUTE);
            int sec = calendar.get(Calendar.SECOND);
            dos.writeByte(year - 2000);
            dos.writeByte(month + 1);
            dos.writeByte(day);
            dos.writeByte(hour);
            dos.writeByte(min);
            dos.writeByte(sec);
            dos.flush();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 给某个字节赋值
     *
     * @return
     */
    public static byte setByteToValue(int value){
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             DataOutputStream dos = new DataOutputStream(byteArrayOutputStream)) {
            dos.writeByte(value);
            dos.flush();
            byte[] bytes = byteArrayOutputStream.toByteArray();
            return bytes[0];
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 目标数组根据指定的位置替换 成 新数组的值
     *
     * @param
     * @throws IOException
     */
    public static void replaceByteByPos(byte[] targetBytes, int fromIndex, int toIndex, byte[] sourceBytes) {
        for (int i = 0; i < sourceBytes.length; i++) {
            Arrays.fill(targetBytes, fromIndex + i, toIndex + i, sourceBytes[i]);
        }
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
                .append(CommonUtils.formateTime(month))
                .append("-")
                .append(CommonUtils.formateTime(day))
                .append(" ")
                .append(CommonUtils.formateTime(hour))
                .append(":")
                .append(CommonUtils.formateTime(min))
                .append(":")
                .append(CommonUtils.formateTime(second));
        map.put("gathertime", builder.toString());
    }
    /*******************************应答 end *******************************************************/

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

}
