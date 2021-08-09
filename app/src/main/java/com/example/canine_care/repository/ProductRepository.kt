package com.example.canine_care.repository


import com.example.canine_care.api.MyApiRequest
import com.example.canine_care.api.ProductAPI
import com.example.canine_care.api.ServiceBuilder
import com.example.canine_care.entity.Product
import com.example.canine_care.response.AddProductResponse
import com.example.canine_care.response.DeleteProductResponse
import com.example.canine_care.response.GetAllProductResponse
import com.example.canine_care.response.ImageResponse
import okhttp3.MultipartBody

class ProductRepository: MyApiRequest() {
    private  val productAPI =
        ServiceBuilder.buildService(ProductAPI::class.java)

    //Add product
    suspend fun addProduct(product : Product): AddProductResponse {
        return apiRequest {
            productAPI.addProduct(
                ServiceBuilder.token!!,product
            )
        }
    }
    //View product
    suspend fun getAllProduct(): GetAllProductResponse {
        return apiRequest {
            productAPI.getAllProduct(
                ServiceBuilder.token!!
            )
        }
    }
    //Delete product
    suspend fun deleteProduct(id :String): DeleteProductResponse {
        return apiRequest {
            productAPI.deleteProduct(
                ServiceBuilder.token!!,id
            )
        }
    }
    //Update product
    suspend fun updateProduct(id :String,product: Product): DeleteProductResponse{
        return apiRequest {
            productAPI.updateProduct(
                ServiceBuilder.token!!, id, product
            )
        }
    }
    //upload image
    suspend fun uploadImage(id: String, body: MultipartBody.Part)
            : ImageResponse {
        return apiRequest {
            productAPI.uploadImage(
                ServiceBuilder.token!!, id, body
            )
        }
    }
}