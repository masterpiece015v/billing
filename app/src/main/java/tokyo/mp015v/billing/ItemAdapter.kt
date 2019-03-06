package tokyo.mp015v.billing

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.TextView

data class ViewHolder(val item_no : TextView, val item_price : TextView, val item_tax : TextView)

class ItemAdapter(context : Context, items : List<Item>) : ArrayAdapter<Item>(context,0,items){

    val layoutInflater : LayoutInflater= context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater


    //override fun getItem(p0: Int): Any {
    //    return itemList.get(p0)
    //}

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var view = p1
        var holder : ViewHolder?

        if( view == null ){
            view = layoutInflater.inflate(R.layout.item,p2,false)
            holder = ViewHolder(
                    view.findViewById(R.id.item_no),
                    view.findViewById(R.id.item_price),
                    view.findViewById(R.id.item_tax)
            )
            view.tag = holder
        }else{
            holder = view.tag as ViewHolder
        }

        val listItem = getItem(p0) as Item
        holder.item_no.text = "No:" + listItem.item_no.toString()
        holder.item_price.text = "税込み:" + listItem.item_price.toString()
        holder.item_tax.text = "消費税率:" + listItem.item_tax.toString()

        return view!!
    }
}