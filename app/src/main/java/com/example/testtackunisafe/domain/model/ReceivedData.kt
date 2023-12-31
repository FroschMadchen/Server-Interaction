package com.example.testtackunisafe.domain.model

data class ReceivedData(
    val key:String,
    val success: Boolean,
    val list_id: Int, //    list_id - id списка, созданный на сервере, по этому id можно взаимодествовать со списком.
    val name: String,
    val shop_list:List<Product>,
    val item_id:Int,
    val new_value:Boolean
)
class Product(
    val created: String,
    val name:String,
    val item_id:Int,
    val quantity:Int,
    val list_id:Int,
    var crossedOut: Boolean = false
) {
    constructor( name:String, quantity: Int,list_id:Int)
            : this("", name, 0, quantity,list_id)
    constructor( name:String, quantity: Int,list_id:Int,item_id: Int)
            : this("", name, item_id, quantity,list_id)
}







