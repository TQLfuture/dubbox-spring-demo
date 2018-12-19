package test;

import javax.xml.bind.annotation.*;

/**
 * @XmlRootElement //用户类级别的注解 对应xml的跟元素。通过name属性定义这个根节点的名称
 *
 * @XmlAccessorType //定义映射这个类中的何种类型都需要映射到xml。 (如果不存在@XmlAccessorType,默认使用XmlAccessType.PUBLIC_MEMBER注解)
 *
 * 　　参数：XmlAccessType.FIELD: java对象中的所有成员变量。
 *
 * 　　XmlAccessType.PROPERTY：java对象中所有通过getter/setter方式访问的成员变量。
 *
 * 　　XmlAccessType.PUBLIC_MEMBER：java对象中所有的public访问权限的成员变量和通过getter/setter方式访问的成员变量。
 *
 * 　　XmlAccessType.NONE: java对象的所有属性都不映射为xml的元素。
 *
 *
 *  @XmlAttribute // 用于把java对象的属性映射为xml的属性,并可通过name属性为生成的xml属性指定别名。
 *
 *  @XmlElement // 指定一个字段或get/set方法映射到xml的节点。通过name属性定义这个根节点的名称。
 *
 *  @XmlElementWrapper //为数组或集合定义一个父节点。通过name属性定义这个父节点的名称。
 *
 *
 *  注意事项： 如果java对象属性同时添加了get和set方法，注解不能定义在属性的定义上，只需在get或者set方法上定义一个即可，否则jaxb会报错
 *
 *
 *
 *
 */

@XmlRootElement //用户类级别的注解 对应xml的跟元素。通过name属性定义这个根节点的名称
public class Student {


    private String name;

    private String age;
    private String className;

    public String getName() {
        return name;
    }

    @XmlElement(name = "NAME")
    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }
    @XmlAttribute(name = "attr")
    public void setAge(String age) {
        this.age = age;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", className='" + className + '\'' +
                '}';
    }
}
