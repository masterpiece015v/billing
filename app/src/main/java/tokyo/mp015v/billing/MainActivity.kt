package tokyo.mp015v.billing

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.*
import kotlin.math.ceil
import kotlin.math.floor
data class ListItem(var no : String,var price : String )

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sw1 = findViewById<Switch>(R.id.sw_tax1)
        val sw2 = findViewById<Switch>(R.id.sw_tax2)
        val btn_add = findViewById<Button>(R.id.btn_add)
        val lst_item = findViewById<ListView>(R.id.lst_item)
        val edt_price = findViewById<EditText>(R.id.edt_price)
        val txt_total = findViewById<TextView>(R.id.txt_total)

        var total = 0
        var count = 1
        val list = ArrayList<Item>()

        val adapter = ItemAdapter(this,list )
        lst_item.adapter = adapter

        //ボタンのイベント
        btn_add.setOnClickListener {
            //金額の取り出し
            var price = Integer.parseInt( edt_price.editableText.toString() )
            var tax = 8
            //消費税の計算
            if( sw1.isChecked==false ){
                if( sw2.isChecked==false ){
                    tax = 8
                    price = ceil(price * 1.08 ).toInt()
                }else{
                    tax = 10
                    if( (price * 1.1) - floor(price*1.1) >= 0.1 ){
                        price = ceil(price * 1.1 ).toInt()
                    }else{
                        price = floor( price * 1.1).toInt()
                    }
                }
            }
            //合計金額の計算
            total = total + price

            //リストへの追加
            list.add(Item(count.toLong(),price.toLong(),tax ))

            count = count + 1

            txt_total.text = "合計金額:${total}"

            //キボードを閉じる
            if( currentFocus != null ){
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(currentFocus.windowToken,InputMethodManager.HIDE_NOT_ALWAYS)
            }

            //Editのクリア
            edt_price.editableText.clear()
        }

    }
}
