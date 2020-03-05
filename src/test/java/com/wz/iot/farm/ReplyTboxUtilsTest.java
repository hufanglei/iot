package com.wz.iot.farm;
import org.junit.Test;


public class ReplyTboxUtilsTest {

    /**
     * 对车辆登入应答
     */
    @Test
    public void vehicleLogin() {
        String hexStr = "232302FE4C3030303030304133303030303135323001011313071E0D02130101030100000003D0920CE427424E01003F680000020101FF574E204E20580CE42710050007131032017A23470601060F5D01540F5301014D01044B070000000000000000000801010CE4274200540001540F5C0F5C0F5C0F590F5A0F5D0F5B0F5A0F5C0F5C0F5D0F590F5C0F5C0F5B0F580F5C0F5C0F5C0F5C0F5C0F5B0F5C0F5C0F580F580F580F580F560F570F580F560F570F570F580F550F580F570F580F590F570F580F590F580F560F570F570F590F580F570F580F580F580F570F570F560F570F570F560F540F5A0F580F560F590F580F570F580F590F580F570F5A0F580F590F590F5A0F570F560F570F570F5A0F580F550F570F53090101000E4D4C4C4B4C4B4C4B4C4B4B4B4C4BC6";
        byte replySignByte = 0x01;
        byte[] bytes = ReplyTboxUtils.hexStringToByte(hexStr);
        byte[] vehicleLogin = ReplyTboxUtils.vehicleLogin(bytes, replySignByte);
        String s = ResolveUtils.bytesToHexString(vehicleLogin);
        System.out.println(s);
    }

    /**
     * 对车辆登出应答
     */
    @Test
    public void vehicleLoginOut() {
        String hexStr = "232304FE4C413942475A4C46304A5959434B30303601000813071A0E02020001D7";
        byte replySignByte = 0x01;
        byte[] bytes = ReplyTboxUtils.hexStringToByte(hexStr);
        byte[] vehicleLoginOut = ReplyTboxUtils.vehicleLoginOut(bytes, replySignByte);
        String s = ResolveUtils.bytesToHexString(vehicleLoginOut);
        System.out.println(s);
    }


    /**
     * 对心跳应答
     */
    @Test
    public void heartbeat() {
        String hexStr = "232307FE4C30303030303041343030303033383335010000CC";
        byte replySignByte = 0x01;
        byte[] bytes = ReplyTboxUtils.hexStringToByte(hexStr);
        byte[] heartbeat = ReplyTboxUtils.heartbeat(bytes, replySignByte);
        String s = ResolveUtils.bytesToHexString(heartbeat);
        System.out.println(s);
    }


    /**
     * 对校时应答
     */
    @Test
    public void timeCheck() {
        String hexStr = "232308FE4C53344153423343334847363438363934010000C1";
        byte replySignByte = 0x01;
        byte[] bytes = ReplyTboxUtils.hexStringToByte(hexStr);
        byte[] timeCheck = ReplyTboxUtils.timeCheck(bytes, replySignByte);
        String s = ReplyTboxUtils.bytesToHexString(timeCheck);
        System.out.println(s);
    }

    /**
     * 对替换vin码应答
     */
    @Test
    public void replyAndReplaceVin() {
    }
}