package com.example.testxmlui.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.testxmlui.R
import com.example.testxmlui.databinding.ActivityMainBinding

class CActivityMainXML : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonPlus.setOnLongClickListener {
            val x = Toast.makeText(
                this,
                "Долгое нажатие",
                Toast.LENGTH_LONG
            ).show()
            return@setOnLongClickListener true
        }
    }

    fun onButtonCalculateClick(view : View)
    {
        val n1 = binding.textViewInput1.text
//        Toast.makeText(
//            this,
//            n1,
//            Toast.LENGTH_LONG
//        ).show()

        binding.textViewOutput.text = n1
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean
    {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.activity_main_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(
        item: MenuItem
    ): Boolean {
        // Handle item selection.
        return when (item.itemId) {
            R.id.settings -> {
                openSettingActivity()
                true
            }
            R.id.help -> {
                showHelp()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    private fun openSettingActivity()
    {
        Toast.makeText(
            this,
            "Нажата кнопка \"Настройки\"",
            Toast.LENGTH_LONG
        ).show()
    }
    private fun showHelp()
    {
        Toast.makeText(
            this,
            "Это справка по программе",
            Toast.LENGTH_LONG
        ).show()
    }
}