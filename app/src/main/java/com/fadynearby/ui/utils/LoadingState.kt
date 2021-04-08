package com.fadynearby.ui.utils

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.fadynearby.BR

class LoadingState: BaseObservable() {

    fun loadingState(){
        showRetry = false
        showError = false
        errorMessage = ""
        showLoading = true

    }

    fun loadedState(){
        showLoading = false
        showRetry = false
        showError = false
        errorMessage = ""
    }

    fun errorState(){
        showLoading = false
        showRetry = true
        showError = false
        errorMessage = ""
    }

    fun errorState(error: String){
        showLoading = false
        showRetry = true
        showError = true
        errorMessage = error
    }

    @get:Bindable
    var showLoading: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.showLoading)
        }

    @get:Bindable
    var showRetry: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.showRetry)
        }

    @get:Bindable
    var showError: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.showError)
        }

    @get:Bindable
    var errorMessage: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.errorMessage)
        }

}