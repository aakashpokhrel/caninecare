package com.example.canine_care

import com.example.canine_care.api.ServiceBuilder
import com.example.canine_care.entity.Appointment
import com.example.canine_care.entity.Pet
import com.example.canine_care.entity.Product
import com.example.canine_care.entity.User
import com.example.canine_care.repository.AppointmentRepository
import com.example.canine_care.repository.PetRepository
import com.example.canine_care.repository.ProductRepository
import com.example.canine_care.repository.UserRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class UnitTesting {

    private lateinit var userRepository: UserRepository
    private lateinit var productRepository:ProductRepository
    private lateinit var petRepository:PetRepository
    private lateinit var appointmentRepository:AppointmentRepository
    //Login test
    @Test
    fun checkLogin() = runBlocking {
        userRepository = UserRepository()
        val response = userRepository.loginUser("ram", "123456789")
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult, actualResult)
    }

    //registration test
    @Test
    fun registerUser() = runBlocking {
        val user = User(
            fname = "Saksham",
            lname = "Shrestha",
            username = "saksham",
            email="saksham@gmail.com",
            password = "saksham123",

        )
        userRepository = UserRepository()
        val response = userRepository.registerUser(user)
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult, actualResult)
    }

    //add product test
    @Test
    fun addproduct () = runBlocking {

        productRepository = ProductRepository()
        val product = Product(
            pname = "Biscuit",
            desc = "Good for dogs",
            price = 300
        )
        ServiceBuilder.token = "Bearer" + userRepository.loginUser("ram", "123456789").token
        val expectedResult = true
        val actualResult = productRepository.addProduct(product).success
        Assert.assertEquals(expectedResult, actualResult)
    }

    //add pet test
    @Test
    fun addpet() = runBlocking {
        petRepository = PetRepository()
        val pet = Pet(
            petname = "Pitbull",
            petage = "5",
            petdesc = "Good dog",
            petpiece = "2",
            petprice = 80000
        )
        ServiceBuilder.token = "Bearer" + userRepository.loginUser("ram", "123456789").token

        val expectedResult = true
        val actualResult = petRepository.addPet(pet).success
        Assert.assertEquals(expectedResult, actualResult)
    }

    //add booking
    @Test
    fun addbooking()  = runBlocking {
        appointmentRepository = AppointmentRepository()
        val appointment = Appointment(
            fullname = "Bipson",
            mnumber = "987434567",
            email = "bipu@gmail.com",
            apdate = "2022/5/25",
            apaddress = "lamjung"
        )
        ServiceBuilder.token = "Bearer" + userRepository.loginUser("sulav", "123456789").token
        val expectedResult = true
        val actualResut = appointmentRepository.addAppointment(appointment).success
        Assert.assertEquals(expectedResult, actualResut)
    }
}