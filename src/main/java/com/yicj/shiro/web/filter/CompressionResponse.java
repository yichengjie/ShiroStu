package com.yicj.shiro.web.filter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by yichengjie on 2017/6/29.
 */
public class CompressionResponse  extends HttpServletResponseWrapper {
    protected HttpServletResponse response ;
    private ServletOutputStream out ;
    private CompressedStream  compressedOut ;
    private PrintWriter writer ;
    protected int contentLength ;
    public CompressionResponse(HttpServletResponse response) throws IOException {
        super (response);
        this.response = response;
        compressedOut = new CompressedStream(response.getOutputStream());
    }
    public void setContentLength(int len) {
        contentLength = len;
    }
    public ServletOutputStream getOutputStream()throws IOException {
        if(null ==  out){
            if(null !=  writer) {
                throw new IllegalStateException( "getWriter() has already been called on this response." );
            }
            out = compressedOut ;
        }
        return out ;
    }
    public PrintWriter getWriter()throws IOException{
        if(null == writer){
            if(null !=  out){
                throw new IllegalStateException("getOutputStream() has already been called on this response." );
            }
            writer = new PrintWriter(compressedOut);
        }
        return writer ;
    }
    public void flushBuffer(){
        try{
            if( writer != null ){
                writer.flush();
            }else if(out != null){
                out .flush();
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void reset() {
        super.reset();
        try{
            compressedOut.reset();
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void resetBuffer(){
        super .resetBuffer();
        try{
            compressedOut.reset();
        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }
    public void close()throws IOException {
        compressedOut.close();
    }

}
