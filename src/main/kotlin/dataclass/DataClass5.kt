package dataclass

/**
 * 데이터클래스가 필요한 이유?
 * - 객체 동등성 비교 (equals)
 * - 해시 코드 (hashCode)
 * - 객체를 문자열로 표현 (toString)
 * - 불변성을 유지하며 복사 (copy)
 * - 프로퍼티를 순서대로 가져온다(componentN)
 */

data class PersonData5(val name: String, val age: Int)

fun main() {
    // 프로퍼티를 순서대로 가져온다(componentN)
    // componentN은 데이터 클래스에 정의된 프로퍼티를 순서대로 가져올 수 있다
    val personData = PersonData5(name = "tony", age = 12)
    println("이름=${personData.component1()}, 나이=${personData.component2()}")  // 이름=tony, 나이=12

    // 구조분해할당 을 사용해 좀 더 쉽고 안전하게 변수를 선언할 수 있다
    val (name, age) = personData
    println("이름=${name}, 나이=${age}")  // 이름=tony, 나이=12


}