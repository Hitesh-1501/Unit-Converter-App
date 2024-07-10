package com.example.unitconverter

import android.os.Bundle
import android.text.style.BackgroundColorSpan
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnitConverter() {

    var inputValue by remember{mutableStateOf("")}
    var outputValue by remember{mutableStateOf("")}
    var inputUnit by remember{mutableStateOf("Meters")}
    var outputUnit by remember{mutableStateOf("Meters")}
    var iExpanded by remember{mutableStateOf(false)}
    var oExpanded by remember{mutableStateOf(false)}
    var conversationFactor = remember{mutableStateOf(1.00)}
    var oconversationFactor = remember{mutableStateOf(1.00)}

    fun ConvertUnits() {
        // ?: - Elvis Operator
        val inputValueDouble = inputValue.toDoubleOrNull()?: 0.0
        val result = (inputValueDouble * conversationFactor.value * 100/ oconversationFactor.value).roundToInt()/100.0
        outputValue = result.toString()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally


    ) {
        // Here All Element will be stacked below each other
        Text(text = "Unit Converter",
             style = TextStyle(
                 fontFamily = FontFamily.Monospace,
                 fontSize = 32.sp,
                 color = Color.Red
             )
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputValue,
            onValueChange = {
            inputValue = it
            ConvertUnits()
            //Here goes what should happen, when the value of our OutlinedTextField changes
            },
            label = { Text(text = "Enter Value")},
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Red,
                cursorColor = Color.Gray

            ), textStyle = TextStyle(
                fontFamily = FontFamily.SansSerif
            )
        )
        Spacer(modifier =  Modifier.height(16.dp))

        Row {
            // Here all Element will be stacked next to each other
           // Input Box
           Box {
                // Input Button
                Button(onClick = { iExpanded = true }) {
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription ="Arrow Down")
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false }) {
                    DropdownMenuItem(
                        text = { Text("Centimeters") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Centimeters"
                            conversationFactor.value = 0.01
                            ConvertUnits()
                        })
                    DropdownMenuItem(
                        text = { Text("Meters") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Meters"
                            conversationFactor.value = 1.0
                            ConvertUnits()
                        })
                    DropdownMenuItem(
                        text = { Text("Feet") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Feet"
                            conversationFactor.value = 0.3048
                            ConvertUnits()
                        })
                    DropdownMenuItem(
                        text = { Text("Millimeters") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Millimeters"
                            conversationFactor.value = 0.001
                            ConvertUnits()
                        })
                }
           }
           Spacer(modifier =  Modifier.width(16.dp))
           // Output Box
           Box {
               // Output Button
               Button(onClick = { oExpanded = true }) {
                   Text(text = outputUnit)
                   Icon(Icons.Default.ArrowDropDown, contentDescription ="Arrow Down")
               }
               DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false }) {
                   DropdownMenuItem(
                       text = { Text("Centimeters") },
                       onClick = {
                           oExpanded = false
                           outputUnit = "Centimeters"
                           oconversationFactor.value = 0.01
                           ConvertUnits()
                       })
                   DropdownMenuItem(
                       text = { Text("Meters") },
                       onClick = {
                           oExpanded = false
                           outputUnit = "Meters"
                           oconversationFactor.value = 1.00
                           ConvertUnits()
                       })
                   DropdownMenuItem(
                       text = { Text("Feet") },
                       onClick = {
                           oExpanded = false
                           outputUnit = "Feet"
                           oconversationFactor.value = 0.3048
                           ConvertUnits()
                       })
                   DropdownMenuItem(
                       text = { Text("Millimeters") },
                       onClick = {
                           oExpanded = false
                           outputUnit = "Millimeters"
                           oconversationFactor.value = 0.001
                           ConvertUnits()
                       })
               }
           }
              
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Result Text
        Text(text = "Result $outputValue $outputUnit",
                     fontFamily = FontFamily.SansSerif,
                     color = Color.Red,
                     style = MaterialTheme.typography.headlineMedium)

    }
}


@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {

       UnitConverter()
}