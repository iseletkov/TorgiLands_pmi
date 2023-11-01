package com.example.testxmlui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.addCallback
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.testxmlui.ui.theme.TestXMLUITheme


class CActivityLandInfo : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestXMLUITheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
        val b = intent.extras
        Toast.makeText(
            this,
            ""+(b?.getString("MY_PARAM", "Параметр не найден!!!")?:"Параметры не переданы!!!"),
            Toast.LENGTH_LONG
        ).show()

        onBackPressedDispatcher.addCallback(this){
            val resultIntent = Intent()
            resultIntent.putExtra("MY_PARAM_2", "jjj ffff")
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TestXMLUITheme {
        Greeting("Android")
    }
}