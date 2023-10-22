package dstu.kuvaiev.expensetracker

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import dstu.kuvaiev.expensetracker.domain.Expense
import dstu.kuvaiev.expensetracker.domain.ExpenseTracker
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : ComponentActivity() {
    private lateinit var addExpenseButton: Button
    private lateinit var calculateTotalButton: Button
    private lateinit var totalExpenseTextView: TextView
    private lateinit var expenseAmountEditText: EditText
    private lateinit var messageTextView: TextView
    private val expenseTracker = ExpenseTracker()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        // Знаходимо компоненти за їх ідентифікаторами у макеті
        addExpenseButton = findViewById(R.id.addExpenseButton)
        calculateTotalButton = findViewById(R.id.calculateTotalButton)
        totalExpenseTextView = findViewById(R.id.totalExpenseTextView)
        expenseAmountEditText = findViewById(R.id.expenseAmountEditText)
        messageTextView = findViewById(R.id.messageTextView) // Знаходження нового текстового поля

        // Додаємо обробники подій на кнопки
        addExpenseButton.setOnClickListener { onAddExpenseClick() }
        calculateTotalButton.setOnClickListener { onCalculateTotalClick() }
    }

    // Вивід повідомлення про додану витрату разом із датою та сумою
    private fun displayExpenseAddedMessage(expense: Expense) {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val formattedDate = dateFormat.format(expense.date)
        val message = "Додано нову витрату:\nДата створення: $formattedDate;\nСума витрати: ${expense.amount}"
        messageTextView.text = message
    }

    // Метод викликається при натисканні кнопки "Додати витрату"
    private fun onAddExpenseClick() {
        // Отримуємо введену суму витрати з текстового поля
        val expenseAmountText = expenseAmountEditText.text.toString()
        val expenseAmountValue = expenseAmountText.toDouble()

        // Створюємо об'єкт витрати з отриманими даними
        val currentDate = Date()
        val expense = Expense(expenseAmountValue, currentDate)

        // Додаємо витрату до трекера
        expenseTracker.addExpense(expense)

        // Оновлюємо відображення загальної суми витрат
        updateTotalExpense()

        // Виводимо повідомлення про додану витрату разом із датою та сумою
        displayExpenseAddedMessage(expense)
    }

    // Метод викликається при натисканні кнопки "Обчислити загальні витрати"
    private fun onCalculateTotalClick() {
        // Оновлюємо відображення загальної суми витрат
        updateTotalExpense()

        // Виводимо повідомлення "Обчислено загальну суму витрат"
        messageTextView.text = "Обчислено загальну суму витрат"
    }

    // Метод для оновлення відображення загальної суми витрат
    private fun updateTotalExpense() {
        // Обчислюємо загальну суму витрат з використанням трекера витрат
        val totalExpense = expenseTracker.calculateTotalExpenses()

        // Форматуємо результат з двома знаками після коми
        val decimalFormat = DecimalFormat("#.##")
        val formattedTotalExpense = decimalFormat.format(totalExpense)

        // Оновлюємо текст у текств'ю полі для відображення
        totalExpenseTextView.text = "Загальна сума витрат: $formattedTotalExpense"
    }
}


