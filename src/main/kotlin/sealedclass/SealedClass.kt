package sealedclass

/**
 * 실드 클래스를 쓰는 이유
 *  - 하나의 상위 클래스 또는 인터페이스에서 하위 클래스 정의를 제한할 수 있는 방법
 */

// 먼저 Developer라는 추상클래스를 상속받은 BackendDeveloper, FrontendDeveloper 클래스가 있다
abstract class Developer {
    abstract val name: String
    abstract fun code(language: String)
}


data class BackendDeveloper(override val name: String) : Developer() {
    override fun code(language: String) {
        println("저는 백엔드 개발자 $name 입니다 ${language}를 사용합니다.")
    }
}

data class FrontendDeveloper(override val name: String) : Developer() {
    override fun code(language: String) {
        println("저는 프론트엔드 개발자 $name 입니다 ${language}를 사용합니다")
    }
}

// 그다음은 생성된 Developer 객체를 보관하는 DeveloperPool을 만든다
object DeveloperPool {
    var pool = mutableMapOf<String, Developer>()

    fun add(developer: Developer) = when(developer) {
        is BackendDeveloper -> pool[developer.name] = developer
        is FrontendDeveloper -> pool[developer.name] = developer

        // 만약 else를 삭제하면 -> 컴파일 오류
        // 컴파일러 입장에선 Developer를 상속한 하위클래스가 몇개나 되는지 어떤 클래스가 상속받고 있는지 알 수 있는 방법이 없다
        else -> {
            println("지원하지 않는 개발자입니다.")
        }
    }

    fun getDeveloper(name: String) = pool[name]
}

fun main() {
    var beName = "mykim"
    val backendDeveloper = BackendDeveloper(name = beName)
    DeveloperPool.add(backendDeveloper)
    backendDeveloper.code("Java & Kotlin")

    var feName = "jk"
    val frontendDeveloper = FrontendDeveloper(name = feName)
    DeveloperPool.add(frontendDeveloper)
    frontendDeveloper.code("Javascript")

    println(DeveloperPool.getDeveloper(beName))
    println(DeveloperPool.getDeveloper(feName))
}