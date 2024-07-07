package extensionfunction

/**
 * [확장 함수]
 * - 코틀린은 클래스를 상속하거나 데코레이터 패턴과 같은 디자인 패턴을 사용하지 않고도 클래스를 확장할 수 있는 기능을 제공
 * - 예를들어 일반적으로 수정할 수 없는 코틀린의 표준 라이브러리에 기능을 추가하기 위해 확장을 사용할 수 있음
 * - 확장 함수는 실제론 대상 객체를 수정하지 않는다 자바 내부적으로 static 메서드를 만듬
 * - 첫번째 인자로 확장 대상 객체를 사용
 */

// Top Level에 선언
// 확장 함수 내부의 this는 확장의 대상이 되는 객채의 참조이다 (이런한 것을 receiver 혹은 수신자 객체라고 부른다)
fun String.first(): Char {
    return this[0]
}

fun String.addFirst(char: Char): String {
    return char + this.substring(0)
}


class MyExample {
    fun printMessage() {
        println("클래스 출력 : 클래스의 멤버 함수")
    }
}

fun MyExample.printMessage() {
    println("클래스 출력 : 확장함수")
}

fun MyExample.printMessage(message: String) {
    println(message)
}

fun MyExample?.printNullOrNotNull() {
    if (this == null) {
        println("널인 경우에만 출력")
    } else {
        println("널이 아닌 경우에만 출력")
    }
}

fun main() {
    println("ABCD".first())
    println("ABCD".addFirst('Z'))

    // 확장하려는 클래스에 동일한 명칭의 함수가 존재할 경우 클래스의 멤버 함수가 우선
    MyExample().printMessage()

    // 함수의 시그니처가 다른 경우는 문제 없이 확장 기능을 사용할 수 있음
    MyExample().printMessage("test")

    var myExample: MyExample? = null
    myExample.printNullOrNotNull()  // 널인 경우에만 출력

    myExample = MyExample()
    myExample.printNullOrNotNull()  // 널이 아닌 경우에만 출력
}
