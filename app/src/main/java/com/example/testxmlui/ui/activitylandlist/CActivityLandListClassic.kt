package com.example.testxmlui.ui.activitylandlist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testxmlui.R
import com.example.testxmlui.databinding.ActivityLandListBinding
import com.example.testxmlui.model.CLand
import com.example.testxmlui.ui.CActivityLandDetailsClassic
import java.util.UUID

class CActivityLandListClassic : AppCompatActivity() {
    private lateinit var binding: ActivityLandListBinding

    private lateinit var activityLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLandListBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val dataset = mutableListOf(
            CLand(

                UUID.randomUUID(),
                "Земельный участок 1",
                100000.0,
                1400.0,
                "ИЖС"
            ),
            CLand(
                UUID.randomUUID(),
                "Сдаётся земля в аренду",
                10000.0,
                1000.0,
                "ЛПХ"
            ),
            CLand(
                UUID.randomUUID(),
                "Земельный участок 100500",
                123456.78,
                1400.0,
                "ИЖС"
            ),
            CLand(
                UUID.randomUUID(),
                "Сдаётся земля в аренду",
                10000.0,
                1000.0,
                "ЛПХ"
            ),
            CLand(
                UUID.randomUUID(),
                "Сдаётся земля в аренду",
                10000.0,
                1000.0,
                "ЛПХ"
            ),
            CLand(
                UUID.randomUUID(),
                "Сдаётся земля в аренду",
                10000.0,
                1000.0,
                "ЛПХ"
            ),
            CLand(
                UUID.randomUUID(),
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

        activityLauncher                    = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult())
        { result ->
            if (result.resultCode != RESULT_OK)
                return@registerForActivityResult

            val bundle                      = result.data?.extras
            val id                          = bundle?.getString(getString(R.string.PARAM_ID))?.let{tempId->
                UUID.fromString(tempId)
            }
            val header                      = (bundle?.getString(getString(R.string.PARAM_HEADER), "")?:"")
            val square                      = (bundle?.getDouble(getString(R.string.PARAM_SQUARE)))
            val price                       = (bundle?.getDouble(getString(R.string.PARAM_PRICE)))
            val type                        = (bundle?.getString(getString(R.string.PARAM_TYPE), "")?:"")
            var index                       = -1
            dataset
                .forEachIndexed { ind, item ->
                    if (item.id == id) {
                        item.header = header
                        item.square = square?:0.0
                        item.price = price?:0.0
                        item.type = type
                        index = ind
                    }
                }

            if (index<0)
            {
                dataset.add(
                    CLand(
                        UUID.randomUUID(),
                        header,
                        price?:0.0,
                        square?:0.0,
                        type
                    )
                )
                index = dataset.size-1
            }
            customAdapter.notifyItemChanged(index)
        }
        binding.floatingActionButtonAddItem.setOnClickListener {
            val intent = Intent(this, CActivityLandDetailsClassic::class.java)
            activityLauncher.launch(intent)
        }
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