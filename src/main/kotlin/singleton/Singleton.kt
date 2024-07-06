package singleton

import java.time.LocalDateTime

/**
 * 싱글톤 패턴은 클래스의 인스턴스를 하나의 단일 인스턴스로 제한하는 디자인 패턴이다
 * 싱글톤 패턴을 구현할때는 몇가지 제약사항을 통해 구현한다
 * - 직접 인스턴스화 하지 못하도록 생성자를 private 으로 숨긴다
 * - getInstance() 라는 클래스의 단일 인스턴스를 반환하는 static 메서드를 제공한다
 * - 멀티-스레드 환경에서도 안전하게 유일한 인스턴스를 반환해야한다
 *
 *
 * 코틀린은 언어에서 객체 선언을 통해 싱글톤을 기본 지원한다
 * - 객체 선언은 object 키워드를 사용한다
 */

object Singleton {
    val a = 1234
    fun printA() = println(a)
}

// 객체 선언을 사용하면 자바의 static 유틸리티를 대신해 쉽게 싱글톤 기반의 유틸리티를 만들 수 있다
object DatetimeUtils {
    // const : java 의 상수
    const val DEFAULT_FORMAT = "YYYY-MM-DD"

    val now: LocalDateTime
        get() {
            return LocalDateTime.now()
        }

    fun same(a: LocalDateTime, b: LocalDateTime) :Boolean {
        return a == b
    }
}

fun main() {
    // 함수나 변수를 사용할때는 클래스 한정자를 사용한다 (클래스명.함수명)
    println(Singleton.a)
    Singleton.printA()


    println(DatetimeUtils.now)
    println(DatetimeUtils.now)
    println(DatetimeUtils.now)
    println(DatetimeUtils.DEFAULT_FORMAT) // YYYY-MM-DD

    val now = LocalDateTime.now()
    println(DatetimeUtils.same(now, now)) // true
}