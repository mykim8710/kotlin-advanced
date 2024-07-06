package companion

// companion 키워드를 사용해 클래스 내부에 객체 선언을 사용할 수 있다

class MyClass {
    // 생성자를 private으로 숨기고 newInstance 함수를 통해서만 객체 생성을 가능하게 함
    private constructor()

    // 동반객체는 이름을 가질 수 있다
    companion object MyCompanion {
        val a = 1234
        fun newInstance() = MyClass()
    }
}

fun main() {
    // 동반객체의 멤버는 object로 선언한 객체와 마찬가지로 클래스 한정자를 사용해 호출할 수 있다
    println(MyClass.a)
    println(MyClass.newInstance())

    // 이렇게도 가능하나 생략 가능
    println(MyClass.MyCompanion.a)
    println(MyClass.MyCompanion.newInstance())
}