import org.junit.jupiter.api.Test;

import java.util.UUID;

public class UploadTest {

    /**
     * UUID
     */
    @Test
    public void test01() {
        String string = UUID.randomUUID().toString();
        /*
            UUID串：f14ad6a8-75b1-4295-a34d-fd39f6c31110
            36个字符 = 32个十六进制字符 + 4个分隔符
            ● UUID由32个十六进制字符（0-9, a-f）组成，分为五段，用连字符（-）分隔
            ● UUID它是一个128位的二进制数
            ● 但为了方便人类阅读和存储，它被编码成了这种十六进制字符串的形式
            ● 十六进制中的每个字符可以表示 4 位二进制数，
              因此，32个十六进制字符可以表示 32 * 4 = 128 位二进制数
         */
        System.out.println(UUID.randomUUID());  // f14ad6a8-75b1-4295-a34d-fd39f6c31110
        System.out.println(string.length());  // 36
        System.out.println(System.currentTimeMillis());

    }

}
