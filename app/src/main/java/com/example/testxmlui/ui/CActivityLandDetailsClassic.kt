package com.example.testxmlui.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testxmlui.R
import com.example.testxmlui.databinding.ActivityLandDetailsBinding
import com.example.testxmlui.databinding.ActivityLandListBinding
import java.util.UUID

class CActivityLandDetailsClassic           : AppCompatActivity() {
    private var header                      = ""
    private var price                       = 0.0
    private var square                      = 0.0
    private var type                        = ""
    private var id                          : UUID?
                                            = null

    private lateinit var binding            : ActivityLandDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding                             = ActivityLandDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle                          = intent.extras
        bundle?.let {
            header                          = (bundle.getString(getString(R.string.PARAM_HEADER), "")?:"")
            square                          = (bundle.getDouble(getString(R.string.PARAM_SQUARE)))
            price                           = (bundle.getDouble(getString(R.string.PARAM_PRICE)))
            type                            = (bundle.getString(getString(R.string.PARAM_TYPE), "")?:"")
            id                              = bundle.getString(getString(R.string.PARAM_ID))?.let{tempId->
                UUID.fromString(tempId)
            }
        }
        id                                  = id?:UUID.randomUUID()

        binding.textFieldHeader.editText?.setText(header)
        binding.textFieldPrice.editText?.setText(price.toString())
        binding.textFieldSquare.editText?.setText(square.toString())
        binding.textFieldType.editText?.setText(type)

        binding.ButtonSave.setOnClickListener {
            val resultIntent = Intent()
            header = binding.textFieldHeader.editText?.text.toString()
            type = binding.textFieldType.editText?.text.toString()

            resultIntent.putExtra(getString(R.string.PARAM_HEADER), header)
            resultIntent.putExtra(getString(R.string.PARAM_PRICE), binding.textFieldPrice.editText?.text?.toString()?.toDouble()?:"")
            resultIntent.putExtra(getString(R.string.PARAM_SQUARE), binding.textFieldSquare.editText?.text?.toString()?.toDouble()?:"")
            resultIntent.putExtra(getString(R.string.PARAM_TYPE), type)
            resultIntent.putExtra(getString(R.string.PARAM_ID), id.toString())
            setResult(RESULT_OK, resultIntent)
            finish()
        }
        binding.ButtonCancel.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }
    }
}