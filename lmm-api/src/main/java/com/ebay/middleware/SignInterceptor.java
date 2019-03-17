package com.ebay.middleware;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.ebay.core.NoSign;
import com.ebay.core.SignException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: jf <for1988@126.com>
 * @date: 2016/12/6
 */

@Component
public class SignInterceptor extends HandlerInterceptorAdapter {

		@Value("${app.secret}")
		private String AppSecret;
		@Value("${app.sign-skip}")
		private Boolean signSkip;

		private List<String> EXCLUED_KEYS = Collections.singletonList("sign");

		Logger logger = LoggerFactory.getLogger(SignInterceptor.class);

		@Override
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        /*InputStream is = request.getInputStream();
        int size = request.getContentLength();

        byte[] reqBodyBytes = readBytes(is, size);

        String res = new String(reqBodyBytes);

        System.out.println(res);*/

				HandlerMethod method = (HandlerMethod) handler;
				if (method.getMethodAnnotation(NoSign.class) != null) {
						return true;
				}


				Map<String, String[]> params = request.getParameterMap();


				if ("/user/check_verify".equals(request.getServletPath())) {
						return true;
				}

				if ("/rb/result".equals(request.getServletPath())) {
						return true;
				}

				if (signSkip) {
						return true;
				}
//        if (signSkip && ArrayUtils.isNotEmpty(params.get("_xyz"))) {
//            return true;
//        }

				String[] signs = params.get("sign");
				if (ArrayUtils.isEmpty(signs)) {
						throw new SignException("miss sign [" + request.getRequestURI() + "]");
				}
				String sign = signs[0];

				String[] keys = params.keySet().toArray(new String[]{});
				Arrays.sort(keys);

				String[] values = Stream.of(keys).map(key -> {
						if (!excludeKey(key) && ArrayUtils.isNotEmpty(params.get(key))) {
								return key + "=" + params.get(key)[0];
						} else {
								return null;
						}
				}).filter(Objects::nonNull).collect(Collectors.toList()).toArray(new String[]{});

				String signString = String.join("&", values) + AppSecret;
				String sign2 = DigestUtils.md5Hex(signString);

				//logger.info("sign [" + signString + "]  = " + sign2 + ",sign [ get ] = " + sign);
				//System.out.println("sign [" + signString + "]  = " + sign2);

				if (Objects.equals(sign, sign2)) {
						return true;
				} else {
						throw new SignException("error sign [" + request.getRequestURI() + "]");
				}
		}

		public static final byte[] readBytes(InputStream is, int contentLen) throws IOException {
				if (contentLen > 0) {
						int readLen = 0;

						int readLengthThisTime = 0;

						byte[] message = new byte[contentLen];

						try {

								while (readLen != contentLen) {

										readLengthThisTime = is.read(message, readLen, contentLen
												- readLen);

										if (readLengthThisTime == -1) {// Should not happen.
												break;
										}

										readLen += readLengthThisTime;
								}

								return message;
						} catch (IOException e) {
								// Ignore
								// e.printStackTrace();
						} finally {
								is.close();
						}
				}

				return new byte[]{};
		}

		private boolean excludeKey(String key) {
				return StringUtils.isEmpty(key) || EXCLUED_KEYS.contains(key);
		}
}
