import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.snailpasswordmanager.R
import com.example.snailpasswordmanager.databinding.ChatItemBinding
import com.example.snailpasswordmanager.databinding.SmileItemBinding
import com.example.snailpasswordmanager.domain.model.SmileMessageEntity

class SmileChatAdapter: RecyclerView.Adapter<SmileChatAdapter.SmileChatViewHolder>() {

    var list = ArrayList<SmileMessageEntity>()
        set(value) {
            field.clear()
            field.addAll(value)
            notifyDataSetChanged()
        }

    fun setSmileMessage(l: List<SmileMessageEntity>){
        list.clear()
        list.addAll(l)
        Log.d("test",list.size.toString())
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SmileChatViewHolder {
        //TODO("Not yet implemented")
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.smile_item,
            parent,
            false
        )
        Log.d("test","onCreateViewHolder")

        return SmileChatViewHolder(view)
    }

    override fun onBindViewHolder(holder: SmileChatViewHolder, position: Int) {
        //TODO("Not yet implemented")
        Log.d("test","onBindViewHolder")
        holder.bind(list.get(position))


    }


    override fun getItemCount(): Int {
        return list.size
        //TODO("Not yet implemented")
    }




    class SmileChatViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = SmileItemBinding.bind(view)


        fun bind(smileMessageEntity: SmileMessageEntity) = with(binding){
            binding.textView.setText("${smileMessageEntity.sender_id} : ${smileMessageEntity.emo}")
          //textView2.setText("test")
        }
    }
}