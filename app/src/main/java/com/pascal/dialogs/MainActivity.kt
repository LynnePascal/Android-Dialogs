package com.pascal.dialogs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonBasic:Button=findViewById(R.id.buttonBasic)
        val buttonCustom: Button=findViewById(R.id.buttonCustom)

        buttonBasic.setOnClickListener {
            val builder= AlertDialog.Builder(this)
            builder.setTitle("Are you sure you want to delete?")
            builder.setMessage("Once you delete this photo, you won't be able to recover")
            builder.setPositiveButton("Yes"){_, _ ->
                Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show()
            }
            builder.setNegativeButton("No"){_, _->
                Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show()
            }

            //builder.create().show()
            //Make sure it is cancellable
            val alert:AlertDialog=builder.create()
            alert.setCancelable(false)
            alert.show()
        }
        buttonCustom.setOnClickListener {

            val builder= AlertDialog.Builder(this)
            val layout= layoutInflater.inflate(R.layout.custom_dialog, null)
            builder.setTitle("Interest Calculator")
            val edtPrincipal:EditText= layout.findViewById(R.id.edtPrincipal)
            val edtPeriod:EditText= layout.findViewById(R.id.edtPeriod)
            val edtRate:EditText= layout.findViewById(R.id.edtRate)

            builder.setView(layout)

            builder.setPositiveButton("Calculator"){_, _ ->
                val principal= edtPrincipal.text.toString().trim().toDoubleOrNull()
                val period= edtPeriod.text.toString().trim().toDoubleOrNull()
                val rate= edtRate.text.toString().trim().toDoubleOrNull()

                if (principal !=null && period!=null && rate !=null){
                    calculateInterest(principal, rate, period)
/*
                if (principal.isNotEmpty() && period.isNotEmpty() && rate.isNotEmpty()){
                    calculateInterest(principal.toDouble(), rate.toDouble(),period.toDouble(),2)*/
                }else{
                    Toast.makeText(this, "Invalid values", Toast.LENGTH_SHORT).show()
                }

            }
            builder.show()
        }
    }

    fun calculateInterest(principal:Double, rate:Double, period:Double, numberTimes:Int=1){
        val base=numberTimes*100
        var amount= principal*Math.pow((1*rate/100),period)
        Toast.makeText(this, "KES $amount", Toast.LENGTH_LONG).show()
        Log.d("INTEREST","$amount")

    }
}