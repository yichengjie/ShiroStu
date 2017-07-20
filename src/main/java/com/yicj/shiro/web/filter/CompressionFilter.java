package com.yicj.shiro.web.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Created by yichengjie on 2017/6/29.
 */
public class CompressionFilter  implements Filter {
    protected Log log   = LogFactory.getLog(this.getClass()) ;
    //@SuppressWarnings("unchecked")
    public void doFilter(ServletRequest request, ServletResponse response,
                             FilterChain chain)  throws IOException, ServletException{
        boolean compress = false ;
        if(request instanceof HttpServletRequest){
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            Enumeration headers = httpRequest.getHeaders("Accept-Encoding");
            while(headers.hasMoreElements()){
                String value = (String) headers.nextElement();
                if(value.indexOf("gzip" ) != -1){
                    compress =  true ;
                }
            }
        }
        if(compress){ // 如果浏览器支持则压缩
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.addHeader("Content-Encoding","gzip");
            CompressionResponse compressionResponse = new CompressionResponse(httpResponse);
            chain.doFilter(request, compressionResponse );
            compressionResponse.close();
        }else{//如果浏览器不支持则不压缩
            chain.doFilter(request, response);
        }
    }
    public void init(FilterConfig config)  throws   ServletException {
    }

    public void destroy(){
    }
}
