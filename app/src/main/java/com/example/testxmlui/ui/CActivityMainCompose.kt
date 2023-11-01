package com.example.testxmlui.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.testxmlui.CActivityLandInfo
import com.example.testxmlui.R
import com.example.testxmlui.ui.theme.TestXMLUITheme
import com.example.testxmlui.vm.CViewModelActivityCompose

class CActivityMainCompose      : ComponentActivity() {
    private val viewModel       : CViewModelActivityCompose by viewModels()

    private lateinit var activityLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PreviewUI()
        }

        activityLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult())
        { result ->
            // используем result
            if (result.resultCode == RESULT_OK) {
                val data = result.data?.extras
                Toast.makeText(
                    this,
                    ""+(data?.getString("MY_PARAM_2", "Параметр не найден!!!")?:"Параметры не переданы!!!"),
                    Toast.LENGTH_LONG
                ).show()
                var x = 0
            }
        }
    }

    fun onClick()
    {
        val intent = Intent(this, CActivityLandInfo::class.java)
        intent.putExtra("MY_PARAM", viewModel.value1)
        activityLauncher.launch(intent)

        val val1        : Double
        val val2        : Double
        val message = try {
            val1 = viewModel.value1.toDouble()
            val2 = viewModel.value2.toDouble()
            ""+(val1+val2)
        }
        catch (e : Exception)
        {
            "Некорректный ввод!"
        }
        Toast.makeText(
            this,
            message,
            Toast.LENGTH_LONG
        )
            .show()
    }
//    class CContractActivityLandInfo : ActivityResultContract<String, Int?>() {
//        override fun createIntent(context: Context, input: String): Intent {
//            return Intent(context, CContractActivityLandInfo::class.java)
//                //.putExtra("my_input_key", input)
//        }
//
//        override fun parseResult(resultCode: Int, intent: Intent?
//        ): Int {
//            return 0
//        }
////        = when {
//////            resultCode != Activity.RESULT_OK -> null
//////            else -> intent?.getIntExtra("my_result_key", 42)
////        }
//
////        override fun getSynchronousResult(context: Context, input: String?): SynchronousResult<Int?>? {
////            return if (input.isNullOrEmpty()) SynchronousResult(42) else null
////        }
//    }
}
@Composable
private fun Menu()
{
    // fetching local context
    val context = LocalContext.current
    val viewModel = viewModel { CViewModelActivityCompose() }
    DropdownMenu(
        expanded = viewModel.menuState,
        onDismissRequest = { viewModel.updateMenuState(false) }
    ) {

        DropdownMenuItem(
            onClick = {
                viewModel.updateMenuState(false)
                Toast.makeText(context, "Settings", Toast.LENGTH_SHORT).show()
            },
            text = {
                Text(text = context.getString(R.string.settings))
            }
        )
        DropdownMenuItem(
            onClick = {
                viewModel.updateMenuState(false)
                Toast.makeText(context, "Help", Toast.LENGTH_SHORT).show()
            },
            text = {
                Text(text = context.getString(R.string.help))
            }
        )
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopAppBar()
{
    // fetching local context
    val context = LocalContext.current
    val viewModel = viewModel { CViewModelActivityCompose() }
    TopAppBar(
        title = {
            Text(
                context.getString(
                    R.string.list_lands
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Localized description"
                )
            }
        },
        actions = {
            // Creating Icon button for dropdown menu
            IconButton(
                onClick = { viewModel.updateMenuState(!viewModel.menuState) }) {
                Icon(Icons.Default.Menu, "")
            }
            Menu()
        }
    )
}
@Composable
private fun Content(
    innerPadding : PaddingValues
)
{
    // fetching local context
    val activity = LocalContext.current as? CActivityMainCompose
    val viewModel = viewModel { CViewModelActivityCompose() }
    Column(
        modifier = Modifier
            .padding(innerPadding),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    )
    {
        OutlinedTextField(
            value = viewModel.value1,
            onValueChange = { value1 -> viewModel.updateValue1(value1) },
            label = { Text("Значение 1") }
        )
        OutlinedTextField(
            value = viewModel.value2,
            onValueChange = { value2 -> viewModel.updateValue2(value2) },
            label = { Text("Значение 2") }
        )
        FilledTonalButton(
            onClick = { activity?.onClick() }
        ) {
            Text("Tonal")
        }
    }
}
@Preview
@Composable
fun PreviewUI()
{
    TestXMLUITheme(
        dynamicColor = false
    )
    {
        Scaffold (
            topBar = { TopAppBar() },
            content = { Content(it) }
        )
    }
}
