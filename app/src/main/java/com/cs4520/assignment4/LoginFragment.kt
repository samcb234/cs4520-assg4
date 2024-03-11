package com.cs4520.assignment4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class LoginFragment: Fragment(R.layout.login_layout) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.login_layout, container, false)

        view.findViewById<Button>(R.id.loginButton).setOnClickListener(View.OnClickListener {

            val username = view.findViewById<EditText>(R.id.usernameText)
            val password = view.findViewById<EditText>(R.id.passwordText)
            if(username.text.toString() == ("admin") && password.text.toString() == ("admin")){
                findNavController().navigate(R.id.action_loginFragment_to_productListFragment)
            } else {
                val toast = Toast.makeText(context, "Please enter correct username and password", Toast.LENGTH_SHORT)
                toast.show()
            }

            username.setText("")
            password.setText("")
        })
        return view
    }
}