package dstu.kuvaiev.expensetracker.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dstu.kuvaiev.expensetracker.domain.Expense
import dstu.kuvaiev.expensetracker.domain.ExpenseTracker
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainViewModel : ViewModel() {
    val expenseAmount = MutableLiveData<String>("")
    val totalExpenseText = MutableLiveData<String>("Загальна сума витрат: 0.0")
    val messageText = MutableLiveData<String>("")

    private val expenseTracker = ExpenseTracker()

    fun onAddExpenseClick() {
        val expenseAmountText = expenseAmount.value ?: ""
        val expenseAmountValue = expenseAmountText.toDoubleOrNull()

        expenseAmountValue?.let {
            val currentDate = Date()
            val expense = Expense(it, currentDate)
            expenseTracker.addExpense(expense)

            updateTotalExpense()
            displayExpenseAddedMessage(expense)
        }
    }

    fun onCalculateTotalClick() {
        updateTotalExpense()
        messageText.value = "Обчислено загальну суму витрат"
    }

    private fun updateTotalExpense() {
        val totalExpense = expenseTracker.calculateTotalExpenses()
        val decimalFormat = DecimalFormat("#.##")
        totalExpenseText.value = "Загальна сума витрат: ${decimalFormat.format(totalExpense)}"
    }

    private fun displayExpenseAddedMessage(expense: Expense) {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val formattedDate = dateFormat.format(expense.date)
        val message = "Додано нову витрату:\nДата створення: $formattedDate;\nСума витрати: ${expense.amount}"
        messageText.value = message
    }
}


