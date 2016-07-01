package cjc.utils;

import java.io.InputStream;
import java.io.Reader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import cjc.weixin.sdk.msg.ReceivedMessage;
import cjc.weixin.sdk.msg.TextMessage;

public class JaxbXmlUtil {
	
	
	 @SuppressWarnings("unchecked")
	    public static <T> T readConfigFromStream(Class<T> clazz, InputStream dataStream) throws JAXBException {
	        try {
	            JAXBContext jc = JAXBContext.newInstance(clazz);
	            Unmarshaller u = jc.createUnmarshaller();
	            return (T) u.unmarshal(dataStream);
	        } catch (JAXBException e) {
	            throw e;
	        }
	    }
	 
	 @SuppressWarnings("unchecked")
	    public static <T> T readConfigFromReader(Class<T> clazz, Reader reader) throws JAXBException {
	        try {
	            JAXBContext jc = JAXBContext.newInstance(clazz);
	            Unmarshaller u = jc.createUnmarshaller();
	            return (T) u.unmarshal(reader);
	        } catch (JAXBException e) {
	            throw e;
	        }
	    }
	 
	 public static String toXML(Object obj) {
	        try {
	            JAXBContext context = JAXBContext.newInstance(obj.getClass());
	            Marshaller marshaller = context.createMarshaller();
	            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");// //编码格式
	            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);// 是否格式化生成的xml串
	            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);// 是否省略xm头声明信息
	            StringWriter writer = new StringWriter();
	            marshaller.marshal(obj, writer);
	            return writer.toString();
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }
//	 @SuppressWarnings("unchecked")
//	    public static <T> T readString(Class<T> clazz, String context) throws JAXBException {
//	        try {
//	            JAXBContext jc = JAXBContext.newInstance(clazz);
//	            Unmarshaller u = jc.createUnmarshaller();
//	            return (T) u.unmarshal(new File(context));
//	        } catch (JAXBException e) {
//	            // logger.trace(e);
//	            throw e;
//	        }
//	    }
}
