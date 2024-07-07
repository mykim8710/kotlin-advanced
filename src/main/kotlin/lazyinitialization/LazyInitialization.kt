package lazyinitialization


/**
 * [지연초기화]
 * - 대상에 대한 초기화를 미뤘다가 실제 사용시점에 초기화하는 기법
 * - 초기화 과정에서 자원을 많이 쓰거나 오버헤드가 발생할 경우 지연초기화를 사용하는게 유리
 * - 지연초기화 쓰임 예시
 *   - 웹페이지에서 특정 스크롤에 도달했을때 컨텐츠를 보여주는 무한 스크롤
 *   - 싱글톤 패턴의 지연초기화
 *   - JPA의 엔티티 LazyLoading 기능
 *  - 코틀린은 두가지 다른 방식의 지연초기화를 제공
 */

class HelloBot {
    // 나중에 값을 수정할 수 있게 var로 선언하여 가변으로 만들어야한다
    // 데이터클래스에서 봤듯이 var로 선언하는 것은 몇 가지 위험성을 가지고 있으므로 될수 있으면 불변으로 유지하는 것이 좋다
    // var greeting: String? = null

    // 코틀린에서 제공하는 by lazy 를 사용하면 불변성을 유지하면서 지연초기화가 가능
    val greeting: String by lazy {
        // by lazy를 사용하면 사용 시점에 1회만 초기화 로직이 동작한다
        println("초기화 로직 수행")
        getHello()
    }

    fun sayHello() {
        println(greeting)
    }
}

fun getHello(): String {
    return "안녕하세요~~"
}

fun main() {
    val helloBot = HelloBot()

    //helloBot.greeting = getHello()
    helloBot.sayHello()
    helloBot.sayHello()

    // by lazy는 기본적으로 멀티-스레드 환경에서도 안전하게 동작하도록 설계
    // - by lazy 내부에서 스레드에 대한 동기화처리를 해주기 때문
    // - 기본 모드는 `LazyThreadSafetyMode.*SYNCHRONIZED*` 과 동일
    // - 만약 `LazyThreadSafetyMode.*NONE`* 모드로 변경한 뒤 멀티-스레드 환경에서 실행하면 > 실행할때마다 결과가 계속 달라지는 것을 볼 수 있다
    // - 멀티-스레드 환경이 아닌 경우에는 동기화 작업이 오버헤드가 될 수 있으므로 `NONE` 모드를 사용하고
    // - 멀티-스레드 환경이어도 동기화가 필요하지 않은 경우라면 `PUBLICATION` 모드를 사용

}

/**
 * lateinit
 * - 가변 프로퍼티에 대한 지연초기화가 필요한 경우가 있다
 * - 특정 프레임워크나 라이브러리에서 지연초기화가 필요한 경우
 * - 예를 들어 테스트 코드를 작성할때 특정 애노테이션에 초기화 코드를 작성해야하는 경우가 있다
 * class `7_LateExample` {
 *
 *     @Autowired
 *     lateinit var service: TestService
 *
 *     lateinit var subject: TestTarget
 *
 *     @SetUp
 *     fun setup() {
 *         subject = TestTarget()
 *     }
 *
 *     @Test
 *     fun test() {
 *         subject.doSomething()
 *     }
 * }
 */


