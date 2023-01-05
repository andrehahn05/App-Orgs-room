package com.hahn.orgs.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hahn.orgs.database.AppDatabase
import com.hahn.orgs.database.dao.ProductDao
import com.hahn.orgs.databinding.ActivityFormProductBinding
import com.hahn.orgs.extensions.tryLoadimage
import com.hahn.orgs.model.Product
import com.hahn.orgs.ui.dialog.FormImageDialog
import java.math.BigDecimal

class FormProductActivity : AppCompatActivity() {
    
    private val binding by lazy {
        ActivityFormProductBinding.inflate(layoutInflater)
    }
    
    private val productDao: ProductDao by lazy {
        val db = AppDatabase.getInstance(this)
        db.productDao()
    }
    private var url: String? = null
    private var productId = 0L
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = "Cadastar Produto"
        confgBtnSave()
        handleImage()
        
        tryLoadProduct()
    }
    
    private fun handleImage() {
        binding.activityFormImageView.setOnClickListener {
            FormImageDialog(this)
                .showDialog(url) { image ->
                    url = image
                    binding.activityFormImageView.tryLoadimage(url)
                }
        }
    }
    
    override fun onResume() {
        super.onResume()
        getProduct()
    }
    
    private fun getProduct() {
        productDao.findById(productId)?.let {
            completedFields(it)
        }
    }
    
    private fun tryLoadProduct() {
        productId = intent.getLongExtra(KEY_PRODUCT_ID, 0L)
    }
    
    private fun completedFields(product: Product) {
        title = "Alterar produdo"
        url = product.image
        with(binding) {
            activityFormImageView.tryLoadimage(product.image)
            activityFormName.setText(product.name)
            activityFormDescription.setText(product.description)
            activityFormVal.setText(product.price.toPlainString())
        }
    }
    
    private fun confgBtnSave() {
        val btnSave = binding.btnFormSaveProd
        btnSave.setOnClickListener {
            val newProduct = createProduct()
            productDao.store(newProduct)
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
            id = productId,
            name = name,
            description = descripition,
            price = value,
            image = url,
        )
    }
}
