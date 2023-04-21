package behavioural

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test


interface HandlerChain {
    fun addHeader(inputHeader: String): String
}

class AuthenticationHeader(private val token: String?, var next: HandlerChain? = null) : HandlerChain {
    override fun addHeader(inputHeader: String): String =
        "$inputHeader\nAuthorization: $token"
            .let { next?.addHeader(it) ?: it }
}

class ContentTypeHeader(private val contentType: String, var next: HandlerChain? = null) : HandlerChain {
    override fun addHeader(inputHeader: String): String =
        "$inputHeader\nContentType: $contentType"
            .let { next?.addHeader(it) ?: it }

}

class BodyPayloadHeader(private val body: String, val next: HandlerChain? = null) : HandlerChain {
    override fun addHeader(inputHeader: String): String =
        "$inputHeader\n$body"
            .let { next?.addHeader(it) ?: it }
}

class ChainOResponsibilityTest {
    @Test
    fun testChainOResponsibility() {
        val authenticationHeader = AuthenticationHeader("123456")
        val contentTypeHeader = ContentTypeHeader("json")
        val bodyPayloadHeader = BodyPayloadHeader("Body: {\"username\" = \"john\"}")

        authenticationHeader.next = contentTypeHeader
        contentTypeHeader.next = bodyPayloadHeader

        val messageWithAuthentication = authenticationHeader.addHeader("Header with authentication")
        println(messageWithAuthentication)

        println("------------------")

        val messageWithoutAuthentication = contentTypeHeader.addHeader("Header without authentication")
        println(messageWithoutAuthentication)

        Assertions.assertThat(messageWithAuthentication).isEqualTo(
            """
                Header with authentication
                Authorization: 123456
                ContentType: json
                Body: {"username" = "john"}
            """.trimIndent()
        )

        Assertions.assertThat(messageWithoutAuthentication).isEqualTo(
            """
                Header without authentication
                ContentType: json
                Body: {"username" = "john"}
            """.trimIndent()
        )
    }
}