package dataclass

/**
 * 데이터클래스가 필요한 이유?
 * - 객체 동등성 비교 (equals)
 * - 해시 코드 (hashCode)
 * - 객체를 문자열로 표현 (toString)
 * - 불변성을 유지하며 복사 (copy)
 * - 프로퍼티를 순서대로 가져온다(componentN)
 */


class Person2(val name: String, val age: Int) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Person2

        if (name != other.name) return false
        if (age != other.age) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + age
        return result
    }

}

data class PersonData2(val name: String, val age: Int)

fun main() {
    // 해시 코드 (hashCode)
    // equals를 재정의할때 반드시 hashCode도 재정의한다
    // JVM 언어 기준으로 객체 비교시 equals로 true를 반환하는 객체는 hashCode도 같아야한다
    // 즉 equals가 true인데 hashCode가 다르다면 Hash 계열 자료구조에서 정상적으로 동작하지 않는다

    val person1 = Person2(name = "tony", age = 12)
    val person2 = Person2(name = "tony", age = 12)
    println(person1 == person2) // true

    val set = hashSetOf(person1)
    println(set.contains(person2)) // false, hashCode 구현시 true


    val personData1 = PersonData2(name = "tony", age = 12)
    val personData2 = PersonData2(name = "tony", age = 12)
    println(personData1 == personData2) // true

    val setData = hashSetOf(personData1)
    println(setData.contains(personData2)) // true
}