import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

data class User(var firstName : String, var lastName : String, var  username : String, var password : String) : Serializable{
}