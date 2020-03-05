package com.wz.iot.newe;
import com.google.gson.Gson;
import org.junit.Test;
import java.io.IOException;
import java.util.Map;
import static com.wz.iot.newe.ResolveUtils.hexStringToByte;
public class ResolveUtilsTest {

    /**
     * 实时数据 / 补发信息
     *
     * @throws IOException
     */
    @Test
    public void realTimeInfoResolve() throws IOException {
        String hexData = "232302FE4C413944484B4D543548424A584B3233360101E513071A0E02000101030100000008FEBC15C027735A010F06A30000020101045D4E124E206E16262710050006E84B2E01B59CCF0601010CF801020CF80101550103540700000000000000000008010115C0277300A80001A80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF80CF809010100385555545454545454555454555555555554545555555554545454545454545454545555555454545454545454545454555455545455555555C8";
        byte[] bytes = hexStringToByte(hexData);
        Map<String, Object> resolve = ResolveUtils.resolveRealtime(bytes);
        System.out.println(new Gson().toJson(resolve));
    }


}