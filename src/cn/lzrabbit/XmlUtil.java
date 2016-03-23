package cn.lzrabbit;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.*;

public class XmlUtil {

    public static String toXML(Object obj) {
        try {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");// //�����ʽ
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);// �Ƿ��ʽ�����ɵ�xml��
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);// �Ƿ�ʡ��xmͷ������Ϣ
            StringWriter writer = new StringWriter();
            marshaller.marshal(obj, writer);
            return writer.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T fromXML(String xml, Class<T> valueType) {
        try {
            JAXBContext context = JAXBContext.newInstance(valueType);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (T) unmarshaller.unmarshal(new StringReader(xml));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}