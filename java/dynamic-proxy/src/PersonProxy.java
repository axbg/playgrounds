import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class PersonProxy implements InvocationHandler {
    private Person person;

    public PersonProxy(Person person) {
        this.person = person;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        System.out.println("Intercepted call: " + method.getName());

        switch (method.getName()) {
            case "getName":
                return person.getName();
            case "setName":
                person.setName(args[0].toString());
                break;
            case "getAge":
                return person.getAge();
            case "setAge":
                person.setAge(Integer.parseInt(args[0].toString()));
                break;
            case "equals":
                return person.equals(args[0]);
        }

        return null;
    }
}
