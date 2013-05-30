/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grandroid.net;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.message.BasicHeader;

import android.util.Log;

/**
 * 
 * @author Rovers
 */
public class PostFileEntity implements HttpEntity {

    private final static char[] MULTIPART_CHARS =
            "-_1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private String boundary = null;
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    boolean isSetLast = false;
    boolean isSetFirst = false;

    /**
     * 
     */
    public PostFileEntity() {
        StringBuilder buf = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < 30; i++) {
            buf.append(MULTIPART_CHARS[rand.nextInt(MULTIPART_CHARS.length)]);
        }
        this.boundary = buf.toString();

    }

    /**
     * 
     */
    public void writeFirstBoundary() {
        if (!isSetFirst) {
            try {
                out.write(("--" + boundary + "\r\n").getBytes());
            } catch (IOException e) {
                Log.e("PostFileEntity", e.getMessage());
                e.printStackTrace();
            }
        }
        isSetFirst = true;
    }

    /**
     * 
     */
    public void writeLastBoundary() {
        if (isSetLast) {
            return;
        }
        try {
            out.write(("\r\n--" + boundary + "--\r\n").getBytes());
        } catch (IOException e) {
            Log.e("PostFileEntity", e.getMessage());
            e.printStackTrace();
        }
        isSetLast = true;
    }

    /**
     * 
     * @param key
     * @param value
     */
    public void addPart(String key, String value) {
        writeFirstBoundary();
        try {
            out.write(("Content-Disposition: form-data; name=\""
                    + key + "\"\r\n").getBytes());
            out.write("Content-Type: text/plain; charset=UTF-8\r\n".getBytes());
            out.write("Content-Transfer-Encoding: 8bit\r\n\r\n".getBytes());
            out.write(value.getBytes());
            out.write(("\r\n--" + boundary + "\r\n").getBytes());
        } catch (IOException e) {
            Log.e("PostFileEntity", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 
     * @param key
     * @param value
     */
    public void addPart(String key, File value) {
        writeFirstBoundary();
        try {
            out.write(("Content-Disposition: form-data; name=\"" + key
                    + "\"; filename=\"" + value.getName() + "\"\r\n").getBytes());
            out.write("Content-Type: application/octet-stream\r\n".getBytes());
            out.write("Content-Transfer-Encoding: binary\r\n\r\n".getBytes());

            FileInputStream fin = new FileInputStream(value);
            int data = fin.read();
            while (data != -1) {
                out.write(data);
                data = fin.read();
            }

        } catch (IOException e) {
            Log.e("PostFileEntity", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 
     * @return
     */
    public long getContentLength() {
        writeLastBoundary();
        return out.toByteArray().length;
    }

    /**
     * 
     * @return
     */
    public Header getContentType() {
        return new BasicHeader("Content-Type", "multipart/form-data; boundary="
                + boundary);
    }

    /**
     * 
     * @return
     */
    public boolean isChunked() {
        return false;
    }

    /**
     * 
     * @return
     */
    public boolean isRepeatable() {
        return false;
    }

    /**
     * 
     * @return
     */
    public boolean isStreaming() {
        return false;
    }

    /**
     * 
     * @param outstream
     * @throws IOException
     */
    public void writeTo(OutputStream outstream) throws IOException {
        outstream.write(out.toByteArray());
    }

    /**
     * 
     * @return
     */
    public Header getContentEncoding() {
        return null;
    }

    /**
     * 
     * @throws IOException
     * @throws UnsupportedOperationException
     */
    public void consumeContent() throws IOException,
            UnsupportedOperationException {
        throw new UnsupportedOperationException(
                "Streaming entity does not implement #consumeContent()");
    }

    /**
     * 
     * @return
     * @throws IOException
     * @throws UnsupportedOperationException
     */
    public InputStream getContent() throws IOException,
            UnsupportedOperationException {
        throw new UnsupportedOperationException(
                "Multipart form entity does not implement #getContent()");
    }
}
