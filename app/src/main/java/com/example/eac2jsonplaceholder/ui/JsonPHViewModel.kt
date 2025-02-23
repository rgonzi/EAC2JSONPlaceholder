import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.eac2jsonplaceholder.JsonPHApplication
import com.example.eac2jsonplaceholder.data.JsonPHRepository
import com.example.eac2jsonplaceholder.network.Post
import com.example.eac2jsonplaceholder.network.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface DataState<out T> {
    data class Success<out T>(val data: T) : DataState<T>
    object Error : DataState<Nothing>
    object Loading : DataState<Nothing>
}

class JsonPHViewModel(private val jsonPHRepository: JsonPHRepository) : ViewModel() {
    private val _usersList = MutableStateFlow<List<User>>(emptyList())
    val usersList: StateFlow<List<User>> = _usersList.asStateFlow()

    private val _postList = MutableStateFlow<List<Post>>(emptyList())
    val postList: StateFlow<List<Post>> = _postList.asStateFlow()

    private val _usersState = MutableStateFlow<DataState<List<User>>>(DataState.Loading)
    val usersState = _usersState.asStateFlow()

    private val _postsState = MutableStateFlow<DataState<List<Post>>>(DataState.Loading)
    val postsState = _postsState.asStateFlow()

    init {
        getUsers()
    }

    fun getUsers() {
        viewModelScope.launch {
            try {
                _usersList.value = jsonPHRepository.getUsers()
                _usersState.value = DataState.Success(_usersList.value)
            } catch (e: IOException) {
                _usersState.value = DataState.Error
            }
        }
    }

    fun getPostsByUserId(userId: Int) {
        viewModelScope.launch {
            try {
                _postList.value = jsonPHRepository.getPosts(userId)
                _postsState.value = DataState.Success(_postList.value)
            } catch (e: IOException) {
                _postsState.value = DataState.Error
            }
        }
    }

    companion object  {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as JsonPHApplication)
                val jsonPHRepository = application.container.jsonPHRepository
                JsonPHViewModel(jsonPHRepository = jsonPHRepository)
            }
        }
    }
}