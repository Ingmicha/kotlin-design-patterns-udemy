package behavioural

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

sealed class AuthorizationState

object UnAuthorized : AuthorizationState()

class Authorized(val userName: String) : AuthorizationState()

class AuthorizationPresenter {
    var state: AuthorizationState = UnAuthorized

    val isAuthorized: Boolean
        get() = when (state) {
            is Authorized -> true
            is UnAuthorized -> false
        }

     val userName: String
        get() {
            return when (val state = this.state) {
                is Authorized -> state.userName
                is UnAuthorized -> "Unknown"
            }
        }

    fun loginUser(userName: String) {
        state = Authorized(userName)
    }

    fun logoutUser(userName: String) {
        state = UnAuthorized
    }

    override fun toString(): String = "User $userName is logged in: $isAuthorized"
}

class StateTest {
    @Test
    fun testState(){
        val authorizationPresenter = AuthorizationPresenter()
        authorizationPresenter.loginUser("admin")
        println(authorizationPresenter)
        Assertions.assertThat(authorizationPresenter.isAuthorized).isEqualTo(true)
        Assertions.assertThat(authorizationPresenter.userName).isEqualTo("admin")

        authorizationPresenter.logoutUser("admin")
        println(authorizationPresenter)
        Assertions.assertThat(authorizationPresenter.isAuthorized).isEqualTo(false)
        Assertions.assertThat(authorizationPresenter.userName).isEqualTo("Unknown")
    }
}