package fr.neon.e_kfc_v2

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData


class MainViewModel : ViewModel() {

    private val _actualTemp = MutableLiveData<Int>(15)
    val actualTemp: LiveData<Int> get() = _actualTemp
    private val _selectedTemp = MutableLiveData<Int>(23)
    val selectedTemp: LiveData<Int> get() = _selectedTemp


    fun sentPlusSelectedTemp(value: Int) {
        _selectedTemp.value = (selectedTemp.value)?.plus(value)
    }

    fun sentMinusSelectedTemp(value: Int) {
        _selectedTemp.value = (selectedTemp.value)?.minus(value)
    }

    fun setActualTemp(value: Double) {
        _actualTemp.value = value.toInt()
    }

}