package com.shashi.onebanc.util

import android.widget.TextView

class AddRemoveItem {

    fun addItem(tvQty: TextView) : Boolean {
        val qty = tvQty.text.toString().toInt()
        tvQty.text = (qty + 1).toString()
        return true
    }

    fun removeItem(tvQty: TextView) : Boolean {
        val qty = tvQty.text.toString().toInt()
        if (qty == 0)
            return false

        tvQty.text = (qty - 1).toString()
        return true
    }
}