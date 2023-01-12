package com.hahn.orgs.ui.activity

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.hahn.orgs.database.AppDatabase
import com.hahn.orgs.database.dao.ProductDao
import com.hahn.orgs.databinding.ActivityFormProductBinding
import com.hahn.orgs.extensions.tryLoadimage
import com.hahn.orgs.model.Product
import com.hahn.orgs.ui.dialog.FormImageDialog
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import java.math.BigDecimal

class FormProductActivity : UserBaseActivity() {
    
    private var url: String? = null
    private var productId = 0L
    private val binding by lazy {
        ActivityFormProductBinding.inflate(layoutInflater)
    }
    private val productDao: ProductDao by lazy {
        AppDatabase.getInstance(this).productDao()
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = "Cadastar Produto"
        handleBtnSave()
        changeImage()
        getExtraProduct()
        getProduct()
    }
    
    private fun getProduct() {
        lifecycleScope.launch {
            productDao.findById(productId).collect {
                it?.let {
                    title = "Alterando ${it.name}"
                    completedFields(it)
                }
            }
        }
    }
    
    private fun changeImage() {
        binding.activityFormImageView.setOnClickListener {
            FormImageDialog(this)
                .showDialog(url) { image ->
                    url = image
                    binding.activityFormImageView.tryLoadimage(url)
                }
        }
    }
    
    private fun getExtraProduct() {
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
    
    private fun handleBtnSave() {
        val btnSave = binding.btnFormSaveProd
        btnSave.setOnClickListener {
            lifecycleScope.launch {
                user.firstOrNull()?.let { user ->
                    productDao.add(createProduct(user.id))
                    finish()
                }
            }
        }
    }
    
    private fun createProduct(userId: String): Product {
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
            userId = userId
        )
    }
}

