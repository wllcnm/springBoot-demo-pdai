package com.example.springbootdemo.Filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;

@Slf4j
public class RequestCachingFilter extends OncePerRequestFilter {

    /**
     * This {@code doFilter} implementation stores a request attribute for
     * "already filtered", proceeding without filtering again if the
     * attribute is already there.
     *
     * @param request     request
     * @param response    response
     * @param filterChain filterChain
     * @throws ServletException ServletException
     * @throws IOException      IOException
     * @see #getAlreadyFilteredAttributeName
     * @see #shouldNotFilter
     * @see #doFilterInternal
     */
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        boolean isFirstRequest = !isAsyncDispatch(request);
        HttpServletRequest requestWrapper = request;

        //如果是第一次请求,就将该请求转换为ContentCachingRequestWrapper代理类
        //该类的作用是将请求中的body缓存起来
        if (isFirstRequest && !(request instanceof ContentCachingRequestWrapper)) {
            requestWrapper = new ContentCachingRequestWrapper(request);
        }
        try {
            filterChain.doFilter(requestWrapper, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

