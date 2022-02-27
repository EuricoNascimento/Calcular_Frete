package com.eurico.calcularfrete.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eurico.calcularfrete.data.api.model.Distance
import com.eurico.calcularfrete.data.reporsitory.DistanceMatrixRepository
import com.eurico.calcularfrete.entity.CalculateInfo
import com.eurico.calcularfrete.entity.RouteCost
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.math.BigDecimal
import javax.inject.Inject

@HiltViewModel
class MainActivityViewlModel @Inject constructor(
    private val distanceMatrixRepository: DistanceMatrixRepository
): ViewModel(){

    private val _route = MutableLiveData<RouteCost>()
    val route: LiveData<RouteCost> = _route

    fun getRouteCost(calculateInfo: CalculateInfo) {
        viewModelScope.launch {
            val distanceResult = distanceMatrixRepository.getDistance(calculateInfo.start, calculateInfo.end)
            val routeCost = if (distanceResult.status == "OK" && distanceResult.rows[0].elements[0].status == "OK"){
                val sCost = calcCost(calculateInfo.consumable, calculateInfo.price, distanceResult.rows[0].elements[0].distance.value)
                RouteCost(routeDistance = distanceResult.rows[0].elements[0].distance.text, cost = sCost)
            } else{
                RouteCost()
            }
            println(routeCost)
            _route.value = routeCost
        }
    }


    private fun calcCost(kml: BigDecimal, price: BigDecimal, meter: Int): String{
        val km = (meter / 1000.0).toBigDecimal()
        return "R$ " + ((km / kml) * price).format(2)
    }

    private fun BigDecimal.format(i: Int) = "%.${i}f".format(this)

    /*init {
        viewModelScope.launch {
            val distance = distanceMatrixRepository.getDistance(
                "CAISI - Centro de Atenção Integral ao Idoso Rua Salvador de Oliveira - Sítio Leal, São Luís - MA",
                "Condomínio Ville de France Avenida São Luís Rei de França - Turu, São Luís - MA")
            _route.value = distance
        }
    }*/
}