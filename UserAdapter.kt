import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.theminerboomer.R
import de.hdodenhof.circleimageview.CircleImageView

//create adapter call data into recycle
class UserAdapter(private val userArrayList: ArrayList<User>) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.boardlist, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentEmp = userArrayList[position]
        holder.tvEmpName.text = currentEmp.userName
        holder.tvEmpStage.text = currentEmp.hScore.toString()

        if(currentEmp.imageId == 1){
            holder.imgUser.setImageResource(R.drawable.avatar1)
        }
        if(currentEmp.imageId == 2){
            holder.imgUser.setImageResource(R.drawable.avatar2)
        }
        if(currentEmp.imageId == 3){
            holder.imgUser.setImageResource(R.drawable.avatar3)
        }
        if(currentEmp.imageId == 4){
            holder.imgUser.setImageResource(R.drawable.avatar4)
        }
        if(currentEmp.imageId == 5){
            holder.imgUser.setImageResource(R.drawable.avatar5)
        }
        if(currentEmp.imageId == 6){
            holder.imgUser.setImageResource(R.drawable.avatar6)
        }
        if(currentEmp.imageId == 7){
            holder.imgUser.setImageResource(R.drawable.avatar7)
        }
        if(currentEmp.imageId == 8){
            holder.imgUser.setImageResource(R.drawable.avatar8)
        }
        if(currentEmp.imageId == 9){
            holder.imgUser.setImageResource(R.drawable.avatar9)
        }

    }

    override fun getItemCount(): Int {
        return userArrayList.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvEmpName : TextView = itemView.findViewById(R.id.userN)
        val tvEmpStage : TextView = itemView.findViewById(R.id.stgNo)
        val imgUser : CircleImageView = itemView.findViewById(R.id.userImage)
    }
}