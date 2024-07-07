package pairanddestructuring

fun add(a: Int, b: Int): Int {
    return a + b
}

fun main() {
    println(add(1, 2))

    // 이와 같이 튜플을 사용하면 튜플 하나의 인자에 여러 원소를 포함할 수 있다
    println(plus(Tuple(1, 2)))

    // 페어는 불변이다
    val pair = Pair("A", 1)
    // pair.first = "B"// 컴파일 에러
    println(pair)
    printPair(pair)

    // toList 를 사용해 요소를 불변 리스트로 만들 수 있다
    val toList = pair.toList()
    println(toList)

    val newPair = pair.copy(first = "B", second = 2)
    println(newPair)
    printPair(newPair)


    // 페어가 2개의 요소를 가질 수 있다면 트리플은 3개의 요소를 가질 수 있는 튜플
    // 첫번째인자는 first 두번째인자는 second  세번째인자는 third를 사용할 수 있다
    // - 페어의 모든 특성을 동일하게 가지고 있다
    // - 불변성
    // - copy(), componentN()
    val triple = Triple("A", "B", "C")
    println(triple)



    // [구조분해할당]
    // 구조분해할당을 사용하면 값을 분해해서 한번에 여러 변수를 초기화할 수 있다
    val newTriple = triple.copy(third = "D")
    val (a, b, c) = newTriple

    println("$a $b $c")


    // 구조분해할당은 for loop에서도 유용하게 사용
    // 사실 map을 초기화할때 사용하는 to는 내부적으로 페어를 사용
    var map = mutableMapOf("이상훈" to "개발자")

    // 따라서 아래와 같이 바꿀 수 있다
    map = mutableMapOf(Pair("이상훈","개발자"))

    for ( (key, value) in map) {
        println("${key}의 직업은 $value")
    }

}

// 함수 정의를 기반으로 코드를 작성하면 튜플이라는 클래스를 만들어 사용
class Tuple(val a : Int, val b : Int) {}

fun plus(tuple: Tuple) : Int {
    return tuple.a + tuple.b
}

// 코틀린은 페어를 통해 2개의 요소가 있는 튜플을 기본 제공
// 첫번째인자는 first 두번째인자는 second 로 사용
fun plus(pair: Pair<Int, Int>) : Int {
    return pair.first + pair.second
}

fun printPair(pair: Pair<String, Int>) {
    println("${pair.first} ${pair.second}")
}