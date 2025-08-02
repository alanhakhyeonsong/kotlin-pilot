package me.ramos.kotlinpilot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinPilotApplication

fun main(args: Array<String>) {
    runApplication<KotlinPilotApplication>(*args)
}
