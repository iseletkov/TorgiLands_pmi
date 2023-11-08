package com.example.testxmlui.model

import java.util.UUID

data class CLand(
    var id : UUID,
    var header : String,
    var price : Double,
    var square : Double,
    var type : String
)