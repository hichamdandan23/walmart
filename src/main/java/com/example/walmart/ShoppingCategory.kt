package com.example.walmart

import Categorie
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_shopping_category.*
import kotlinx.android.synthetic.main.categorie.view.*

class ShoppingCategory : AppCompatActivity() {

    var categories = arrayOf<String>("Electronics", "Clothing", "Beauty", "Food")

    var images = intArrayOf(R.drawable.electronics, R.drawable.clothing, R.drawable.beauty, R.drawable.food)

    var adapter:CategorieAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_category)

        val bundle=intent.extras
        username.text = bundle!!.getString("username")

        var ctgrs = arrayListOf<Categorie>()
        var i = 0
        for(c in categories)
        {
            var ctg = Categorie(images[i], categories[i])
            ctgrs.add(ctg)
            i++
        }

        adapter = CategorieAdapter(this, ctgrs)
        list.adapter = adapter

    }

    class  CategorieAdapter:BaseAdapter {
        // Adapter need to display list of foods
        var listOfCategories= ArrayList<Categorie>()
        var context: Context?=null
        // accepts a context and list of foods then call parent default constructor
        constructor(context:Context,listOfCategories:ArrayList<Categorie>):super(){
            this.context=context
            this.listOfCategories=listOfCategories
        }
        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            val food = this.listOfCategories[p0]
            // Inflate your own layout into your adapter
            var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var foodView= inflator.inflate(R.layout.categorie,null)
            // Set the items on your own layout view
            foodView.image.setImageResource(food.image!!)
            foodView.image.setOnClickListener {

                /*val intent = Intent(context,FoodDetails::class.java)
                intent.putExtra("name",food.name!!)
                intent.putExtra("des",food.des!!)
                intent.putExtra("image",food.image!!)
                context!!.startActivity(intent)*/
                Toast.makeText(context, "You have chosen the ${foodView.title.text} category of shopping", Toast.LENGTH_LONG).show()
            }
            foodView.title.text =  food.title!!
            return  foodView

        }

        override fun getItem(p0: Int): Any {
            return listOfCategories[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int {

            return listOfCategories.size
        }

    }

}
