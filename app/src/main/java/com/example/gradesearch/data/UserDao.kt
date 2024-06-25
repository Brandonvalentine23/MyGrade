import androidx.room.Dao
import androidx.room.Query
import com.example.gradesearch.data.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user WHERE useremail = :email AND password = :password LIMIT 1")
    suspend fun getUser(email: String, password: String): User?
}
