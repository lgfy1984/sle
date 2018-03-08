package com.qmh.sle.utils;

import com.qmh.sle.bean.SPatient;

import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.net.ssl.SSLHandshakeException;


/***
 * json工具解析类
 */
public class JsonUtils {

    public static final ObjectMapper MAPPER = new ObjectMapper().configure(
            DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    //设置日期格式
    public static final SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

    public static <T> T toBean(Class<T> type, InputStream is) {
        T obj = null;
        try {
            obj = parse(is, type, null);
        } catch (IOException e) {
        }
        return obj;
    }

    /**
     * 对获取到的网络数据进行处理
     *
     * @param inputStream
     * @param type
     * @param instance
     * @return
     * @throws java.io.IOException
     */
    private static <T> T parse(InputStream inputStream, Class<T> type, T instance) throws
            IOException {
        InputStreamReader reader = null;
        try {
            reader = new InputStreamReader(inputStream);
            String data = IOUtils.toString(reader);
            MAPPER.setDateFormat(fmt);

            if (type != null) {
                return MAPPER.readValue(data, type);
            } else if (instance != null) {
                return MAPPER.readerForUpdating(instance).readValue(data);
            } else {
                return null;
            }
        } catch (SSLHandshakeException e) {
            throw new SSLHandshakeException("You can disable certificate checking by setting " +
                    "ignoreCertificateErrors on GitlabHTTPRequestor");
        } finally {
            IOUtils.closeQuietly(reader);
        }
    }

    public static <T> T toBean(Class<T> type, byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        return toBean(type, new ByteArrayInputStream(bytes));
    }

    public static <T> List<T> getList(Class<T[]> type, byte[] bytes) {
        if (bytes == null) return null;
        List<T> results = new ArrayList<T>();
        try {
            T[] _next = toBean(type, bytes);
            if (_next != null)
                Collections.addAll(results, _next);
        } catch (Exception e) {
            return null;
        }
        return results;
    }

    public static String bean2Json(Object obj) throws IOException {
        MAPPER.setDateFormat(fmt);
        StringWriter sw = new StringWriter();
        JsonGenerator gen = new JsonFactory().createJsonGenerator(sw);
        MAPPER.writeValue(gen, obj);
        gen.close();
        return sw.toString();
    }
    public static <T> T json2Bean(String jsonStr, Class<T> objClass)
            throws IOException {

        MAPPER.setDateFormat(fmt);
        return MAPPER.readValue(jsonStr, objClass);
    }

//    public static void main(String[] args) {
//        SPatient v1 = new SPatient();
//        v1.setId("222222222");
//        try {
//            System.out.println(bean2Json(v1));
//
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
}
