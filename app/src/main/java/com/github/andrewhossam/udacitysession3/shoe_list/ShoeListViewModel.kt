package com.github.andrewhossam.udacitysession3.shoe_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.github.andrewhossam.udacitysession3.database.Shoe
import com.github.andrewhossam.udacitysession3.database.ShoeDatabaseDao
import kotlinx.coroutines.launch

class ShoeListViewModel(
    private val shoeDatabaseDao: ShoeDatabaseDao,
) : ViewModel() {
    fun delete() {
        viewModelScope.launch {
            shoeDatabaseDao.deleteAll()
        }
    }

    val allShoes: LiveData<List<Shoe>>
        get() {
            return shoeDatabaseDao.getAll()
        }

    companion object {
        class ShoeListViewModelFactory(
            private val shoeDatabaseDao: ShoeDatabaseDao,
        ) : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return if (modelClass.isAssignableFrom(ShoeListViewModel::class.java)) {
                    ShoeListViewModel(
                        shoeDatabaseDao
                    ) as T
                } else
                    throw IllegalArgumentException("ViewModel Not Found")
            }
        }
    }

}