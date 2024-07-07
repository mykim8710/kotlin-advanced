package lambda

import jdk.jshell.execution.Util

/**
 * 함수형 프로그래밍 정의
 * - 함수형 프로그래밍은 혹은 FP(Functional-Programming)은 수학의 함수적 개념을 참고해 만든 패러다임의 하나로 깔끔하고 유지보수가 용이한 소프트웨어를 만들기 위해 함수를 사용
 * - 함수형 패러다임은 부수효과가 없고 똑같은 input이 들어오면 항상 동일한 output을 내놓는 순수함수의 개념을 기반으로 람다, 고차함수, 커리, 메모이제이션, 모나드 등의 개념을 포함
 */

fun main() {
    // 함수를 값으로 사용
    // val func: () -> Unit = {}
    // - 함수는 값이 될 수 있고 역으로 값은 함수가 될 수 있다 그러므로 함수에 인자로 넘기거나 데이터 구조에 저장할 수 있다
    // - 리스트에 저장 후 사용
    val list = mutableListOf(helloPrint)
    list[0]() // 함수 호출을 위해 ()를 써야한다


    // 함수는 값이므로 변수로 받아서 처리할 수 있다
    val func = list[0]
    func()


    // 다른 함수의 인자로 사용
    call(helloPrint)


    // fun으로 선언된 함수는 값으로 다룰 수 없다
    // val list2 = mutableListOf(printNo) // 컴파일 오류


    // 고차 함수 (Higher-Order Function, HOF)라고 한다 고차 함수는 함수를 인자로 받거나 결과로 돌려주는 함수를 의미
    // 컬렉션의 filter, map, forEach 등도 함수를 인자로 받아서 결과를 반환하므로 고차함수
    val list3 = listOf("a", "b", "c")
    val printStr: (String) -> Unit = { println(it) }
    forEachStr(list3, printStr)


    // 익명함수와 람다 식
    // 함수형 프로그래밍에선 이름 없는 무명 함수를 익명함수
    outerFunc()


}

fun outerFunc() : () -> Unit {
    return fun() {
        println("이것이 익명함수")
    }
}


val helloPrint: () -> Unit = {
    println("Hello")
}

fun call(block: () -> Unit) {
    block()
}

fun printNo() {
    println("No!")
}

// 인자를 받아 결과를 리턴하는 함수 값 만들기
var printMessage: (String) -> Unit = {
    //message: String -> println(message)

    // 타입생략
    //message -> println(message)

    // it 사용
    println(it)
}

// 다수의 인자를 받아 결과를 리턴하는 함수 값 만들어보기
val plus: (Int, Int) -> Int = {a, b -> a + b}



fun forEachStr(collection: Collection<String>, action: (String) -> Unit) {
    for (item in collection) {
        action(item)
    }
}