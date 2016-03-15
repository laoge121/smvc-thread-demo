package jaxb;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ResourceBundle;

/**
 * xml 到实体类
 * Created by pei.xu on 2015/11/5.
 */
public class JaxbUtil {


    public void XmlToObject(String str, Object obj) throws JAXBException, IOException {

        JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        //ClassPathResource classPathResource = new ClassPathResource("aa.xml", JaxbUtil.class);
        //obj = (Class) unmarshaller.unmarshal(classPathResource.getInputStream());
        obj = (Object) unmarshaller.unmarshal(new File("F:/gitsvn/smvc-thread/smvc-server/src/main/java/jaxb/aa.xml"));
        System.out.println(obj.toString());
    }

    public void ObjectToXMl(Object object) throws JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());

        Marshaller marshaller = jaxbContext.createMarshaller();

        marshaller.marshal(object, System.out);
    }

    public static void main(String[] args) throws JAXBException, URISyntaxException, IOException {

        Person person = new Person();
        person.setName("aaa");
        person.setPassword("vvvv");
        new JaxbUtil().ObjectToXMl(person);

        new JaxbUtil().XmlToObject("", new Person());

    }

}
