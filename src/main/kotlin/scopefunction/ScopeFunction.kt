package scopefunction

/**
 * 스코프 함수
 * - 코틀린의 표준 라이브러리에는 객체의 컨텍스트 내에서 코드 블록을 실행하기 위해서만 존재하는 몇가지 함수가 포함되어 있는데 이를 `스코프 함수` 라고 부른다
 * - 스코프 함수를 제대로 사용하면 불필요한 변수 선언이 없어지며 코드를 더 간결하고 읽기 쉽게 만든다
 * - 스코프 함수의 코드 블록 내부에서는 `변수명을 사용하지 않고도` 객체에 접근할 수 있는데 그 이유는 수신자 객체에 접근할 수 있기 때문이다
 * - 수신자 객체는 람다식 내부에서 사용할 수 있는 객체의 참조이다
 * - 스코프 함수를 사용하면 수신자 객체에 대한 참조로 `this`  또는 `it` 를 사용한다
 *
 *
 * 코틀린은 총 5개의 유용한 스코프 함수를 제공
 * [함수명]     [수신자 객체 참조 방법]     [반환 값]     [확장 함수 여부]
 * let	            it	             함수의 결과	     O
 * run	            this	         함수의 결과	     O
 * with	            this	         함수의 결과	     X
 * apply	        this	         컨텍스트 객체	     O
 * also	            it	             컨텍스트 객체	     O
 *
 *
 * 스코프 함수 사용시 유의할 점
 * - 스코프 함수는 모두 기능이 유사하기 때문에 실무에선 섞어쓰는 경우도 많음
 * - this는 키워드, 키워드는 사전에 정의된 예약어이기 때문에 다른 의미로 사용할 수 없지만 it은 특정 용도에서만 작동하는 소프트 키워드이기 때문에 다른 용도로 사용할 수 있음
 * - 중첩으로 사용하는 경우 this, it에 대해 혼동하기 쉽다
 * - 또한 중첩 함수내에서 외부 함수에 대한 접근을 하려면 it은 자기 자신의 참조기 때문에 불가능
 *
 */


fun main() {
    // [let]

    // null이 아닌 경우 사용될 로직을 작성하고 새로운 결과를 반환하고 싶을때 사용
    var str : String? = null
    str?.let {
        println(it)		// 아무것도 출력되지 않음
    }

    // null이 아닌 경우 실행
    var str2 : String = "test"
    str2?.let {
        println(it)     // test
    }

    // 함수의 결과를 반환 (let 함수 내부의 마지막 코드가 결과로 반환)
    val str3: String? = "안녕"

    val result = str3?.let {
        println(it)
        1234 // let 함수 마지막 코드가 결과로 반환
    }
    println(result) // 1234


    // [run]
    // 수신 객체의 프로퍼티를 구성하거나 새로운 결과를 반환하고 싶을때
    val databaseClient = DatabaseClient()
    databaseClient.url = "localhost:3306"
    databaseClient.username = "mysql"
    databaseClient.password = "1234"
    val isConnected = databaseClient.connection()
    println(isConnected)

    // run 사용
    val isConnected2 = DatabaseClient().run {
        url = "localhost:3306"
        username = "mysql"
        password = "1234"
        connection()
    }

    println(isConnected2)


    // [with]
    // 결과 반환없이 내부에서 수신 객체를 이용해 다른 함수를 호출하고 싶을때 사용
    var str_ = "안녕하세요"
    with(str_) {
        println("length : $length")
    }

    // let이나 run과 같이 함수의 결과가 반환
    var length = with(str_){
        length
    }
    println(length)

    // 다른 스코프 함수와 다른 점은 with는 확장 함수가 아님



    // [apply]
    // 수신 객체의 프로퍼티를 구성하고 수신 객체를 그대로 결과로 반환하고 싶을때 사용
    // let, run, with는 함수의 결과가 반환타입으로 변환되는데 반해서 apply는 수신 객체 그대로 반환
    val client : DatabaseClient = DatabaseClient().apply {
        url = "localhost:3306"
        username = "mysql"
        password = "1234"
        connection()
    }
    println(client) // scopefunction.DatabaseClient@4f023edb


    // [also]
    // 부수 작업을 수행하고 전달받은 수신 객체를 그대로 결과로 반환하고 싶을때 사용
    // also 미사용
    val user : User = User(name = "test", password = "1234")
    user.validate()

    // also 사용
    val alsoUser = User(name = "test", password = "1234").also {
        it.validate()
    }

}

class User(val name: String, val password: String) {

    fun validate() {
        if (name.isNotEmpty() && password.isNotEmpty()) {
            println("검증 성공!")
        } else {
            println("검증 실패!")
        }
    }

}


class DatabaseClient {
    var url: String? = null
    var username: String? = null
    var password: String? = null

    fun connection() : Boolean {
        println("DB 접속 중")

        Thread.sleep(1000)

        println("DB 접속 완료")
        return true
    }
}


