## 어노테이션이란?

프로그램에게 추가적인 정보를 제공해주는 메타데이터로 다음과 같은 용도에 쓰임

컴파일러에게 코드 작성 문법 에러를 체크하도록 정보 제공 </br>
ex) 메소드가 재정의 되어 있는지 확인할 수 있다. (@Override) </br>
소프트웨어 개발 툴이 빌드나 배치 시 코드를 자동으로 생성할 수 있도록 정보를 제공 </br>
ex) XML 설정 파일을 자동 생성하거나 배포를 위한 JAR 압축 파일을 자동생성할 수 있다. </br>
실행 시(런타임 시) 특정 기능을 실행하도록 정보를 제공 </br>
ex) 객체가 애플리케이션 내부에서 해야 할 역할을 정의할 수 있다. (서블릿, 컨트롤러, ...) </br></br>

## 어노테이션의 엘리먼트 멤버

어노테이션을 코드에 적용할 때 외부의 값을 입력받을 수 있도록 하는 역할 </br>
엘리먼트 타입은 기본 타입, 참조 타입 모두 사용할 수 있다.

```java
public @interface Annotation {
	String elementName1();
	int elementName2() default 5;
}
```

</br>

## 어노테이션 적용 시 엘리먼트 값을 지정하는 방법

```java
@AnnotationName(elementName1 = “값”, elementName2 = 3);
@AnnotationName(elementName1 = “값”);
```

</br>

어노테이션의 기본 엘리먼트 value

```java
public @interface Annotation {
	String value(); // 기본 엘리먼트 선언
	int elementName() default 5;
}
```

</br>

어노테이션을 적용할 때 엘리먼트 명을 생략 가능

```java
@Annotation(“값”);
```

</br>

두 개 이상의 속성을 기술할 때에는 value = 값 형태로 기술

```java
@Annotation(value = “값”, elementName = 3);
```

</br>

## 어노테이션 적용 대상

코드 상에서 어노테이션을 적용할 수 있는 대상을 나타내며, </br>
java.lang.annotation.ElementType enum 상수로 정의되어 있다.

- TYPE : 클래스, 인터페이스, enum 타입
- ANNOTATION_TYPE : 어노테이션
- FIELD : 필드
- CONSTRUCTOR : 생성자
- METHOD : 메소드
- LOCAL_VARIABLE : 로컬 변수
- PACKAGE : 패키지
  </br></br>

## 어노테이션 적용 대상 지정 방법

@Target의 어노테이션으로 적용 대상 지정, <br/>
@Target의 기본 엘리먼트인 value의 타입은 ElementType 배열

```java
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD})
    public @interface AnnotationName {
}

...

@AnnotationName : TYPE(Class) : O
public class ClassName {
	@AnnotationName // FIELD : O
	private String fieldName;

	@AnnotationName // CONSTRUCTOR : X
	public ClassName() { };

	@AnnotationName // METHOD : O
	public void methodName() { };
}
```

</br>

## 어노테이션 유지 정책

어노테이션 적용 코드가 유지되는 시점을 지정하는 것 </br>
java.lang.annotation.RetentionPolicy enum 상수로 정의되어 있다.

### SOURCE

소스 상에서만 어노테이션 정보를 유지한다. 소스 코드를 분석할 때만 의미가 있으며, 바이트 코드 파일에는 정보가 남지 않는다.

### CLASS

바이트 코드 파일까지 어노테이션 정보를 유지한다. 하지만 리플렉션을 이용하여 어노테이션 정보를 얻을 수는 없다.

### RUNTIME

바이트 코드 파일까지 어노테이션 정보를 유지하면서, 리플렉션을 이용해서 런타임에 어노테이션 정보를 얻을 수 있다.
</br></br>

---

리플렉션(Reflection) : 런타임에 클래스의 메타 데이터(필드, 생성자, 메소드, 어노테이션의 정보)를 얻는 기능으로, 이러한 리플렉션을 이용해 어노테이션 정보를 얻기 위해서 유지 정책을 RUNTIME으로 설정해야 한다.

---

</br>

## 유지 정책 지정 방법

@Retention 어노테이션으로 유지 정책을 지정 <br/>
@Retention의 기본 엘리먼트인 value의 타입은 RetentionPolicy

```java
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
    public @interface AnnotationName {
}
```

</br>

## 어노테이션 정보를 얻기 위한 메소드

### isAnnotationPresent(Class<? extends Annotation> annotationClass)

지정한 어노테이션이 적용되었는지 여부, Class에서 호출했을 경우 상위 클래스에 적용된 경우에도 true를 리턴

### getAnnotation(Class<T> annotationClass)

지정한 어노테이션이 적용되어 있으면 어노테이션을 리턴하고 그렇지 않다면 null을 리턴, Class에서 호출했을 경우 상위 클래스에 적용된 경우에도 어노테이션을 리턴

### getAnnotations()

적용된 모든 어노테이션을 리턴, Class에서 호출했을 경우 상위 클래스에 적용된 모든 어노테이션도 모두 포함, 적용된 어노테이션이 없을 경우 길이가 0인 배열을 리턴

### getDelcaredAnnotations()

직접 적용된 모든 어노테이션을 리턴, Class에서 호출했을 경우 상위 클래스에 적용된 어노테이션은 포함되지 않는다.

<br/><br/>

> Reference

http://www.yes24.com/Product/Goods/15651484
