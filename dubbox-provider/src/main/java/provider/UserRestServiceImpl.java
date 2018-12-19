package provider;

import api.User;
import api.UserRestService;
import api.UserService;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * 参考博客：https://blog.csdn.net/u012129031/article/details/53009565
 *
 * 但是在dubbo中，我们还自动支持目前业界普遍使用的方式，即用一个URL后缀（.json和.xml）来指定想用的数据格式。
 * 例如，在添加上述annotation后，直接访问http://localhost:8888/users/1001.json则表示用json格式，
 * 直接访问http://localhost:8888/users/1002.xml则表示用xml格式，比用HTTP Header更简单直观.
 * 。Twitter、微博等的REST API都是采用这种方式。
 *
 * 注意：这里要支持XML格式数据，在annotation中既可以用MediaType.TEXT_XML，也可以用MediaType.APPLICATION_XML，但是TEXT_XML是更常用的，
 * 并且如果要利用上述的URL后缀方式来指定数据格式，只能配置为TEXT_XML才能生效。
 *
 * XML数据格式的额外要求:
 *      由于JAX-RS的实现一般都用标准的JAXB（Java API for XML Binding）来序列化和反序列化XML格式数据，所以我们需要为每一个要用XML传输的对象添加一个类级别的JAXB annotation，否则序列化将报错。例如为getUser()中返回的User添加如下：
 *
 *          @XmlRootElement
 *          public class User implements Serializable {
 *              // ...
 *          }
 *
 *  定制序列化：Dubbo中的REST实现是用JAXB做XML序列化，用Jackson做JSON序列化，所以在对象上添加JAXB或Jackson的annotation即可以定制映射。
 *  例如，定制对象属性映射到XML元素的名字：
 *
 *      @XmlRootElement
 *      @XmlAccessorType(XmlAccessType.FIELD)
 *      public class User implements Serializable {
 *
 *          @XmlElement(name="username")
 *          private String name;
 *      }
 *
 *      定制对象属性映射到JSON字段的名字：
 *      public class User implements Serializable {
 *
 *          @JsonProperty("username")
 *          private String name;
 *      }
 *
 */




//如果所有方法都支持同样类型的输入输出数据格式，则我们无需在每个方法上做配置，只需要在服务类上添加annotation即可

@Path("users")
@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_XML}) // 注释代表的是一个资源可以接受的 MIME 类型。
//上例中的getUser()方法支持分别返回JSON和XML格式的数据，只需要在annotation中同时包含两种格式即可：
@Produces({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8}) //注释代表的是一个资源可以返回的 MIME 类型。
public class UserRestServiceImpl implements UserRestService {

    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GET
    @Path("{id : \\d+}")
    public User getUser(@PathParam("id") Long id) { // @PathParam     获取url中指定参数名称：
        HttpServletRequest request = RpcContext.getContext().getRequest(HttpServletRequest.class);
        if (request != null) {
            System.out.println("Client IP address from RpcContext: " + request.getRemoteAddr());
        }
        HttpServletResponse response =  RpcContext.getContext().getResponse(HttpServletResponse.class);
        if (response != null) {
            System.out.println("Response object from RpcContext: " + RpcContext.getContext().getResponse(HttpServletResponse.class));
        }
        return userService.getUser(id);
    }

    /**
     * 测试
     * @QueryParam 获取get请求中的查询参数：
     * public User getUser(@QueryParam("name") String name,@QueryParam("age") int age)
     *
     * 如果需要为参数设置默认值，可以使用@DefaultValue
     * public User getUser(@QueryParam("name") String name,@DefaultValue("26") @QueryParam("age") int age)
     *
     * @FormParam 获取post请求中表单中的数据：
     *
     */
}
