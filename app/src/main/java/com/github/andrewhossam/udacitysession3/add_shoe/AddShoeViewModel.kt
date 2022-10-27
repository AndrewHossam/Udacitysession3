package com.github.andrewhossam.udacitysession3.add_shoe

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.github.andrewhossam.udacitysession3.database.Shoe
import com.github.andrewhossam.udacitysession3.database.ShoeDatabaseDao
import kotlinx.coroutines.launch

class AddShoeViewModel(
    private val shoeDatabaseDao: ShoeDatabaseDao,
) : ViewModel() {
    val shoeName = MutableLiveData<String>()
    val shoeSize = MutableLiveData<String>()

    val shoeAdded = MutableLiveData<Boolean>()

    fun addShoe() {
        viewModelScope.launch {
            if (shoeName.value.isNullOrBlank() || shoeSize.value.isNullOrBlank())
                return@launch
            shoeDatabaseDao.insertShoe(
                Shoe(
                    name = shoeName.value!!,
                    size = shoeSize.value?.toInt() ?: -1,
                )
            )
        }
        shoeAdded.value = true
    }

    companion object {
        class AddShoeViewModelFactory(
            private val shoeDatabaseDao: ShoeDatabaseDao,
        ) : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return if (modelClass.isAssignableFrom(AddShoeViewModel::class.java)) {
                    AddShoeViewModel(
                        shoeDatabaseDao
                    ) as T
                } else
                    throw IllegalArgumentException("ViewModel Not Found")
            }
        }
    }

}