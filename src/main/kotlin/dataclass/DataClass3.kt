package dataclass

/**
 * 데이터클래스가 필요한 이유?
 * - 객체 동등성 비교 (equals)
 * - 해시 코드 (hashCode)
 * - 객체를 문자열로 표현 (toString)
 * - 불변성을 유지하며 복사 (copy)
 * - 프로퍼티를 순서대로 가져온다(componentN)
 */


class Person3(val name: String, val age: Int)

data class PersonData3(val name: String, val age: Int)

fun main() {
    // 객체를 문자열로 표현 (toString)
    val person = Person3(name = "tony", age = 12)
    println(person.toString()) // dataclass.Person3@1be6f5c3

    val personData = PersonData3(name = "tony", age = 12)
    println(personData) //Person(name=tony, age=12)
}