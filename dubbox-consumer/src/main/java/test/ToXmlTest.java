package test;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class ToXmlTest {

    public static void main(String[] args) throws JAXBException {
        Student student = new Student();
        student.setAge("12");
        student.setName("tom");
        student.setClassName("internet");


        Student student2 = new Student();
        student2.setAge("122");
        student2.setName("tom2");
        student2.setClassName("internet2");

        List<Student> list = new ArrayList<>();
        list.add(student);
        list.add(student2);

//        JAXBContext context = JAXBContext.newInstance(Student.class);
//        Marshaller marshaller = context.createMarshaller();
//        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//        marshaller.setProperty(Marshaller.JAXB_ENCODING, "GBK");
//        StringWriter writer = new StringWriter();
//        marshaller.marshal(student,writer);

//        StudentList studentList = new StudentList();
//        studentList.setStudentList(list);
//
//        JAXBContext context = JAXBContext.newInstance(StudentList.class);
//        Marshaller marshaller = context.createMarshaller();
//        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//        marshaller.setProperty(Marshaller.JAXB_ENCODING, "GBK");
//        StringWriter writer = new StringWriter();
//        marshaller.marshal(studentList,writer);
//        System.out.printf(writer.toString());

        test1();
    }


    public static void test1() throws JAXBException {
        String xmlPath = "D:\\tqlJavaWorkspace\\dubboxp\\dubbox-consumer\\src\\main\\java\\test\\a.xml";
        JAXBContext context = JAXBContext.newInstance(StudentList.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Object object = unmarshaller.unmarshal(new File(xmlPath));
        if (object instanceof StudentList) {
            StudentList studentList = (StudentList) object;
            System.out.printf(studentList.toString());
        }


    }
}
