package springprinciple.core.singleton;

public class SingletonServiceTest {

    private static final SingletonServiceTest instance = new SingletonServiceTest();

    public static SingletonServiceTest getInstance() {
        return instance;
    }

    private SingletonServiceTest() {
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }

    // 1. static 영역에 객체 instance를 미리 하나 생성
    // 2. 이 객체 인스턴스가 필요하면 오직 getInstance() 메서드를 통해서만 조회가능
    // 3. 1개의 객체 인스턴스만 존재해야 하므로, 생성자를 private로 막아서 혹시라도 외부에서 new 키워드로 객체 인스턴스가 생성되는 것을 막는다.
}
