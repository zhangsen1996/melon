import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.*;

import static java.lang.annotation.ElementType.*;

public class Test<V extends @Custom(id=1) Number & Serializable> {
    private V v;

    private int n;

    public V getV() {
        return v;
    }

    public void setV(V v) {
        this.v = v;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        Method[] declaredMethods = User.class.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            Parameter[] parameters = declaredMethod.getParameters();
            Class<?>[] parameterTypes = declaredMethod.getParameterTypes();
            System.out.println();
        }


        Test<Integer> integerTest = new Test<>();
        integerTest.setV(1);
        Integer v = integerTest.getV();
        Field[] fields = integerTest.getClass().getDeclaredFields();
        for (Field field : fields) {
            Type genericType = field.getGenericType();

            System.out.println();
        }

        Method[] methods = integerTest.getClass().getMethods();
        for (Method method : methods) {
            Class<?>[] parameterTypes = method.getParameterTypes();
            System.out.println();
        }

        Schema<Demo> schema = RuntimeSchema.getSchema(Demo.class);
        LinkedBuffer buffer = LinkedBuffer.allocate(512);
        Demo demo = new Demo();
        Class<?>[] parameterTypes = {User.class,User.class};
        demo.setParameterTypes(parameterTypes);
        demo.setVersion(5);
        final byte[] protostuff;
        try
        {
            protostuff = ProtostuffIOUtil.toByteArray(demo, schema, buffer);
        }
        finally
        {
            buffer.clear();
        }

        // deser
        Demo fooParsed = schema.newMessage();
        ProtostuffIOUtil.mergeFrom(protostuff, fooParsed, schema);
        Object o = fooParsed.getParameterTypes()[0].newInstance();
        System.out.println();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String s = objectMapper.writeValueAsString(demo);
            byte[] bytes = s.getBytes();
            System.out.println();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        Custom annotation = Demo.class.getAnnotation(Custom.class);
        InvocationHandler invocationHandler = Proxy.getInvocationHandler(annotation);


    }
}

@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ANNOTATION_TYPE, CONSTRUCTOR, FIELD,
        METHOD, PACKAGE, PARAMETER, TYPE, TYPE_PARAMETER, TYPE_USE})
@interface Custom {
    int id();
}

@Custom(id=2)
class Demo{
    /**
     * 版本
     */
    private int version;
    private int version1=55555555;
    private int version2=666666666;
    /**
     * 参数
     */
    private Object[] args={new User(),new User()};
    /**
     * 方法参数类型
     */
    private Class<?>[] parameterTypes;


    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getVersion1() {
        return version1;
    }

    public void setVersion1(int version1) {
        this.version1 = version1;
    }

    public int getVersion2() {
        return version2;
    }

    public void setVersion2(int version2) {
        this.version2 = version2;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }
}

class User {
    private int id;

    private String name="c";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAll(String name,int id){

    }
}