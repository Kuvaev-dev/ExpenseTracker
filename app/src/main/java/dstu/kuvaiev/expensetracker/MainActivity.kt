package dstu.kuvaiev.expensetracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import dstu.kuvaiev.expensetracker.databinding.MainActivityBinding
import dstu.kuvaiev.expensetracker.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.addExpenseButton.setOnClickListener {
            viewModel.onAddExpenseClick()
        }

        binding.calculateTotalButton.setOnClickListener {
            viewModel.onCalculateTotalClick()
        }
    }
}



