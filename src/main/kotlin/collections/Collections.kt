package collections

import java.util.*
import kotlin.collections.ArrayList

/**
 * 코틀린 표준 라이브러리는 기본 컬렉션 타입인 List, Set, Map 을 제공
 * - 컬렉션은 두가지 종류로 나뉜다
 *   - 불변 컬렉션(Immutable) : 읽기 전용 컬렉션
 *   - 가변 컬렉션(Mutable) : 삽입, 수정, 삭제와 같은 쓰기 작업이 가능한 컬렉션
 */

fun main() {
    // 컬렉션을 생성할때 가장 일반적인 방법은 표준 라이브러리 함수를 사용

    // [List]
    // Immutable 리스트 생성 : 불변
    val immutableNumberList : List<Int> = listOf(1, 2, 3, 4, 5)

    // Mutable 리스트 생성 : 가변
    val mutableCurrencyList = mutableListOf<String>()
    mutableCurrencyList.add("달러")
    mutableCurrencyList.add("유로")
    mutableCurrencyList.add("원")

    // apply 함수를 사용하면 가독성이 좋아짐
    val mutableCurrencyList2 = mutableListOf<String>().apply {
        add("달러")
        add("유로")
        add("원")
    }


    // [Set]
    // Immutable Set 생성 : 불변
    val immutableNumberSet = setOf(1, 2, 3, 4)

    // Mutable Set 생성 : 불변
    val mutableSet = mutableSetOf<Int>()
    mutableSet.add(1)
    mutableSet.add(2)
    mutableSet.add(3)
    mutableSet.add(4)


    // [Map]
    // Immutable Map 생성 : 불변
    // to 라는 중위 함수로 key - value 구조를 전달
    val immutableMap = mapOf("one" to 1, "two" to 2, "three" to 3, "four" to 4)

    // Mutable Set Map : 불변
    val mutableMap = mutableMapOf<String, Int>()
    mutableMap["one"] = 1
    mutableMap["two"] = 2
    mutableMap["three"] = 3
    mutableMap["four"] = 4


    // ================================================================================


    // 컬렉션 빌더를 사용하여 컬렉션을 생성할 수 있음
    // - buildList, buildSet, buildMap 3종류를 제공
    // - build 내부에선 Mutable, 반환시엔 Immutable 불변

    val buildList : List<Int> = buildList {
        add(1)
        add(2)
        add(3)
    }

    // liked List
    val linkedList = LinkedList<Int>().apply {
        addFirst(1)
        add(2)
        addLast(3)
    }

    // array List
    val arrayList = ArrayList<Int>().apply {
        addFirst(1)
        add(2)
        addLast(3)
    }

    val buildSet : Set<String> = buildSet {
        add("one")
        add("two")
        add("three")
    }

    val buildMap : Map<String, Int> = buildMap {
        "one" to 1
        "two" to 2
        "three" to 3
    }


    // ================================================================================


    // 코틀린의 컬렉션은 Iterable 의 구현체이므로 순차적 반복이 가능
    val iterator = mutableCurrencyList2.iterator()
    while (iterator.hasNext()) {
        println(iterator.next())
    }

    // 자바에선 `foreach` 를 사용하면 Iterable을 구현한 컬렉션을 반복할 수 있음
    // 코틀린은 앞서 학습한 `for loop` 를 사용하면 암시적으로 이터레이터를 사용하기 때문에 좀 더 쉽게 반복할 수 있음
    for (currency in mutableCurrencyList) {
        println(currency)
    }


    // 코틀린 표준 라이브러리에는 컬렉션 사용시 자주 사용되는 패턴인 forEach, map, filter 와 같은 유용한 인라인 함수를 제공
    mutableCurrencyList.forEach {
        println(it)
    }


    // for loop를 map으로 변환하기 전
    val lowerList = listOf("a", "b", "c", "d")
    val upperList = mutableListOf<String>()

    for (lowerCase in lowerList) {
        upperList.add(lowerCase.uppercase())
    }

    println(upperList)


    // for loop를 map으로 변환 후
    val upperList2 = lowerList.map { it.uppercase() }
    println(upperList2)


    // for loop를 filter로 변환하기 전
    val filteredList = mutableListOf<String>()
    for (upperCase in upperList) {
        if (upperCase == "A" || upperCase == "C") {
            filteredList.add(upperCase)
        }
    }
    println(filteredList)

    // for loop를 filter로 변환 후
    val filteredList2 = upperList.filter {
        it == "A" || it == "C"
    }
    println(filteredList2)


    // 코틀린에서는 sequence 를 사용해 자바8 스트림과 같이 Lazy하게 동작시킬 수 있음
    val filteredList3 = upperList
                        .asSequence()
                        .filter { it == "A" || it == "C" }
    println(filteredList3)


    // 시퀀스 API도 최종 연산자를 사용해야 중간 연산자가 동작
    val filteredList4 = upperList
                        .asSequence()
                        .filter { it == "A" || it == "C" }
                        .toList()
    println(filteredList4)

    /**
     * - 일반적으로 인라인 함수는 각각 함수가 `동작할때마다` 조건에 맞는 컬렉션을 생성
     * - 시퀀스 API는 각각의 함수가 동작할때 시퀀스를 생성하고 최종 연산자를 호출할때 `1` 개의 컬렉션을 생성
     * - 벤치마크상으로 일반적으론 인라인 함수가 빠르기 때문에 인라인 함수를 쓰고 대량의 데이터를 다룰때는 시퀀스 API를 사용하길 추천
     */

}