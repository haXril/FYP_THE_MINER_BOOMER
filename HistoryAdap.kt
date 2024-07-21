import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.theminerboomer.R

//create adapter call data into recycle
class HistoryAdap(private val userArrayList: ArrayList<HistoryPlayer>) :
    RecyclerView.Adapter<HistoryAdap.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.historyboard, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentEmp = userArrayList[position]
        holder.tvEmpName.text = currentEmp.levelName
        holder.tvEmpStage.text = currentEmp.playerStage.toString()

    }

    override fun getItemCount(): Int {
        return userArrayList.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvEmpName : TextView = itemView.findViewById(R.id.userN)
        val tvEmpStage : TextView = itemView.findViewById(R.id.stgNo)
    }
}