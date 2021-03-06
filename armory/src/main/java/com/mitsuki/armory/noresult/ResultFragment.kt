package com.mitsuki.armory.noresult

import android.content.Intent
import android.os.Bundle
import android.util.SparseArray
import androidx.fragment.app.Fragment

class ResultFragment : Fragment() {

    private val callbacks =
        SparseArray<(requestCode: Int, resultCode: Int, data: Intent?) -> Unit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    /******************************************************************************************************************/

    fun startActivityForResult(
        intent: Intent,
        requestCode: Int,
        callback: (requestCode: Int, resultCode: Int, data: Intent?) -> Unit
    ) {
        callbacks.put(requestCode, callback)
        startActivityForResult(intent, requestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbacks.get(requestCode)?.invoke(requestCode, resultCode, data)
        callbacks.remove(requestCode)
    }
}