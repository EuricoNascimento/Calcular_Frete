package com.eurico.calcularfrete.ui.home

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.eurico.calcularfrete.R
import com.eurico.calcularfrete.entity.CalculateInfo


@Composable
fun formDistance() {
    val mainActivityViewlModel = viewModel(modelClass = MainActivityViewlModel::class.java)
    val context = LocalContext.current
    val startRoute = remember{ mutableStateOf("") }
    val endRoute = remember{ mutableStateOf("") }
    val kml = remember { mutableStateOf("") }
    val gasPrince = remember { mutableStateOf("") }
    val costRoute = remember { mutableStateOf("0.0") }
    val distance = remember { mutableStateOf("0 Km") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        TextField(
            value = startRoute.value,
            onValueChange = {
                startRoute.value = it
            },
            label = { Text(text = stringResource(id = R.string.starting_trip)) },
            singleLine = true,
            modifier = Modifier.padding(20.dp)
        )
        TextField(
            value = endRoute.value,
            onValueChange = {
                endRoute.value = it
            },
            label = { Text(text = stringResource(id = R.string.end_of_trip)) },
            singleLine = true,
            modifier = Modifier.padding(20.dp)
        )
        TextField(
            value = kml.value,
            keyboardOptions = KeyboardOptions( keyboardType = KeyboardType.Number),
            onValueChange = {
                val aux = it.replace(',','.')
                if(aux.toBigDecimalOrNull() != null){
                    kml.value = it
                } else {
                    kml.value = ""
                    Toast.makeText(context, "Insira um valor numerico", Toast.LENGTH_SHORT).show()
                }
            },
            singleLine = true,
            label = { Text(text = stringResource(id = R.string.kml)) },
            modifier = Modifier.padding(20.dp)
        )
        TextField(
            value = gasPrince.value,
            keyboardOptions = KeyboardOptions( keyboardType = KeyboardType.Number),
            onValueChange = {
                val aux = it.replace(',','.')
                if(aux.toBigDecimalOrNull() != null){
                    gasPrince.value = it
                } else {
                    gasPrince.value = ""
                    Toast.makeText(context, "Insira um valor numerico", Toast.LENGTH_SHORT).show()
                }
            },
            singleLine = true,
            label = { Text(text = stringResource(id = R.string.gas_price)) },
            modifier = Modifier.padding(20.dp)
        )
        Text(text = costRoute.value,
            fontSize = 78.sp,
            modifier = Modifier.padding(20.dp))
        Text(text = distance.value,
            fontSize = 78.sp,
            modifier = Modifier.padding(20.dp)
        )
        Button(onClick = {
            val result = validationFields(startRoute.value, endRoute.value, kml.value, gasPrince.value)
            if (result != null){
                mainActivityViewlModel.getRouteCost(result)
                val help = mainActivityViewlModel.route.value
                if (help != null) {
                    costRoute.value = help.cost
                    distance.value = help.routeDistance
                } else{
                    Toast.makeText(context, "Endereço invalido", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, "Dados invalidos", Toast.LENGTH_SHORT).show()
            }
        }) {
            Icon(
                Icons.Default.Create,
                contentDescription = "Calcular",
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text("Calcular")
        }
    }
}

private fun validationFields(
    start: String,
    end: String,
    consumable: String,
    price: String
): CalculateInfo? {
    val calculateInfo: CalculateInfo? = if(start != "" && end != "" && consumable != "" && price != ""){
        val consu = consumable.toBigDecimalOrNull()
        val pri = price.toBigDecimalOrNull()
        if(consu != null && pri != null){
            CalculateInfo(start = start, end = end, consumable = consu, price = pri)
        } else {
            null
        }
    } else {
        null
    }
    return calculateInfo
}


