package com.example.testxmlui.ui.activitylandlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testxmlui.R
import com.example.testxmlui.databinding.ActivityLandListBinding
import com.example.testxmlui.model.CLand

class CActivityLandListXML : AppCompatActivity() {
    private lateinit var binding: ActivityLandListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLandListBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val dataset = listOf(
            CLand(
                "Земельный участок 1",
                100000.0,
                1400.0,
                "ИЖС"
            ),
            CLand(
                "Сдаётся земля в аренду",
                10000.0,
                1000.0,
                "ЛПХ"
            ),
            CLand(
                "Земельный участок 100500",
                123456.78,
                1400.0,
                "ИЖС"
            ),
                    CLand(
                    "Сдаётся земля в аренду",
            10000.0,
            1000.0,
            "ЛПХ"
            ),
            CLand(
                "Сдаётся земля в аренду",
                10000.0,
                1000.0,
                "ЛПХ"
            ),
            CLand(
                "Сдаётся земля в аренду",
                10000.0,
                1000.0,
                "ЛПХ"
            ),
            CLand(
                "Сдаётся земля в аренду",
                10000.0,
                1000.0,
                "ЛПХ"
            )

        )
        val layoutManager = LinearLayoutManager(this)
        val customAdapter = CRecyclerViewAdapterLandList(dataset)
        binding.RecyclerViewLands.adapter = customAdapter
        binding.RecyclerViewLands.layoutManager = layoutManager
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