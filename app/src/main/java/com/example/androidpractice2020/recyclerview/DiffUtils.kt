import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil
import com.example.androidpractice2020.recyclerview.Note

class DiffUtils (
    private val oldList: ArrayList<Note>,
    private val newList: ArrayList<Note>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int =
        oldList.size

    override fun getNewListSize(): Int =
        newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]
}