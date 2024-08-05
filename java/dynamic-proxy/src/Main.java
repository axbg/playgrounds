import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {
        Person personProxy = (Person) Proxy.newProxyInstance(
                Person.class.getClassLoader(),
                new Class<?>[]{Person.class},
                new PersonProxy(new PersonImpl())
        );

        personProxy.setName("John");
        personProxy.setAge(21);

        System.out.println("Person: " + personProxy.getName()
                + ", " + personProxy.getAge());
    }
}
