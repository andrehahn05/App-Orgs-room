package com.hahn.orgs.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hahn.orgs.database.AppDatabase
import com.hahn.orgs.databinding.ActivityFormProductBinding
import com.hahn.orgs.extensions.tryloadimage
import com.hahn.orgs.model.Product
import com.hahn.orgs.ui.dialog.FormImageDialog
import java.math.BigDecimal

class FormProductActivity : AppCompatActivity() {
    
    private val binding by lazy {
        ActivityFormProductBinding.inflate(layoutInflater)
    }
    private var url: String? = null
    private  var idProduct = 0L
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = "Cadastar Produto"
        confgBtnSave()
        binding.activityFormImageView.setOnClickListener {
            FormImageDialog(this)
                .showDialog(url) { image ->
                    url = image
                    binding.activityFormImageView.tryloadimage(url)
                }
        }
        
        @Suppress("DEPRECATION")
        intent.getParcelableExtra<Product>(KEY_PRODUCT)?.let { loadedProduct ->
            title = "Alterar produdo"
            url = loadedProduct.image
            idProduct = loadedProduct.id
            binding.activityFormImageView.tryloadimage(loadedProduct.image)
            binding.activityFormName.setText(loadedProduct.name)
            binding.activityFormDescription.setText(loadedProduct.description)
            binding.activityFormVal.setText(loadedProduct.price.toPlainString())
        }
    }
    
    private fun confgBtnSave() {
        val btnSave = binding.btnFormSaveProd
        val db = AppDatabase.getInstance(this)
        val productDao = db.productDao()
        btnSave.setOnClickListener {
            val newProduct = createProduct()
            if(idProduct > 0){
                productDao.update(newProduct)
            }else{
                productDao.store(newProduct)
            }
            finish()
        }
    }
    
    private fun createProduct(): Product {
        val inputName = binding.activityFormName
        val inputDescription = binding.activityFormDescription
        val inputPrice = binding.activityFormVal
        val name = inputName.text.toString()
        val descripition = inputDescription.text.toString()
        val priceTxt = inputPrice.text.toString()
        val value = if (priceTxt.isBlank()) BigDecimal.ZERO else BigDecimal(priceTxt)
        
        return Product(
            id = idProduct,
            name = name,
            description = descripition,
            price = value,
            image = url,
        )
    }
}
