import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun authenticate(email: String, password: String) {
        // Example of asynchronous authentication logic (replace with your actual implementation)
        if (email == "test@example.com" && password == "password") {
            _loginResult.value = LoginResult.Success // Authentication successful
        } else {
            _loginResult.value = LoginResult.Error("Invalid email or password")
        }
    }
}

sealed class LoginResult {
    object Success : LoginResult()
    data class Error(val errorMessage: String) : LoginResult()
}
