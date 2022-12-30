package com.example.springbootdemo.Aspect;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;


import cn.hutool.core.text.CharSequenceUtil;
import com.example.springbootdemo.Exception.BusinessException;
import com.example.springbootdemo.Utils.SignUtil;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.util.ContentCachingRequestWrapper;


/**
 * @author pdai
 */
@Aspect
@Component
public class SignAspect {

    /*
    * 请求头
    * */
    private static final String SIGN_HEADER = "X-SIGN";

    /*
    * 切入点
    * */
    @Pointcut("execution(@com.example.springbootdemo.annotation.Signature * *(..))")
    private void verifySignPointCut() {
        // nothing
    }

   /*
   * 在切入点之前执行
   * */
    @Before("verifySignPointCut()")
    public void verify() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String sign = request.getHeader(SIGN_HEADER);

        // 判断请求头中有没有SIGN_HEADER参数,没有直接抛出异常
        if (CharSequenceUtil.isBlank(sign)) {
            throw new BusinessException("no signature in header: " + SIGN_HEADER);
        }

        // 调用generatedSignature(HttpRequest request)方法校验签名是否正确
        try {
            //得到参数签名,将生成的参数签名与请求头的参数进行比较,对比成功就通过,否则抛出异常
            String generatedSign = generatedSignature(request);

            if (!sign.equals(generatedSign)) {
                throw new BusinessException("invalid signature");
            }
        } catch (Throwable throwable) {
            throw new BusinessException("invalid signature");
        }
    }

    private String generatedSignature(HttpServletRequest request) throws IOException {
        // @RequestBody
        String bodyParam = null;
        if (request instanceof ContentCachingRequestWrapper) {
            bodyParam = new String(((ContentCachingRequestWrapper) request).getContentAsByteArray(), StandardCharsets.UTF_8);
        }

        // @RequestParam
        Map<String, String[]> requestParameterMap = request.getParameterMap();

        // @PathVariable
        String[] paths = null;
        ServletWebRequest webRequest = new ServletWebRequest(request, null);
        Map<String, String> uriTemplateVars = (Map<String, String>) webRequest.getAttribute(
                HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE, RequestAttributes.SCOPE_REQUEST);
        if (!CollectionUtils.isEmpty(uriTemplateVars)) {
            paths = uriTemplateVars.values().toArray(new String[0]);
        }

        return SignUtil.sign(bodyParam, requestParameterMap, paths);
    }

}

