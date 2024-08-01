package com.toy.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.util.TimeZone
import javax.annotation.PostConstruct

/**
 *  @PostConstruct <-> @PreDestroy
 *  - 의존성 주입이 이루어진 후 초기화를 수행하는 메서드
 *  - WAS가 뜰 때 한 번만 기본값을 세팅해두고 두고 두고 사용
 *  - Bean의 생애주기에서 오직 한 번만 수행된다는 것을 보장
 */
@SpringBootApplication
class ToyProjectApplication {
    @PostConstruct
    fun started() {
        // timezone UTC 셋팅
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
    }
}

fun main(args: Array<String>) {
    runApplication<ToyProjectApplication>(*args)
}