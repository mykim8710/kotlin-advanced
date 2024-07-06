package dataclass

/**
 * 데이터클래스가 필요한 이유?
 * - 객체 동등성 비교 (equals)
 * - 해시 코드 (hashCode)
 * - 객체를 문자열로 표현 (toString)
 * - 불변성을 유지하며 복사 (copy)
 * - 프로퍼티를 순서대로 가져온다(componentN)
 */


data class PersonData4(var name: String, var age: Int)

data class PersonData4_(val name: String, val age: Int)

fun main() {
    // 불변성을 유지하며 복사 (copy)
    // - 데이터 클래스의 copy()를 사용하면 객체의 불변성을 쉽게 유지할 수 있다
    // - var를 사용해 프로퍼티를 변경가능하도록 하면 불변이 아니다
    // - 불변성이 깨졌을때의 문제 점은
    // - 우선 불변성이 깨진다면 Hash 계열 자료구조에서 의도치 않은 버그가 발생할 수 있다

    val personData = PersonData4(name = "tony", age = 12)

    val set = hashSetOf(personData)    // setOf면 정상 동작
    println(set.contains(personData))  // true

    personData.name = "strange"
    println(set.contains(personData)) // false


    // 두번째로 멀티-스레드 환경에서 객체의 불변성을 유지하는 것은 동기화 처리를 줄여주고 안정성을 유지하기 위해 중요하다
    // 세번째로 불변성이 지켜지지 않으면 `스마트 캐스트`가 적용되지 않는 이슈가 존재한다.
    // 또한 유지보수 관점에서도 여러 소스에서 객체의 프로퍼티를 제 각각 변경하고 있으면 코드를 파악하는데 어려움이 있다
    // 이런 이유로 기존 객체를 수정하는 것보다 새로운 객체로 복사해서 사용하는 것이 좋다
    // copy를 사용하면 프로퍼티를 `val` 로 유지해 불변성을 유지하는데 도움이 된다
    // 원하는 프로퍼티만 변경하면서 새로운 불변 객체를 생성할 수 있다

    val personData4_ = PersonData4_(name = "tony", age = 12)
    val personData4_Copy = personData4_.copy(name= "strange")
    println(personData4_Copy.toString())  // Person(name=strange, age=12)
}