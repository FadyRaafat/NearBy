package com.fadynearby.ui.utils

class NetworkState(var status: Status?, var msg: String?) {

    enum class Status {
        INIT_RUNNING,
        RUNNING,
        SUCCESS,
        FAILED,
        EMPTY
    }

    companion object {

        var LOADED = NetworkState(Status.SUCCESS, null)
        var LOADING = NetworkState(Status.RUNNING, null)
        var INIT_LOADING = NetworkState(Status.INIT_RUNNING, null)
        var EMPTY_RESPONSE = NetworkState(Status.EMPTY, null)
        var FAILED = NetworkState(Status.FAILED, null)
    }
}
