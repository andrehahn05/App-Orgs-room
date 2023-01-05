package com.hahn.orgs.ui.recyclerView.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.hahn.orgs.R
import com.hahn.orgs.databinding.ProductItemBinding
import com.hahn.orgs.extensions.formatPtBr
import com.hahn.orgs.extensions.tryLoadimage
import com.hahn.orgs.model.Product
import java.util.*

class ProductListAdapter(
    private val context: Context,
    var handleClickOnItem: (products: Product) -> Unit = {},
    var handleClickOnEdit: (products: Product) -> Unit = {},
    var handleClickOnRemove: (products: Product) -> Unit = {},
) : RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {
    
    private val products = mutableListOf<Product>()
    
    inner class ViewHolder(private val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root), PopupMenu.OnMenuItemClickListener {
        
        private lateinit var product: Product
        
        init {
            itemView.setOnLongClickListener {
                showPopup(it)
                true
            }
            
            itemView.setOnClickListener {
                if (::product.isInitialized) {
                    handleClickOnItem(product)
                }
            }
        }
        
        private fun showPopup(view: View) {
            PopupMenu(context, itemView).apply {
                inflate(R.menu.product_details_menu)
                setOnMenuItemClickListener(this@ViewHolder)
                show()
            }
        }
        
        override fun onMenuItemClick(item: MenuItem?): Boolean {
            return when (item?.itemId) {
                R.id.menu_prod_details_edit -> {
                    handleClickOnEdit(product)
                    true
                }
                R.id.menu_prod_details_remove -> {
                    handleClickOnRemove(product)
                    true
                }
                else -> {
                    false
                }
            }
        }
        
        fun bindProduct(product: Product) {
            this.product = product
            val name = binding.prodItemName
            val description = binding.prodItemDescription
            val price = binding.prodItemPrice
            val urlProduct = binding.imageView
            name.text = product.name
            description.text = product.description
            price.text = product.price.formatPtBr()
            urlProduct.tryLoadimage(product.image)
            val visibility = if (product.image != null) View.VISIBLE else View.GONE
            
            binding.imageView.visibility = visibility
        }
    }
    
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ProductItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }
    
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        holder.bindProduct(product)
    }
    
    override fun getItemCount(): Int = products.size
    
    @SuppressLint("NotifyDataSetChanged")
    fun toUpdate(products: List<Product>) {
        this.products.clear()
        this.products.addAll(products)
        notifyDataSetChanged()
    }
}
