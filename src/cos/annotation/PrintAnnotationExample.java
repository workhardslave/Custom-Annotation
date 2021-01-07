package cos.annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PrintAnnotationExample {
    public static void main(String[] args) {
        // Service 클래스에 선언된 모든 메소드의 정보를 메소드 배열로 리턴한다.
        Method[] declaredMethods = Service.class.getDeclaredMethods();

        for(Method method : declaredMethods) {
            // 해당 메소드가 어노테이션이 적용 되어 있는지 확인한다.
            if(method.isAnnotationPresent(PrintAnnotation.class)) {
                // 해당 메소드에 적용된 어노테이션 객체를 가져온다.
                PrintAnnotation printAnnotation = method.getAnnotation(PrintAnnotation.class);

                // 메소드 이름 출력
                System.out.println("[" + method.getName() + "]");
                // 구분선 출력
                for(int i = 0; i < printAnnotation.number(); i++) {
                    System.out.print(printAnnotation.value());
                }
                System.out.println();

                // 메소드 호출
                try {
//                    Service service = new Service();
//                    service.method1(), service.method2(), service.method3();
                    method.invoke(new Service());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                System.out.println();

            }
        }
    }
}
