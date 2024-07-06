package sealedclass

/**
 * Sealed Class 활용 예시
 * - 실드 클래스로 만들면 새로운 하위 클래스가 생길 경우 when식에서 새로운 하위 클래스에 대한 처리를 해야한다
 *
 *
 */


sealed class Developer_ {
    abstract val name: String
    abstract fun code(language: String)
}


data class BackendDeveloper_(override val name: String) : Developer_() {
    override fun code(language: String) {
        println("저는 백엔드 개발자 $name 입니다 ${language}를 사용합니다.")
    }
}

data class FrontendDeveloper_(override val name: String) : Developer_() {
    override fun code(language: String) {
        println("저는 프론트엔드 개발자 $name 입니다 ${language}를 사용합니다")
    }
}

data class AndroidDeveloper_(override val name: String) : Developer_() {
    override fun code(language: String) {
        println("저는 안드로이드 $name 개발자입니다 ${language}를 사용합니다")
    }
}

object OtherDeveloper : Developer_() {
    override val name: String = "익명"

    override fun code(language: String) {
        TODO("Not yet implemented")
    }
}

// 그다음은 생성된 Developer 객체를 보관하는 DeveloperPool을 만든다
object DeveloperPool_ {
    var pool = mutableMapOf<String, Developer_>()

    fun add(developer: Developer_) = when(developer) {
        is BackendDeveloper_ -> pool[developer.name] = developer
        is FrontendDeveloper_ -> pool[developer.name] = developer
        is AndroidDeveloper_ -> pool[developer.name] = developer
        is OtherDeveloper -> println("지원하지않는 개발자종류입니다")
    }

    fun getDeveloper(name: String) = pool[name]
}

fun main() {
    val androidDeveloper = AndroidDeveloper_(name="안드로")
    DeveloperPool_.add(androidDeveloper)
}