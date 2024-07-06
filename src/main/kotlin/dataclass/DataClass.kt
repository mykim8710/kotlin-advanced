package dataclass

/**
 * 데이터 클래스란?
 * - 데이터를 보관하거나 전달하는 목적을 가진 객체를 만들때 사용한다 사용 (e.g. Java Spring 의 dto)
 * - 데이터 클래스를 사용하면 컴파일러가 자동으로 만들어주는 함수들이 있다
 *  - `equals()`
 *  - `hashCode()`
 *  - `toString()`
 *  - `componentN()`
 *  - `copy()`
 * - 기존 자바에선 주로 Lombok을 사용
 */


/**
 * 데이터클래스가 필요한 이유?
 * - 객체 동등성 비교 (equals)
 * - 해시 코드 (hashCode)
 * - 객체를 문자열로 표현 (toString)
 * - 불변성을 유지하며 복사 (copy)
 * - 프로퍼티를 순서대로 가져온다(componentN)
 */

class Person(val name: String, val age: Int) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Person

        if (name != other.name) return false
        if (age != other.age) return false

        return true
    }
}

data class PersonData(val name: String, val age: Int)

fun main() {
    // 객체 동등성 비교 (equals)
    // 객체 동등성 비교 (equals) 일반적으로 두개의 인스턴스의 동등성 비교를 위해 equals를 재정의한다
    // 객체의 동등성 비교시 결과에 대한 차이 일반 클래스
    val person = Person(name = "mykim", age = 38)
    val person2 = Person(name = "mykim", age = 38)
    println(person == person2)  // false

    val personData = PersonData(name = "mykim", age = 38)
    val personData2 = PersonData(name = "mykim", age = 38)
    println(personData == personData2)  // true
}