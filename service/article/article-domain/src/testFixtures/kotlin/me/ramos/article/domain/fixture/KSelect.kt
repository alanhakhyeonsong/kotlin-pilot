package me.ramos.article.domain.fixture

import org.instancio.Select
import org.instancio.TargetSelector
import kotlin.reflect.KProperty1
import kotlin.reflect.jvm.javaField

/**
 * Kotlin용 Instancio Select 유틸리티 클래스
 * Kotlin 프로퍼티 참조를 Instancio TargetSelector로 변환합니다.
 *
 * @author HakHyeon Song
 */
class KSelect {
    companion object {
        fun <T, V> field(property: KProperty1<T, V>): TargetSelector {
            val field = property.javaField!!
            return Select.field(field.declaringClass, field.name)
        }
    }
}
