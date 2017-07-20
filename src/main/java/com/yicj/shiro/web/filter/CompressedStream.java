package com.yicj.shiro.web.filter;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

/**
 * Created by yichengjie on 2017/6/29.
 */
public class CompressedStream extends ServletOutputStream {

    private ServletOutputStream  out ;
    private GZIPOutputStream gzip ;
    public CompressedStream(ServletOutputStream out) throws IOException {
        this . out   = out;
        reset();
    }
    public void close() throws IOException {
        gzip .close();
    }
    public void flush() throws IOException {
        gzip.flush();
    }
    public void write(byte [] b)  throws   IOException {
        write(b, 0, b. length );
    }
    public void  write(byte [] b, int off, int len)throws IOException {
        gzip .write(b, off, len);
    }
    public void write(int b) throws IOException {
        gzip .write(b);
    }

    public void reset() throws IOException {
        gzip = new GZIPOutputStream ( out );
    }
}
