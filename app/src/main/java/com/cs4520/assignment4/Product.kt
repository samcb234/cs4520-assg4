package com.cs4520.assignment4

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
sealed class Product(@SerializedName("name") @PrimaryKey open val name: String?,
                     @SerializedName("type") @ColumnInfo(name = "type") open val type: String?,
                     @SerializedName("expiryDate") @ColumnInfo(name = "expriyDate") open val expiryDate: String?,
                     @SerializedName("price") @ColumnInfo(name = "price") open val price: Int?) {


    data class Food(override val name: String?,
                    override val type: String?,
                    override val expiryDate: String?,
                    override val price: Int?) :Product(name, type, expiryDate, price){

    }

    data class Equipment(override val name: String?,
                    override val type: String?,
                    override val expiryDate: String?,
                    override val price: Int?) :Product(name, type, expiryDate, price){

    }

}