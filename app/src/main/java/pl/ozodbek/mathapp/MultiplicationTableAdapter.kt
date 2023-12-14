package pl.ozodbek.mathapp

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class MultiplicationTableAdapter(
    context: Context,
    private val value: Int,
    private var multiplier: Int
) :
    ArrayAdapter<Int>(context, R.layout.item_multiplication_table) {

    @SuppressLint("SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_multiplication_table, parent, false)
        val textView = view.findViewById<TextView>(R.id.listOfItems)

        textView.text = "${(position + 1)}  x  $multiplier  =  ${((position + 1) * multiplier)}"

        return view
    }

    fun setMultiplier(multiplier: Int) {
        this.multiplier = multiplier
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return value
    }


}