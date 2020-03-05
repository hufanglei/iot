package com.wz.iot.farm;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;

/**
 * 对终端的应答
 *
 * @author hfl on 2019/8/16
 */
public class ReplyTboxUtils {


    /**
     * 1.车辆登入 应答
     *
     * @param bytes
     * @param replySignByte
     * @return
     */
    public static byte[] vehicleLogin(byte[] bytes, byte replySignByte) {
        try {
            //1.修改应答标志
            bytes[3] = replySignByte;
            byte[] newTimeBytes = returnTime();
            //2.替换报文时间替换报文时间
            replaceByteByPos(bytes, 24, newTimeBytes);
            //3.重新计算校验位
            completeBcc(bytes);
        } catch (IOException e) {
            bytes = null;
        }
        return bytes;
    }

    /**
     * 2.车辆登出 应答
     *
     * @param bytes
     * @param replySignByte
     * @return
     */
    public static byte[] vehicleLoginOut(byte[] bytes, byte replySignByte) {
        return vehicleLogin(bytes, replySignByte);
    }


    /**
     * 3. 心跳
     *
     * @param bytes
     * @param replySignByte
     * @return
     */
    public static byte[] heartbeat(byte[] bytes, byte replySignByte) {
        try {
            //1.修改应答标志
            bytes[3] = replySignByte;
            //4.重新计算校验位
            completeBcc(bytes);
            return bytes;
        } catch (Exception e) {
            return null;
        }
    }



    /**
     * 4. 校时
     *
     * @param bytes
     * @param replySignByte
     * @return
     */
    public static byte[] timeCheck(byte[] bytes, byte replySignByte) {

        try {
            //1.修改应答标志
            bytes[3] = replySignByte;

            //2.修改数据单元长度为6
            Arrays.fill(bytes, 22, 23, (byte) 0);
            Arrays.fill(bytes, 23, 24, (byte) 6);

            //校时应答的数据长度是固定的23+6+1
            byte[] newBytes = new byte[30];
            //2.将以前的字节拷贝到新的数组中
            System.arraycopy(bytes, 0, newBytes, 0, bytes.length);

            //将当前时间放入新数组的数据单元，占6个字节
            byte[] newTimeBytes = returnTime();
            replaceByteByPos(newBytes, 24, newTimeBytes);
            //4.重新计算校验位
            completeBcc(newBytes);
            return newBytes;
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * 5.替换vin码
     *
     * @param bytes
     * @param replySignByte
     * @return
     */
    public static byte[] replyAndReplaceVin(byte[] vinBytes, byte[] bytes, byte replySignByte) {
        try {
            //1.修改应答标志
            bytes[3] = replySignByte;
            byte[] newTimeBytes = returnTime();
            //2.替换vin码
            replaceByteByPos(bytes, 4, vinBytes);
            //3.替换报文时间替换报文时间
            replaceByteByPos(bytes, 24, newTimeBytes);
            //4.重新计算校验位
            completeBcc(bytes);
        } catch (IOException e) {
            bytes = null;
        }
        return bytes;
    }

    /**
     * 目标数组根据指定的位置替换 成 新数组的值
     *
     * @param
     * @throws IOException
     */
    public static void replaceByteByPos(byte[] targetBytes, int fromIndex, byte[] sourceBytes) {
        for (int i = 0; i < sourceBytes.length; i++) {
            Arrays.fill(targetBytes, fromIndex + i, fromIndex + 1 + i, sourceBytes[i]);
        }
    }


    /**
     * 给某个字节赋值
     *
     * @return
     */
    private static byte setByteToValue(int value) {
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
     * 当前时间生成6位字节数组
     */
    private static byte[] returnTime() {
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
     * 计算校验码
     *
     * @param bytes
     * @throws IOException
     */
    private static void completeBcc(byte[] bytes) throws IOException {
        String bccHexStr = getBCC(bytes, 2, bytes.length - 1);
        int bcc = Integer.parseInt(bccHexStr, 16);
        bytes[bytes.length - 1] = setByteToValue(bcc);
    }

    /**
     * 获取BCC校验码
     *
     * @param data
     * @param start 开始位置0
     * @param end   字节数组长度
     * @return
     */
    private static String getBCC(byte[] data, int start, int end) {
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


}
