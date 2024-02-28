package com.example.unitconverter_jetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter_jetpack.ui.theme.Unitconverter_jetpackTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Unitconverter_jetpackTheme {
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

    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnitval by remember { mutableStateOf("Select") }
    var outputUnitval by remember { mutableStateOf("Select") }

    var inputUnit by remember { mutableStateOf("Meters") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var iExpand by remember { mutableStateOf(false) }
    var oExpand by remember { mutableStateOf(false) }
    var conversionFactor by remember { mutableStateOf(1.00) }
    var oconversionFactor by remember { mutableStateOf(1.00) }

    fun convertUnits(){
        val inputValueDouble=inputValue.toDoubleOrNull()?:0.0
        val result=(inputValueDouble*conversionFactor*100.0/oconversionFactor).roundToInt()/100.0
        outputValue=result.toString()
    }

   Column(
          modifier= Modifier.fillMaxSize(),
       verticalArrangement = Arrangement.Center,
       horizontalAlignment = Alignment.CenterHorizontally
   )
   {Text("Unit Converter")
       Spacer(modifier = Modifier.height(16.dp))
       OutlinedTextField(value = inputValue, onValueChange ={
           inputValue=it
           convertUnits()
       },
       label = {Text("Enter Value")}
       )
       Spacer(modifier = Modifier.height(16.dp))
       Row {
           Box{
               //Input Button
                Button(onClick = { iExpand=true }) {
                    Text(inputUnit)
                    Icon(Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down")
                }
               DropdownMenu(expanded = iExpand, onDismissRequest = { iExpand=false }) {
                   DropdownMenuItem(
                       text = {Text("Centimeters")},
                       onClick = {
                           iExpand=false
                           inputUnit="Centimeters"
                           conversionFactor=0.01
                           inputUnitval="Centimeters"
                           convertUnits()
                       }
                   )
                   DropdownMenuItem(
                       text = {Text("Meters")},
                       onClick = {
                           iExpand=false
                           inputUnit="Meters"
                           conversionFactor=1.0
                           inputUnitval="Meters"
                           convertUnits()
                       }
                   )
                   DropdownMenuItem(
                       text = {Text("Feet")},
                       onClick = {
                           iExpand=false
                           inputUnit="Feet"
                           conversionFactor=0.3048
                           inputUnitval="Feet"
                           convertUnits()
                       }
                   )
                   DropdownMenuItem(
                       text = {Text("Millimetres")},
                       onClick = {
                           iExpand=false
                           inputUnit="Millimetres"
                           conversionFactor=0.001
                           inputUnitval="Millimetres"
                           convertUnits()
                       }
                   )
               }
           }
           Spacer(modifier = Modifier.width(16.dp))
           Box{
               Button(onClick = { oExpand=true }) {
                   Text(outputUnit)
                   Icon(Icons.Default.ArrowDropDown,
                       contentDescription = "Arrow Down")
               }
               DropdownMenu(expanded = oExpand, onDismissRequest = { oExpand=false }) {
                   DropdownMenuItem(
                       text = {Text("Centimeters")},
                       onClick = {
                           iExpand=false
                           outputUnit="Centimeters"
                           oconversionFactor=0.01
                           outputUnitval="Centimeters"
                           convertUnits()
                       }
                   )
                   DropdownMenuItem(
                       text = {Text("Meters")},
                       onClick = {
                           iExpand=false
                           outputUnit="Meters"
                           oconversionFactor=1.0
                           outputUnitval="Meters"
                           convertUnits()
                       }
                   )
                   DropdownMenuItem(
                       text = {Text("Feet")},
                       onClick = {
                           iExpand=false
                           outputUnit="Feet"
                           oconversionFactor=0.3048
                           outputUnitval="Feet"
                           convertUnits()
                       }
                   )
                   DropdownMenuItem(
                       text = {Text("Millimetres")},
                       onClick = {
                           iExpand=false
                           outputUnit="Millimetres"
                           oconversionFactor=0.001
                           outputUnitval="Millimetres"
                           convertUnits()
                       }
                   )
               }
           }
       }
       Spacer(modifier = Modifier.height(16.dp))
       Text("Result:$outputValue$outputUnit")
   }
}

@Preview(showBackground = true)
@Composable
fun UnitConvererPreview() {
       UnitConverter()
}