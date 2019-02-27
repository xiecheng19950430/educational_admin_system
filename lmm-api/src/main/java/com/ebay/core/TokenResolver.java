package com.ebay.core;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import com.ebay.common.Errors;

import javax.validation.constraints.NotNull;

/**
 * @author: jf <for1988@126.com>
 * @date: 2016/12/7
 */

@Component
public class TokenResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(UserSession.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest webRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        String token = webRequest.getParameter("token");
        //String token = webRequest.getHeader("Authorization");
        if (StringUtils.isNotEmpty(token)) {
            return new UserSession(token);
        } else if (parameter.getParameterAnnotation(NotNull.class) != null) {
            throw new CommonException(Errors.NOT_LOGIN);
        } else {
            return new UserSession();
        }
    }
}
