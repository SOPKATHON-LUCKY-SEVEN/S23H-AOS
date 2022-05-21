package com.sopt.soptkathon.util

import androidx.appcompat.app.AppCompatActivity

class CustomDialog(private val context: AppCompatActivity) {
//    private lateinit var binding: DialogCreateBinding
//    private lateinit var bindingReadBinding: DialogReadBinding
//    private lateinit var bindingCreateAskBinding : DialogCreateaskBinding
//    private val dialog = Dialog(context)
//
//    fun showCreateDialog(@LayoutRes layout: Int) {
//        binding = DialogCreateBinding.inflate(context.layoutInflater)
//
//        dialog.apply {
//            requestWindowFeature(Window.FEATURE_NO_TITLE)
//            setContentView(binding.root)
//            setCancelable(false)
//        }
//        binding.tvCreateOk.setOnClickListener {
//            dialog.dismiss()
//            context.finish()
//        }
//        dialog.show()
//    }
//
//    fun showCreateAskDialog(@LayoutRes layout: Int, text : String) {
//        bindingCreateAskBinding = DialogCreateaskBinding.inflate(context.layoutInflater)
//        bindingCreateAskBinding.tvCreateAskcontent.text=text
//        Log.d(ContentValues.TAG,"CustomDialog - showCreateAskDialog() called text= ${text}")
//        dialog.apply {
//
//            requestWindowFeature(Window.FEATURE_NO_TITLE)
//            setContentView(bindingCreateAskBinding.root)
//            setCancelable(false)
//        }
//        bindingCreateAskBinding.ivCreateAskclose.setOnClickListener {
//            dialog.dismiss()
//        }
//        dialog.show()
//    }
//
//
//    fun showReadDialog(@LayoutRes layout: Int) {
//        bindingReadBinding = DialogReadBinding.inflate(context.layoutInflater)
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        dialog.setContentView(bindingReadBinding.root)
//        dialog.setCancelable(false)
//
//        bindingReadBinding.tvReadBtnok.setOnClickListener {
//            (context as ReadActivity).removeItem()
//            dialog.dismiss()
//        }
//        bindingReadBinding.tvReadBtncancel.setOnClickListener {
//            dialog.dismiss()
//        }
//
//        dialog.show()
//    }
}
