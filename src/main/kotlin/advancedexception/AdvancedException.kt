package advancedexception

import java.io.FileWriter

fun main() {
    // use를 사용한 리소스 해제
    // 코틀린은 try-catch-resources 구문을 제공하지 않지만 use 라는 확장 함수를 제공
    FileWriter("test.txt").use {
        it.write("hello kotlin")
    }


    // runCatching을 사용해 우아하게 예외처리
    // - 코틀린은 try-catch를 통한 예외처리외에도 함수형 스타일의 `Result 패턴`을 구현한 `runCatching`을 제공
    // - Result 패턴이란 함수가 성공하면 캡슐화된 결과를 반환하거나 예외가 발생하면 지정한 작업을 수행하는 패턴
    // 기본형식
    val result = try {
        throwException()
    } catch (e: Exception) {
        println(e.message)  // 예외 발생
        "default value"
    }

    println(result) // default value

    // runCatching 사용
    val result2 = runCatching { throwException() }
        .getOrElse {
            println(it.message)
            "기본값"
        }

    println(result2)

    // getOrNull : 실패인 경우 null을 반환
    val orNull = runCatching { throwException() }.getOrNull()
    println(orNull)

    // exceptionOrNull : 성공인 경우 null을 반환하고 실패인 경우 Throwable을 반환
    val exceptionOrNull = runCatching { throwException() }.exceptionOrNull()
    println(exceptionOrNull)

    // getOrDefault : 성공시엔 성공 값을 반환하고 실패시엔 지정한 기본 값을 반환
    val orDefault = runCatching { throwException() }.getOrDefault("기본값")
    println(orDefault)

    // getOrElse : 실패시 수신자 객체로 Throwable을 전달 받고 let, run과 같이 함수의 결과를 반환한다
    val orElse = runCatching { throwException() }.getOrElse {
        println(it.message)
        "기본값"
    }
    println(orElse)

    // getOrThrow : 성공시엔 값을 반환 실패시엔 예외를 발생시킨다
    val orThrow = runCatching { throwException() }.getOrThrow()

    // map : 성공인 경우 원하는 값으로 변경할 수 있다
    val map = runCatching { "안녕" }.map {
        it + "하세요"
    }.getOrThrow()
    println(map)

    // mapCatching : map 처럼 성공인 경우 원하는 값으로 변경할 수 있다 예외가 발생하면 재처리가능
    val mapCatching = runCatching { "안녕" }.mapCatching {
        throwException()
    }.getOrDefault("기본값")
    println(mapCatching)

    // recover : map은 성공시에 원하는 값으로 변경하지만 recover는 실패시에 원하는 값으로 변경할 수 있다
    val recoverSuccess = runCatching { "정상" }
        .recover {
            "복구"
        }.getOrNull()

    println(recoverSuccess)

    // 실패시
    val recoverFail = runCatching { throwException() }
        .recover {
            "복구"
        }.getOrNull()

    println(recoverFail)

    // recoverCatching : recoverCatching내에서 예외가 발생할 경우 재처리 가능
    // recoverCatching에서 예외가 발생한 경우
    val recoverCatching = runCatching { throwException() }
        .recoverCatching {
            throwException()
        }.getOrDefault("복구")

    println(recoverCatching)
}

fun throwException(): Nothing {
    throw Exception("예외 발생")
}

