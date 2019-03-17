package com.ebay.common;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import com.ebay.common.utils.RSAUtil;

import java.nio.charset.Charset;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PaymentEncryptor {


    /**
     * 私钥
     */
    private RSAPrivateKey privateKey;

    /**
     * 公钥
     */
    private RSAPublicKey publicKey;

    /**
     * 获取私钥
     *
     * @return 当前的私钥对象
     */
    public RSAPrivateKey getPrivateKey() {
        return privateKey;
    }

    /**
     * 获取公钥
     *
     * @return 当前的公钥对象
     */
    public RSAPublicKey getPublicKey() {
        return publicKey;
    }


    public PaymentEncryptor() throws Exception {
        ClassPathResource privateKeyResource = new ClassPathResource("payment_pkcs8_private_key.pem");
        ClassPathResource publicKeyResource = new ClassPathResource("payment_rsa_public_key.pem");
        this.publicKey = RSAUtil.loadPublicKey(publicKeyResource.getInputStream());
        this.privateKey = RSAUtil.loadPrivateKey(privateKeyResource.getInputStream());

        System.out.println("rsa load");
    }


    public String decryptWithBase64(String base64String) throws Exception {
        return RSAUtil.decryptWithBase64(getPrivateKey(), base64String);
    }

    public String encryptWithBase64(String string) throws Exception {
        return RSAUtil.encryptWithBase64(getPublicKey(), string);
    }

    public Map<String, String> decryptWithBase64ToMap(String base64String) throws Exception {
        String string = this.decryptWithBase64(base64String);
        List<NameValuePair> list = URLEncodedUtils.parse(string, Charset.forName("UTF-8"));
        Map<String, String> map = new HashMap();
        for (NameValuePair pair : list) {
            map.put(pair.getName(), pair.getValue());
        }
        return map;
    }

    public String encryptWithBase64FromMap(Map<String, String> map) throws Exception {
        List<NameValuePair> nvpList = new ArrayList<>(map.size());
        for (Map.Entry<String, String> entry : map.entrySet()) {
            nvpList.add(
                    new NameValuePair() {
                        public String getName() {
                            return entry.getKey();
                        }

                        public String getValue() {
                            return entry.getKey();
                        }
                    });
        }
        String string = URLEncodedUtils.format(nvpList, Charset.forName("UTF-8"));
        return RSAUtil.encryptWithBase64(getPublicKey(), string);
    }


}